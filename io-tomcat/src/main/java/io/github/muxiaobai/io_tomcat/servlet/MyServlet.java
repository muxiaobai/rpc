/**
 * Project Name:io-tomcat
 * File Name:MyServlet.java
 * Package Name:io.github.muxiaobai.io_tomcat.servlet
 * Date:2019年3月21日下午12:02:09
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat.servlet;

import io.github.muxiaobai.io_tomcat.util.Request;
import io.github.muxiaobai.io_tomcat.util.Response;

/**
 * ClassName:MyServlet 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午12:02:09 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class MyServlet extends AbstractServlet{

    @Override
    public void doGet(Request request, Response response) {
        response.setWrite("22werwere222");
    }

    @Override
    public void doPost(Request request, Response response) {
    }

}

