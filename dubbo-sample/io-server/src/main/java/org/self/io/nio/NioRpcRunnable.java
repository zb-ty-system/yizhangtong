package org.self.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.self.io.Constants;
import org.self.io.bio.RpcServerProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月14日
 * @version 1.0
 */

public class NioRpcRunnable implements Runnable {
	private Selector selector;
	private ServerSocketChannel svrChannel;
	private volatile boolean stop;
	private int backlog = 1024;
	private int readCapacity = 1024;

	public NioRpcRunnable(int port) {
		try {
			selector = Selector.open();
			svrChannel = ServerSocketChannel.open();
			svrChannel.configureBlocking(false);
			svrChannel.socket().bind(new InetSocketAddress(port), backlog);
			svrChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					it.remove();
					try {
						process(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void process(SelectionKey key) throws Exception {
		if (key.isValid()) {
			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if (key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(readCapacity);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String request = new String(bytes, Constants.CHARSET_UTF8);
					JSONArray requestArray = (JSONArray) JSON.parse(request);
					Integer[] requestNums = requestArray.toArray(new Integer[requestArray.size()]);
					write(sc, JSON.toJSON(new RpcServerProcessor().sum(requestNums)));
				} else if (readBytes < 0) {
					// channel is closed by peer
					key.cancel();
					sc.close();
				} else {
					// do nothing
				}
			}

		}

	}

	private void write(SocketChannel channel, Object response) throws Exception {
		if (response != null) {
			byte[] bytes = response.toString().getBytes(Constants.CHARSET_UTF8);
			ByteBuffer src = ByteBuffer.allocate(bytes.length + 1);
			src.put(bytes);
			src.put(Constants.ENTER_STR.getBytes(Constants.CHARSET_UTF8));
			src.flip();
			channel.write(src);
		}

	}

}
