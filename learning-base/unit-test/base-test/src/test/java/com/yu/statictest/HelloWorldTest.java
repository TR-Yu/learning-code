package com.yu.statictest;

import com.yu.TestMethodNameLogger;
import org.junit.*;
import org.junit.rules.RuleChain;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 简单的静态方法的调用，用于简单的Util类的测试方法内，
 * 1.没有外部依赖，2.入参可以通过简单的构造构造出来
 * <p>
 * 相对于使用main函数来测试优点：
 * 1. 可以通过测试结果很清晰的看到测试单例是否通过，当有大量的需要测试的时候可以直接查看结果
 * 2. 测试不只是为了测试到正常的返回流程，还有一些已经预料到的异常也在测试范围内
 *
 * @author YU
 * @date 2022-06-17 14:53
 */
public class HelloWorldTest {

    private static String str;

   /* @Rule
    public Timeout globalTimeout = Timeout.seconds(1);*/

    @Rule
    public TestMethodNameLogger logger = new TestMethodNameLogger();



    @Test
    public void printStrTest() {
        Assert.assertEquals("Hello Unit-Test", HelloWorld.printStr());
        Assert.assertNotEquals("Hello Unit-Test1", HelloWorld.printStr());
        Assert.assertEquals(0.0012f, 0.0014f, 0.0002);
        Assert.assertEquals(0.0012f, 0.0013f, 0.0001);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 300000L)
    public void calculateTest() throws InterruptedException {
        Assert.assertNotEquals(3.33333F, HelloWorld.calculate(10F, 3F), 0);
        Assert.assertEquals(10F, HelloWorld.calculate(10F, 1F), 0);
        TimeUnit.SECONDS.sleep(2);
    }

    @Test()
    public void divideTest() {
        //测试异常分支
        Assert.assertThrows(DivideByZeroException.class, () -> HelloWorld.divide(3, 0));
        //测试临界值 0,0
        Assert.assertThrows(DivideByZeroException.class, () -> HelloWorld.divide(0, 0));
        //测试正数 10，1
        Assert.assertEquals(10, HelloWorld.divide(10, 1));
    }


    @BeforeClass
    public static void setContent(){
        str = "static before";
        System.out.println(str);
    }

    @Before
    public void setStr(){
        System.out.println("");
    }
}
