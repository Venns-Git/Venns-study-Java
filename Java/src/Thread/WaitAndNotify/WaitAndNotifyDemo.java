package Thread.WaitAndNotify;
/*
    等待唤醒案例:线程之间的通信
        创建一个顾客线程(消费者):告知老板需要的食物种类和数量，调用wait方法，放弃cup的执行，进入到WAITING(无线等待)
        创建一个老板线程(生产者):花费时间制作食物，做好后调用notify方法，幻想顾客
    注意：
        消费者和生产者线程必须同步代码块包裹起来，保证等待和唤醒只能有一个执行
        同步使用的锁对象必须保证唯一
        只有锁对象才能调用wait和notify方法

 */
/*
    进入TimeWaiting(计时等待) 有2种方法:
    1.使用sleep(long m)方法，在毫秒值结束之后，线程睡醒进入到Runnable/Blocked状态
    2.使用wait方法(long m)方法，wait方法如果在毫秒值结束后还没notify被唤醒，就会自动睡醒进入到Runnable/Blocked状态

    唤醒的方法:
        1.notify() 唤醒单个线程
        2.notifyAll() 唤醒所有等待的线程
 */
public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        //创建锁对象
        Object obj = new Object();
        //创建一个消费者线程
        new Thread(){
            @Override
            public void run() {
                //保证等待和唤醒的线程只能有一个执行，需要使用同步技术
                synchronized (obj){
                    System.out.println("告知老板食物的种类的数量");
                    //调用wait方法，放弃其cpu的执行，进入到WAITING状态(无线等待)
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒后执行的代码
                    System.out.println("食物制作完毕.");
                }
            }
        }.start();

        //创建生产者线程
        new Thread(){
            @Override
            public void run() {
                //花时间制作食物
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //保证等待和唤醒的线程只能有一个执行，需要使用同步技术
                synchronized (obj){
                    System.out.println("生产者花费时间后制作完成，告知消费者");
                    //制作好后,唤醒消费者线程
                    obj.notify();
                }
            }
        }.start();
    }
}
