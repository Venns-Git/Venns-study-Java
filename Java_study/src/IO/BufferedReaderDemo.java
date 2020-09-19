package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
    java.i0.BufferedReader extends Reader

    继承自父类的成员方法:
        int read() 读取单个字符并返回
        int read(char[] cbuf)一次读取多个字符，将字符存入数组
        void close() 关闭该流并释放与之关联的所有资源

    构造方法:
        BufferedReader(Reader in) 默认大小缓冲区
        BufferedReader(Reader in,int sz)指定大小缓冲区
        参数:
            Reader in:字符输入流
                可以传递FileReader,缓冲流会给FileReader增加一个缓冲区,提高读取效率

    特有的成员方法:
        String readLine() 读取一行数据
        返回值：返回改行内容，不包括终止符，如果已达流的末尾，则返回null

    使用步骤:
        1.创建字符缓冲输入流对象，构造方法中传递字符输入流
        2.使用字符缓冲数输入流对象的read/readLine读取文本
        3.释放资源
 */
public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        //1.创建字符缓冲输入流对象，构造方法中传递字符输入流
        BufferedReader br = new BufferedReader(new FileReader("D:\\学习\\Java_study\\src\\IO\\b.txt"));
        //2.使用字符缓冲数输入流对象的read/readLine读取文本
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        //3.释放资源
        br.close();
    }
}
