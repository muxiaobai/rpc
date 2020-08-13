/**
 * Project Name:spring-boot
 * File Name:DemoService.java
 * Package Name:io.github.muxiaobai.spring_boot.service
 * Date:2019年3月21日下午7:31:58
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.service;

import io.github.muxiaobai.spring_boot.manage.dao.UserDao;
import io.github.muxiaobai.spring_boot.common.util.jdbc.mysqlUtil;
import io.github.muxiaobai.spring_boot.manage.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ClassName:DemoService 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午7:31:58 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

@Service
public class DemoDBService {


    public void dbConnPool(Integer nums) {
         CountDownLatch countDownLatch = new CountDownLatch(nums);
         io.github.muxiaobai.spring_boot.common.util.jdbc.mysqlUtil mysqlUtil = new mysqlUtil();
        for (int i = 0; i < nums; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("ThreadName:" + Thread.currentThread().getName());
                    String sql = "insert into user (username,password) values (121212,\"" + Thread.currentThread().getName() + "\")";
                    System.out.println("SQL:" + sql);
                    mysqlUtil.execPool(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            countDownLatch.countDown();
        }
    }
    public void dbConn(Integer nums) {
        CountDownLatch countDownLatch = new CountDownLatch(nums);
        io.github.muxiaobai.spring_boot.common.util.jdbc.mysqlUtil mysqlUtil = new mysqlUtil();
        for (int i = 0; i < nums; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("ThreadName:" + Thread.currentThread().getName());
                    String sql = "insert into user (username,password) values (121212,\"" + Thread.currentThread().getName() + "\")";
                    System.out.println("SQL:" + sql);
                    mysqlUtil.execute(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            countDownLatch.countDown();
        }
    }
    @Autowired
    public UserDao userDao;
    public List<User> queryMyBatis(String username){
            return userDao.query(username);
    }
    public  Integer insert(User user){
        return userDao.insert(user);
    }

}