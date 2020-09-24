package com.study.test.juc.aqs;

import java.util.concurrent.Semaphore;

/**
 * https://www.apiref.com/java11-zh/java.base/java/util/concurrent/Semaphore.html
 * @author PengG
 * @date 2020/9/24 10:07
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //创建3车位的停车场（信号量），支持非公平和公平锁，默认非公平
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            final int tempI = i;
            new Thread(() -> {
                try {
                    //停车，停满时。剩下的车需等待有空余车位时才能停
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "   " + tempI + "号车进入停车场");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "   " + tempI + "号停车3秒钟后离开车位.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放信号量（离开停车位，让等待信号量的线程获取）
                    semaphore.release();
                }
            }, i+"").start();
        }
    }
}
