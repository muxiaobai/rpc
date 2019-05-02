package io.github.muxiaobai.spring_boot.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MysqlPoolImpl implements  MysqlPool {
    private int maxSize = 100;

    private LinkedBlockingQueue<Connection> idle = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Connection> busy = new LinkedBlockingQueue<>();
    private AtomicInteger activeSize = new AtomicInteger(0);

    @Override
    public void init() {
        idle = new LinkedBlockingQueue<>();
        busy = new LinkedBlockingQueue<>();
    }

    @Override
    public void destroy() {

    }

    @Override
    public Connection getConn() {
        //从idle找，
        Connection connection = idle.poll();
        //有过有直接取出，放到busy中并返回
        if (connection != null) {
            busy.offer(connection);
            return connection;
        }
        //Idle 没有值，Poll没有满，新建
        if(activeSize.get()<maxSize){
            if(activeSize.incrementAndGet()<= maxSize){
                connection = mysqlUtil.createConn();
                busy.offer(connection);
                return  connection;
            }
        }
        //如果大于的话,连接池繁忙 系统繁忙等待
        try {
            connection = idle.poll(10000,TimeUnit.MICROSECONDS);
            if(connection== null){
                throw  new RuntimeException("等待超时");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void release(Connection connection) {
        busy.remove(connection);
        idle.offer(connection);
    }

    public  void  check(){
        for (int i= 0;i<maxSize;i++){
            Connection connection = idle.poll();
            if(null == connection){
                break;
            }
            try {
                if(!connection.isValid(200)){
                    connection = mysqlUtil.createConn();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            idle.offer(connection);
        }

    }
}
