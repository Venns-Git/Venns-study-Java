package Map;

import java.util.HashMap;
import java.util.Map;

/*
    java.util.Map集合
    特点:
        1.Map集合是一个双列集合，一个元素包含两个值(key,value)
        2.Map集合中的元素，key和value的数据类型可以相同，也可以不同
        3.Map集合中的元素，key不允许重复的，value是可以重复的
        4.Map集合中的元素，key和value是一一对应的
    java.util.HashMap<k,v>集合 implements Map<k,v>接口
    特点：
        1.HashMap集合底层是哈希表，查询速度特别快
        2.HashMap集合是一个无序的集合，存储元素和取出元素的顺序可能不一致
    java.util.LinkedHashMap<k,v>集合 extends HashMap<k,v>集合
        特点:
            1.LinkedHashMap集合底层是哈希表+链表
            2.LinkedHashMap集合是一个有序的集合，存储元素和取出元素顺序是一致的
*/
/*
    Map接口常用方法:
        public V put(K key,V value) 把指定的键和值添加到Map集合中
        public V remove(Object key) 把指定的值 所对应的建值对元素在Map集合中删除，返回被删除元素的值
        public V get(Object key) 根据指定的键,在Map集合中获取对应的值
        boolean containsKey(Object key)判断集合中是否包含指定的键
        public Set<K> keySet() 获取Map集合中所有的键，存储到Set集合中
        public Set<Map.Entry<K,V>> enterSet() 获取到Map集合中所有记得键值对对象的集合(Set集合)
 */
public class MapDemo {
    public static void main(String[] args) {

    }

    /*
         public V put(K key,V value) 把指定的键和值添加到Map集合中
         返回值 V：
            存储键值对的时候，key不重复，返回值V为null
            存储键值对的时候，key重复，会使用新的value替换map中重复的value，返回被替换的value值
     */
    private static void show01() {
        //创建Map集合对象，多态
        Map<String,String> map = new HashMap<>();

        String v1 = map.put("小明", "小红1");
        System.out.println("v1"+v1);

        String v2 = map.put("小明", "小红2");
        System.out.println("v2"+v2);

        System.out.println(map);

        map.put("123","321");
        map.put("456","654");
        map.put("789","654");
        System.out.println(map);
    }

    /*
        public V remove(Object key) 把指定的值 所对应的建值对元素在Map集合中删除，返回被删除元素的值
        返回值 V：
            key存在，V返回被删除的值
            key不存在，V返回null
     */
    private static void show02(){

        //创建Map集合
        Map<String,Integer> map = new HashMap<>();

        map.put("小明",180);
        map.put("小红",170);
        map.put("小刚",160);
        System.out.println(map);

        Integer v1 = map.remove("小明");
        System.out.println(v1);//180
        System.out.println(map);

        Integer v2 = map.remove("小强");
        System.out.println(v2); //null;
    }

    /*
        public V get(Object key) 根据指定的键,在Map集合中获取对应的值
        返回值：
            key存在，返回对应的value值，反之返回null
     */

    private static void show03(){

        //创建Map集合
        Map<String,Integer> map = new HashMap<>();

        map.put("小明",180);
        map.put("小红",170);
        map.put("小刚",160);
        System.out.println(map);

        Integer v1 = map.get("小明");
        System.out.println(v1);

        Integer v2 = map.get("小强");
        System.out.println(v2);
    }

    /*
        boolean containsKey(Object key)判断集合中是否包含指定的键
        包含返回true 不包含返回false
     */
    private static void show04(){
        //创建Map集合
        Map<String,Integer> map = new HashMap<>();

        map.put("小明",180);
        map.put("小红",170);
        map.put("小刚",160);
        System.out.println(map);

        boolean v1 = map.containsKey("小明");
        System.out.println(v1);

        boolean v2 = map.containsKey("小强");
        System.out.println(v2);
    }
}
