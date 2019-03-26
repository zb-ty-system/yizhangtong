package org.self.io.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author zhuwenbing
 * @date 2017年3月12日
 * @version 1.0
 */

public class RpcServer {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		int port = 8080;
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
			while (true) {
				socket = server.accept();
				new Thread(new RpcServerHandler(socket)).start();
			}
		} finally {
			if (server != null) {
				server.close();
			}
		}

	}

}
