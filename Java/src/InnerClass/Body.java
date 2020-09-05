package InnerClass;

public class Body {//外部类

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Heart { //成员内部类
        //内部类方法
        public void beat(){
            System.out.println("心脏跳动");
            System.out.println("我叫"+name);//正确写法
        }
    }
    //外部类方法
    public void methodBody(){
        System.out.println("外部类方法");
        new Heart().beat();
    }
}
