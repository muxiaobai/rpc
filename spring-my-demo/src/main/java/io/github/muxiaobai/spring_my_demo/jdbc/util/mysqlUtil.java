package io.github.muxiaobai.spring_my_demo.jdbc.util;

import io.github.muxiaobai.spring_my_demo.connectionpool.MysqlPool;
import io.github.muxiaobai.spring_my_demo.connectionpool.MysqlPoolImpl;

import java.sql.*;

public class mysqlUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  static Connection createConn(){
            String url  ="jdbc:mysql://localhost:3306/test";
            String userName = "root";
            String password = "123456";
            System.out.println("create connection："+ Thread.currentThread().getName());
        try {
            return  DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public  void  execute (String sql){
        Connection connection  =createConn();
        try {
            Statement statement =  connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static  MysqlPool mysqlPool = new MysqlPoolImpl();

    public  void execPool(String sql){
        Connection connection = mysqlPool.getConn();
        try {
            Statement statement =  connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysqlPool.release(connection);
    }
    public static void main(String[] args) {
        String sql = "insert into user (username,password) values (111,222)";
        mysqlUtil mysqlUtil = new mysqlUtil();
        mysqlUtil.execute(sql);
    }
}
