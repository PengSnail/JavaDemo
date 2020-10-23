package com.study.test.juc.aqs.lock.mylock;

/**
 * @author PengG
 * @date 2020/10/17 15:11
 */
public class Test {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " lock......");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " unlock......");
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " lock......");
            try {
            } finally {
                System.out.println(Thread.currentThread().getName() + " unlock......");
                lock.unlock();
            }
        },"t2").start();
    }
}
