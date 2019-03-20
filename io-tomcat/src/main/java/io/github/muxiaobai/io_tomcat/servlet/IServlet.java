/**
 * Project Name:io-tomcat
 * File Name:IServlet.java
 * Package Name:io.github.muxiaobai.io_tomcat.servlet
 * Date:2019年3月20日下午3:01:02
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat.servlet;
/**
 * ClassName:IServlet 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月20日 下午3:01:02 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public interface IServlet {
    public void init();
    public void service();
    public void destory();
}

