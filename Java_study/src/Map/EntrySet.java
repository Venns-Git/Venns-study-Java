package Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
    Map集合遍历的第二种方式，使用Entry对象遍历
    Entry:键值对对象
    Map集合中的方法:
        public Set<Map.Entry<K,V>> enterSet() 获取到Map集合中所有记得键值对对象的集合(Set集合)
    实现步骤：
        1.使用Map集合中的方法entrySet()，把Map集合中多个Entry对象取出来，存储到一个Set集合中
        2.遍历Set集合，获取每一个Entry对象
        3.使用Entry对象中的方法getKey()和getValue()获取键与值
 */
public class EntrySet {

    public static void main(String[] args) {
        //创建Map集合
        Map<String,Integer> map = new HashMap<>();

        map.put("小明",180);
        map.put("小红",170);
        map.put("小刚",160);
        System.out.println(map);

        //1.使用Map集合中的方法entrySet()，把Map集合中多个Entry对象取出来，存储到一个Set集合中
        Set<Map.Entry<String, Integer>> set = map.entrySet();

        //2.遍历Set集合，获取每一个Entry对象
        //使用迭代器
        Iterator<Map.Entry<String, Integer>> it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String, Integer> entry = it.next();

            //3.使用Entry对象中的方法getKey()和getValue()获取键与值
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key+"="+value);
        }
        System.out.println("-------------------------");
        //使用增强for循环
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            //3.使用Entry对象中的方法getKey()和getValue()获取键与值
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }
}
