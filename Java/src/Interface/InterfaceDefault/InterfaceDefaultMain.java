package Interface.InterfaceDefault;
/*
    1.接口的默认方法，可以通过接口实现类对象，直接调用
    2.接口的默认方法，也可以被接口实现类进行覆盖重写
 */
public class InterfaceDefaultMain {
    public static void main(String[] args) {
        MyinterfaceDefaultA a = new MyinterfaceDefaultA();
        a.methodAbs();//调用抽象方法，实际运行的是实现类

        //调用默认方法，如果实现类中没有，会向上找接口
        a.methodDefault();

        System.out.println("====================");
        MyinterfaceDefaultB b = new MyinterfaceDefaultB();
        b.methodAbs();
        b.methodDefault();
    }
}
