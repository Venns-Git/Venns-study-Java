package IO;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/*
    java.util.Properties集合 extends Hashtable<k,v> implements Map<k,v>
    Properties集合是唯一和IO流相结合的集合
    可以使用集合中的store方法，把集合中的临时数据，持久化的写入到硬盘中存储
    可以使用集合中的load方法，把硬盘中保存的文件(键值对)，读取到集合中使用
    是一个双列集合，key和value默认都是字符串

 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        show01();
        show02();
        show03();
    }

    /*
        使用集合存储数据，遍历取出集合中的数据
        操作字符串的特有方法：
            Object setProperty(String key,String value)调用 Hashtable 的方法 put
            Object getProperty(String)通过key找到value值，此方法相当于map集合中的get(key)方法
            Set<String> stringPropertyNames()返回此属性列表中的键集，相当于Map中的KeySet方法
     */
    private static void show01() {
        //创建Properties集合对象
        Properties prop = new Properties();
        //使用setProperty往集合中添加数据
        prop.setProperty("小明","123");
        prop.setProperty("小红","456");
        //使用StringPropertyNames把集合中的键取出，存储到set集合中
        Set<String> set = prop.stringPropertyNames();
        //遍历Set集合，取出Properties集合的每一个键
        for (String key:set){
            //使用getProperty方法通过key获取value
            String value = prop.getProperty(key);
            System.out.println(key+":"+value);
        }
    }

    /*
        可以使用集合中的store方法，把集合中的临时数据，持久化的写入到硬盘中存储
        void store(OutputStream out,String comments)
        void store(Writer writer,String comments)
        参数：
            OutputStream out:字符输出流，不能写入中文
            Writer writer:字符输出流，可以写中文
            String comments:注释，用来解释说明保存文件是做什么用的，不能使用中文

        使用步骤:
            1.创建一个Properties集合对象，添加数据
            2.创建字节输出流/字符输出流，构造方法中绑定要输出的目的地
            3.使用集合中的store方法
            4.释放资源
     */
    private static void show02() throws IOException {
        //1.创建一个Properties集合对象，添加数据
        Properties prop = new Properties();
        prop.setProperty("小明","123");
        prop.setProperty("小红","456");
        //2.创建字节输出流/字符输出流，构造方法中绑定要输出的目的地
        FileWriter fw = new FileWriter("D:\\学习\\Java_study\\src\\IO\\prop.txt");
        //3.使用集合中的store方法
        prop.store(fw,"save data");
        prop.store(new FileOutputStream("D:\\学习\\Java_study\\src\\IO\\prop.txt"),"save date2");
        //4.释放资源
        fw.close();
    }

    /*
        可以使用集合中的load方法，把硬盘中保存的文件(键值对)，读取到集合中使用
            void load(InputStream inStream)
            void load(Reader reader)
            参数:
                InputStream inStream:字节输入流，不能读取含有中文的键值对
                Reader reader:字符输入流，能读取含有中文的键值对
            使用步骤:
                1.创建Properties集合对象
                2.使用load方法读取保存键值对的文件
                3.遍历Properties集合
            注意:
                1.存储键值对的文件中，键与值默认的链接符号可以使用=,空格(其他符号)
                2.存储键值对的文件中，可以使用#进行注释，被注释的键值对不会再被读取
                3.存储键值对的文件中，键与值默认都是字符串，不用再加上引号
     */
    private static void show03() throws IOException {
        //1.创建Properties集合对象
        Properties prop = new Properties();
        //2.使用load方法读取保存键值对的文件
        prop.load(new FileReader("D:\\学习\\Java_study\\src\\IO\\prop.txt"));
        //3.遍历Properties集合
        Set<String> set = prop.stringPropertyNames();
        for (String key:set){
            String value = prop.getProperty(key);
            System.out.println(key+":"+value);
        }
    }
}
