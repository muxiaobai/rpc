import io.github.muxiaobai.spring_my_demo.demo.service.DbService;
import io.github.muxiaobai.spring_my_demo.mvcframework.servlet.DispatcherServlet;
import io.github.muxiaobai.spring_my_demo.springframework.SpringIoc;

import javax.servlet.ServletException;
import java.util.Map;

public class TestMybatis {
    public static void main(String[] args) {
        SpringIoc springIoc = new SpringIoc();
        Map map = springIoc.getIoc();
        DbService dbService = (DbService)map.get("DbService");
        dbService.getDb("");
    }
}
