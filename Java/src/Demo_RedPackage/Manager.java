package Demo_RedPackage;

import java.util.ArrayList;

public class Manager extends User{
    public Manager(){

    }

    public Manager(String name, int money) {
        super(name, money);
    }
    public ArrayList<Integer> send(int totalMoney,int count){
        //红包集合。
        ArrayList<Integer> redList = new ArrayList<>();
        //异常处理
        int leftMoney = super.getMoney();
        if (totalMoney > leftMoney){
            System.out.println("余额不足!!!");
            return redList;//返回空集合
        }

        //扣钱
        super.setMoney(leftMoney - totalMoney);

        //塞红包
        int avg = totalMoney / count;
        int mod = totalMoney % count;//除不尽的零头

        for (int i = 0; i < count - 1; i++) {
            redList.add(avg);
        }
        int last = avg + mod;
        redList.add(last);

        return redList;
    }
}
