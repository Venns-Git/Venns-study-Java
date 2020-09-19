package IO;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/*
    java.io.printStream:打印流
        为其他输出流添加了功能，使它们能够方便的打印各种数据值表现形式
    特点:
        1.只负责数据的输出，不负责数据的读取
        2.永远不会抛出IOException
        3.有特有的方法，print，println

    构造方法:
        PrintStream(File file):输出的目的地是一个文件
        PrintStream(OutputStream out)：输出目的地是一个字符输出流
        PrintStream(String fileName)：输出目的地是一个文件路径

    PrintStream extends OutputStream
    继承自父类的成员方法：
        - public void close():关闭此输出流并释放于此流相关的任何系统资源
        - public void flush(): 刷新此输出流并强制任何缓冲的输出字节被写出
        - public void write(byte[] b):将b.length()字节从指定的字节输出写入此输出流
        - public void write(byte[] b,int off,int len):从指定的字节数组写入len字节，从偏移量off开始输出到此输出流
        - public abstract void write(int b):将指定的字节输出流

    注意：
        如果使用继承自父类的write方法写数据，那么查看数据的时候会查看编码表 97 -> a
        如果使用自己特有的方法print/println方法，写的数据原样输出 97 -> 97

    可以改变输出语句的目的地(打印流的流向),默认在控制台输出
    使用System.setOut方法改变输出语句的目的地改为参数中传递的打印流的目的地
        static void setOut(PrintStream out) 重新分配“标准”输出流
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        //1.创建打印流PrintStream对象,构造方法中绑定要输出的目的地
        PrintStream ps = new PrintStream("D:\\学习\\Java_study\\src\\IO\\print.txt");

        //使用继承自父类的write方法写数据，那么查看数据的时候会查看编码表
        ps.write(97);
        //使用自己特有的方法print/println方法，写的数据原样输出
        ps.println(97);

        //释放资源
        ps.close();

        System.out.println("我是在控制台输出的");
        PrintStream ps1 = new PrintStream("D:\\学习\\Java_study\\src\\IO\\print.txt");
        System.setOut(ps);//把输出语句的目的地改变为打印流的目的地
        System.out.println("我在打印流的目的地输出");
        ps1.close();
    }
}
