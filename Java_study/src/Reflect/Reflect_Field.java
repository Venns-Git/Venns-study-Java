package Reflect;

import java.lang.reflect.Field;

public class Reflect_Field {
    /*
        class对象方法：
        获取功能:
            1.获取成员变量们
                Field[] getFields() 获取所有public修饰的成员变量
                Field getFiled(String name) 获取指定名称的public修饰的成员变量
                Field[] getDeclaredFields()
                Field getDeclareField(String name)
            2.获取构造方法们
                Constructor<?>[] getConstructors()
                Constructor<T> getConstructor(Class<?>... parameterTypes)
                Constructor<?>[] getDeclaredConstructors()
                Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
            2.获取成员方法们
                Method[] getMethods()
                Method getMethod(String name, Class<?>... parameterTypes)
                Method[] privateGetDeclaredMethods(boolean publicOnly)
                Method getDeclaredMethod(String name, Class<?>... parameterTypes)
            4.获取类名
                String getName()
     */
    public static void main(String[] args) throws Exception {
        //1.获取Person的class对象
        Class personClass = Person.class;
        /*
            获取成员变量们
                Field[] getFields() 获取所有public修饰的成员变量
                Field getFiled(String name) 获取指定名称的public修饰的成员变量

                Field[] getDeclaredFields() 获取所有的成员变量,不考虑修饰符
                Field getDeclareField(String name) 获取指定的成员变量,不考虑修饰符
         */
        //Field[] getFields() 获取所有public修饰的成员变量
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("-----------------------");
        //Field getFiled(String name) 获取指定名称的public修饰的成员变量
        Field a = personClass.getField("a");
        //获取成员a的值
        Person p = new Person();
        Object value = a.get(p);
        System.out.println(value);
        //设置a的值
        a.set(p,"小明");
        System.out.println(p);
        System.out.println("=====================");

        //Field[] getDeclaredFields() 获取所有的成员变量,不考虑修饰符
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        //Field getDeclareField(String name)获取指定的成员变量,不考虑修饰符
        Field d = personClass.getDeclaredField("d");
        //忽略访问权限修饰的安全检查
        d.setAccessible(true);//暴力反射
        Object value2 = d.get(p);
        System.out.println(value2);
    }
}
