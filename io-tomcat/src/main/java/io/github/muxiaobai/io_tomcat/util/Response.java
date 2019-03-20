/**
 * Project Name:io-tomcat
 * File Name:Response.java
 * Package Name:io.github.muxiaobai.io_tomcat.util
 * Date:2019年3月20日上午10:16:32
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat.util;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * ClassName:Response 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月20日 上午10:16:32 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class Response {
    public final static String responseHeader="HTTP/1.1 200 \r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";
    private String write;
    private OutputStream outputStream;
    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    public void init(){
        
    }
    public byte[] out(){
        try {
            return (responseHeader + this.getWrite()).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        return null;
    }
    public String getWrite() {
        return write;
    }
    public void setWrite(String write) {
        this.write = write;
    }
}

