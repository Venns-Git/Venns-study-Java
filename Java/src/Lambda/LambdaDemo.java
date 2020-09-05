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
    }
}
