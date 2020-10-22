package Collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
    Java.util.Set接口 extends Collection接口
    Set接口的特点:
        1.不允许存储重复的元素
        2.没有索引，不能使用带索引的方法
     Java.util.HashSet集合 implements Set接口
     HashSet特点：
        1.是一个无序集合，存取元素顺序可能不一致
        2.底层是一个哈希表结构(查询将速度快)
 */
public class SetDemo {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        //使用add方法往集合添加元素
        set.add(1);
        set.add(3);
        set.add(2);
        set.add(1);
        //使用迭代器遍历set集合
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()){
            System.out.println(it.next());//1,2,3
        }
        //使用增强for循环
        for (Integer i : set) {
            System.out.println(i);
        }
    }
}
