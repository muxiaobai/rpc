/**
 * Project Name:netty-rpc-registry
 * File Name:RegistryHandler.java
 * Package Name:cn.forp.netty_rpc_registry
 * Date:2019年3月13日下午5:46:26
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.netty_rpc_api;
/**
 * ClassName:RegistryHandler 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:46:26 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.github.muxiaobai.netty_rpc_api.InvokeMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
 
 
public class RegistryHandler extends ChannelInboundHandlerAdapter{
 
    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<String, Object>();
    
    private List<String> classCache = new ArrayList<String>();
    
    public RegistryHandler() {
        scannerClass("");
        doRegister();
    }
    public RegistryHandler(String packageName) {
        scannerClass(packageName);
        doRegister();
    }
    private void doRegister() {
        if(classCache.size() ==0 ){return;}
        for(String name:classCache){
            try {
                Class<?> clazz = Class.forName(name);
                Class<?> interfaces = clazz.getInterfaces()[0];
                classMap.put(interfaces.getName(), clazz.newInstance());
            } catch (Exception e) {
                
            }
        }
    }
    
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dirFile = new File(url.getFile());
        for(File file:dirFile.listFiles()){
            if(file.isDirectory()){
                scannerClass(packageName+"."+file.getName());
            }else{
                classCache.add(packageName+"."+file.getName().replace(".class", ""));
            }
        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Object result = new Object();
        InvokeMsg request = (InvokeMsg)msg;
        if(classMap.containsKey(request.getClassName())){
            Object clazz = classMap.get(request.getClassName());
            System.out.println(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(),
             request.getParamTypes());
            result = method.invoke(clazz, request.getValues());
        }
        ctx.writeAndFlush(result);
        ctx.close();
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.flush();
        ctx.close();
    }
    
}