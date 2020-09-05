package Demo_RedPackage;

import java.util.ArrayList;

public class RedPackageMain {

    public static void main(String[] args) {
        Manager manager = new Manager("群主",100);
        Member one = new Member("1号成员",0);
        Member two = new Member("2号成员",10);
        Member three = new Member("3号成员",5);

        manager.show();
        one.show();
        two.show();
        three.show();
        System.out.println("=======发完红包后======");

        ArrayList<Integer> redList = manager.send(20,3);
        one.receive(redList);
        two.receive(redList);
        three.receive(redList);
        manager.show();
        one.show();
        two.show();
        three.show();
    }
}
