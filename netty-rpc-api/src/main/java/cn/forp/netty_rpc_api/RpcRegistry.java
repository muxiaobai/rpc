/**
 * Project Name:netty-rpc-registry
 * File Name:RpcRegistry.java
 * Package Name:cn.forp.netty_rpc_registry
 * Date:2019年3月13日下午5:46:56
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_api;
/**
 * ClassName:RpcRegistry 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:46:56 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
 
public class RpcRegistry{
    
    private int port;
    private String packageName;
    public RpcRegistry(int port){
        this.port = port;
    }
    public RpcRegistry(int port,String packageName){
        this.port = port;
        this.packageName = packageName;
    }
    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workGroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ChannelInitializer<SocketChannel>() {
 
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new 
                            LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,4,0,4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast("encoder",new ObjectEncoder());
                            pipeline.addLast("decoder",new
                             ObjectDecoder(Integer.MAX_VALUE, 
                             ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(new RegistryHandler(packageName));
                        }
                         
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("server listener at port : "+port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}