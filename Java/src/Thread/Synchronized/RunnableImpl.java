package Thread.Synchronized;
/*
    卖票案例出现了线程安全问题
    卖出了不存在的票和重复的票

    解决方案：
        1.使用同步代码块
    格式：
        synchronized(锁对象){
            可能会出现线程安全问题的代码(访问了共享数据的代码)
        }
    注意：
        1.同步代码块中的锁对象，可以是任意的对象
        2.必须保证多个线程使用的锁对象是同一个
        3.锁对象的作用：
            把同步代码块锁住，只让一个线程在同步代码块中执行

     解决方案：
        2.使用同步方法
     使用步骤：
        1.把访问了共享数据的代码抽取出来，放到一个方法中
        2.在方法上添加synchronized修饰符
     格式:
        修饰符 synchronized 返回值类型 方法名(参数列表){
            可能会出现线程安全问题的代码(访问了共享数据的代码)
        }
 */
public class RunnableImpl implements Runnable{
    //定义一个多线程共享的票源
    private static int ticket = 100;

    //创建一个锁对象
    Object obj = new Object();


    @Override
    public void run() {
        //使用死循环，让卖票操作重复执行
        while(true){
            //同步代码块
//            synchronized (obj){
//                if (ticket > 0)
//                {
//                    //提高安全问题出现的概率，让程序睡眠
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //票存在，卖票，ticket--
//                    System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
//                    ticket--;
//                }
//            }
            payTicketStatic();
        }
    }

    /*
        定义一个同步方法
        同步方法也会把方法内部的代码锁住，只让一个线程执行
        同步方法的锁对象就是实现类对象 new RunnableImpl() 也就是 this
     */
//    public synchronized void payTicket(){
//        if (ticket > 0)
//        {
//            //提高安全问题出现的概率，让程序睡眠
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //票存在，卖票，ticket--
//            System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
//            ticket--;
//        }
//    }
    /*
        静态的同步方法
        锁对象是？
        this是对象创建之后产生的，静态方法优先于对象
        静态方法的锁对象是本类class属性 --> class文件对象(反射)
     */
    public static synchronized void payTicketStatic(){
        if (ticket > 0)
        {
            //提高安全问题出现的概率，让程序睡眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //票存在，卖票，ticket--
            System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
            ticket--;
        }
    }

}
