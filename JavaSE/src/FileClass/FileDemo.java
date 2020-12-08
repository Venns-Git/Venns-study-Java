package FileClass;

import java.io.File;

/*
    java.io.file类
    文件和目录路径名的抽象表示形式
    java把电脑中的文件和文件夹(目录)封装为了一个file类
    file类是一个与系统无关的类，任何操作系统都可以使用这个类中的方法

    file：文件 directory：文件夹/目录 path：路径

    静态成员变量:
    static String pathSeparator 与系统有关的路径分隔符
    static char pathSeparatorChar 与系统有关的路径分隔符
    static String separator 与系统有关的默认名称分隔符
    static char separatorChar 与系统有关的默认名称分隔符
    操作路径:路径不能写死了

    构造方法:
        File(String pathname) 通过给定路径名字符串转化为抽象路径名来创建一个新 File 实例
        File(String parent,String child) 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File实例
        File(file parent, String child) 根据 parent 抽象路径名和 child 路径名字符串创建一个新的File实例
 */
public class FileDemo {
    public static void main(String[] args) {
        String pathSeparator = File.pathSeparator;
        System.out.println(pathSeparator);//路径分隔符 Windows:分号 Linux：冒号

        String separator = File.separator;
        System.out.println(separator); //文件名称分隔符 Windows:反斜杠\ Linux:正斜杠/

        //File类的构造方法
        show01();
        show02("d:","学习//Java_study");
        show03();
    }

    /*
        File(String pathname) 通过给定路径名字符串转化为抽象路径名来创建一个新 File 实例
            参数:
                String pathname :字符串的路径名称
                路径可以是以文件结尾，也可以是以文件夹结尾
                路径可以是相对路径，也可以是绝对路径
                路径可以是存在的，也可以是不存在的
                创建File类对象，只是把字符串路径封装为File对象，不考虑路径的真假情况
     */
    private static void show01() {
        File f1 = new File("D:\\学习\\Java_study");
        System.out.println(f1); //重写了Object类的toString方法

        File f2 = new File("..\\Thread");
        System.out.println(f2);
    }

    /*
        File(String parent,String child) 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File实例
        参数:把路径分成了两部分
            String parent:父路径
            String child:子路径
        好处:
            父路径和子路径，可以单独书写，使用起来非常灵活，父路径和子路径都可以变化

     */
    private static void show02(String parent, String child) {
        File file = new File(parent,child);
        System.out.println(file);
    }

    /*
        File(file parent, String child) 根据 parent 抽象路径名和 child 路径名字符串创建一个新的File实例
        参数:把路径分成了两部分
            File parent:父路径
            String child：子路径
        好处:
            父路径和子路径，可以单独书写，使用起来非常灵活，父路径和子路径都可以变化
            父路径是File类型，可以使用File的方法对路径进行一些操作，再使用路径创建对象
     */
    private static void show03() {
        File parent = new File("d:\\");
        File file = new File(parent,"学习\\Java_study");
        System.out.println(file);
    }
}
