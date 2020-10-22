package IO;

import java.io.FileWriter;
import java.io.IOException;

/*
    java.io.Writer 字符输出流，是所有字符输出流的最顶层的父类，是一个抽象类

    共性的成员方法:
        void write(int c)写入单个字符
        void write(char[] cbuf)写入字符数组
        abstract void write(char[] cbuf,int off,int len)写入字符数组的一部分，off开始索引，len写入字符的个数
        void write(String str)写入字符串
        void write(String str,int off,int len)写入字符串数组的一部分，off开始索引，len写入字符的个数
        void flush()刷新该流的缓冲
        void close()关闭此流，弹药先刷新它

    java.io.FileWriter extends OutputStreamWriter extends Writer
    FileWriter:文件字符输出流
    作用：把内存中字符数据写入到文件中
    构造方法:
        FileWriter(File file)根据给定的 File 对象构造一个 FileWriter 对象
        FileWriter(String fileName)根据给定的文件名构造一个 FileWriter对象
        参数:写入数据的目的地
            String fileName:文件的路径
            File file:文件
        作用：
            1.创建一个FileWriter对象
            2.根据构造方法中传递的文件 /文件路径，创建文件
            3.把FileWriter对象指向创建好的文件

    使用步骤:
        1.创建一个FileWrite对象，构造方法中绑定要写入数据的目的地
        2.使用FileWriter中的方法write,把数据写入到内存缓冲区中(字符转换为字节的过程)
        3.使用FileWriter中的方法flush,把内存缓冲区中的数据，刷新到文件中
        4.释放资源(会先把内存缓冲区的数据刷新到文件中)

    flush和close的区别
        - flush：刷新缓冲区，流对象可以继续使用
        - close：先刷新缓冲区，然后通知系统释放资源，流对象不可以再使用了

    续写和换行:
        续写:追加写，使用两个参数的构造方法
            FileWriter(String fileName,boolean append)
            FileWriter(File file,boolean append)
            参数：
                String fileName,File file:写入数据的目的地
                boolean append:续写开关 true：不会创建新的文件覆盖原文件，可以续写，false：创建新的文件覆盖原文件
        换行:换行符号
        Windows: \r\n
        Linux:/n
        Mac:/r
 */
public class WriterDemo {
    public static void main(String[] args) throws IOException {
        //1.创建一个FileWrite对象，构造方法中绑定要写入数据的目的地
        FileWriter fw = new FileWriter("D:\\学习\\Java_study\\src\\IO\\b.txt",true);

        //2.使用FileWriter中的方法write,把数据写入到内存缓冲区中(字符转换为字节的过程)
        //void write(int c)写入单个字符
        fw.write(97);
        //void write(char[] cbuf)写入字符数组
        char[] cs = {'a','b','c','d'};
        fw.write(cs);
        //abstract void write(char[] cbuf,int off,int len)写入字符数组的一部分，off开始索引，len写入字符的个数
        fw.write(cs,0,2);
        //void write(String str)写入字符串
        fw.write("hello world");
        //void write(String str,int off,int len)写入字符串数组的一部分，off开始索引，len写入字符的个数
        fw.write("hello world",0,5);

        //3.使用FileWriter中的方法flush,把内存缓冲区中的数据，刷新到文件中
        fw.flush();

        //4.释放资源(会先把内存缓冲区的数据刷新到文件中)
        fw.close();
    }
}
