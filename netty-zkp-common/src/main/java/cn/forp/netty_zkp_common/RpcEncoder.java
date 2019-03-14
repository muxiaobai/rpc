/**
 * Project Name:netty-zkp-common
 * File Name:RpcEncoder.java
 * Package Name:cn.forp.netty_zkp_common
 * Date:2019年3月14日上午9:33:23
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * ClassName:RpcEncoder 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午9:33:23 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
@SuppressWarnings("rawtypes")
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = SerializationUtil.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}