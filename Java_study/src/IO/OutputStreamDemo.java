package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
    java.lang,OutputStream:此抽象类是表示输出字节流的所有类的超类
    定义了一些子类共性的成员方法:
        - public void close():关闭此输出流并释放于此流相关的任何系统资源
        - public void flush(): 刷新此输出流并强制任何缓冲的输出字节被写出
        - public void write(byte[] b):将b.length()字节从指定的字节输出写入此输出流
        - public void write(byte[] b,int off,int len):从指定的字节数组写入len字节，从偏移量off开始输出到此输出流
        - public abstract void write(int b):将指定的字节输出流

       java.io.FileOutputStream extends OutputStream
       FileOutputStream:文件字节输出流
       作用:把内存中的数据写入到硬盘的文件中

       构造方法:
            FileOutputStream(String name); 创建一个向具有此指定名称的文件中写入数据的输出流
            FileOutputStream(File file);    创建一个向指定File对象表示的文件中写入数据的输出流
            参数:写入数据的目的地
                String name:目的地是一个文件的路径
                File file：目的地是一个文件
            构造方法的作用:
                1.创建一个FileOutputStream对象
                2.会根据构造方法中传递的文件或者文件路径，创建一个空的文件
                3.会把FileOutputStream对象指向创建号的文件

      写入数据的原理(内存 --> 硬盘)
      java程序 --> JVM(java虚拟机) --> OS(操作系统) --> OS调用写数据的方法 --> 把数据写入到文件中

      字节输出流的使用步骤:
        1.创建FileOutputStream对象，构造方法中传递写入数据目的地
        2.调用FileOutputStream对象中的方法write，把数据写入到文件中
        3.释放资源(流使用会占用一定的内存，使用完毕要把内存情况，提高程序效率)
 */
public class OutputStreamDemo {
    public static void main(String[] args) throws IOException {
        //1.创建FileOutputStream对象，构造方法中传递写入数据目的地
        FileOutputStream fos = new FileOutputStream("D:\\学习\\Java_study\\src\\IO\\a.txt");
        //2.调用FileOutputStream对象中的方法write，把数据写入到文件中
        //public abstract void write(int b):将指定的字节输出流
        fos.write(97);
        //3.释放资源(流使用会占用一定的内存，使用完毕要把内存情况，提高程序效率)
        fos.close();


        //一次写多个字节
        //1.创建FileOutputStream对象，构造方法中传递写入数据目的地
        FileOutputStream fos1 = new FileOutputStream(new File("D:\\学习\\Java_study\\src\\IO\\b.txt"));
        //2.调用FileOutputStream对象中的方法write，把数据写入到文件中
        /*
            public void write(byte[] b):将b.length()字节从指定的字节输出写入此输出流
            一次写多个字节:
                如果写的第一个字节是正数(0-127),那么显示的时候会查询ASCII表
                如果写的第一个字节是负数，那第一个字节回合第二个字节，2个字节组成一个中文显示，查询系统默认码表(GBK)
         */
        byte[] bytes = {65,66,67,68,69};//ABCDE
        //byte[] bytes = {-65,-66,-67,68,69};//烤姎E
        fos1.write(bytes);

        /*
            public void write(byte[] b,int off,int len):从指定的字节数组写入len字节，从偏移量off开始输出到此输出流
                int off:数组的开始索引
                int len:写几个字节
         */
        fos1.write(bytes,1,2);//BC

        /*
            写入字符串的方法：可以使用String类中的方法把字符串转化为字节数组
                byte[] getBytes() 把字符串转换为字节数组
         */
        byte[] bytes1 = "hello world".getBytes();
        fos1.write(bytes1);
        fos1.close();


        //追加写/续写:使用两个参数的构造方法
         /*   FileOutputStream(String name,boolean append)
                创建一个向具有指定name的文件中写入数据的输出文件流
              FileOutputStream(File file,boolean append)
                创建一个向指定File对象表示的文件中写入数据的文件输出流

                参数：
                    String name,File file:写入数据的目的地
                    boolean append:追加写开关
                        true:创建对象不会覆盖原文件，继续在文件的末尾追加写数据
                        false:创建一个新文件，覆盖源文件
                写换行:写换行符
                windows:\r\n
                linux:/n
                mac:/r
          */
        FileOutputStream fos2 = new FileOutputStream("D:\\学习\\Java_study\\src\\IO\\b.txt",true);
        for (int i = 1; i <= 10; i++) {
            fos2.write("hello world".getBytes());
            fos2.write("\r\n".getBytes());
        }
        fos2.close();
    }

}
