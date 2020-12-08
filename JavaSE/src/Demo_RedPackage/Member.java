package Demo_RedPackage;

import java.util.ArrayList;
import java.util.Random;

public class Member extends User{
    public Member(){

    }

    public Member(String name, int money) {
        super(name, money);
    }

    public void receive(ArrayList<Integer> list){
        //从集合中随机抽取一个给自己
        int index = new Random().nextInt(list.size());
        //根据索引，从集合中删除，并得到删除的值给自己
        int delta = list.remove(index);
        int money = super.getMoney();
        super.setMoney(money + delta);
    }
}
