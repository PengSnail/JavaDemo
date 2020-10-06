package com.study.test.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * 弱键 Map Demo
 * @author PengG
 * @date 2020/9/27 9:50
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
//        myHashMapDemo();
        System.out.println("--------------------");
        myWeakHashMapDemo();
    }

    public static void myHashMapDemo() {
        HashMap<String, Object> map = new HashMap<>();
        String key = new String("key_1");
        String value = new String("value_1");
        map.put(key, value);
        System.out.println(map + "\t" + map.size());
        key = null;
        System.out.println(map + "\t" + map.size());
        System.gc();
        System.out.println(map + "\t" + map.size());
    }

    public static void myWeakHashMapDemo() {
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        weakHashMap.put(key, value);
        System.out.println(weakHashMap + "\t" + weakHashMap.size());
        key = null;
        System.out.println(weakHashMap + "\t" + weakHashMap.size());
        System.gc();
        System.out.println(weakHashMap + "\t" + weakHashMap.size());
    }
}
