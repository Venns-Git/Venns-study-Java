package Thread.setName;

public class SetThreadName {
    public static void main(String[] args) {
        //开启多线程
        MyThread03 mt = new MyThread03();
        mt.setName("小强");
        mt.start();

        //开启多线程
        new MyThread03("小红").start();
    }
}
