package com.study.test.basics.proxy.dynamic_proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 * @author PengG
 */
public class HelloProxy {
    private Hello hello;

    public HelloProxy(Hello hello) {
        this.hello = hello;
    }

    public Object getProxyInstance() {
       return Proxy.newProxyInstance(
               hello.getClass().getClassLoader(),
               hello.getClass().getInterfaces(),
               new InvocationHandler() {
                   @Override
                   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                       System.out.println("run before sayHello");
                       Object invoke = method.invoke(hello, args);
                       System.out.println("run after sayHello");
                       return invoke;
                   }
               }
       );
    }
}
