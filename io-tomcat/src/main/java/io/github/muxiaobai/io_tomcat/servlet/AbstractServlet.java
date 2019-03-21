/**
 * Project Name:io-tomcat
 * File Name:AbstractServlet.java
 * Package Name:io.github.muxiaobai.io_tomcat.servlet
 * Date:2019年3月20日下午3:01:41
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat.servlet;

import io.github.muxiaobai.io_tomcat.util.Request;
import io.github.muxiaobai.io_tomcat.util.Response;

/**
 * ClassName:AbstractServlet 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月20日 下午3:01:41 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public abstract class AbstractServlet implements IServlet {

    @Override
    public void init() {
        
        // TODO Auto-generated method stub
        
    }

    @Override
    public void service(Request request,Response response) {
        if("GET".equalsIgnoreCase(request.getMethod())){
            this.doGet( request, response);
        }
    }

    @Override
    public void destory() {
        
        // TODO Auto-generated method stub
        
    }
    public abstract void doGet(Request request,Response response);

    public abstract void doPost(Request request,Response response);
    
    
}

