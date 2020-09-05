package Exception;

import java.util.List;

/*
    异常的注意事项
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        /*
            多个异常的使用捕获又该如果处理？
            1.多个异常分别处理
            2.多个异常一次捕获，多次处理
            3.多个异常一次捕获，一次处理
         */
        int[] a = {1,2,3};
        System.out.println(a[3]);//ArrayIndexOfBoundsException 数组下标越界异常

        List<Integer> list = List.of(1,2,3);
        System.out.println(list.get(3));//抛出异常

        //1.多个异常分别处理
        try{
            int[] a1 = {1,2,3};
            System.out.println(a1[3]);//ArrayIndexOfBoundsException 数组下标越界异常
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }

        try {
            List<Integer> list1 = List.of(1,2,3);
            System.out.println(list1.get(3));//抛出异常
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        //2.多个异常一次捕获，多次处理
        /*
            一个try多个catch注意事项:
                catch里面定义的异常变量，如果有子父类关系，子类的异常变量必须写在上面
                ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException
         */
        try {
            int[] a2 = {1,2,3};
            System.out.println(a2[3]);
            List<Integer> list2 = List.of(1,2,3);
            System.out.println(list2.get(3));
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        //3.多个异常一次捕获，一次处理
        try {
            int[] a3 = {1,2,3};
            System.out.println(a3[3]);
            List<Integer> list3 = List.of(1,2,3);
            System.out.println(list3.get(3));
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        /*
            如果finally有return语句，永远返回finally中的结果，避免该情况
         */
        int x = getA();
        System.out.println(x);
    }

    /*
        定义一个方法 返回变量a的值
     */
    public static int getA(){
        int a = 10;
        try {
            return  a;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            //一定会执行的代码
            a = 100;
            return a;
        }
    }
}
