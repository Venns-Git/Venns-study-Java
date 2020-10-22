package Interface.InterfaceDefault;

public class MyinterfaceDefaultB implements MyinterfaceDefault {
    @Override
    public void methodAbs() {
        System.out.println("实现了抽象方法BBB");
    }

    @Override
    public void methodDefault() {
        System.out.println("接口的实现类B覆盖重写了接口默认方法");
    }
}
