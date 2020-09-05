package Exception;
/*
    异常产生过程解析
 */
public class ExceptionAnalyze {

    public static void main(String[] args) {
        //创建一个int类型的数组，并赋值
        int[] arr = {1,2,3};
        int e = getElement(arr, 3);
        /*  访问数组的3索引，但数组并没有3索引，JVM就会检测出程序出现异常
                1.JVM会根据异常产生的原因创建一个异常对象，这个异常对象包含了异常产生的(内容，原因，位置)
                    new ArrayIndexOutOfBoundsException("3");
                2.在getElement方法中，没有异常的处理逻辑(try-catch)，那么JVM就会把异常抛出给方法的调用者main方法来处理这个异常
                3.main方法接受到了这个异常对象，main方法也没有异常的处理逻辑，就继续把对象抛出给main方法的调用者，JVM处理
                4.JVM接受到这个异常对象
                    1).把异常对象(内容，原因，位置)打印到控制台
                    2).JVM终止当前正在执行的Java程序
        */
        System.out.println(e);
    }

    /*
        定义一个方法，获取数组索引处的元素
        参数：
            int[] arr
            int index
     */
    public static int getElement(int[] arr,int index){
        int ele = arr[index];
        return ele;
    }
}
