package Annotation;

public @interface MyAnno {
    int age();
    String name() default "小明";
}
