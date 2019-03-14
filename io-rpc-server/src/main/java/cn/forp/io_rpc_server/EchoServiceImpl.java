package cn.forp.io_rpc_server;

import io.github.muxiaobai.io_rpc_common.EchoService;

/** 
 * 
 * @author zhang
 * @Date  2016年9月9日 下午9:00:16
 * @doing 
 */

public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String ping) {
		return ping!=null?ping+"--->I am ok!":"I am ok.";
	}

}
