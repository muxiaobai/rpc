import io.github.muxiaobai.spring_my_demo.demo.service.OneService;
import io.github.muxiaobai.spring_my_demo.mvcframework.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        try {
            dispatcherServlet.init();
            OneService oneService = (OneService) dispatcherServlet.getIoc().get("oneService");
            System.out.println(oneService.retName(":dddd"));
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    private static final int nums = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(nums);
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(nums+1);
    public OneService oneService;

    public void test() {
        for (int i = 0; i < nums; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    oneService.retName("");
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + ",result:");
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

    }
