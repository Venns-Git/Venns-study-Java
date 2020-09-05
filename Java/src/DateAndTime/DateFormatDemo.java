package DateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    java.until.DateFormat
    DateFormat类：日期/时间 格式转换
    成员方法：
        String format(Date date) 按照指定的模式，把Date日期，格式化为符合模式的字符串
        Date parse(String source) 把符合模式的字符串，解析为Date日期
    构造方法：
        SimpleDateFormat(String pattern) 用给定的模式和默认语言环境的日期格式符号构造
        String pattern: 传递指定的模式
        模式：区分小大写
            y   年
            M   月
            d   日
            H   时
            m   分
            s   秒
 */
public class DateFormatDemo {

    public static void main(String[] args) throws ParseException {
        demo01();
        demo02();
    }

    /*
        使用format方法将日期格式化为文本
        使用步骤：
            1.创建SimpleDateFormat对象，构造方法中传递指定的模式
            2.调用SimpleDateFormat对象中的format方法
     */
    public static void demo01(){
        //1.创建SimpleDateFormat对象，构造方法中传递指定的模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //2.调用SimpleDateFormat对象中的format方法
        Date date = new Date();
        System.out.println(date);
        System.out.println(sdf.format(date));
    }

    /*
        使用parse方法,把文本解析为日期
        使用步骤：
            1.创建SimpleDateFormat对象，构造方法中传递指定的模式
            2.调用SimpleDateFormat对象的parse方法
         注意:
            parse方法声明了一个ParseException解析异常
            如果字符串和方法中的模式不一样，那么程序就会抛出这个异常
            调用了一个会抛出异常的方法，就必须处理这个异常
            要么throws继续声明抛出这一个异常，要么try...catch自己处理这个异常
     */
    public static void demo02() throws ParseException {
        //1.创建SimpleDateFormat对象，构造方法中传递指定的模式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-07-19 19:31:49");
        System.out.println(date);
    }
}
