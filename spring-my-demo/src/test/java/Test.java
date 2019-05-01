import io.github.muxiaobai.spring_my_demo.mvcframework.servlet.DispatcherServlet;

import javax.servlet.ServletException;

public class Test {
    public static void main(String[] args) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        try {
            dispatcherServlet.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
