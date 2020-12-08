package Collection;

import java.util.LinkedList;

/*
    java.util.LinkedList集合 implements List接口
    LinkedList集合特点:
        1.底层时一个链表结构，查询慢，增删快
        2.包含了大量操作首尾元素的方法
        注意：使用LinkedList集合特有的方法，不能使用多态
     特有方法：
        public void addFirst(E e) 将指定元素插入列表开头
        public void addLast(E e) 将指定元素插入列表结尾
        public void push(E e) 将元素推入此列表所表示的堆栈

        public E getFirst() 返回此列表的第一个元素
        public E getLast() 返回此列表的最后一个元素

        public E removeFirst() 移除并返回此列表第一个元素
        public E removeLast() 移除并返回此列表的最后一个元素
        public E pop() 从此列表所表示的堆栈弹出一个元素

        public boolean isEmpty() 判断列表是否为空
 */
public class LinkedListDemo {

    public static void main(String[] args) {

    }
    /*
        public void addFirst(E e) 将指定元素插入列表开头
        public void addLast(E e) 将指定元素插入列表结尾
        public void push(E e) 将元素推入此列表所表示的堆栈
     */
    private static void demo01(){
        //创建LinkedList集合对象
        LinkedList<String> linked = new LinkedList<>();
        //使用add方法添加元素
        linked.add("a");
        linked.add("b");
        linked.add("c");
        linked.add("d");
        System.out.println(linked);

        //public void addFirst(E e) 将指定元素插入列表开头
        //等价 public void push(E e)
        linked.addFirst("hello");
        System.out.println(linked);

        //public void addLast(E e) 将指定元素插入列表结尾
        //等价 add()
        linked.addLast("world");
        System.out.println(linked);
    }

    /*
        public E getFirst() 返回此列表的第一个元素
        public E getLast() 返回此列表的最后一个元素
     */
    private static void  demo02(){
        //创建LinkedList集合对象
        LinkedList<String> linked = new LinkedList<>();
        //使用add方法添加元素
        linked.add("a");
        linked.add("b");
        linked.add("c");
        linked.add("d");
        System.out.println(linked);

        //linked.clear();//清空集合，再次获取会抛出异常

        //public boolean isEmpty() 判断列表是否为空
        if (!linked.isEmpty()) {
            //public E getFirst() 返回此列表的第一个元素
            System.out.println(linked.getFirst());
            //public E getLast() 返回此列表的最后一个元素
            System.out.println(linked.getLast());
        }
    }

    /*
        public E removeFirst() 移除并返回此列表第一个元素
        public E removeLast() 移除并返回此列表的最后一个元素
        public E pop() 从此列表所表示的堆栈弹出一个元素
     */
    private static void demo03(){
        //创建LinkedList集合对象
        LinkedList<String> linked = new LinkedList<>();
        //使用add方法添加元素
        linked.add("a");
        linked.add("b");
        linked.add("c");
        linked.add("d");
        System.out.println(linked);

        //public E removeFirst() 移除并返回此列表第一个元素
        //等价 pop()
        System.out.println(linked.removeFirst());
        System.out.println(linked);

        //public E removeLast() 移除并返回此列表的最后一个元素
        System.out.println(linked.removeLast());
        System.out.println(linked);
    }
}
