package com.study.test.memory;

/**
 * 堆栈溢出
 * Exception in thread "main" java.lang.StackOverflowError
 * 	at com.study.test.memory.StackOverflowErrorDemo.stackOverflowError(StackOverflowErrorDemo.java:9)
 * 	at com.study.test.memory.StackOverflowErrorDemo.stackOverflowError(StackOverflowErrorDemo.java:9)
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args){
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
