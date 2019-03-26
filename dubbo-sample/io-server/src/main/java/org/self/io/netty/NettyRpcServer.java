package org.self.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月16日
 * @version 1.0
 */

public class NettyRpcServer {
	public static void main(String args[]) throws Exception {
		new NettyRpcServer().bind(8080);
	}

	public void bind(int port) throws Exception {
		EventLoopGroup acceptorGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(acceptorGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		} finally {
			acceptorGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
