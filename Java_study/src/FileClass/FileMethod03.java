package FileClass;

import java.io.File;
import java.io.IOException;

/*
    创建删除功能的方法
        public boolean createNewFile(); 当且仅当具有该名称的文件尚不纯在时，创建一个新的空文件
        public boolean delete(); 删除由此File表示的文件或目录
        public boolean mkdir(); 创建由此File表示的目录
        public boolean mkdirs(); 创建由此File表示的目录，包括任何必需但不存在的父目录
 */
public class FileMethod03 {

    public static void main(String[] args) throws IOException {
//        show01();
//        show02();
        show03();
    }

    /*
        public boolean createNewFile(); 当且仅当具有该名称的文件尚不纯在时，创建一个新的空文件
        创建文件的路径和名称在构造方法中给出(构造方法的参数)
        返回值:
            true:文件不存在，创建文件，返回true
            false:文件存在，不会创建，返回false
        注意:
            1.此方法只能创建文件，不能创建文件夹
            2.创建文件的路径必须存在，否则会抛出异常

        声明抛出了IOException，我们调用这个方法就必须处理这个异常，要么throws，要么try...catch
    */
    private static void show01()  throws IOException {
        File f1 = new File("D:\\学习\\Java_study\\src\\FileClass\\1.txt");
        boolean b1 = f1.createNewFile();
        System.out.println("b1:"+b1);

        File f2 = new File("src\\FileClass\\2.txt");
        System.out.println(f2.createNewFile());
    }

    /*
        public boolean mkdir(); 创建单级空文件夹
        public boolean mkdirs(); 既可以创建单级空文件夹，也可以创建多级文件夹
        返回值:
            true:文件夹不存在，创建文件，返回true
            false:文件夹存在，不会创建，返回false;构造方法中路径不存在也返回false
        注意:
        1.此方法只能创建文件夹，不能创建文件
     */
    private static void show02(){
        File f1 = new File("D:\\学习\\Java_study\\src\\FileClass\\aaa");
        boolean b1 = f1.mkdir();

        File f2 = new File("D:\\学习\\Java_study\\src\\FileClass\\bbb\\ccc");
        System.out.println(f2.mkdirs());
    }

    /*
        public boolean delete(); 删除由此File表示的文件或目录
        此方法，可以删除构造方法路径中给出的文件/文件夹
        返回值：
            true：文件/文件夹删除成功
            false:文件夹中有内容，不会删除;构造方法中的路径不存在
        注意:
            delete方法是直接在硬盘中删除文件/文件夹，不走回收站
     */
    private static void show03(){
        File f1 = new File("D:\\学习\\Java_study\\src\\FileClass\\aaa");
        boolean b1 = f1.delete();
        System.out.println(b1);
    }
}
