package InnerClass;
/*
    内部类的同名变量访问
*/
public class Outer1 {

    int num = 10; //外部类的成员变量

    public class Inner /*extends Object*/{

        int num = 20; //内部类的成员变量

        public void  methodInner(){
            int num = 30;//内部类方法的局部变量

            System.out.println(num);//30 局部变量
            System.out.println(this.num);//20 内部类的成员变量
            System.out.println(Outer1.this.num);//10 外部类的成员变量
        }
    }
}
