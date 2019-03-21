package io.github.muxiaobai.io_tomcat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import io.github.muxiaobai.io_tomcat.util.XmlUtils;

/** 
 * 
 * @author zhang
 * @Date  2016年9月9日 下午9:17:33
 * @doing 
 */

public class TomcatServer {
    
    public static final HashMap<String, Object> servlet = new HashMap<String, Object>();
    public static final HashMap<String, Object> servletMapping = new HashMap<String, Object>();
    
   
    private void init() {
        XmlUtils xml = new XmlUtils(XmlUtils.class.getResource("/")+"web.xml");
        xml.init(servlet, servletMapping);
    }
     @SuppressWarnings("resource")
	private void start() throws IOException{
	    ServerSocket serverSocket = new ServerSocket();
	    this.init();
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        System.out.println("Tomcat Server:  http://localhost:8080");
        while(true){
            Socket socket = serverSocket.accept();
            SocketProcess socketProcess = new SocketProcess(socket);
            System.out.println("currentThread:"+Thread.currentThread());
            socketProcess.start();
        }
	 }
     
     public static void main(String[] args) throws IOException{
         TomcatServer  server= new TomcatServer();
         server.start();
     }
}
