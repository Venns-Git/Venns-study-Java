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
        生成doc文档

    内置注解:
        @Override:检测被该注解标注的方法是否是继承父类(接口)的
        @Deprecated:该注解标注的内容,已过时
        @SuppressWarnings:压制警告 一般传递参数all

    自定义注解：
        格式:
            元注解
            public @interface 注解名称{
                属性列表;
            }
        本质:注解本质上就是一个接口，该接口默认继承Annotation接口
            public interface MyAnno extends java.lang.annotation.Annotation{}
        属性：接口中的抽象方法
            1.返回值类型只能为基本数据类型，字符串，枚举，注解，以上类型的数组
            2.定义了属性，在使用时需要给属性赋值
                如果定义属性时使用default给属性默认值，使用时可以不赋值
                如果只有一个属性需要赋值，且属性的名称为value，则value可以省略

    元注解：负责注解其他注解
        @Target：描述注解能够作用的位置
            ElementType取值：
                TYPE：可以作用与类上
                METHOD:可以作用于方法上
                FIELD：可以作用于成员变量上
        @Retention：描述注解被保留的阶段
            @Retention(RetentionPolicy.RUNTIME):当前被描述的注解，会保留到class字节码文件中，并被JVM读取到
        @Document：描述注解是否被抽取到api文档中
        @Inherited：描述注解是否被子类继承

    在程序中使用(解析)注解:获取注解中定义的属性值
        1.获取注解定义位置的对象 (Class对象，method对象，field对象)
        2.获取指定的注解 getAnnotation(Class)
        3.获取注解中的抽象方法获取配置的属性值
 */
public class AnnotationMain {


}
