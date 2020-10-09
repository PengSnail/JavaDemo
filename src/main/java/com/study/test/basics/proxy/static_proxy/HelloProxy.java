package com.study.test.basics.proxy.static_proxy;

/**
 * 代理类
 * @author PengG
 */
public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy(Hello hello) {
        this.hello = hello;
    }

    @Override
    public void sayHello() {
        System.out.println("run before sayHello");
        this.hello.sayHello();;
        System.out.println("run after sayHello");
    }
}
