package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/*
    java.io.ObjectInputStream extends InputStream
    对象的反序列化流：把文件中保存的对象以流的方式读取出来使用

    构造方法:
        ObjectInputStream(InputStream in) 创建从指定 InputStream 读取的 ObjectInputStream
        参数:
            InputStream in :字节输入流

    特有的成员方法
        Object readObject():从 ObjectInputStream 读取对象

    使用步骤:
        1.创建ObjectInputStream对象，构造方法中传递字节输入流
        2.使用ObjectInputStream对象中的readObject方法读取保存对象的文件
        3.释放资源
        4.使用对象

    readObject方法声明抛出了ClassNotFoundException (class文件找不到异常)

    当不存在对象的class文件时抛出异常
    反序列化的前提：
        1.类必须实现 Serializable 接口
        2.必须存在类对应的class文件


 */
public class ObjectInputStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //1.创建ObjectInputStream对象，构造方法中传递字节输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\学习\\Java_study\\src\\IO\\person.txt"));
        //2.使用ObjectInputStream对象中的readObject方法读取保存对象的文件
        Object o = ois.readObject();
        //3.释放资源
        ois.close();
        //4.使用对象
        System.out.println(o.toString());
        Person p = (Person)o;
        System.out.println(p.getName() + p.getAge());
    }
}
