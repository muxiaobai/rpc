/**
 * Project Name:spring-boot
 * File Name:RedisLock.java
 * Package Name:io.github.muxiaobai.spring_boot.lock
 * Date:2019年4月10日上午11:45:09
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.spring_boot.lock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * ClassName:ZookeeperLock
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年4月10日 上午11:45:09 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class ZookeeperLock extends AbsLock implements Watcher{
    private static CountDownLatch CountDownLatch = new CountDownLatch(1);
    private static String Node= "demo";
    private ZkClient ZkClient ;
    @Override
    public  Boolean getLock() {
           this.ZkClient = new ZkClient("127.0.0.1:2181", 10000); 
           this.ZkClient.createPersistent(Node);
        return false;
    };
    @Override
    public void waitLock() {
        try {
           CountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void unlock(){
           ZkClient.delete(Node);
           ZkClient.close();
    }
    @Override
    public void process(WatchedEvent paramWatchedEvent) {
        if (Node.equals(paramWatchedEvent.getPath())) {
            CountDownLatch.countDown();
        }
    }
}

