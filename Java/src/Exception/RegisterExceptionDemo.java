package Exception;

import java.util.Scanner;

/*
    模拟注册操作，如果用户名已存在，则抛出一个并提示
    分析:
        1.使用数组保存已经注册的的用户名(数据库)
        2.使用Scanner获取用户输入的注册名(前端，页面)
        3.定义一个方法，对用户输入的注册名进行判断
            遍历存储已经注册过的用户名数组，获取每一个用户名
            使用获取到的用户名和用户输入的用户名比较
                true：用户名已经存在，抛出RegisterException异常，告知用户
                false：继续遍历比较，
             如果循环结束，还没有找到重复的用户名，提示用户注册成功
 */
public class RegisterExceptionDemo {
    static String[] usernames = {"张三","王五","李四"};

    public static void main(String[] args) /*throws RegisterException*/ {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入你要注册的用户名:");
        String username = in.next();
        CheckUsername(username);
    }

    //定义一个方法，对用户输入的用户名进行判断
    public static void CheckUsername(String username) /*throws RegisterException */ {
        for(String name : usernames){
            if (name.equals(username)){
                try {
                    throw new RegisterException("该用户名已被注册!");
                } catch (RegisterException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
        System.out.println("恭喜您，注册成功");
    }
}
