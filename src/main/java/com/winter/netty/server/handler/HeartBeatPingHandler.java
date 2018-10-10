/**
 * 
 */
package com.winter.netty.server.handler;

import com.winter.netty.entity.Header;
import com.winter.netty.entity.Message;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 下午4:40:29
 */
@Sharable
public class HeartBeatPingHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof Message) {
			Message m = (Message) msg;
			if (m.getHeader().getCammand() == Header.HEART_BEAT_CAMMAND) {
				System.out.println("收到" + ctx.channel().remoteAddress() + "的心跳pong...");
			} else {
				ctx.fireChannelRead(msg);
			}
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleState state = ((IdleStateEvent) evt).state();
			if (state == IdleState.READER_IDLE) {
				System.out.println(ctx.channel().remoteAddress() + "很久没读了,发送心跳");
				Header heartBeatHeader = new Header();
				heartBeatHeader.setCammand(Header.HEART_BEAT_CAMMAND);
				Message message = new Message(heartBeatHeader);
				ctx.writeAndFlush(message);// 发出心跳
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

}
