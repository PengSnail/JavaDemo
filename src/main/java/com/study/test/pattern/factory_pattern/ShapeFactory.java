package com.study.test.pattern.factory_pattern;

/**
 * <p><A href="https://www.runoob.com/design-pattern/factory-pattern.html">菜鸟教程-工厂模式</A></p>
 * 定义工厂实例化工具类，用于生产给定信息的实体类对象；
 * 隐藏类的实例化过程，使用者无需关系对象如何创建。
 * 优点：
 *      面向接口编程，体现了面向对象的思想；
 *      将创建对象的工作转移到了工厂类；
 * @author PengG
 */
public class ShapeFactory {

    /**
     * 使用 getShape 方法获取形状类型的对象
     * @param shapeType 给定类型
     * @return 对应的实体类对象
     */
    public static Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
