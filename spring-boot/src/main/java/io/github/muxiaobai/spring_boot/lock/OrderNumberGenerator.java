/**
 * Project Name:spring-boot
 * File Name:OrderNumberGenerator.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日下午4:49:25
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.lock;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

/**
 * ClassName:OrderNumberGenerator 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 下午4:49:25 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
@Service
public class OrderNumberGenerator {
      public static int count =0;
      private static Object lock = new Object();
      private java.util.concurrent.locks.Lock lock2 = new ReentrantLock();
      private Lock lock3 = new ZookeeperLock();
    
      /**
       *   //可能会有重复的number
       * getNumber:().
       * @author Mu Xiaobai
       * @return
       * @since JDK 1.8
       */
      public String getNumber(){
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String result = "" + ++count;
          return result;
      }
        /**
         *  jvm级别     //synchronized
         * getNumberBySync:().
         * @author Mu Xiaobai
         * @return
         * @since JDK 1.8
         */
      public String getNumberBySync(){
          synchronized(lock){
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String result = "" + ++count;
              return result;
          }
      }
      /**
       *  jdk级别     //lock
       * getNumberByLock:().
       * @author Mu Xiaobai
       * @return
       * @since JDK 1.8
       */
      public String getNumberByLock(){
          try{
              lock2.lock();
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String result = "" + ++count;
              return result;
          }finally{
              lock2.unlock();
          }
      }
      /**
       * 分布式 Zookeeper方式实现
       * getNumberByZook:().
       * @author Mu Xiaobai
       * @return
       * @since JDK 1.8
       */
      public String getNumberByZook(){
          try{
              lock3.lock();
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String result = "" + ++count;
              return result;
          }finally{
              lock3.unlock();
          }
      }
}

