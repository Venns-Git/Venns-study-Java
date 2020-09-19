package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/*
    JDK5.0开始引入的新技术
    作用:
        1.不是程序本身，可以对程序做出解释
        2.可以被其他程序(编译器等)读取
    格式：
        以"@注释名"开头，还可以添加一些参数值，例如:@suppressWarnings(value="unchecked")
    使用:
        可以附加在package，class，method，field等上面，相当于添加了额外的辅助信息
        可以通过反射机制编程实现对这些元数据的访问

    内置注解:
        @Override:定义在java.lang.Override中，只适用与修饰方法，表示一个方法声明打算重写超类中的另一个方法
        @Deprecated:定义在java.lang.Deprecated中，可修饰方法，属性，类，表示不推荐使用
        @SuppressWarnings:定义在java.lang.SuppressWarnings中，用来抑制编译时的警告信息,需要添加参数才能使用

    元注解：负责注解其他注解
        @Target：用于描述注解的使用范围
        @Retention：用于描述注解的生命周期（）
        @Document：说明该注解将被包含在javadoc中
        @Inherited：说明子类可以继承父类中的该注解
 */
public class AnnotationDemo {


}
//定义一个注解
@Target(value = ElementType.METHOD)
@interface MyAnnotation{

}