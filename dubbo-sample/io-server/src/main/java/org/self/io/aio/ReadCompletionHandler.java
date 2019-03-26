package org.self.io.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.self.io.Constants;
import org.self.io.bio.RpcServerProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月15日
 * @version 1.0
 */

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel channel;

	public ReadCompletionHandler(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		try {
			attachment.flip();
			byte[] bytes = new byte[attachment.remaining()];
			attachment.get(bytes);
			String request = new String(bytes, Constants.CHARSET_UTF8);
			JSONArray requestArray = (JSONArray) JSON.parse(request);
			Integer[] requestNums = requestArray.toArray(new Integer[requestArray.size()]);
			write(JSON.toJSON(new RpcServerProcessor().sum(requestNums)));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private void write(Object response) {
		try {
			if (response != null) {
				byte[] bytes = response.toString().getBytes(Constants.CHARSET_UTF8);
				ByteBuffer src = ByteBuffer.allocate(bytes.length + 1);
				src.put(bytes);
				src.put(Constants.ENTER_STR.getBytes(Constants.CHARSET_UTF8));
				src.flip();
				channel.write(src, src, new CompletionHandler<Integer, ByteBuffer>() {
					@Override
					public void completed(Integer result, ByteBuffer buffer) {
						if (buffer.hasRemaining()) {
							channel.write(buffer, buffer, this);
						}
					}

					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						failed(exc, attachment);
					}
				});
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		exc.printStackTrace();
		try {
			this.channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
