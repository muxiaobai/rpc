
启动初始化

绑定端口socket

#### springmvc
#### 配置阶段
- web.xml 注解
- DispatcherServlet SpringMvc
- 配置初始化参数
- /path
##### 初始化阶段

- init
- 加载application.xml
- 扫描相关class
- IOC 容器初始化 Map <BeanName,Object>
- 依赖注入 注解
- 初始化HandMappping Map<URL,Conttroller>

##### 运行阶段

- service doGet doPost
- doDispatch 方法  根据用户写的handMapping匹配
- 反射 动态调用
- response

 org.springframework.web.servlet;


        this.initMultipartResolver(context);
        this.initLocaleResolver(context);
        this.initThemeResolver(context);
        this.initHandlerMappings(context);
        this.initHandlerAdapters(context);
        this.initHandlerExceptionResolvers(context);
        this.initRequestToViewNameTranslator(context);
        this.initViewResolvers(context);
        this.initFlashMapManager(context);
#### spring ioc aop 

####mybatis

数据库连接池
