package io.github.muxiaobai.spring_boot.common.util.jdbc;

import io.github.muxiaobai.spring_boot.manage.vo.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
                Field[] fields = clazz.getDeclaredFields();
                for (Field field: fields){
                        if(field.isAnnotationPresent(Column.class)){
                            String columnName = field.getAnnotation(Column.class).value().trim();
                            if("".equals(columnName)){
                                columnName= field.getName();
                            }
                            String methodName ="set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1);
                            Method method = clazz.getMethod(methodName,field.getType());
                            Class typeName = field.getType();
                            field.setAccessible(true);
                            if(typeName == Long.class ){
                                field.set(object,resultSet.getLong(columnName));
                                //method.invoke(object,resultSet.getLong(columnName));
                            }else if(typeName == String.class){
                                field.set(object,resultSet.getString(columnName));
                                //method.invoke(object,resultSet.getString(columnName));
                            }
                        }
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
        String querySql = "select * from  user where username = '121212'";

        mysqlUtil mysqlUtil = new mysqlUtil();
//        mysqlUtil.execute(sql);
        List list = mysqlUtil.executeQuery(querySql);
        System.out.println(list.toString());

    }
}
