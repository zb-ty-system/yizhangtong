package org.self.io.aio;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月15日
 * @version 1.0
 */

public class AioRpcServer {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		int port = 8080;
		AioRpcRunnable rpcSvr = new AioRpcRunnable(port);
		new Thread(rpcSvr, "AioRpcServer").start();
	}
}
