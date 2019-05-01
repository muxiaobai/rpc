package io.github.muxiaobai.spring_my_demo.mvcframework.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.*;

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        //加载配置文件
        doLoadConfig();
        //扫描相关类
        doScanner(properties.getProperty("ScanPackge"));
        //实例化所有的Class，存入IOC容器
        doInstance();
        //自动化注入
        doAutowired();
        //初始化HanderMapping
        initHanderMapping();

        System.out.println(" MVC Framework inited");
    }

    private Properties properties = new Properties();

    private void  doLoadConfig(){

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private List<String> classNames=  new ArrayList();
    private void doScanner(String packageName){
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file:classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(packageName+"."+file.getName());
            }else {
                String className = packageName+ "."+ file.getName().replace(".class","");
                classNames.add(className);
            }
        }
    }

    private Map<String, Object> ioc = new HashMap<>();
    private void  doInstance(){
        if(!classNames.isEmpty()){
            for (String className: classNames){
                //反射实例化对象
                try {
                    Class clazz = Class.forName(className);
                    //controller
                    if(clazz.isAnnotationPresent(Controller.class)){
                        String beanName = this.firstLow(clazz.getSimpleName());
                        ioc.put(beanName,clazz.newInstance());
                        //service
                    }else if(clazz.isAnnotationPresent(Service.class)){
                        // 1 defult ""
                        // 2  自定义
                        // 3 根据类型利用接口的key
                        Service service =(Service)clazz.getAnnotation(Service.class);
                        String beanName = service.value();
                        //空
                        if("".equals(beanName.trim())){
                            beanName =this.firstLow(clazz.getSimpleName());
                        }
                        //没有接口的service
                        Object instance = clazz.newInstance();
                        ioc.put(beanName,instance);
                        //接口
                        Class[] intefaces = clazz.getInterfaces();
                        for (Class inteface: intefaces){
                            ioc.put(inteface.getName(),instance);
                        }

                    }else{
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String firstLow(String beanName){
        char[] chars = beanName.toCharArray();
        chars[0] +=32;
        return  String.valueOf(chars);
    }
    private void  doAutowired(){
        if(!ioc.isEmpty()){
            for (Map.Entry entry : ioc.entrySet()){
                //所有的字段
                Field[]  fields =  entry.getValue().getClass().getDeclaredFields();
                for(Field field : fields){
                    if(!field.isAnnotationPresent(Autowried.class)){
                        continue;
                    }
                    //属性中有Autowired
                    Autowried autowired =  field.getAnnotation(Autowried.class);
                    String beanName = autowired.value().trim();
                    if("".equals(beanName)){
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);
                    try {
                        field.set(entry.getValue(),ioc.get(field.getName()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                }


            }

        }
    }
    private  void  initHanderMapping(){

    }
}
