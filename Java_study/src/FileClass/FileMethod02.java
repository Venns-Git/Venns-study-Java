package FileClass;

import java.io.File;

/*
    判断功能的方法:
        public boolean exists(); 此File表示的文件或目录是否实际存在
        public boolean isDirectory(); 此File表示的是否为目录
        public boolean isFile(); 此File表示的是否为文件
 */
public class FileMethod02 {
    public static void main(String[] args) {
        show01();
        show02();
        show03();
    }

    /*
        public boolean exists(); 此File表示的文件或目录是否实际存在
     */
    private static void show01() {
        File f1 = new File("D:\\学习\\Java_study\\src\\WrapperClass.java");
        System.out.println(f1.exists());

        File f2 = new File("D:\\学习\\Java_study\\src\\WrapperClass1.java");
        System.out.println(f2.exists());
    }

    /*
        public boolean isDirectory(); 此File表示的是否为目录
        注意：
            使用前提路径必须存在
     */
    private static void show02() {
        File f1 = new File("D:\\学习\\Java_study\\src");
        System.out.println(f1.isDirectory());

        File f2 = new File("D:\\学习\\Java_study\\src\\WrapperClass1.java");
        System.out.println(f2.isDirectory());
    }

    /*
        public boolean isFile(); 此File表示的是否为文件
        注意：
            使用前提路径必须存在
     */
    private static void show03() {
        File f1 = new File("D:\\学习\\Java_study\\src");
        System.out.println(f1.isFile());

        File f2 = new File("D:\\学习\\Java_study\\src\\WrapperClass.java");
        System.out.println(f2.isFile());
    }

}
