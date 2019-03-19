package io.github.muxiaobai.io_rpc_common;

import io.github.muxiaobai.io_rpc_common.RPCExporter;

/** 
 * 
 * @author zhang
 * @Date  2016年9月9日 下午9:17:33
 * @doing 
 */

public class RPCServer {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
				    System.out.println("server listener at port : " + 8080);
					RPCExporter.exporter("localhost", 8080);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
	}
}
