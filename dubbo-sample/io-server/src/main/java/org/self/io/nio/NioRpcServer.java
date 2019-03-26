package org.self.io.nio;

/**
 * 
 * @author zhuwenbing
 * @date 2017年3月14日
 * @version 1.0
 */

public class NioRpcServer {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		int port = 8080;
		NioRpcRunnable rpcSvr = new NioRpcRunnable(port);
		new Thread(rpcSvr, "NioRpcServer").start();
	}
}
