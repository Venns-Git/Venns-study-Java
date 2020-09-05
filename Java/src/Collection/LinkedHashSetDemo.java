package Collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
/*
    Java.util.LinkedHashSet集合 extends HashSet集合
    LinkedHashSet集合特点:
        底层是一个哈希表(数组+链表/红黑树)+链表：多了一条链表(纪律元素的存储顺序,保证元素有序)
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("abc");
        set.add("hello");
        set.add("world");
        set.add("abc");
        System.out.println(set);//无序，不允许重复

        LinkedHashSet<String> linked = new LinkedHashSet<>();
        linked.add("abc");
        linked.add("hello");
        linked.add("world");
        linked.add("abc");
        System.out.println(linked);//有序，不允许重复
    }
}
