package Thread.setName;
/*
    设置线程名称：
        1.使用Thread类中的setName();
        2.创建一个带参数的构造方法，参数传递线程的名称，调用父类带参构造方法，把线程名称传递给父类，让父类(Thread)给子线程起一个名字
            Thread(String name)分配新的 Thread 对象
 */
public class MyThread03 extends Thread{
    public MyThread03(){

    }
    public MyThread03(String name){
        //调用父类带参构造方法，把线程名称传递给父类，让父类(Thread)给子线程起一个名字
        super(name);
    }
    @Override
    public void run() {
        //获取线程的名称
        System.out.println(Thread.currentThread().getName());
    }
}
