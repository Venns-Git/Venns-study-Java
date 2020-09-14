package IO;

import java.io.FileReader;
import java.io.IOException;

/*
    java.io.Reader 字符输入流，是字符输入流最顶层的父类，定义了一些共性的成员方法，是一个抽象类

    共性的成员方法:
        int read() 读取单个字符并返回
        int read(char[] cbuf)一次读取多个字符，将字符存入数组
        void close() 关闭该流并释放与之关联的所有资源

    java.io.FileReader extends InputStreamReader extends Reader
    FileReader:文件字符输入流
    作用:把硬盘文件中的数据以字符的方式读取到内存中

    构造方法:
        FileReader(String fileName)
        FileReader(File file)
        参数:读取文件的数据源
            String fileName :文件的路径
            File file:文件
        作用：
            1.会创建一个FilerReader对象
            2.会把FileReader对象指向要读取的文件

    FileReader使用步骤：
        1.创建FileReader对象，构造方法中绑定要读取的数据源
        2.使用FileReader对象中的方法read读取文件
        3.释放资源
 */
public class ReaderDemo {
    public static void main(String[] args) throws IOException {
        //1.创建FileReader对象，构造方法中绑定要读取的数据源
        FileReader fr = new FileReader("D:\\学习\\Java_study\\src\\IO\\b.txt");
        //2.使用FileReader对象中的方法read读取文件
        //int read() 读取单个字符并返回
        int len = 0;
        while((len = fr.read()) != -1){
            System.out.print((char)len);
        }

        //int read(char[] cbuf)一次读取多个字符，将字符存入数组
        char[] cs = new char[1024];//存储读取到的多个字符
        int len1 = 0;//记录的是每次读取的有效字符个数
        while((len = fr.read(cs)) != -1){
            System.out.print(new String(cs,0,len));
        }

        //3.释放资源
        fr.close();
    }
}
