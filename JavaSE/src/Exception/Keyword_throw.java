package Exception;
/*
    throw关键字
    作用：
        可以使用throw关键字在指定的方法中抛出指定的异常
    使用格式：
        throw new xxxException("异常产生的原因");
    注意:
        1.throw关键字必须写在方法的内部
        2.throw关键字后边new的对象必须是Exception或者Exception的子类对象
        3.throw关键字抛出指定的异常对象，我们就必须处理处理这个异常对象
            1).throw关键字后面创建的是RuntimeException或者RuntimeException的子类对象，可以不处理，交给JVM
            2).throw关键字后面创建的是编译异常，我们就必须处理这个异常，那么throw，要么try...catch
 */
public class Keyword_throw {
    public static void main(String[] args) {

        int[] arr1 = null;
        int e1 = getElement(arr1,0);
        System.out.println(e1);

        int[] arr2 = new int[3];
        int e2 = getElement(arr2, 3);
        System.out.println(e2);
    }
    /*
        定义一个方法，获取数组索引处的元素
        参数：
            int[] arr
            int index
     */
    public static int getElement(int[] arr,int index){
        /*
            对传递过来的参数数组进行合法检验
                如果为null，抛出空指针异常(运行期异常),默认交给JVM处理
            对index进行合法性检验
                如果index范围不在数组的索引范围内，就抛出数组越界异常(运行期异常),默认交给JVM处理

         */
        if (arr == null){
            throw new NullPointerException("传递数组为null");
        }
        if (index < 0 || index > arr.length-1){
            throw new ArrayIndexOutOfBoundsException("数组索引越界");
        }
        int ele = arr[index];
        return ele;
    }
}
