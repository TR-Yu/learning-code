package com.yu.locks;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * MCS自旋锁
 *
 * @author YU
 * @date 2022-06-08 15:21
 */
public class MCSLock implements Lock {

    @Override
    public void lock() {
        lock();
    }

    @Override
    public void unlock() {

    }

    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isWaiting = true; // 默认是在等待锁
    }

    /**
     * 指向最后一个申请锁的MCSNode
     */
    volatile MCSNode queue;
    private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER
            = AtomicReferenceFieldUpdater
            .newUpdater(MCSLock.class, MCSNode.class, "queue");

    public void lock(MCSNode currentThread) {
        //step 1
        MCSNode predecessor = UPDATER.getAndSet(this, currentThread);
        if (predecessor != null) {
            // step 2
            predecessor.next = currentThread;
            // step 3
            while (currentThread.isWaiting) {
            }
        } else { // 只有一个线程在使用锁，没有前驱来通知它，所以得自己标记自己已获得锁
            currentThread.isWaiting = false;
        }
    }

    public void unlock(MCSNode currentThread) {
        // 锁拥有者进行释放锁才有意义
        if (currentThread.isWaiting) {
            return;
        }

        // 检查是否有人排在自己后面
        if (currentThread.next == null) {
            // step 4
            if (UPDATER.compareAndSet(this, currentThread, null)) {
                // compareAndSet返回true表示确实没有人排在自己后面
                return;
            } else {
                // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
                // 这里之所以要忙等是因为：step 1执行完后，step 2可能还没执行完
                // step 5
                while (currentThread.next == null) {
                }
            }
        }
        currentThread.next.isWaiting = false;
        // for GC
        currentThread.next = null;
    }
}
