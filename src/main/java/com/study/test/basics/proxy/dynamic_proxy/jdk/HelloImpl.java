package com.study.test.basics.proxy.dynamic_proxy.jdk;


/**
 * 委托类
 * @author PengG
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Say hello ...");
    }
}
