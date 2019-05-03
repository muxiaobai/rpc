package io.github.muxiaobai.spring_my_demo.mybatis.jdbcTemplate;

import io.github.muxiaobai.spring_my_demo.mybatis.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class jdbcTemplate {

    private Connection connection;

    public jdbcTemplate(Connection connection) {
        this.connection = connection;
    }
    public void load(){
    }
    public List executeListQuery(String sql,Class clazz){
        List list = new ArrayList();
        try {
            PreparedStatement preparedStatement =  this.connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            int len = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
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
        return  list;
    }

}
