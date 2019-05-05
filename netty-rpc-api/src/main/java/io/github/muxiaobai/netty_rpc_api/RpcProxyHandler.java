/**
 * Project Name:netty-rpc-consumer
 * File Name:RpcProxyHandler.java
 * Package Name:cn.forp.netty_rpc_consumer
 * Date:2019年3月13日下午5:49:36
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.netty_rpc_api;
/**
 * ClassName:RpcProxyHandler 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:49:36 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
 
public class RpcProxyHandler extends ChannelInboundHandlerAdapter{
 
    private Object response;
    
    public Object getResponse(){
        return this.response;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("client receive message: "+msg);
        response = msg;
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.flush();
        ctx.close();
    }
    
}
