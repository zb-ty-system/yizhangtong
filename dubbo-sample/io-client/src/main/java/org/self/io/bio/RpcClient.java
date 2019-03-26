package org.self.io.bio;

import org.self.io.RequestProcessor;

/**
 * 
 * @author zhuwenbing
 * @date 2017年3月12日
 * @version 1.0
 */

public class RpcClient {
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) {
		Integer[] request = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		RequestProcessor processor = null;
		processor = new LocalRequestProcessor();
		long timeCost = System.currentTimeMillis();
		long localSum = processor.sum(request);
		System.out.println("localSum: " + localSum + ", " + "timeCost: " + (System.currentTimeMillis() - timeCost));

		processor = new RemoteRequestProcessor();
		timeCost = System.currentTimeMillis();
		long remoteSum = processor.sum(request);
		System.out.println("remoteSum: " + remoteSum + ", " + "timeCost: " + (System.currentTimeMillis() - timeCost));
	}

}
