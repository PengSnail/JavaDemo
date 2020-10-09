package com.study.test.basics.proxy.static_proxy;

/**
 * 静态代理测试类
 * @author PengG
 */
public class StaticProxyTest {
   public static void main(String[] args) {
      HelloImpl helloImpl = new HelloImpl();
      HelloProxy helloProxy = new HelloProxy(helloImpl);
      //最后由代理类执行方法，进行增强
      helloProxy.sayHello();
   }
}