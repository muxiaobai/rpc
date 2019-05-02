package io.github.muxiaobai.spring_my_demo.jdbc.util;

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
    private  void  execute (String sql){
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
    public static void main(String[] args) {
        String sql = "insert into user (username,password) values (111,222)";
        mysqlUtil mysqlUtil = new mysqlUtil();
        mysqlUtil.execute(sql);
    }
}
