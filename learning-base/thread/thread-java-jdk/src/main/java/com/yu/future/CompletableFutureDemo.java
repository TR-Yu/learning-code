package com.yu.future;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * @author YU
 * @date 2022-06-09 23:06
 */
public class CompletableFutureDemo {
    Logger logger = Logger.getLogger("CompletableFutureDemo");

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* new CompletableFutureDemo().completableFutureTo();*/
        /*  new CompletableFutureDemo().completableThen();*/
        /*  new CompletableFutureDemo().completableMain();*/
        new CompletableFutureDemo().completableCombain();
    }

    private void completableFutureTo() throws ExecutionException, InterruptedException {
        //
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("had success");
        logger.info(completableFuture.get());

        // 运行在单线程中异步计算
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }
            logger.info("运行在一个单独的线程当中");
        }).get();

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("the string return");
            return "return the value";
        });
        logger.info(supplyAsync.get());
    }

    private void completableThen() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    logger.info(Thread.currentThread().getName() + " 😀");
                    return "赞";
                }).thenApply(el -> {
                    logger.info(Thread.currentThread().getName() + " 在看");
                    return el + ",再看";
                }).thenApply(el -> {
                    logger.info(Thread.currentThread().getName() + " 转发");
                    return el + ",转发";
                }).thenAccept(el -> {
                    logger.info("thenAccept");
                    logger.info(Thread.currentThread().getName() + el);
                }).thenApplyAsync(el -> {
                    logger.info("异步" + Thread.currentThread().getName() + " 👀 😃 🤳 ");
                    return "赞";
                }).thenApplyAsync(el -> {
                    logger.info("异步" + Thread.currentThread().getName() + " 再看");
                    return el + ",再看";
                }).thenApplyAsync(el -> {
                    logger.info("异步" + Thread.currentThread().getName() + " 转发");
                    return el + ",转发";
                }).thenAcceptAsync(el -> logger.info("异步" + Thread.currentThread().getName() + el))
                .get();
        logger.info(Thread.currentThread().getName() + " 是否三联");
    }

    private void completableMain() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("yes");
        future.thenApply(el -> {
                    logger.info(Thread.currentThread().getName() + " " + el);
                    return el + "one";
                }).thenRunAsync(() -> System.out.println(Thread.currentThread().getName() + " 新的线程"))
                .thenRun(() -> System.out.println(Thread.currentThread().getName() + " 这是啥线程")).get();
    }

    private void completableCombain() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<Object> future = CompletableFuture
                .supplyAsync(() -> {
                    logger.info(Thread.currentThread().getName() + "start doing things");
                    return "start";
                }, executorService)
                .thenAccept(el -> {
                    logger.info(Thread.currentThread().getName() + "then handler");
                })
                .thenRunAsync(() -> {
                    logger.info(Thread.currentThread().getName() + "then next handler");
                }, executorService)
                .thenRun(() -> {
                    logger.info(Thread.currentThread().getName() + " then end handler");
                })
                .handle((res, ex) -> {
                    if (ex != null) {
                        logger.info(Thread.currentThread().getName() + "发生异常了" + ex.getMessage());
                        return "exception";
                    }
                    return res;
                });

        logger.info(String.valueOf(Thread.currentThread().getName() + future.get()));

        executorService.shutdown();
    }
}
