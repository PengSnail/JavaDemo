package com.study.test.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    //内存够用的时候就保留，不够用就回收
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    public static void softRef_Memory_NotEnough(){
        //给 new Object(); 生成一个强引用 o1
        Object o1 = new Object();
        //给 new Object(); 生一个软引用 softReference
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        //移除强引用 o1
        o1 = null;
        try{
            //申请大内存触发GC，回收软应用对象
            byte[] bytes = new byte[30*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args){
//        softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
