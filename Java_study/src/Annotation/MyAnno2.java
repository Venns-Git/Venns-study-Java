package Annotation;

import java.lang.annotation.*;

/*
    元注解：负责注解其他注解
        @Target：用于描述注解的使用范围
        @Retention：用于描述注解的生命周期（）
        @Document：说明该注解将被包含在javadoc中
        @Inherited：说明子类可以继承父类中的该注解

 */
@Target(value = {ElementType.TYPE,ElementType.METHOD,ElementType.FIELD}) //表示该注解只能作用与类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnno2 {
}
