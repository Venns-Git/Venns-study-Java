package SystemAndStringBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
    java.lang.System
    常用方法：
        public static long currentTimeMillis();返回毫秒为单位的当前时间
        public static void arraycopy(Object src,int srcPos,Object dest,int desPos,int length); 将数组中指定的数据拷贝到另外一个数组中
 */
public class SystemDemo {
    public static void main(String[] args) {
        demo02();
    }

    /*
        public static long currentTimeMillis();返回毫秒为单位的当前时间
        用来测试程序的效率
     */
    private static void demo01(){
        //程序执行前，获取一次毫秒值
        long s = System.currentTimeMillis();
        //执行for循环
        for (int i = 0; i < 9999; i++) {
            System.out.println(i);
        }
        //程序执行后，再获取一次毫秒值
        long e = System.currentTimeMillis();
        System.out.println(e-s);
    }
    /*
        public static void arraycopy(Object src,int srcPos,Object dest,int desPos,int length);
        将数组中指定的数据拷贝到另外一个数组中
        参数：
            Object src:源数组
            int srcPos:源数组中起始位置
            Object dest:目标数组
            int desPos:目标数组中起始位置
            ine length:要复制的数组元素的数量
    */
    private static void demo02(){
        //定义数组
        int[] src = {1,2,3,4,5};//源数组
        int[] dest = {6,7,8,9,10};//目标数组

        System.out.println("复制前："+ Arrays.toString(dest));
        //使用arraycopy方法
        System.arraycopy(src,0,dest,0,3);
        System.out.println("复制后:"+Arrays.toString(dest));
    }
}
