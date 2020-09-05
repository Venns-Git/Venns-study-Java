package Collection;

import java.util.ArrayList;

/*
    增强for循环:底层使用的也是迭代器，使用for循环的格式，简化了迭代器的书写
    所有的单列集合都可以使用for each
    格式：
        for(集合/数组的数据类型 变量名;集合名/数组名字){
               ...
        }
     tips:新的for循环必须有被遍历的目标，目标只能是Collection或者数组
 */
public class ForeachDemo {

    public static void main(String[] args) {
        demo01();
        demo02();
    }

    //使用增强for循环遍历数组
    private static void demo01(){
        int[] arr = {1,2,3,4,5,6};
        for (int i:arr){
            System.out.println(i);
        }
    }

    //使用增强for循环遍历集合
    private static void demo02(){
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        for (String i:list){
            System.out.println(i);
        }
    }
}
