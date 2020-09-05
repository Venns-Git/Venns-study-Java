package Collection;

import java.util.HashSet;

/*
    哈希表:数组+红黑树
        数组结构:把元素进行了分组(相同哈希值)
        红黑树:把相同哈希值的元素连接在一起
    特点:查询速度快
 */
/*
    HashSet存储自定义元素
    Set集合保证元素唯一：
        存储的元素(String,Integer,...)必须重写HashCode和equals方法
    要求：
        同名和同年龄的人，视为同一个人，只能存储一次
 */
public class HashSetDemo {
    public static void main(String[] args) {
        //创建HashSet集合存储Person
        HashSet<Person> set = new HashSet<>();
        Person p1 = new Person("小明",18);
        Person p2 = new Person("小明",18);
        Person p3 = new Person("小明",19);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println(set);
    }
}
