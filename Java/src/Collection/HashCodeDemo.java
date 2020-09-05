package Collection;

import ClassAndObject.Student;

/*
    哈希值:是一个十进制的整数，由系统随机给出(就是对象的地址，是一个逻辑地址，非实际存储物理地址)
    Object类中的hashCode()方法返回对象的哈希码值
    public native int hashCode();
    native:代表该方法调用的时本地操作系统的方法
 */
public class HashCodeDemo{

    public static void main(String[] args) {
        //Student类继承了Object类，所以可以直接使用hashCode方法
        Student s1 = new Student();
        int h1 = s1.hashCode();
        System.out.println(h1);

        /*
            String类的哈希值
                String类重写了Object类的hashCode方法
         */
        String s2 = "abc";
        String s3 = "abc";
        System.out.println(s2.hashCode());
        System.out.println(s2.hashCode());
    }
}
