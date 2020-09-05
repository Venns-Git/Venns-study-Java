package ClassAndObject;
/*
    定义一个学生类,包括:
        属性(成员变量)：
            String name;//姓名
            int age;//年龄
        行为(成员方法):
            public void eat(){}; //吃饭
            public void sleep(){}; //睡觉
            public void study(){}; //学习
    注意事项：
        1.成员变量是直接定义在类当中的，在方法外面
        2.成员方法不要写static关键字
*/
public class Student {
    //成员变量
    String name;  //姓名
    int age;    //年龄

    //成员方法
    public void eat(){
        System.out.println("吃饭!");
    }
    public void sleep(){
        System.out.println("睡觉!");
    }
    public void study(){
        System.out.println("学习!");
    }
}
