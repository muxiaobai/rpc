package io.github.muxiaobai.spring_my_demo.springframework;

import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringIoc {
    public SpringIoc() {
        this.init();
    }

    public void init(){
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
                            ioc.put(this.firstLow(inteface.getSimpleName()),instance);
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
            for (Map.Entry<String,Object> entry : ioc.entrySet()){
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
//    private Map<String, Method> handMapping = new HashMap<String,Method>();
    private List<Hander> handMapping = new ArrayList<>();
    private  void  initHanderMapping(){
        if(ioc.isEmpty()){return;}
        for (Map.Entry entry : ioc.entrySet()) {
                Class clazz = entry.getValue().getClass();
                if (!clazz.isAnnotationPresent(Controller.class)) { continue; }
                //类上的路径
                String url ="";
                if(clazz.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                    url = requestMapping.value();
                }
                //方法上的注解路径
                //使用map
                /*
                Method[] methods = clazz.getMethods();
                for (Method method : methods){
                    if(!method.isAnnotationPresent(RequestMapping.class)){continue; }
                    RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                    String mapurl = url +requestMapping.value();
                    handMapping.put(mapurl,method);
                    System.out.println("mapurl:"+mapurl+",method:"+method);
                }
                */
                //使用list
                Method[] methods = clazz.getMethods();
                for (Method method : methods){
                    if(!method.isAnnotationPresent(RequestMapping.class)){continue; }
                    RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                    String mapurl = url +requestMapping.value();
                    Pattern pattern = Pattern.compile("/"+url+requestMapping.value());
                    Hander hander = new Hander(pattern,entry.getValue(),method);
                    handMapping.add(hander);
                    System.out.println("mapurl:"+mapurl+",method:"+method);
                }

        }
    }
    //内部类
    private class Hander {
        protected  Object controller;
        protected Method method;
        protected Pattern pattern;
        protected Map<String,Integer> paramIndexMapping;

        protected  Hander(Pattern pattern ,Object controller,Method method){
            this.pattern = pattern;
            this.controller = controller;
            this.method = method;
            paramIndexMapping = new HashMap<String,Integer>();
            putParamIndexMapping(method);
        }
        private  void  putParamIndexMapping(Method method){
            //方法中的注解参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0;i<pa.length;i++){
                for (Annotation annotation:pa[i]){
                    String paramName = ((RequestParam)annotation).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName,i);
                    }
                }
            }
            //
            Class[] paramsTypes = method.getParameterTypes();
            for (int i =0 ;i<paramsTypes.length; i++){
                Class type = paramsTypes[i].getClass();
                if(type == HttpServletRequest.class||  type == HttpServletResponse.class){
                    paramIndexMapping.put(type.getName(),i);
                }
            }
        }
    }

    public Map getIoc() {
        return this.ioc;
    }
}

