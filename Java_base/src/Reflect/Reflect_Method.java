package Reflect;

import java.lang.reflect.Method;

public class Reflect_Method {
    public static void main(String[] args) throws Exception {
        //0.获取Person的Class对象
        Class personClass = Person.class;
        /*
            获取成员方法们
                Method[] getMethods()
                Method getMethod(String name, Class<?>... parameterTypes)
                Method[] privateGetDeclaredMethods(boolean publicOnly)
                Method getDeclaredMethod(String name, Class<?>... parameterTypes)
         */
        //获取指定名称的方法
        //空参
        Method eat_method = personClass.getMethod("eat");
        Person p = new Person();
        //执行方法
        eat_method.invoke(p);

        //带参
        Method eat_method2 = personClass.getMethod("eat", String.class);
        //执行方法
        eat_method2.invoke(p,"apple");

        //获取所有public修饰的方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            String name = method.getName();
            System.out.println(name);
        }
    }
}
