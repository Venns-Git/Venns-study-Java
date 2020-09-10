package Thread.InnerClassThread;
/*
    匿名内部类方式实现多线程的创建
    作用：简化代码
        把子类继承父类，重写父类的方法，创建子类对象合成一步完成
        把实现类实现接口，重写接口中的方法，创建实现类对象合成一步完成
    匿名内部类的最终产物:子类/实现类对象，而这个类没有名字

    格式：
        new 父类/接口(){
            重写父类/接口中的方法
        };
 */
public class InnerClassThreadDemo {
    public static void main(String[] args) {
        //继承父类
        new Thread(){
            //重写run方法，设置线程任务
            @Override
            public void run() {
                for (int i = 0; i< 20;i++){
                    System.out.println(Thread.currentThread().getName()+"-->"+i);
                }
            }
        }.start();

        //实现接口
         Runnable r =  new Runnable(){
            //重写run方法，设置线程任务
            @Override
            public void run() {
                for (int i = 0; i< 20;i++){
                    System.out.println(Thread.currentThread().getName()+"-->"+i);
                }
            }
        };
         new Thread(r).start();

         //简化接口的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< 20;i++){
                    System.out.println(Thread.currentThread().getName()+"-->"+i);
                }
            }
        }).start();

    }
}
