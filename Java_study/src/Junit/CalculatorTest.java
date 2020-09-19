package Junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    /**
     * 初始化方法
     * 用于资源申请，所有测试方法在执行之前都会先执行该方法
     */
    @BeforeEach
    public void init(){
        System.out.println("init...");
    }

    /**
     * 释放资源
     * 在所有测试方法执行完后，都会自动执行该方法
     */
    @AfterEach
    public void close(){
        System.out.println("close...");
    }

    /**
     * 测试add方法
     */
    @Test
    public void testAdd(){
        System.out.println("测试成功");
        //1.创建计算机对象
        Calculator c = new Calculator();
        //2.调用add方法
        int result = c.add(1, 2);
        //System.out.println(result);

        //3.断言
        Assertions.assertEquals(3,result);
    }
}
