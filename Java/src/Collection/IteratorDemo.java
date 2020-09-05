package Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
    对Collection进行迭代的迭代器(对集合进行遍历)
    常用方法:
        boolean hasNext() 判断集合中还有没有下一个元素
        E next()    取出集合中的下一个元素
    Iterator迭代器，是一个接口，需要使用接口的实现类对象
    Collection接口中有一个方法iterator(),返回的就是迭代器的实现类对象
        Iterator<E> iterator()
    使用步骤：
        1.使用集合中的方法获取迭代器实现类对象,使用接口接收(多态)
        2.使用Iterator接口中的方法hasNext判断还有没有下一个元素
        3.使用Iterator接口中的方法next取出集合中的下一个元素
 */
public class IteratorDemo {

    public static void main(String[] args) {
        //创建一个集合对象
        Collection<String> coll = new ArrayList<>();
        //往集合中添加元素
        coll.add("hello");
        coll.add("add");
        coll.add("world");
        coll.add("java");

        /*
            1.使用集合中的方法获取迭代器实现类对象,使用接口接收(多态)
            注意:Iterator<E>接口也是有泛型的，迭代器的泛型跟着集合走，集合是什么泛型，迭代器就是声明泛型
         */
        Iterator<String> it = coll.iterator();

        //2.使用Iterator接口中的方法hasNext判断还有没有下一个元素
        boolean b = it.hasNext();
        System.out.println(b);//true

        //3.使用Iterator接口中的方法next取出集合中的下一个元素
        String s = it.next();
        System.out.println(s);
        //没有元素再取出元素会抛出异常

        while (it.hasNext()){
            String e = it.next();
            System.out.println(e);
        }
    }
}
