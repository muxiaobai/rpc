package io.github.muxiaobai.io_rpc_common;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 * @author zhang
 * @Date 2016年9月9日 下午9:17:33
 * @doing
 */

public class RPCImporter<S> {
	@SuppressWarnings("unchecked")
	public S importer(final Class<?> serviceClass, final InetSocketAddress address) {
		return (S)Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[] { serviceClass.getInterfaces()[0] },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Socket socket = null;
						ObjectOutputStream objectOutputStream = null;
						ObjectInputStream objectInputStream = null;
						try {
							socket=new Socket();
							socket.connect(address);
							objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
							objectOutputStream.writeUTF(serviceClass.getName());
							objectOutputStream.writeUTF(method.getName());
							objectOutputStream.writeObject(method.getParameterTypes());
							objectOutputStream.writeObject(args);
							objectInputStream=new ObjectInputStream(socket.getInputStream());
							return objectInputStream.readObject();
						} finally {
							if(socket!=null){
								socket.close();
							}
							if(objectInputStream!=null){
								objectInputStream.close();
							}
							if(objectOutputStream!=null){
								objectOutputStream.close();
							}
							
						}
					
					}

				});
	}
}
