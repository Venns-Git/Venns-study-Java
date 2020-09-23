package Annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
    框架类：不改变该类任何代码，可以创建任意类对象，执行任意方法
 */
@Pro(className = "Annotation.Student",methodName = "eat")
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.解析注解
        //1.1获取该类的字节码文件对象
        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        //2.获取上边的注解对象
        ////其实就是在内存中生成了该注解接口的子类实现对象
        Pro an = reflectTestClass.getAnnotation(Pro.class);
        //调用注解对象中定义的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();
        System.out.println(className);
        System.out.println(methodName);
        //3.加载该类进内存
        Class cls = Class.forName(className);
        //4.创建对象
        Constructor constructor = cls.getConstructor();
        Object obj = constructor.newInstance();
        //获取方法对象
        Method method = cls.getMethod(methodName);
        //执行方法
        method.invoke(obj);
    }
}
