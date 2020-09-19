package IO;

import java.io.Serializable;

/*
    序列化和反序列化的时候，会抛出NotSerializableException 没有序列化异常
    必须实现Serializable接口，就会给类添加一个标记
    我们进行序列化和反序列化的时候就会检测类上是否有这个标记

    static关键字:静态关键字
        静态关键字优先于非静态加载到内存中(静态优先于对象进入到内存中)
        被static修饰的成员变量是不能被序列化的，序列化的都是对象
        private static int age;写入18，读出为0

    transient关键字：瞬态关键字
        被transient修饰的成员变量，不能被序列化

    JVM反序列化对象时，能找到class文件，但是class文件在序列化对象之后发生了修改，那么反序列化操作也会失败，抛出InvalidClassException异常
    解决方案：手动定义序列化
        private static final long serialVersionUID = 42L;

 */
public class Person implements Serializable {
    private String name;
    private transient int age;
    private static final long serialVersionUID = 42L;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
