package org.self.io.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.self.io.Constants;
import org.self.io.RequestProcessor;

import com.alibaba.fastjson.JSON;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月12日
 * @version 1.0
 */

public class RemoteRequestProcessor implements RequestProcessor {

	public Long sum(Integer... request) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1", 8080);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Constants.CHARSET_UTF8));
			out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Constants.CHARSET_UTF8)));
			out.println(JSON.toJSONString(request));
			out.flush();
			Object sum = JSON.parse(in.readLine());
			return Long.valueOf(sum.toString());
		} catch (Exception e) {
			throw new RuntimeException("Remote process exception", e);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
