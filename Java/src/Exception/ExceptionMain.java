package Exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
    异常：在程序执行过程过，出现的非正常的情况，最终会导致JVM的非正常停止
        在Java等面向对象的编程语言中，异常本身就是一个类，产生异常就是创建异常对象并抛出一个异常对象，Java处理异常的方式是中断处理

    Throwable:是Java语言中所有错误或异常的超类
            Error:不能处理，只能尽量避免 ()
            Exception:由于使用不当导致，可以避免的
                    Exception:编译器异常，进行编译Java程序出现的问题
                    RuntimeException:运行期异常，Java程序运行过程中出现的问题
 */
public class ExceptionMain {
    public static void main(String[] args) {
        //Exception:编译器异常，进行编译Java程序出现的问题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化日期
        Date date = null;
        try {
            date = sdf.parse("1999-09-99");//把字符串格式的日期转换为Date格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);

        //RuntimeException:运行期异常，Java程序运行过程中出现的问题
        int[] arr = {1,2,3};
        try {
            //可能会出现异常的代码
            System.out.println(arr[3]);
        }catch (Exception e){
            //异常的处理逻辑
            e.printStackTrace();
        }

        /*
            int[] arr = new int[1024*1024*1024];
            Error:错误
            OutOfMemoryError:内存溢出错误
         */

        System.out.println("后续代码");
    }
}
