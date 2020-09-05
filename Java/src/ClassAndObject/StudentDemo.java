package ClassAndObject;

/*
    通常情况下,一个类不能直接使用,需要根据类创建一个对象,才能使用
    1.导包
        import 包名称.类名称
        对于和当前类属于同一个包的情况，可以省略导包语句
    2.创建
        类名称 对象名 = new 类名称();
    3.使用
        使用成员变量:对象名.成员变量名
        使用成员方法:对象名.成员方法名(参数)
    注意事项:
        如果成员变量没有进行赋值，那么将会有一个默认值，规则和数组一样
 */
/*
    局部变量和成员变量
    1.定义的位置不同
        局部变量：在方法内部
        成员变量：在方法外部，直接写在类当中
    2.作用域不同
        局部变量：只有在方法中才可以使用
        成员变量：整个类都可以使用
    3.默认值不同
        局部变量：没有默认值，如果需要使用，必须手动进行赋值
        成员变量：如果没有赋值，会有默认值，规则和数组一样
    4.内存位置不一样
        局部变量：位于栈内存
        成员变量：位于堆内存
    5.生命周期不同
        局部变量：随着方法进栈而诞生，随着方法出栈而消失
        成员变量：随着对象创建而诞生，随着对象被垃圾回收而消失
 */
public class StudentDemo {
    public static void main(String[] args) {
        //1.导包
        //Student类与当前StudentDemo位于同一个包ClassAndObject下.省略

        //2.创建
        Student stu = new Student();

        //3.1使用成员变量
        System.out.println(stu.name); //null
        System.out.println(stu.age);  //0

        //3.2改变成员变量的值
        stu.age = 19;
        stu.name = "XiaoMing";

        //4.1使用成员方法
        stu.sleep();
        stu.eat();
        stu.study();
    }
}
