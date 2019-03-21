/**
 * Project Name:io-tomcat
 * File Name:SocketProcess.java
 * Package Name:io.github.muxiaobai.io_tomcat
 * Date:2019年3月21日上午11:59:27
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat;

import java.io.OutputStream;
import java.net.Socket;

import io.github.muxiaobai.io_tomcat.servlet.IServlet;
import io.github.muxiaobai.io_tomcat.util.Request;
import io.github.muxiaobai.io_tomcat.util.Response;
/**
 * ClassName:SocketProcess 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 上午11:59:27 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class SocketProcess extends Thread{
    protected  Socket socket;
    public SocketProcess(Socket socket){
        this.socket = socket;
    } 

    @Override
    public void run() {
        try {
            Request request = new Request(socket.getInputStream());
            Response response = new Response(socket.getOutputStream());
            request.init();
            //从映射中找
            System.out.println(request.getUrl());
            String urlMap = request.getUrl();
            String servelName = (String) TomcatServer.servletMapping.get(urlMap);
            System.out.println(servelName);
            
            if(servelName!=null && !servelName.isEmpty()) {
                 //映射有的话找到对应的对象
                  String clazz = (String) TomcatServer.servlet.get(servelName);
                  IServlet servlet = (IServlet) Class.forName(clazz).newInstance();
                  if(servlet!=null) {  
                    servlet.service(request,response);
                  }else {
                    System.out.println("找不到对应的servlet");
                  }
                
            }else {
                System.out.println("找不到对应的servletMapping");
            }
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(response.out());
            outputStream.flush();
            outputStream.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
