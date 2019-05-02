import io.github.muxiaobai.spring_my_demo.demo.service.OneService;
import io.github.muxiaobai.spring_my_demo.jdbc.util.mysqlUtil;
import io.github.muxiaobai.spring_my_demo.mvcframework.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
    private static final int nums = 150;
    private CountDownLatch countDownLatch = new CountDownLatch(nums);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(nums+1);
    private mysqlUtil mysqlUtil = new mysqlUtil();

    public void test() {
        for (int i = 0; i < nums; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("ThreadName:" + Thread.currentThread().getName());
                    String sql = "insert into user (username,password) values (121212,\""+Thread.currentThread().getName()+"\")";
                    System.out.println("SQL:" +sql);
//                    mysqlUtil.execute(sql);
                    mysqlUtil.execPool(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            countDownLatch.countDown();
        }

        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    public  void  getIOC (){
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        try {
            dispatcherServlet.init();
            OneService oneService = (OneService) dispatcherServlet.getIoc().get("oneService");
            System.out.println(oneService.retName(":dddd"));
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
