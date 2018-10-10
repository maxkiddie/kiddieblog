/**
 * 
 */
package com.winter.netty.client.handler;

import com.winter.netty.entity.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xuzhaojie
 *
 *         2018年9月29日 上午9:56:43
 */
public class ClientHandler extends SimpleChannelInboundHandler<Message> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.err.println(ctx.channel().remoteAddress() + "远程链接出现异常...");
		ctx.channel().close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		System.out.println("客户端收到了:" + msg);
	}
}