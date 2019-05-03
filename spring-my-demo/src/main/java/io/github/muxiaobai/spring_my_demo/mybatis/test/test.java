package io.github.muxiaobai.spring_my_demo.mybatis.test;

import io.github.muxiaobai.spring_my_demo.connectionpool.MysqlPool;
import io.github.muxiaobai.spring_my_demo.connectionpool.MysqlPoolImpl;
import io.github.muxiaobai.spring_my_demo.demo.vo.User;
import io.github.muxiaobai.spring_my_demo.mybatis.jdbcTemplate.jdbcTemplate;

import java.sql.Connection;
import java.util.List;

public class test {

    public static void main(String[] args) {
        String querySql = "select * from  user where username = '121212'";
        MysqlPool mysqlPool = new MysqlPoolImpl();
        Connection connection = mysqlPool.getConn();
        jdbcTemplate jdbcTemplate = new jdbcTemplate(connection);
        List list = jdbcTemplate.executeListQuery(querySql, User.class);
        mysqlPool.release(connection);
        System.out.println(list.toString());

    }
}
