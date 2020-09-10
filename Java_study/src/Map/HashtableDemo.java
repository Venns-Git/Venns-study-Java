package Map;

import java.util.HashMap;
import java.util.Hashtable;

/*
    Hashtable<K,V> implements Map<K,V> 接口
    Hashtable：底层也是一个哈希表，是一个线程安全的集合，是单线程的集合，速度慢
    HashMap：底层是一个哈希表，是一个线程不安全的集合，是多线程的集合，速度快

    HashMap集合：可以存储null值，null键
    Hashtable集合:不能存储null值，null键

    Hashtable和Vector一样，在jdl1.2版本后被更先进的集合(HashMap,Arraylist)取代
    Hashtable的子类Properties依然活跃，是为一个和IO流相结合的集合
 */
public class HashtableDemo {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put(null,"a");
        map.put("b",null);
        map.put(null,null);
        System.out.println(map);

        Hashtable<String,String> table = new Hashtable<>();
        table.put(null,"a"); //NullPointerException 空指针异常
    }
}
