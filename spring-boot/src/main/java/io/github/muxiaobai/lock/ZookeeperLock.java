/**
 * Project Name:spring-boot
 * File Name:RedisLock.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日上午11:45:09
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.lock;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * ClassName:ZookeeperLock
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 上午11:45:09 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class ZookeeperLock extends AbsLock{
    private static CountDownLatch CountDownLatch = null;
    private static String Node= "/demo";
    private ZkClient ZkClient =new ZkClient("127.0.0.1:2181", 1000, 1000, new SerializableSerializer());
    @Override
    public  Boolean getLock() {
        try{
           ZkClient.createEphemeral(Node);
           return true;
        }catch(Exception e){
            return false;
        }
    };
    @Override
    public void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("==通知：====="+dataPath);
                if(CountDownLatch!=null){
                    CountDownLatch.countDown();
                }
            }
            @Override
            public void handleDataChange(String arg0, Object arg1) throws Exception {
            }
        };
        ZkClient.subscribeDataChanges(Node, listener);
        if(ZkClient.exists(Node)){
            CountDownLatch =new CountDownLatch(1);
            try {
                CountDownLatch.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        }
        ZkClient.unsubscribeDataChanges(Node, listener);
    }
    @Override
    public void unlock(){
        if(ZkClient!=null){
//            ZkClient.delete(Node);
            ZkClient.close();
            System.out.println("=========释放到分布式锁=========");
        }
    }
  
}
// create -e /demo value  
// create -e -s /demo value 
//-e 临时 -s 序列
