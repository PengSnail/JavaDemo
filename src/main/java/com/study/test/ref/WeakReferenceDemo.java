package com.study.test.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args){
        //给 new Object(); 生成一个强引用 o1
        Object o1 = new Object();
        //给 new Object(); 生一个弱引用 weakReference
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        //移除强引用 o1
        o1 = null;

        System.gc();
        System.out.println("...............");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}