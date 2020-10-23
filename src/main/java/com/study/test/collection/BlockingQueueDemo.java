package com.study.test.collection;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * https://www.apiref.com/java11-zh/java.base/java/util/concurrent/BlockingQueue.html
 * 由数组结构组成的有界阻塞队列
 * @author PengG
 * @date 2020/9/24 10:59
 */
public class BlockingQueueDemo {

    private final static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        blockTake();
    }

    /**
     * 抛出异常的的添加元素方法 add()
     * Exception in thread "main" java.lang.IllegalStateException: Queue full
     */
    private static void addThrowError() {
        System.out.println(arrayBlockingQueue.add(1));
        System.out.println(arrayBlockingQueue.add(2));
        System.out.println(arrayBlockingQueue.add(3));
        //Exception in thread "main" java.lang.IllegalStateException: Queue full
        System.out.println(arrayBlockingQueue.add(4));
    }

    /**
     * 抛出异常的获取并删除元素方法 remove()
     * Exception in thread "main" java.util.NoSuchElementException
     */
    private static void removeThrowError() {
        System.out.println(arrayBlockingQueue.add(1));
        System.out.println(arrayBlockingQueue.add(2));
        System.out.println(arrayBlockingQueue.add(3));

        //返回但不删除队列的头部元素，没有元素时抛出异常：Exception in thread "main" java.util.NoSuchElementException
        System.out.println(arrayBlockingQueue.element());

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //Exception in thread "main" java.util.NoSuchElementException
        System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 有返回值的添加元素方法 offer()
     * 队列元素已满时，返回false
     */
    private static void resultOffer() {
        System.out.println(arrayBlockingQueue.offer(1));
        System.out.println(arrayBlockingQueue.offer(2));
        System.out.println(arrayBlockingQueue.offer(3));
        //队列已满，返回false
        System.out.println(arrayBlockingQueue.offer(4));
    }

    /**
     * 有返回值的获取并删除元素方法 offer()
     * 队列中的元素为空时，返回null
     */
    private static void resultPoll() {
        System.out.println(arrayBlockingQueue.offer(1));
        System.out.println(arrayBlockingQueue.offer(2));
        System.out.println(arrayBlockingQueue.offer(3));

        //检索但不删除队列的头部元素，如果队列为空，则返回null
        System.out.println(arrayBlockingQueue.peek());

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //队列已空，返回null
        System.out.println(arrayBlockingQueue.poll());
    }

    /**
     * 阻塞式的添加元素方法 put
     * 队列满时添加元素的操作将被阻塞，直至队列有空位
     */
    public static void blockPut() {
        try {
            arrayBlockingQueue.put(1);
            arrayBlockingQueue.put(2);
            arrayBlockingQueue.put(3);
            arrayBlockingQueue.put(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞式的获取并删除元素方法 put
     * 队列满时添加元素的操作将被阻塞，直至队列有元素
     */
    public static void blockTake() {
        try {
            arrayBlockingQueue.put(1);
            arrayBlockingQueue.put(2);
            arrayBlockingQueue.put(3);

            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            System.out.println(arrayBlockingQueue.take());
            //队列已空，获取元素的动作被阻塞，直至队列有新的元素
            System.out.println(arrayBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
