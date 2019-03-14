/**
 * Project Name:netty-zkp-common
 * File Name:RpcDecoder.java
 * Package Name:cn.forp.netty_zkp_common
 * Date:2019年3月14日上午9:33:57
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_common;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * ClassName:RpcDecoder 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午9:33:57 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = SerializationUtil.deserialize(data, genericClass);
        out.add(obj);
    }
}