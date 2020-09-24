package com.study.test.juc.aqs.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 *  A打印5次，B打印10次，C打印15次
 *  紧接着
 *  A打印5次，B打印10次，C打印15次
 *  ……
 *  重复10轮
 */
class ShareResource{
    private Lock lock = new ReentrantLock();
    private int number = 1;
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                conditionA.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "     "  + i);
            }
            number = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                conditionB.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "     "  + i);
            }
            number = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                conditionC.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "     "  + i);
            }
            number = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author PengG
 * @date 2020/9/24 14:34
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                resource.print15();
            }
        }, "C").start();
    }
}
