package com.study.test.basics.proxy.static_proxy;

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
