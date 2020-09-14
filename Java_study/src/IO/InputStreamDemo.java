package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;

/*
    java.io.InputStream
    此抽象类是表示字节输入流的所有类的超类

    定义了所有子类的共性方法
        int read() 从输入流中读取数据的下一个字节
        int read(byte[] b)从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中
        void close() 关闭此输入流并释放与该留关联的所有系统资源

    java.io.FileInputStream extends InputStream
    FileInputStream: 文件字节输入流
    作用:把硬盘文件中的数据，读取到内存中使用

    构造方法:
        FileInputStream(String name)
        FileInputStream(File file)
        参数：读取文件的数据源
            String name:文件的路径
            File file:文件
        作用:
            1.创建一个FileInputStream对象
            2.会把FileInputStream对象指定构造方法中要读取的文件

     读取数据的原理(硬盘 -> 内存):
        Java程序 -> JVM -> OS -> OS读取数据的方法 -> 读取文件

     字节输入流的使用步骤
        1.创建FileInputStream对象，构造方法中绑定要读取的数据源
        2.使用FileInputStream对象中的方法read，读取文件
        3.释放资源
 */
public class InputStreamDemo {
    public static void main(String[] args) throws IOException {
        //1.读取一个字节
        //1.创建FileInputStream对象，构造方法中绑定要读取的数据源
        FileInputStream fis = new FileInputStream("D:\\学习\\Java_study\\src\\IO\\a.txt");
        //2.使用FileInputStream对象中的方法read，读取文件
        //int read() 读取文件中的一个字节并返回，读取到文件的末尾返回-1
        /*
            读取文件时一个重复的过程，可以使用循环优化
            使用while循环,读取到-1接受
         */
        int len = 0;//记录读取到的字节
        while((len = fis.read()) != -1) {
            System.out.print((char) len);
        }
        //3.释放资源
        fis.close();

        //2.读取多个字节
        //1.创建FileInputStream对象，构造方法中绑定要读取的数据源
        FileInputStream fis1 = new FileInputStream("D:\\学习\\Java_study\\src\\IO\\a.txt");
        //2.使用FileInputStream对象中的方法read，读取文件
        //int read(byte[] b)从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中
        /*
            参数byte[]
                祈祷缓冲作用,存储每次读取到的多个字节，长度一般定义1024(1kb)或1024的整数倍
            返回值int
                每次读取到的有效字节个数
         */
        byte[] bytes = new byte[1024]; //存储读取到的多个字节
        int len1 = 0;
        while((len1 = fis1.read(bytes)) != -1){
            System.out.println(new String(bytes,0,len1));
        }

        //3.释放资源
        fis1.close();
    }
}
