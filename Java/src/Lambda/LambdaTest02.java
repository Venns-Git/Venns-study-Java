package Lambda;

import java.util.Arrays;
import java.util.Comparator;

/*
    Lambda表达式有参数有返回值的练习
    需求:
        使用数组存储多个Person对象
        对数组中的Person对象使用Arrays的sort方法通过对年龄的升序排序
 */
public class LambdaTest02 {

    public static void main(String[] args) {
        //使用数组存储多个Person对象
        Person[] arr = {
                new Person("小明",18),
                new Person("小红",19),
                new Person("小强",20),
        };

        //对数组中的Person对象使用数组Arrays的sort方法通过年龄进行升序排序
        Arrays.sort(arr, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        //使用Lambda表达式，简化匿名内部类
        Arrays.sort(arr,(Person o1, Person o2) -> {
            return o1.getAge() - o2.getAge();
        });

        //遍历数组
        for (Person p : arr) {
            System.out.println(p);
        }
    }
}
