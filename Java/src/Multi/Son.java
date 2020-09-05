package Multi;

public class Son extends Dad {
    int num = 20;
    int numDad = 18;
    @Override
    public void method() {
        System.out.println("子类方法");
    }

    public void methodSon(){
        System.out.println("子类特有方法");
    }
}
