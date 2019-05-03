package io.github.muxiaobai.spring_boot.util.jdbc;

import io.github.muxiaobai.spring_boot.vo.User;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mysqlUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  static Connection createConn(){
            String url  ="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC";
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
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.execute();

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
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysqlPool.release(connection);
    }
    public List executeQuery(String sql){
        Connection connection  =createConn();
        List list = new ArrayList();
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            int len = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                Class clazz = User.class;
                Object object = clazz.newInstance();
                for (int i =0;i<len;i++){
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    Field field = clazz.getField(columnName);
                    field.setAccessible(true);
                    field.set(object,resultSet.getInt(i));

                }
                list.add(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public static void main(String[] args) {
        String sql = "insert into user (username,password) values (111,222)";
        String querySql = "select * from  user ";

        mysqlUtil mysqlUtil = new mysqlUtil();
//        mysqlUtil.execute(sql);
        List list = mysqlUtil.executeQuery(querySql);
        System.out.println(list.toString());
    }
}
