package io.github.muxiaobai.spring_my_demo.demo.service;

import io.github.muxiaobai.spring_my_demo.connectionpool.MysqlPool;
import io.github.muxiaobai.spring_my_demo.connectionpool.MysqlPoolImpl;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.Autowried;
import io.github.muxiaobai.spring_my_demo.mvcframework.annotation.Service;
import io.github.muxiaobai.spring_my_demo.mybatis.annotation.Select;

import java.sql.Connection;

@Service
public class DbService {
    @Autowried
    public  MysqlPool mysqlPool;
    @Select("select * from user")
    public  String getDb(String name){
        Connection connection = mysqlPool.getConn();
        String sql = "";
        return  "gggg"+name;
    }

}
