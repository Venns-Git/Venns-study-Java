package InnerClass;

public class Outer2 {

    public void methodOuter(){

        class Inner{//局部内部类
            int num = 10;
            public void methodInner(){
                System.out.println(num);//10
            }
        }

        Inner inner = new Inner();
        inner.methodInner();
    }
}
