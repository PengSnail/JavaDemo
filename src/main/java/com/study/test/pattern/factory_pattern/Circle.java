package com.study.test.pattern.factory_pattern;

/**
 * @author PengG
 */
public class Circle implements Shape {
 
   @Override
   public void draw() {
      System.out.println("Inside Circle::draw() method.");
   }
}