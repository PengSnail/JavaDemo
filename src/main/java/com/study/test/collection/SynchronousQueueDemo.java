package com.study.test.collection;

import java.util.concurrent.SynchronousQueue;

/**
 * https://www.apiref.com/java11-zh/java.base/java/util/concurrent/SynchronousQueue.html
 * 与其他 Blocking Queue 不同， SynchronousQueue 是一个不存储元素的 Blocking Queue。
 * 每个插入（put）操作必须等待另一个线程执行相应的删除（take）操作，反之亦然
 * @author PengG
 * @date 2020/9/24 11:23
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "   " + 1);
                synchronousQueue.put(1);

                System.out.println(Thread.currentThread().getName() + "   " + 2);
                synchronousQueue.put(2);

                System.out.println(Thread.currentThread().getName() + "   " + 3);
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "put").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "   " + synchronousQueue.take());

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "   " + synchronousQueue.take());

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "   " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "take").start();

        /*
         * put   1
         * take   1
         * put   2
         * take   2
         * put   3
         * take   3
         */
    }
}
