package InnerClass;
/*
    内部类:一个类内部包含的另外一个类，如人体和心脏

    分类：
        1.成员内部类
        2.局部内部类(包含匿名内部类)
*/
/*
成员内部类的定义格式:
    修饰符 class 外部类名称{
        修饰符 class 内部类名称{
            //....
        }
        //....
    }
注意:内用外，随意访问，外用内，需要内部类对象
如何使用成员内部类
    1.间接方式：在外部类的方法中使用内部类，然后main只是调用外部类方法
    2.直接方式：外部类名称.内部类名称 对象名 = new 外部类名称().new 内部类名称();
 */
/*
    局部内部类:定义在一个方法内部的类
    “局部”：只有当前所属的方法才能使用它,出了这个方法外面就不能用了

定义格式：
    修饰符 class 外部类名称{
        修饰符 返回值类型 外部类方法(参数列表){
            class 局部类名称{
                //...
            }
        }
    }
注意事项:局部内部类如果希望访问所在方法的局部变量，那么这个局部变量必须是【有效final的】
 */
/*
    如果接口的实现类(或者时父类的子类)只需要使用唯一的一次
    那么这种情况下就可以省略掉该类的定义，而改为使用【匿名内部类】

    匿名内部类定义格式：
        接口名称 对象名 = new 接口名称(){
            覆盖重写接口中所有抽象方法
        }
    注意事项：
        1.匿名内部类，在创建对象的时候，只能使用唯一的一次。
        2.匿名对象，在掉用方法的时候，只能调用唯一的一次。
        3.匿名内部类是省略了实现类/子类名称，但是匿名对象是省略了对象名称
 */
/*
    定义类时的权限修饰符规则:
    public > protected > (default) > private
        1.外部类：public / (default)
        2.成员内部类：public / protected / (default) / private
        3.局部内部类：什么都不能写
 */
public class InnerClassMain {

    public static void main(String[] args) {
        //成员变量使用
        //1.间接方式
        Body body = new Body();//外部类对象
        //通过外部类的对象，调用外部类方法，里面间接使用内部类Heart
        body.methodBody();

        //2.直接方式
        Body.Heart heart = new Body().new Heart();
        heart.beat();

        //局部内部类使用
        Outer2 obj = new Outer2();
        obj.methodOuter();


        //普通写法
        MyInterFace obj1 = new MyInterFaceImpl();
        obj1.method();
        //使用匿名内部类
        MyInterFace obj2 = new MyInterFace() {
            @Override
            public void method() {
                System.out.println("匿名内部类实现了方法！！");
            }
        };
        obj2.method();
    }
}
