package org.self.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月15日
 * @version 1.0
 */

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioRpcRunnable> {

	@Override
	public void completed(AsynchronousSocketChannel result, AioRpcRunnable attachment) {
		attachment.asynchronousServerSocketChannel.accept(attachment, this);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result));
	}

	@Override
	public void failed(Throwable exc, AioRpcRunnable attachment) {
		exc.printStackTrace();
		attachment.latch.countDown();
	}

}
