package com.study.test.thread;

/**
 * 死锁Demo
 * 两个线程两把锁
 * @author PengG
 * @date 2020/9/25 9:11
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "获取到锁" + lockA + "，开始尝试获取锁" + lockB);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName() + "获取到锁" + lockB + "，开始尝试获取锁" + lockA);
                }
            }
        }, "AAAAA").start();

        new Thread(() -> {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "获取到锁" + lockB + "，开始尝试获取锁" + lockA);
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁" + lockA + "，开始尝试获取锁" + lockB);
                }
            }
        }, "BBBBB").start();
    }

    /*
     * jstack 进程ID
     * Java stack information for the threads listed above:
     * ===================================================
     * "BBBBB":
     *         at com.study.test.thread.DeadLockDemo.lambda$main$1(DeadLockDemo.java:32)
     *         - waiting to lock <0x000000076b09b930> (a java.lang.String)
     *         - locked <0x000000076b09b968> (a java.lang.String)
     *         at com.study.test.thread.DeadLockDemo$$Lambda$2/2094777811.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     * "AAAAA":
     *         at com.study.test.thread.DeadLockDemo.lambda$main$0(DeadLockDemo.java:23)
     *         - waiting to lock <0x000000076b09b968> (a java.lang.String)
     *         - locked <0x000000076b09b930> (a java.lang.String)
     *         at com.study.test.thread.DeadLockDemo$$Lambda$1/1879492184.run(Unknown Source)
     *         at java.lang.Thread.run(Thread.java:748)
     *
     * Found 1 deadlock.
     */
}
