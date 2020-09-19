package Reflect;

import java.lang.reflect.Constructor;

public class Reflect_Constructor {
    public static void main(String[] args) throws Exception {
        //0.获取Person的Class对象
        Class personClass = Person.class;
        /*
            获取构造方法们
                Constructor<?>[] getConstructors()
                Constructor<T> getConstructor(Class<?>... parameterTypes)
                Constructor<?>[] getDeclaredConstructors()
                Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
         */

        //Constructor<T> getConstructor(Class<?>... parameterTypes)
        Constructor constructor1 = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor1);
        //创建对象
        Object peroson1 = constructor1.newInstance("小明", 23);
        System.out.println(peroson1);
        System.out.println("-----------------");
        //空参
        Constructor constructor2 = personClass.getConstructor();
        System.out.println(constructor2);
        Object peroson2 = constructor2.newInstance();
        System.out.println(peroson2);
    }
}
