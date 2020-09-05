package Generic;

public class MyGenericClassDemo {
    public static void main(String[] args) {
        //不写泛型默认为Object类型
        MyGenericClass mgc = new MyGenericClass();
        mgc.setName("只能是字符串");
        Object obj = mgc.getName();
        System.out.println(obj);

        //创建GenericClass对象，泛型使用Integer类型
        MyGenericClass<Integer> mgc2 = new MyGenericClass<>();
        mgc2.setName(1);
        Integer name = mgc2.getName();
        System.out.println(name);

        //调用自定义泛型方法
        GenericMethod gm = new GenericMethod();
        gm.method01(10);
        gm.method01("abc");
        gm.method01(123);
        gm.method01(true);

        //调用自定义泛型静态方法
        GenericMethod.method02("123");
        GenericMethod.method02(123);
        GenericMethod.method02(true);

        //测试自定义泛型接口1
        GenericInterfaceImpl gi1 = new GenericInterfaceImpl();
        gi1.method("123");

        //测试自定义泛型接口2
        GenericInterfaceImpl2<String> gi2 = new GenericInterfaceImpl2<>();
        gi2.method("123");
        GenericInterfaceImpl2<Integer> gi3 = new GenericInterfaceImpl2<>();
        gi3.method(123);
    }
}
