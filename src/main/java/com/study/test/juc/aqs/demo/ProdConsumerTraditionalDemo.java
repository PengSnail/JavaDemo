package com.study.test.juc.aqs.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *  题目：一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1
 *  线程  操作（方法）  资源类
 *  判断  干活  通知
 *  防止虚假唤醒机制 多线程判断要用 while
 */
class MyResource {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产方法
     */
    public void increment() {
        lock.lock();
        try {
            //判断是否已被消费
            while (number != 0) {
                //等待被消费
                condition.await();
            }
            //开始生产
            number++;
            System.out.println(Thread.currentThread().getName() + "  生产了  " + number);
            //生成完成，通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费方法
     */
    public void decrement() {
        lock.lock();
        try {
            //判断是否已经生产了
            while (number == 0) {
                //仍为有生产出来，等待生产
                condition.await();
            }
            //开始消费
            number--;
            System.out.println(Thread.currentThread().getName() + "  消费了  " + number);
            //消费完毕，通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 生产者消费者的传统实现
 * @author PengG
 * @date 2020/9/24 13:44
 */
public class ProdConsumerTraditionalDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    myResource.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    myResource.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
    /*
     * A  生产了  1
     * B  消费了  0
     * A  生产了  1
     * B  消费了  0
     * A  生产了  1
     * B  消费了  0
     * A  生产了  1
     * B  消费了  0
     * A  生产了  1
     * B  消费了  0
     */
}