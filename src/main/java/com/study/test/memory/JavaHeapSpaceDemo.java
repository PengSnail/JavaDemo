package com.study.test.memory;

import java.util.Random;

/**
 * Java 堆空间内存溢出
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3332)
 * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
 * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:208)
 * 	at com.study.test.memory.JavaHeapSpaceDemo.main(JavaHeapSpaceDemo.java:10)
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args){
        String str = "seu";

        while(true){
            str += str + new Random().nextInt(11111111) + new Random().nextInt(22222222);
            // 如果池已包含等于String方法确定的String对象的字符串，则返回池中的字符串。
            // 否则，将此String对象添加到池中，并返回对此String对象的引用。
            str.intern();
        }

    }
}
