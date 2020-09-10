package Thread.WaitAndNotifyDemo;
/*
    线程类:包子铺
 */
public class BaoZiPu extends Thread{
    //成员位置创建一个包子变量
    private BaoZi bz;

    //使用带参构造方法，为包子变量赋值
    public BaoZiPu(BaoZi bz){
        this.bz = bz;
    }

    //设置线程任务:生产包子
    @Override
    public void run() {
        //定义变量
        int cnt = 0;
        while (true){
            //必须使用同步技术保证两个线程只能有一个在执行
            synchronized (bz){
                //对包子状态进行判断
                if (bz.flag == true){
                    //包子铺调用wait方法进入等待状态
                    try {
                        bz.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒后执行
                //交替生产两种包子
                if (cnt % 2 == 0){
                    //生产第一种包子
                    bz.pi = "薄皮";
                    bz.xian = "三鲜";
                }else {
                    //生产第二种包子
                    bz.pi = "冰皮";
                    bz.xian = "牛肉";
                }
                cnt++;
                System.out.println("包子铺正在生产"+bz.pi+bz.xian+"的包子");
                //生产包子需要3s
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //生产好后修改为有
                bz.flag = true;
                //唤醒消费者线程
                bz.notify();
                System.out.println("包子铺已经生产好了:"+bz.pi+bz.xian+"的包子");
            }
        }

    }
}
