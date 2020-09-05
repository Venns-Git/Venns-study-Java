package Map;

import java.util.HashMap;

/*
    HashMap存储自定义类型键值
    Map集合保证key式唯一的：
        作为key的元素，必须重写hashcode和equals方法，以保证key唯一
 */
public class HashMapSavePerson {
    public static void main(String[] args) {

        show01();
    }

    /*
     HashMap存储自定义键值
     key：String类型
         String类重写了hashcode和equals方法
     value:Person类型
         value可以重复(同名和同年龄视为同一个人)
  */
    private static void show01(){
        //创建HashMap集合
        HashMap<String, Person> map = new HashMap<>();
        //往集合中添加元素
        map.put("北京",new Person("张三",18));
        map.put("上海",new Person("李四",19));
        map.put("广州",new Person("王五",20));
        map.put("北京",new Person("赵六",18));
        //使用keySet+for遍历Map集合
        for (String key : map.keySet()) {
            Person value = map.get(key);
            System.out.println(key+" -- > "+value);
        }
    }
}
