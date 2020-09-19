package IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
    java.io.BufferedReader extends Writer
    字符缓冲输出流

    继承自父类的共性成员方法:
        void write(int c)写入单个字符
        void write(char[] cbuf)写入字符数组
        abstract void write(char[] cbuf,int off,int len)写入字符数组的一部分，off开始索引，len写入字符的个数
        void write(String str)写入字符串
        void write(String str,int off,int len)写入字符串数组的一部分，off开始索引，len写入字符的个数
        void flush()刷新该流的缓冲
        void close()关闭此流，但要先刷新它

    构造方法:
        BufferedWriter(Writer out) 创建一个默认大小缓冲区的缓冲字符输入流
        BufferedWriter(Writer out,int sz)创建一个给定大小的缓冲区
        参数:
            Writer out:字符输出流
                我们可以传递FileWriter,缓冲流会给FilerWriter增加一个缓冲区，提高写入效率
            int sz:缓冲区大小，不写默认大小

    特有的成员方法:
        void newLine() 写一个行分隔符,会根据不同的操作系统，获取不同的行分隔符

    使用步骤:
        1.创建字符缓冲输出流对象，构造方法中传递字符输出流
        2.调用字符缓冲输出流中的方法writer，把数据写入到内存缓冲区中
        3.调用字符缓冲输出流中的方法flush，把内存缓冲区的数据，刷新到文件中
        4.释放资源
 */
public class BufferedWriterDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建字符缓冲输出流对象，构造方法中传递字符输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\学习\\Java_study\\src\\IO\\b.txt"));
        //2.调用字符缓冲输出流中的方法writer，把数据写入到内存缓冲区中
        for (int i = 0; i < 10; i++) {
            bw.write("hello world");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
