package Collection;

import java.util.ArrayList;
import java.util.Collection;

/*
    集合                  数组
    长度固定            长度可变
    同类型              类型可变
    可存储基本数据类型    只能存储对象
Collection接口:
        List接口:Vector集合 ArrayList集合 LinkedList集合
            特点: 1.有序的集合，存取元素顺序相同
                  2.允许存储重复的元素
                  3.有索引，可以使用普通的for循环遍历
        Set接口：TreeSet集合 HashSet集合 LinkedHashSet集合(有序)
            特点： 1.不允许重复元素
                  2.没有索引
 */
/*
    List:有索引，可以存储重复的元素，可以保证存取顺序
        ArrayList:底层是数组实现的，查询快，增删慢
        LinkedList:底层是链表实现的，查询慢，增删快
    Set:无索引，不可以存储重复元素，存取无须
        HashSet:底层由哈希表+(红黑树)实现的，无索引，不可以存储重复元素，存取无序
        LinkedHashSet:底层由哈希表+链表实现的，无索引，不可以存储重复元素，可以保证存取顺序
        TreeSet:底层是二叉树实现，一般用于排序
 */
/*
    Collection常用方法：
        boolean add(E e);   向集合中添加元素
        boolean remove(E e);    删除集合中某个元素
        void clear();   清空集合
        boolean contains(E e);  判断集合中是否有某个元素
        boolean isEmpty();  判断集合是否为空
        int size(); 获取集合长度
        Object[] toArray(); 将集合转换成数组
 */
public class CollectionDemo {

    public static void main(String[] args) {
        //创建集合对象
        Collection<String> coll = new ArrayList<>();
        //boolean add(E e);   向集合中添加元素
        coll.add("hello");
        coll.add("world");
        System.out.println(coll);

        //boolean remove(E e);    删除集合中某个元素
        boolean result = coll.remove("hello");
        System.out.println(result);
        System.out.println(coll);

        //void clear();   清空集合
        coll.clear();
        System.out.println(coll);

       // boolean contains(E e);  判断集合中是否有某个元素
        boolean res = coll.contains("hello");
        System.out.println(res);
        System.out.println(coll);

        //boolean isEmpty();  判断集合是否为空
        System.out.println(coll.isEmpty());

        //int size(); 获取集合长度
        System.out.println(coll.size());

        // Object[] toArray(); 将集合转换成数组
        Object[] arr = coll.toArray();

        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
