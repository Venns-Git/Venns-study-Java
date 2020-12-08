package IO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/*
    java.io.BufferedInputStream extends InputStream
    BufferedInputStream:字节缓冲输入流

    继承自父类的成员方法:
        int read() 从输入流中读取数据的下一个字节
        int read(byte[] b)从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中
        void close() 关闭此输入流并释放与该留关联的所有系统资源

    构造方法:
        BufferedInputStream(InputStream in)默认缓冲区大小
        BufferedInputStream(InputStream in,int size)指定缓冲区大小
        参数：
            InputStream in：字节输入流，可以传递FileInputStream
            int size:指定的缓冲区大小

    使用步骤:
        1.创建一个FileInputStream对象，构造方法中绑定要读取的数据源
        2.创建BufferedInputStream对象，构造方法中传递FileInputStream对象，提高效率
        3.使用BufferedInputStream对象中的read方法读取文件
        4.释放资源
 */
public class BufferedInputStreamDemo {
    public static void main(String[] args) throws IOException {
        //1.创建一个FileInputStream对象，构造方法中绑定要读取的数据源
        FileInputStream fis = new FileInputStream("D:\\学习\\Java_study\\src\\IO\\a.txt");
        //2.创建BufferedInputStream对象，构造方法中传递FileInputStream对象，提高效率
        BufferedInputStream bis = new BufferedInputStream(fis);
        //使用BufferedInputStream对象中的read方法读取文件
        //int read() 从输入流中读取数据的下一个字节
        int len = 0;
        while ((len = bis.read()) != -1){
            System.out.println((char)len);
        }
        //int read(byte[] b)从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中
        byte[] bytes = new byte[1024];//存储每次读取的数据
        int len1 = 0;//记录每次读取的有效字节个数
        while ((len1 = bis.read()) != -1){
            System.out.println(new String(bytes,0,len1));
        }
        //4.释放资源
        bis.close();
    }
}
