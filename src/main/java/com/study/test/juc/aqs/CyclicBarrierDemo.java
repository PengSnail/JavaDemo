package com.study.test.juc.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * https://www.apiref.com/java11-zh/java.base/java/util/concurrent/CyclicBarrier.html
 * @author PengG
 * @date 2020/9/24 9:54
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println(Thread.currentThread().getName() + "  人到齐了开会");
        });
        for (int i = 1; i <= 6; i++) {
            final int tempI = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "   " + tempI +"到了会议室");
                    //当调用 await() 的线程数没达到给定数量（parties）时。均在此处阻塞
                    //只有达到（parties）时，继续一起执行（人到齐了一起开会）
                    cyclicBarrier.await();
                    //cyclicBarrier.await(1, TimeUnit.MICROSECONDS); //等待一定时间，超时不再等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i+"").start();
        }
    }
}
