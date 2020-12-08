package Exception;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
    throws关键字:异常处理的第一种方式
    作用:
        当方法内部抛出异常对象的时候，我们就必须处理这个对象
        可以使用throws关键字处理对象，会把异常对象声明抛出给方法的调用者处理(自己不处理，给别人处理)，最终交给JVM处理-->中断处理
    使用格式：方法声明时使用
        修饰符 返回值类型 方法名(参数列表) throws aaaException,bbbException{
            throw new aaaException("参数原因");
            throw new bbbException("参数原因");
        }
    注意：
        1.throws关键字必须写在方法声明处
        2.throws关键字后面声明的异常必须是Exception或者Exception子类
        3.方法内部如果抛出了多个异常对象，那么throws后面也必须声明多个异常
            如果抛出的异常的多个对象有子父类关系，那么直接声明父类异常即可
        4.调用了一个声明抛出异常的方法，我们就必须处理声明的异常
            要么继续使用throws声明抛出,交给方法的调用者处理，最终交给JVM
            要么try...catch自己处理异常
 */
public class Keyword_throws {
    public static void main(String[] args) throws IOException {
        readFile("c:\\a.txt");
    }

    /*
        定义一个方法，对传递的文件路径进行合法性判断
        如果不是"c:\\a.txt"，就抛出文件找不到的异常，告知方法的调用者
        注意：
            FileNotFoundException是编译异常，抛出了编译异常，就必须处理这个异常
            可以使用throws继续声明抛出FileNotFoundException这个异常对象，让方法的调用者处理
            IOException是fileNotFoundException的父类对象
     */
    public static void readFile(String fileName) throws IOException {
        /*
            如果传递的路径，不是txt结尾
            那么我们就抛出IO异常对象，告知方法的调用者，文件的后缀名错误
         */
        if (fileName.endsWith(".txt")){
            throw new IOException("文件后缀名错误");
        }

        if (fileName.equals("c:\\a.tex")) {
            throw new FileNotFoundException("传递文件地址不正确");
        }
        System.out.println("路径正确");
    }
}
