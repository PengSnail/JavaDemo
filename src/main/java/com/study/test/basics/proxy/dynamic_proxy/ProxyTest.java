package com.study.test.basics.proxy.dynamic_proxy;

/**
 * 动态代理测试类
 * @author PengG
 */
public class ProxyTest {
   public static void main(String[] args) {
      Hello hello = new HelloImpl();
      Hello proxyInstance = (Hello) new HelloProxy(hello).getProxyInstance();
      proxyInstance.sayHello();
   }
}