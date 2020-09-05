package Thread.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    解决方案:
        3.使用Lock锁 java.util.concurrent.locks.lock接口
    Lock接口中的方法:
        void lock() 获取锁
        void unlock() 释放锁
    java.util.concurrent.Locks.ReentrantLock Implements Lock接口
    使用步骤：
        1.在成员位置创建一个ReentrantLock对象
        2.在可能会出现线程安全问题的代码前调用Lock接口中的方法lock获取锁
        3.在可能会出现线程安全问题的代码后调用lock接口的方法unlock释放锁

 */
public class RunnableImpl implements Runnable{
    //定义一个多线程共享的票源
    private int ticket = 100;

     //1.在成员位置创建一个ReentrantLock对象
    Lock l = new ReentrantLock();
    @Override
    public void run() {
        //使用死循环，让卖票操作重复执行
        while(true){
            //2.在可能会出现线程安全问题的代码前调用Lock接口中的方法lock获取锁
            l.lock();
            if (ticket > 0)
            {
                //提高安全问题出现的概率，让程序睡眠
                try {
                    Thread.sleep(10);
                    //票存在，卖票，ticket--
                    System.out.println(Thread.currentThread().getName()+"正在卖第"+ticket+"张票");
                    ticket--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //3.在可能会出现线程安全问题的代码后调用lock接口的方法unlock释放锁
                    l.unlock();
                }
            }
        }
    }
}
