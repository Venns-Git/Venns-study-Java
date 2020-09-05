package Interface;
/*
    接口当中也可以定义“成员变量”，但是必须使用public static final 三个关键词修饰
    从效果上看，这其实就是接口的【常量】
    格式：
        public static final 数据类型 常量名称 = 数据值；
    接口中常量的名称，使用完全大写的字母，用下划线进行分割。（推荐命名规则）
 */
/*
    接口注意事项：
        1.接口时没有静态代码块或者构造方法的。
        2.一个类的直接父类时唯一的，但是一个类可以实现多个接口。
        3.如果实现类所实现的多个接口当中，存在重复的抽象方法，那么只需要覆盖重写一次即可。
        4.如果实现类没有覆盖重写所有接口当中的所有抽象方法，那么实现类就必须是一个抽象类。
        5.如果实现类实现的多个接口中，存在重复的默认方法，那么实现类一定要冲突的默认方法覆盖重写。
        6.如果一个类直接父类当中的方法，和接口当中的默认方法产生了冲突，会优先使用父类中的方法。
 */
public interface MyInterfaceConst {

    //这其实就是一个常量，一旦赋值，不可以修改
    public static final int NUM_OF_MY_CLASS = 10;
}
