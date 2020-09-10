package FileClass;

import java.io.File;

/*
    File类中的常用方法

        获取功能的方法:
            public String getAbsolutePath(); 返回此File的绝对路径
            public String gerPath(); 将此File转化为路径名字符串
            public String getName(); 返回由此File表示的文件或目录名称
            public long length(); 返回此File表示的文件长度
 */
public class FileMethod01 {
    public static void main(String[] args) {
//        show01();
//        show02();
//        show03();
        show04();
    }

    /*
        public String getAbsolutePath(); 返回此File的绝对路径
     */
    private static void show01() {
        File f1 = new File("d:\\学习\\Java_study\\Thread");
        System.out.println(f1.getAbsolutePath());

        File f2 = new File("Thread");
        System.out.println(f2.getAbsolutePath());
    }

    /*
        public String gerPath(); 将此File转化为路径名字符串
        toString方法调用的就是getPath方法
     */
    private static void show02() {
        File f1 = new File("d:\\学习\\Java_study\\Thread");
        System.out.println(f1.getPath());

        File f2 = new File("Thread");
        System.out.println(f2.getPath());
    }

    /*
        public String getName(); 返回由此File表示的文件或目录名称
        获取的就是传递路径的结尾部分(文件/文件夹)
     */
    private static void show03(){
        File f1 = new File("d:\\学习\\src\\Java_study\\Thread\\ThreadMain.java");
        String name1 = f1.getName();
        System.out.println(name1);

        File f2 = new File("d:\\学习\\src\\Java_study\\Thread");
        String name2 = f2.getName();
        System.out.println(name2);
    }

    /*
        public long length(); 返回此File表示的文件长度
        获取的是构造方法指定的文件的大小，以字节为单位
        注意:
            文件夹是没有大小概念的，不能获取文件夹的大小
            如果构造方法中给出的路径不存在，那么length方法返回0
     */
    private static void show04(){
        File f1 = new File("D:\\学习\\Java_study\\src\\WrapperClass.java");
        long l1 = f1.length();
        System.out.println(l1);

        File f2 = new File("D:\\学习\\Java_study\\src\\WrapperClass1.java");
        System.out.println(f2.length());

        File f3 = new File("D:\\学习\\Java_study\\src");
        System.out.println(f3.length());
    }
}
