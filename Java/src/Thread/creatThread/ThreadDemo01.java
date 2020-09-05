package Thread.creatThread;

/*
    创建多线程程序的第一种方式：创建Thread类的子类
    java.lang.Thread类 描述线程的类，
    想要实现多线程的程序，就必须继承Thread类

    实现步骤:
        1.创建一个Thread类的子类
        2.在Thread类的子类中重写Thread类的run方法，设置线程任务
        3.创建Thread类的子类对象
        4.调用Thread类的start方法，开启新线程，执行run方法
        void start()
        当前main线程和另一个创建的新线程并发运行
        多次启动一个线程是非法的，特别是当线程已经结束执行后，不能再重新启动
    java程序属于抢占式调度，哪个线程的优先级高，哪个线程优先执行，同一个优先级，随机选择一个执行
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        //3.创建Thread类的子类对象
        MyThread mt = new MyThread();
        //4.调用Thread类的start方法，开启新线程，执行run方法
        mt.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("main:"+i);
        }
    }

}
