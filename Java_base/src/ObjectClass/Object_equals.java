package ObjectClass;

public class Object_equals {

    public static void main(String[] args) {
        /*
            Person对象类默认继承了Object类，所有可以使用Object类的equals方法
            public boolean equals(Object obj){
                return (this == obj);
            }
            基本数据类型:比较的是值
            引用数据类型:比较的两个对象的地址值
        */
        /*
            Object类的equals方法默认比较的是两个对象的地址值
            需求：重写equals方法，使其比较两个对象的属性值(name,age)
            问题：
                隐含一个多态
                Object obj = p2 = new Person("小红","18");
                弊端:无法使用子类特有的内容
                解决：可以使用向下转型(把Object类型转换为Person)
         */
        Person p1 = new Person("小明",18);
        Person p2 = new Person("小明",18);

        System.out.println(p1);
        System.out.println(p2);

        boolean b = p1.equals(p2);
        System.out.println(b);// false

        //p1=p2;
        boolean c = p1.equals(p2);
        System.out.println(c);

    }
}
