package IO;

import java.io.FileWriter;
import java.io.IOException;

/*
    在jdk1.7之前使用 try catch finally处理流中的异常
    格式：
        try{
            可能会产生异常的代码
        }catch(异常类的变量 变量名){
            异常的处理逻辑
       }finally{
            一定会执行的代码
            资源释放
       }

   jdk7的新特性
   在try的后面可以增加一个(),在括号中可以定义流对象
   那么这个流对象的作用域就在try中有效
   try中的代码执行完毕，会自动把流对象释放,就不用写finally
   格式:
        try(定义流对象;定义流对象){
            可能会产生异常的代码
        }catch(异常类的变量 变量名){
            异常的处理逻辑
       }

   jdk9的新特性
   try的前边可以定义流对象
   try后边的括号中可以直接引入流对象的名称
   在try代码块执行完毕后，流对象也可以释放掉,不用写finally
   格式:
        A a = new A();
        B b = new B();
        try(a,b){
            可能会产生异常的代码
        }catch(异常类的变量 变量名){
            异常的处理逻辑
       }
 */
public class TryCatch {
    public static void main(String[] args) throws IOException {

        //jdk1.7之前
        //提高变量fw的作用域，让finally可以使用
        //变量定义是可以没有值，使用时必须有值
        FileWriter fw = null;
        try{
            fw = new FileWriter("D:\\学习\\Java_study\\src\\IO\\b.txt",true);
            fw.write("hello world",0,5);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //创建对象失败，fw的默认值是null，null是不能调用方法的，会抛出空指针异常，需要判断
            if (fw != null)
            {
                try {
                    //fw.close()方法声明抛出IOException，必须处理这个异常对象，要么throws,要么 try catch
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //jdk7之后
            try( FileWriter fw1 =  new FileWriter("D:\\学习\\Java_study\\src\\IO\\b.txt",true)){
                fw1.write("hello world",0,5);
            }catch (IOException e){
                e.printStackTrace();
            }

            //jdk9新特性
            FileWriter fw2 =  new FileWriter("D:\\学习\\Java_study\\src\\IO\\b.txt",true);
            try(fw2){
                fw2.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
