/**
 * 
 */
package com.winter.netty.client.handler;

import com.winter.netty.client.NettyClient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuzhaojie
 *
 *         2018年10月10日 上午10:01:04
 */
public class ConnectionHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyClient.attempts = 0;
		System.out.println(ctx.channel().remoteAddress() + "链路连接成功...");
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.err.println(ctx.channel().remoteAddress() + "远程链路断开了,现在进行重连...");
		ctx.fireChannelInactive();
		NettyClient.connect();
	}
}
