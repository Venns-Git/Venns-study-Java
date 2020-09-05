package Thread.WaitAndNotifyDemo;
/*
    线程类：吃货
 */
public class ChiHuo extends Thread{
    //成员位置创建一个包子变量
    private BaoZi bz;

    //使用带参构造方法，为包子变量赋值
    public ChiHuo(BaoZi bz){
        this.bz = bz;
    }

    //设置线程任务:吃包子

    @Override
    public void run() {
        while(true){
            //必须使用同步技术保证两个线程只能有一个在执行
            synchronized (bz){
                //对包子状态进行判断
                if (bz.flag == false){
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //被唤醒之后吃包子
                System.out.println("吃货正在吃:"+bz.pi+bz.xian+"的包子");
                //吃完后修改包子状态
                bz.flag = false;
                //唤醒包子铺线程，生产包子
                bz.notify();
                System.out.println("吃货已经把"+bz.pi+bz.xian+"的包子吃完了");
            }
        }
    }
}
