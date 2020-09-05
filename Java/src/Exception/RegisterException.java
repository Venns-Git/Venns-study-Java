package Exception;
/*
    自定义异常类：
        Java提供的异常类不够我们使用时，就需要我们自己定义一些异常类
    格式：
        public class xxxException extends Exception/RuntimeException{
            添加一个空参数的构造方法
            添加一个带异常信息的构造方法
        }
     注意：
        1.自定义异常类一般都是以Exception结尾，说明该类是一个异常类
        2.自定义异常类，必须继承Exception或者RuntimeException
            继承Exception：那么自定义的异常类就是一个编译器异常，如果方法内部就必须处理这个异常，要么throws，要么tru..catch
            继承RuntimeException：那么自定义异常就是一个运行期异常，无需处理。交给虚拟机（中断处理）
 */
public class RegisterException extends Exception{
    //添加一个空参数构造方法
    public RegisterException(){
        super();
    }

    /*
        添加一个带异常信息的构造方法
        发现所有的异常类都会有一个带异常信息的构造方法，方法内部会调用父类带异常信息的构造方法，让父类来处理这个异常信息
     */
    public RegisterException(String message){
        super(message);
    }
}
