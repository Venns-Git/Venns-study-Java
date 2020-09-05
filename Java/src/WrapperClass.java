import java.net.Inet4Address;

/*
    基本类型    包装类
    byte        Byte
    short       Short
    int         Integer
    long        Long
    float       Float
    double      Double
    char        Character
    boolean     Boolean
装箱：基本类型 -> 包装类  使用包装类的构造方法或者静态方法 valueOf();
拆箱： 包装类  -> 基本类型    成员方法
 */
/*
    自动装箱和拆箱：
        基本类型的数据和包装类之间可以自动的相互转换
        JDK1.5之后出现的特性
 */
/*
    基本类型与字符串之间的转换：
    基本类型 -> 字符串
        1.基本类型数据的值+""   最简单的方式
        2.使用包装类中的静态方法 toString()
        3.使用String类中的静态方法：valueOf()
    字符串 -> 基本类型
        使用包装类中的静态方法 ：parseXX();
 */
public class WrapperClass {
    public static void main(String[] args) {
        //装箱
        //构造方法
        Integer in1 = new Integer(1);//过时
        Integer in2 = new Integer("1");
        //静态方法
        Integer in3 = Integer.valueOf(1);
        Integer in4 = Integer.valueOf("1");

        //拆箱
        int i = in1.intValue();

        //自动装箱
        Integer in = 1;
        //自动拆箱
        //in + 2 : in.intValue() +2
        //in = in + 2: in = new Integer(3)
        in = in +2;

        //基本类型 -> 字符串
        String s1 = 100 + "";
        System.out.println(s1+200);//100200

        String s2 = Integer.toString(100);
        System.out.println(s2+200);//100200

        String s3 = String.valueOf(100);
        System.out.println(s3+200);//100200

        //字符串   -> 基本类型
        int a1 = Integer.parseInt("100");
        System.out.println(a1+200);//300

        int a2 = Integer.parseInt("a");
        System.out.println(a2);//数字格式化异常
    }
}
