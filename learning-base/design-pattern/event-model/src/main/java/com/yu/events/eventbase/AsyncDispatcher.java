package com.yu.events.eventbase;

import com.yu.events.eventbase.eventInterface.Dispatcher;
import com.yu.events.eventbase.eventInterface.Event;
import com.yu.events.eventbase.eventInterface.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 异步调度器
 * <p>
 * 每一个事件类型允许注册一个或是多个事件处理器
 * 事件循环有哪些在单独的线程中
 * 事件处理器与事件循环运行在不同的线程中（异步）
 * 同一个事件的多个事件处理器运行在同一线程中（同步）
 *
 * @author YU
 * @date 2022-05-28 14:17
 */
public class AsyncDispatcher implements Dispatcher {
    private static final Logger LOG = Logger.getLogger(AsyncDispatcher.class.getSimpleName());
    private static final int EVENT_PRINT_THRESHOLD = 1000;

    private final BlockingQueue<Event<?>> eventQueue;
    private volatile boolean stopped = false;

    private Thread eventHandlingThread;
    private final ExecutorService eventHandingPool;
    protected final Map<Object, EventHandler<?, ?>> eventDispatchers;

    public AsyncDispatcher(ExecutorService eventHandingPool) {
        this(eventHandingPool, new LinkedBlockingDeque<>());
    }

    public AsyncDispatcher(ExecutorService eventHandingPool, BlockingQueue<Event<?>> eventQueue) {
        this.eventQueue = eventQueue;
        this.eventHandingPool = eventHandingPool;
        this.eventDispatchers = new ConcurrentHashMap<>();
    }

    /**
     * 循环取出 event事件，并分发
     *
     * @return
     */
    Runnable createThread() {
        Runnable runnable = () -> {
            while (!stopped && !Thread.currentThread().isInterrupted()) {
                Event<?> event;
                try {
                    event = eventQueue.take();
                } catch (InterruptedException e) {
                    if (!stopped) {
                        LOG.warning("AsyncDispatcher thread interrupted\n" + e.getMessage());
                    }
                    return;
                }
                dispatch(event);
            }
        };
        return runnable;
    }

    public void serviceStart() {
        // 创建一个单线程的线程池
        ThreadPoolExecutor singleThread = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), runnable -> {
            eventHandlingThread = new Thread(runnable);
            eventHandlingThread.setName("AsyncDispatcher event handler");
            return eventHandlingThread;
        });
        singleThread.execute(createThread());
    }

    /**
     * 关闭事件循环
     */
    public void serviceStop() {
        stopped = true;
        if (eventHandlingThread != null) {
            eventHandlingThread.interrupt();
            try {
                eventHandlingThread.join();
            } catch (InterruptedException ie) {
                LOG.warning("Interrupted Exception while stopping");
            }
        }
    }

    /**
     * 分发函数，事件循环调用
     */
    protected <T extends Enum<T>, E extends Event<T>> void dispatch(E event) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("Dispatching the event " + event.getClass().getName() + "."
                    + event.toString());
        }

        T type = event.getType();
        try {
            @SuppressWarnings("unchecked")
            EventHandler<T, E> handler1 = (EventHandler<T, E>) eventDispatchers.get(type.getClass());
            @SuppressWarnings("unchecked")
            EventHandler<T, E> handler2 = (EventHandler<T, E>) eventDispatchers.get(type);
            if (handler1 == null && handler2 == null) {
                throw new Exception("No handler for registered for " + type);
            }
            // 提交事件处理到 worker 线程池
            eventHandingPool.execute(() -> {
                if (handler1 != null) {
                    handler1.handle(event);
                }
                if (handler2 != null) {
                    handler2.handle(event);
                }
            });
        } catch (Throwable t) {
            LOG.info("Interrupted Exception while stopping");
        }
    }

    private <T extends Enum<T>, E extends Event<T>> void internalRegister(Object eventType, EventHandler<T, E> handler) {
        // check to see if we have a listener registered
        // 检查是否已注册侦听器
        @SuppressWarnings("unchecked")
        EventHandler<T, E> registeredHandler = (EventHandler<T, E>) eventDispatchers.get(eventType);
        LOG.info("Registering " + eventType + " for " + handler.getClass());
        if (registeredHandler == null) {
            eventDispatchers.put(eventType, handler);
        } else if (!(registeredHandler instanceof MultiListenerHandler)) {
            MultiListenerHandler<T, E> multiHandler = new MultiListenerHandler<>();
            multiHandler.addHandler(registeredHandler);
            multiHandler.addHandler(handler);
            eventDispatchers.put(eventType, multiHandler);
        } else {
            // already a multilistener, just add to it
            // 已经是 multilistener，只需添加即可
            MultiListenerHandler<T, E> multiHandler = (MultiListenerHandler<T, E>) registeredHandler;
            multiHandler.addHandler(handler);
        }
    }

    @Override
    public <T extends Enum<T>, E extends Event<T>> void dispatchEvent(E event) {
        int queueSize = eventQueue.size();
        if (queueSize != 0 && queueSize % EVENT_PRINT_THRESHOLD == 0) {
            LOG.info("Size of event-queue is " + queueSize);
        }
        int remainingCapacity = eventQueue.remainingCapacity();
        if (remainingCapacity < EVENT_PRINT_THRESHOLD) {
            LOG.warning("Very low remaining capacity in the event-queue: " + remainingCapacity);
        }
        try {
            eventQueue.put(event);
        } catch (InterruptedException e) {
            LOG.info("interrupted while put in event queue");
            throw new RuntimeException(e);
        }

    }

    @Override
    public <T extends Enum<T>, E extends Event<T>> void register(T eventType, EventHandler<T, E> eventHandler) {
        this.internalRegister(eventType, eventHandler);
    }

    @Override
    public <T extends Enum<T>, E extends Event<T>> void register(Class<T> eventType, EventHandler<T, E> eventHandler) {
        this.internalRegister(eventType, eventHandler);
    }

    static class MultiListenerHandler<T extends Enum<T>, E extends Event<T>> implements EventHandler<T, E> {
        List<EventHandler<T, E>> listofHandlers;

        public MultiListenerHandler() {
            this.listofHandlers = new ArrayList<>();
        }

        @Override
        public void handle(E event) {
            for (EventHandler<T, E> handler : listofHandlers) {
                handler.handle(event);
            }
        }

        void addHandler(EventHandler<T, E> handler) {
            listofHandlers.add(handler);
        }
    }
}
