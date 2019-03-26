package org.self.io.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.self.io.Constants;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月12日
 * @version 1.0
 */

public class RpcServerHandler implements Runnable {
	private Socket socket;

	public RpcServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), Constants.CHARSET_UTF8));
			out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Constants.CHARSET_UTF8)));
			String request = null;
			while (true) {
				request = in.readLine();
				if (request == null)
					break;
				JSONArray requestArray = (JSONArray) JSON.parse(request);
				Integer[] requestNums = requestArray.toArray(new Integer[requestArray.size()]);
				out.println(JSON.toJSON(new RpcServerProcessor().sum(requestNums)));
				out.flush();
			}
		} catch (Exception e) {
			if (this.socket != null) {
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
