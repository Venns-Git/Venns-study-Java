package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/*
    java.io.ObjectOutputStream extends OutputStream
    对象的序列化流:把对象以流的形式写入到文件中保存

    构造方法：
        ObjectOutputStream(OutputStream out) 创建写入指定OutputStream 的 ObjectOutputStream
        参数：
            OutputStream out：字节输出流

    特有的成员方法:
        void writeObject(Object obj) 将指定的对象写入 ObjectOutputStream

    使用步骤：
        1.创建一个ObjectOutputStream对象，构造方法中传递字节输出流
        2.使用ObjectOutputStream对象中的writeObject方法，把对象写入文件中
        3.释放资源
 */
public class ObjectOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        //1.创建一个ObjectOutputStream对象，构造方法中传递字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\学习\\Java_study\\src\\IO\\person.txt"));
        //2.使用ObjectOutputStream对象中的writeObject方法，把对象写入文件中
        oos.writeObject(new Person("小明",18));
        //3.释放资源
        oos.close();
    }
}
