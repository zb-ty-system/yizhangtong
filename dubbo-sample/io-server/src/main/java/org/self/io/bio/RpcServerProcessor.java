package org.self.io.bio;

import org.self.io.RequestProcessor;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月12日
 * @version 1.0
 */

public class RpcServerProcessor implements RequestProcessor {
	public Long sum(Integer... request) {
		long sum = 0;
		for (int i : request) {
			sum += i;
		}
		return sum;
	}

}
