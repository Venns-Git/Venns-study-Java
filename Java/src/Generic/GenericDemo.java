package Generic;

import java.util.ArrayList;
import java.util.Iterator;

/*
    泛型:是一种未知的数据类型,当我们不知道使用什么数据类型时，可以使用泛型
        也可以看成时一个变量，用来接受数据类型
        E e：Element     元素
        T t：type        类型
 */
/*
    泛型的通配符：
        ?:代表任意数据类型
    使用方式：
        不能创建对象使用
        只能作为方法的参数使用
    上限限定：
        ? extends E    代表使用的泛型只能是E类型的子类/本身
    下限限定:
        ? super E      代表使用的泛型只能是E类的父类/本身
    Integer extends Number extends Object
    String extends Object
 */
public class GenericDemo {

    public static void main(String[] args) {
        show01();
    }

    /*b
        创建集合对象，不使用泛型
        好处:
            集合不使用泛型，默认就是Object类型，可以存储任意类型的数据
        弊端：
            不安全，会引发异常
     */
    private static void show01() {
        ArrayList list = new ArrayList();
        list.add("abc");
        list.add(1);

        //使用迭代器遍历list集合
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);

            //想要使用String特有方法，length获取字符串长度，不能使用
            //需要向下转型
            String s = (String) obj;
            System.out.println(s.length());
        }
    }

    /*
        创建集合对象，使用泛型
        好处:
            1.避免了类型转换的魔法，存储的是什么类型，取出的就是什么类型
            2.把运行期异常，提升到了编译期
        弊端:
            泛型是什么类型，就只能存储什么类型
     */
    private static void show02(){
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        //list.add(123) 编译报错

        //使用迭代器遍历list集合
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s.length());
        }
    }

    /*
        定义一个方法，能遍历所有的ArrayList集合
        注意:泛型没有继承概念的
     */
    public static void printArray(ArrayList<?> list){
        //使用迭代器遍历集合
        Iterator<?> it = list.iterator();
        while (it.hasNext()){
            //it.next()，返回类型是Object类型
            Object o = it.next();
            System.out.println(o);
        }
    }
}