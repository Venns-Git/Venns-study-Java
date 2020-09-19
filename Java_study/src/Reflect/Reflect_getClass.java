package Reflect;

public class Reflect_getClass {
        /*
            获取class对象的方式：
            1.Class.forName("全类名"):将字节码文件加载进内存，返回class对象
            2.类名.class：通过类名的属性class获取
            3.对象.getClass()：getClass()定义在Object中
        */
        public static void main(String[] args) throws Exception {
            //1.Class.forName()
            Class cls = Class.forName("Reflect.Person");
            System.out.println(cls);
            //2.类名.class
            Class cls2 = Person.class;
            System.out.println(cls2);
            //3.对象.getClass()
            Person p = new Person();
            Class cls3 = p.getClass();
            System.out.println(cls3);

            //用 == 比较三个对象
            System.out.println(cls == cls2);//true
            System.out.println(cls == cls3);//true
        }
}
