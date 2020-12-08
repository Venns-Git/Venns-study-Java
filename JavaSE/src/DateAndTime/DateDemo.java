package DateAndTime;

import java.util.Date;

/*
    java.util.Date:表示日期何事件的类
 */
public class DateDemo {

    public static void main(String[] args) {
        //当前系统时间到
        //1970年1月1日00:00:00(英国格林威治时间)
        //1970年1月1日08:00:00(北京时间)
        // 一共经历了多少毫秒
        System.out.println(System.currentTimeMillis());
        demo01();//Date类无参构造
        demo02(System.currentTimeMillis());//Date类有参构造
        demo03();//Date类成员方法getTime()
    }
    /*
        Date()类的空参数构造方法
        获取当前系统的时间和日期
     */
    public static void demo01(){
        Date date = new Date();
        System.out.println(date);
    }
    /*
        Date()类带参数构造方法
        传递毫秒值，把毫秒值转换为Date日期
     */
    public static void demo02(long date){
        Date d = new Date(date);
        System.out.println(d);
    }
    /*
        Date类的成员方法:long getTime()
        把日期转换为毫秒(相当于System.currentTimeMillis())
     */
    public static void demo03(){
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
    }
}
