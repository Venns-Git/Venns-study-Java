package Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
    java.util.List接口   extends Collection接口
    List接口的特点：
        1.有序的集合，存取元素顺序一致
        2.有索引，包含了一些带索引的方法
        3.允许存储重复的元素
    List接口中带索引的方法(特有)
        public void add(int index,E element) 将指定的元素，添加到指定位置
        public E get(int index) 返回集合中指定位置的元素
        public E remove(int index) 移除集合中指定位置的元素并返回
        public E set(int index,E element) 用指定元素替换集合中指定位置的元素，返回原来的元素
     注意：操作索引时，防止索引越界异常
 */
public class ListDemo {

    public static void main(String[] args) {
        //创建一个List集合对象，多态
        List<String> list = new ArrayList<>();
        //使用add方法往集合中添加元素
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        //打印集合
        System.out.println(list);//[a, b, c, d] 不是地址，重写了toString

        //public void add(int index,E element) 将指定的元素，添加到指定位置
        //b,c之间添加hello
        list.add(2,"hello");
        System.out.println(list);

        //public E get(int index) 返回集合中指定位置的元素
        //移除hello
        System.out.println(list.remove(2));
        System.out.println(list);

        // public E set(int index,E element) 用指定元素替换集合中指定位置的元素，返回原来的元素
        //把a替换为A
        System.out.println(list.set(0, "A"));
        System.out.println(list);

        //List遍历
        //1.普通for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //2.使用迭代器
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        //3.使用增强for
        for (String s : list) {
            System.out.println(s);
        }
    }
}
