package org.self.io.netty;

import org.self.io.Constants;
import org.self.io.bio.RpcServerProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月16日
 * @version 1.0
 */

public class NettyRpcServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		String request = new String(bytes, Constants.CHARSET_UTF8);
		JSONArray requestArray = (JSONArray) JSON.parse(request);
		Integer[] requestNums = requestArray.toArray(new Integer[requestArray.size()]);
		ByteBuf src = Unpooled.copiedBuffer(
				(JSON.toJSONString(new RpcServerProcessor().sum(requestNums)) + "\n").getBytes(Constants.CHARSET_UTF8));
		ctx.write(src);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}
