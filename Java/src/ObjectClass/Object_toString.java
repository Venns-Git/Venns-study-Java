package ObjectClass;

/*
    java.lang.Object类
    类 Object 是类层次结构的根(最顶层)的类，每个类都使用Object作为超(父)类
    所有对象(包括数组)都实现这个类的方法
 */
public class Object_toString {

    public static void main(String[] args) {
        /*
            Person类默认基础Object类，所有可以使用Object的toString方法
        */

        Person p = new Person("张三",18);
        String s = p.toString();
        System.out.println(s);//ObjectClass.Person@b4c966a

        //直接打印对象的名字，其实就是调用对象的toString的方法 p = p.toString()
        System.out.println(p);//ObjectClass.Person@b4c966a

        /*
            看一个类是否重写了toString方法，直接打印这类对应的对象即可。
            如果没有重写toSting方法，那么打印的就是对象的地址值(默认)
            否则，就按照重写的方式打印
         */
    }


}
