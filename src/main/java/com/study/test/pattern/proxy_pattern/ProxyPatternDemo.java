package com.study.test.pattern.proxy_pattern;

public class ProxyPatternDemo {
   
   public static void main(String[] args) {
      Image image = new ProxyImage("test_10mb.jpg");
 
      // 图像将从磁盘加载
      // 初始化 ProxyImage 中的 RealImage 对象，有延迟加载的功能
      image.display(); 
      System.out.println("");
      // 图像不需要从磁盘加载
      // ProxyImage 中的 RealImage 对象已初始化完毕
      image.display();  
   }
}