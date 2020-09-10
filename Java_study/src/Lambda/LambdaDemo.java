package Lambda;
/*
    Lambda表达式的标准格式:
        由3部分组成
            1.一些参数
            2.一个箭头
            3.一段代码
        格式：
            (参数列表) -> {一些重写方法的代码}
        解释：
            ():接口中抽象方法的参数列表
            ->:把参数传递给方法体{}
            {}:重写接口的抽象方法体

    Lambda表达式:可推导，可以省略
    凡是根据上下文推导出来的内容，都可以省略书写
    可以省略的内容:
        1.(参数列表):括号中参数列表的数据类型，可以省略
        2.(参数列表):括号中的参数如果只有一个，那么和类型和括号都可以省略
        3.{一些代码}:{}中的代码只有一行，无论是否有返回值，都可以省略({},return,;)
            注意:要省略，必须一起省略

    Lambda的使用前提
        1.使用Lambda必须具有接口，且要求接口中有且仅有一个抽象方法
        2.使用Lambda必须具有上下文推断
 */
public class LambdaDemo {
    public static void main(String[] args) {
        //使用匿名内部类的方式，实现多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"创建了新线程");
            }
        }).start();

        //使用Lambda表达式，实现多线程
        new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"创建了新线程");
            }).start();

        //优化省略Lambda
        new Thread(()-> System.out.println(Thread.currentThread().getName()+"创建了新线程")).start();
    }
}
