package com.yu.locks;

import org.w3c.dom.Node;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 锁的接口
 *
 * @author YU
 * @date 2022-06-08 13:26
 */
public class CHLLock implements Lock{

    /**
     * 尾节点引用,是所有线程共同引用的，
     */
    private final AtomicReference<QNode> tail;

    /**
     * 当前节点，线程内变量，每个线程都有
     */
    private final ThreadLocal<QNode> myNode;

    /**
     * 前驱节点 线程内变量，每个线程都有
     */
    private final ThreadLocal<QNode> myPred;

    public CHLLock() {
        this.tail = new AtomicReference<>(new QNode());
        this.myNode = ThreadLocal.withInitial(QNode::new);
        this.myPred = ThreadLocal.withInitial(QNode::new);
    }

    @Override
    public void lock() {
        QNode currentNode = myNode.get();
        currentNode.locked = true;
        QNode predNode = tail.getAndSet(currentNode);
        myPred.set(predNode);
        while (predNode.locked) {

        }
    }

    @Override
    public void unlock() {
        QNode currentNode = myNode.get();
        currentNode.locked = false;
        myNode.set(myPred.get());
    }

    private static class QNode{
        volatile boolean locked = false;
    }
}
