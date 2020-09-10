package Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    java.utils.Collections集合工具类，用来对集合进行操作
    常用方法:
        public static <T> boolean addAll(Collections<T>,T...elements) 往集合添加元素
        public static void shuffle(List<?> list) 打乱集合顺序
        public static <T> void sort(List<T> list) 将集合中元素按照默认规则排序
        public static <T> void sort(List<T> list,Comparator<? super T>) 将集合中元素按照规则排序
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        //往集合中添加多个元素
        // list.add("a");
        //list.add("b");
        //list.add("c");
        //list.add("d");

        //public static <T> boolean addAll(Collections<T>,T...elements) 往集合添加元素
        Collections.addAll(list,"a","b","c","d");
        System.out.println(list);

        //public static void shuffle(List<?> list) 打乱集合顺序
        Collections.shuffle(list);
        System.out.println(list);

        //public static <T> void sort(List<T> list) 将集合中元素按照默认规则排序
        //注意：被排序的集合中的元素，必须实现Comparable,重写接口中的方法ComparaTo定义排序规则
        Collections.sort(list);
        System.out.println(list);

        //public static <T> void sort(List<T> list,Comparator<? super T>) 将集合中元素按照规则排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //return o1.charAt(0) - o2.charAt(0) //按照首字母升序
                return o2.hashCode() - o1.hashCode(); //按照哈希值降序
            }
        });
    }
}
