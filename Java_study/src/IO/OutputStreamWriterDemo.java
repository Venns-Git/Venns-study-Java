package IO;

import java.io.*;

/*
    java.io.OutputStreamWriter extends Writer
    字符流通向字节流的桥梁，可使用指定的 charset 将要写入流中的字符编码成字节(编码)

    继承自父类的共性的成员方法:
        void write(int c)写入单个字符
        void write(char[] cbuf)写入字符数组
        abstract void write(char[] cbuf,int off,int len)写入字符数组的一部分，off开始索引，len写入字符的个数
        void write(String str)写入字符串
        void write(String str,int off,int len)写入字符串数组的一部分，off开始索引，len写入字符的个数
        void flush()刷新该流的缓冲
        void close()关闭此流，但要先刷新它

    构造方法:
        OutputStreamWriter(OutputStream out) 创建使用默认的字符编码的OutputStreamWriter
        OutputStreamWriter(OutputStream out,String charsetName) 创建使用指定字符集的 OutputStreamWriter
        参数：
            OutputStream out :字节输出流，可以用来写转换之后的字节到文件中
            String charsetName：指定编码表名称，不区分大小写，默认使用UTF-8

    使用步骤:
        1.创建OutputStreamWriter对象，构造方法中传递字节输入流和指定的编码表名称
        2.使用OutputStreamWriter对象中的writer,将字符转换为字节存储到缓冲区中
        3.使用OutputStreamWriter对象中的方法flush，把内存缓冲区的字节刷新到文件中
        4.资源释放
 */
public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
        write_utf_8();
        write_gbk();
    }

    /*
        使用转换流OutputStreamWriter写utf-8格式的文件
     */
    private static void write_utf_8() throws IOException {
        //1.创建OutputStreamWriter对象，构造方法中传递字节输入流和指定的编码表名称
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\学习\\Java_study\\src\\IO\\b.txt"));
        //2.使用OutputStreamWriter对象中的writer,将字符转换为字节存储到缓冲区中
        osw.write("你好");
        //3.使用OutputStreamWriter对象中的方法flush，把内存缓冲区的字节刷新到文件中
        osw.flush();
        //4.资源释放
        osw.close();
    }

    /*
        使用转换流OutputStreamWriter写gbk格式的文件
     */
    private static void write_gbk() throws IOException {
        //1.创建OutputStreamWriter对象，构造方法中传递字节输入流和指定的编码表名称
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\学习\\Java_study\\src\\IO\\b.txt"),"GBK");
        //2.使用OutputStreamWriter对象中的writer,将字符转换为字节存储到缓冲区中
        osw.write("你好");
        //3.使用OutputStreamWriter对象中的方法flush，把内存缓冲区的字节刷新到文件中
        osw.flush();
        //4.资源释放
        osw.close();
    }

}
