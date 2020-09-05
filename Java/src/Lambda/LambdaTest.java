package Lambda;
/*
    需求：
        给定一个cook接口，内部含唯一的抽象方法makeFood，无参数无返回值
        使用Lambda的标准格式调用invokeCook方法，打印输出"吃饭了"字样

 */
public class LambdaTest {
    public static void main(String[] args) {

        //调用invokeCook方法，参数是Cook接口，传递Cook接口的匿名内部类对象
        invokeCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("吃饭了!!");
            }
        });

        //使用Lambda表达式简化匿名内部类的书写
        invokeCook(() -> {
            System.out.println("吃饭了~~~");
        });
    }

    //定义一个方法，参数传递Cook接口，方法内部调用Cook接口的makeFood方法
    public static void invokeCook(Cook cook){
        cook.makeFood();
    }
}
