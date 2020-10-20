package com.study.test.juc.aqs.lock.readwritelock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author PengG
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        Object o = new Object();
        new Thread(() -> {
            dataContainer.write(o);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }, "Thread_0").start();

        new Thread(() -> {
            dataContainer.read();
        }, "Thread_1").start();

        new Thread(() -> {
            dataContainer.read();
        }, "Thread_2").start();

        new Thread(() -> {
            dataContainer.write(o);
        }, "Thread_3").start();
    }

}

/**
 * 数据容器
 */
class DataContainer {
    private Object data;

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        r.lock();
        System.out.println(Thread.currentThread().getName() + " 获取读锁");
        try {
            return this.data;
        } finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
        }
    }


    public void write(Object o) {
        w.lock();
        System.out.println(Thread.currentThread().getName() + " 获取写锁");
        try {
            this.data = o;
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
        }
    }
}