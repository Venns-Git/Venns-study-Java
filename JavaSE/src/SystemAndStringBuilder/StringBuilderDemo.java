package SystemAndStringBuilder;

/*
    java.lang.StringBuilder:    字符串缓冲区,可以提高字符串的操作效率（看成一个长度可以变化的字符串）
                                底层也是一个数组，但是没有被final修饰，可以改变长度
        构造方法：
            public StringBuilder():构造一个空的StringBuilder容器
            public StringBuilder(String str):构造一个StringBuilder容器,并把字符串添加进去
        成员方法:
            public StringBuilder append(....) 添加任意类型数据的字符串形式，并返回当前对象自身
            public String toSting();将当前StringBuilder对象转换为String对象
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder bu1 = new StringBuilder();
        System.out.println("bu1"+bu1);

        StringBuilder bu2 = new StringBuilder("abc");
        System.out.println("bu2"+bu2);
        demo01();
    }

    /*
         public StringBuilder append(....) 添加任意类型数据的字符串形式，并返回当前对象自身
         参数:任意数据类型
     */
    private static void demo01(){
        //创建StringBuilder对象
        StringBuilder bu1 = new StringBuilder();
        //使用append方法往StringBuilder中添加数据
        StringBuilder bu2 = bu1.append("abc");
        System.out.println(bu1);//abc
        System.out.println(bu2);//abc
        System.out.println(bu1 == bu2);//true 两个对象是同一个对象

        //使用append方法无需接收返回值
        bu1.append("abc");
        bu1.append(1);
        bu1.append(true);
        bu1.append(3.4);
        //链式编程
        bu1.append("abc").append(3.1).append(1).append(true);
        System.out.println(bu1);
    }
    /*
        StringBuilder和String的相互转换
            String -> StringBuilder:使用StringBuilder的构造方法
            StringBuilder -> String:使用toString方法
     */
    private static void demo02(){
        //String -> StringBuilder
        String str = new String("hello");
        System.out.println("str:"+str);
        StringBuilder bu = new StringBuilder(str);
        bu.append(" world");
        System.out.println("bu:"+bu);
        //String -> StringBuilder
        String s = bu.toString();
        System.out.println("s:"+s);
    }
}
