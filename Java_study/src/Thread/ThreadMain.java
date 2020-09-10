package Thread;
/*
    主线程：执行主方法(main)的线程
    单线程程序:java程序中只有一个线程
    执行从main方法开始，从上到下一次执行

    JVM执行main方法，main()方法会进入栈内存
    JVM会找操作系统开辟一条main方法通向cpu的执行路径
    cpu就可以通过这个路径来执行main方法
    而这个路径就叫main(主)线程

 */
public class ThreadMain {
    public static void main(String[] args) {
        Person p1 = new Person("小强");
        p1.run();

        Person p2 = new Person("小红");
        p2.run();
    }
}
