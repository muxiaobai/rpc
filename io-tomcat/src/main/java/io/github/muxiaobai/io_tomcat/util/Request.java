/**
 * Project Name:io-tomcat
 * File Name:Request.java
 * Package Name:io.github.muxiaobai.io_tomcat.util
 * Date:2019年3月20日上午10:16:24
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ClassName:Request 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月20日 上午10:16:24 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class Request {
    private String method;
    private String url;
    private String proto;
    private InputStream inputStream;
    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public void init() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String[] methodAndUrl = bufferedReader.readLine().split(" ");
        this.method= methodAndUrl[0];
        this.url=methodAndUrl[1];
        System.out.println(method);
        System.out.println(url);
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getProto() {
        return proto;
    }
    public void setProto(String proto) {
        this.proto = proto;
    }
    
}

