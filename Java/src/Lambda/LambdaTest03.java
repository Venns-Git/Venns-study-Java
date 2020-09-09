package Lambda;
/*
    Lambda表达式有返回值的练习
    需求:
        给定一个计算器Calculator接口，内含抽象方法calc可以将两个int数字相加
        使用Lambda的标准格式调用invokeCalc方法，完成10和20相加计算
 */
public class LambdaTest03 {
    public static void main(String[] args) {
        //调用invokeCalc方法，参数使用匿名内部类
        invokeCalc(10, 20, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        });

        //使用Lambda表达式简化匿名内部类
        invokeCalc(10,20,(int a,int b) ->{
            return a + b;
        });
    }

    /*
        定义一个方法,传递两个int类型的整数，传递Calculator接口
        接口内部调用接口的中的calc方法
     */
    public static void invokeCalc(int a,int b,Calculator c){
        int sum = c.calc(a,b);
        System.out.println(sum);
    }
}
