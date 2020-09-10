
/*
    final关键字代表最终的，不可改变的

    常见的四种用法:
        1.修饰类:表示这个类不能有任何的子类
        2.修饰方法：表示这个方法不能被覆盖重写
        3.修饰局部变量：表示这个变量不能进行更改
        4.修饰成员变量:表示这个变量也不可变
                     a.由于成员变量具有默认值，用了final关键字后必须手动赋值
                     b.对于final的成员变量，要么使用直接赋值，那么通过构造方法
                     c.必须保证类当中所有重载的构造方法，都最终会对final的成员变量进行赋值

        注意事项：对于类，方法来说，abstract和final关键字不能同时使用，因为矛盾
 */

//修饰类
public final class Keyword_Final {

    //修饰成员变量
    private final String name = "xiaoMing";
    //修饰成员方法
    public final void method(){
        //修饰局部变量
        final int num = 10;
    }
}
