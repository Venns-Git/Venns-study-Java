package ObjectClass;

public class Person {
    private String name;
    private int age;

    @Override
    public boolean equals(Object obj){
        //增加判断，传递参数为null,直接返回false
        if (obj == null)return false;
        //增加判断，传递参数为this本身，直接返回true
        if (obj == this)return true;
        /*
            增加判断,是Person类型才转换，防止类型转换异常
         */
        if(obj instanceof Person){
            //使用向下转型,把obj类型转换为Person
            Person p = (Person)obj;
            //比较两个对象的属性,一个是调用方法的this(p1),一个就是p(obj=p2)
            boolean b = this.name.equals(p.name) && this.age == p.age;
            return b;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Person{name="+this.name+" ,age="+this.age+"}";
    }

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
}
