package io.github.muxiaobai.spring_my_demo.connectionpool;

import java.sql.Connection;

public interface MysqlPool {
    //初始化
    void init();
    // 销毁
    void destroy();
    //获取连接
    Connection getConn();
    //释放连接
    void release(Connection connection);


}
