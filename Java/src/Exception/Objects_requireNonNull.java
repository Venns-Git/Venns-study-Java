package Exception;

import java.util.Objects;

/*
    Object static <T> T requireNonNull(T obj):查看指定对象是不是null
        源码:
            public static <T> T requireNOnNull(T obj){
                if (obj == null)
                    throw new NullPointerException();
                return obj;
            }

 */
public class Objects_requireNonNull {
    public static void main(String[] args) {
        method(null);
    }

    public static void method(Object obj) {
        //对传递过来的参数进行合法性判断，判断是否为null

//        if (obj == null)
//            throw new NullPointerException("传递参数为null");

       // Objects.requireNonNull(obj);
        Objects.requireNonNull(obj,"传递参数为null");
    }
}
