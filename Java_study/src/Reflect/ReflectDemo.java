package Reflect;
/*
    Java代码在计算机的3个阶段:
        Source源代码阶段 -> Class类对象阶段 -> Runtime运行时阶段

    反射：将类的各个组成部分封装为其他对象，这就是反射机制
    好处:
        1.可以在程序运行过程中，操作这些对象
        2.可以解耦，提高程序的可扩展性

    获取class对象的方式：
        1.Class.forName("全类名"):将字节码文件加载进内存，返回class对象
            多用于配置文件，将类名定义在配置文件中，读取文件，加载类
        2.类名.class：通过类名的属性class获取
            多用于参数的传递
        3.对象.getClass()：getClass()定义在Object中
            多用于对象的获取字节码的方式
        结论：同一个字节码文件(*.class)在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的Class对象都是同一个

    class对象方法：
        获取功能:
            1.获取成员变量们
                Field[] getFields() 获取所有public修饰的成员变量
                Field getFiled(String name) 获取指定名称的public修饰的成员变量
                Field[] getDeclaredFields() 获取所有的成员变量,不考虑修饰符
                Field getDeclareField(String name) 获取指定的成员变量,不考虑修饰符
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
                    Class.getName();

        Field:成员变量
            操作：
                1.设置值：void set(Object obj,Object value)
                2.获取值：get(Object obj)
                3.忽略访问权限修饰符的安全检查：setAccessible(true) 暴力反射
        Constructor:构造方法
            创建对象:
                T newInstance(Object...initargs)
        Method：方法对象
            执行方法:
                Object invoke(Object obj, Object... args)
            获取方法的名称：
                String getName()
 */
public class ReflectDemo {


}
