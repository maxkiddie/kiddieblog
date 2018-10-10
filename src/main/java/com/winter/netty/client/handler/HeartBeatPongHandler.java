/**
 * 
 */
package com.winter.netty.client.handler;

import com.winter.netty.entity.Header;
import com.winter.netty.entity.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

/**
 * @author xuzhaojie
 *
 *         2018年10月10日 上午10:01:04
 */
@Sharable
public class HeartBeatPongHandler extends SimpleChannelInboundHandler<Message> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		if (msg.getHeader().getCammand() == Header.HEART_BEAT_CAMMAND) {
			System.out.println("收到" + ctx.channel().remoteAddress() + "的心跳ping...");
			Header heartBeatHeader = new Header();
			heartBeatHeader.setCammand(Header.HEART_BEAT_CAMMAND);
			Message message = new Message(heartBeatHeader);
			ctx.writeAndFlush(message);// 发出心跳
		}
	}
}
