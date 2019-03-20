package io.github.muxiaobai.io_tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import io.github.muxiaobai.io_tomcat.util.Request;
import io.github.muxiaobai.io_tomcat.util.Response;

/** 
 * 
 * @author zhang
 * @Date  2016年9月9日 下午9:17:33
 * @doing 
 */

public class TomcatServer {
	@SuppressWarnings("resource")
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        System.out.println("Tomcat Server ：http://localhost:8080");
	    while(true){
    	    Socket socket = serverSocket.accept();
    	    System.out.println("currentThread:"+Thread.currentThread());
    	    InputStream inputStream = socket.getInputStream();
    	    OutputStream outputStream = socket.getOutputStream();
    	    Request request = new Request(inputStream);
    	    request.init();
    	    Response response = new Response(outputStream);
    	    response.setWrite("hello");
    	    outputStream.write(response.out());
    	    outputStream.flush();
    	    outputStream.close();
    	    socket.close();
	    }
	}
}
