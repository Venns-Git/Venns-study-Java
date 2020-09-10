package ObjectClass;

import java.util.Objects;

public class ObjectsDemo {

    public static void main(String[] args) {
        String s1 = null;
        String s2 = "aaa";

        //错误
        //boolean b = s1.equals(s2);//null是不能调用方法的，会抛出空指针异常

        //正确 调用Objects工具类
        boolean b = Objects.equals(s1, s2);
    }
}
