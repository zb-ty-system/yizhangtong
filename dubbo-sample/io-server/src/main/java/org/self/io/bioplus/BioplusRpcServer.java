package org.self.io.bioplus;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.self.io.bio.RpcServerHandler;

/**
 * 
 * @author zhuwenbing
 * @date 2017年3月14日
 * @version 1.0
 */

public class BioplusRpcServer {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		int port = 8080;
		int maxPoolsize = 64;
		long keepAliveTime = 600L;
		int queueSize = 8192;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// default port
			}
		}
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			Socket socket = null;
			ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolsize,
					keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
			while (true) {
				socket = server.accept();
				executor.execute(new RpcServerHandler(socket));
			}
		} finally {
			if (server != null) {
				server.close();
			}
		}
	}
}
