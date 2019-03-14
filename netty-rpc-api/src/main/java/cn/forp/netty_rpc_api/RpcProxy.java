/**
 * Project Name:netty-rpc-consumer
 * File Name:RpcProxy.java
 * Package Name:cn.forp.netty_rpc_consumer
 * Date:2019年3月13日下午5:49:10
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_api;
/**
 * ClassName:RpcProxy 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:49:10 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.forp.netty_rpc_api.InvokeMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
 
public class RpcProxy {
    @SuppressWarnings("unchecked")
    public static <T> T create(Class<?> clazz){
        MethodProxy proxy = new MethodProxy(clazz);
        T result = (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, proxy);
        return result;
    }
}
 
class MethodProxy implements InvocationHandler{
    private Class<?> clazz;
    public MethodProxy(Class<?> clazz) {
        this.clazz = clazz;
    }
 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            try {
                return method.invoke(this, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return rpcInvoke(proxy,method,args);
        }
        return args;
    }
 
    private Object rpcInvoke(Object proxy, Method method, Object[] args) {
        InvokeMsg msg = new InvokeMsg();
        msg.setClassName(this.clazz.getName());
        msg.setMethodName(method.getName());
        msg.setValues(args);
        msg.setParamTypes(method.getParameterTypes());
        final RpcProxyHandler handler = new RpcProxyHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                     .channel(NioSocketChannel.class)
                     .option(ChannelOption.TCP_NODELAY, true)
                     .handler(new ChannelInitializer<SocketChannel>() {
 
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("frameDecoder",new 
                            LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4,0,4));
                            pipeline.addLast("frameEncoder",
                             new LengthFieldPrepender(4));
                            pipeline.addLast("encoder",new ObjectEncoder());
                            pipeline.addLast("decoder",new 
                             ObjectDecoder(Integer.MAX_VALUE, 
                             ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast("handler",handler);
                        }
                         
                    });
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();
            future.channel().writeAndFlush(msg).sync();
            future.channel().closeFuture().sync();
            return handler.getResponse();
        } catch (Exception e) {
            group.shutdownGracefully();
        }
        return null;
    }
    
}