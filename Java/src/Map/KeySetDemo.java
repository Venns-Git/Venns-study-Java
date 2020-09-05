package Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
    Map集合的第一种遍历方式：通过键找值的方式
    Map集合中的方法：
        public Set<K> keySet() 获取Map集合中所有的键，存储到Set集合中
    实现步骤：
        1.使用Map集合中的方法KeySet(),把Map集合所有的key取出来，存储到一个Set集合中
        2.遍历Set集合，获取Map集合中的每一个key
        3.通过Map集合中的方法get()，通过key找到value
 */
public class KeySetDemo {

    public static void main(String[] args) {

        //创建Map集合
        Map<String,Integer> map = new HashMap<>();

        map.put("小明",180);
        map.put("小红",170);
        map.put("小刚",160);
        System.out.println(map);

        //1.使用Map集合中的方法KeySet(),把Map集合所有的key取出来，存储到一个Set集合中
        Set<String> set = map.keySet();

        //2.遍历Set集合，获取Map集合中的每一个key
        //使用迭代器遍历Set集合
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            String key = it.next();

            //3.通过Map集合中的方法get()，通过key找到value
            Integer value = map.get(key);
            System.out.println(key+"="+value);
        }
        System.out.println("--------------------");
        //使用增强for循环遍历Set集合
        for (String key : map.keySet()) {
            //3.通过Map集合中的方法get()，通过key找到value
            Integer value = map.get(key);
            System.out.println(key+"="+value);
        }
    }
}
