/**
 * 
 */
package com.winter.netty.handler;

import java.util.Date;

import com.winter.netty.entity.Header;
import com.winter.netty.entity.Message;
import com.winter.utils.DateUtil;

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
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof Message) {
			Message m = (Message) msg;
			if (m.getHeader().getCammand() == Header.HEART_BEAT_CAMMAND_PING) {
				System.out.println(
						"收到" + ctx.channel().remoteAddress() + "的心跳ping..." + DateUtil.getCurrentTimeFullStr());
				Message message = new Message(Header.HEART_BEAT_CAMMAND_PONG);
				ctx.writeAndFlush(message);// 发出心跳
			} else if (m.getHeader().getCammand() == Header.HEART_BEAT_CAMMAND_PONG) {
				System.out.println(
						"收到" + ctx.channel().remoteAddress() + "的心跳pong..." + DateUtil.getCurrentTimeFullStr());
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
				System.out.println(
						ctx.channel().remoteAddress() + "很久没读了,发送心跳ping..." + DateUtil.getCurrentTimeFullStr());
				Message message = new Message(Header.HEART_BEAT_CAMMAND_PING);
				ctx.writeAndFlush(message);// 发出心跳
			}
			if (state == IdleState.WRITER_IDLE) {
				System.out.println(
						ctx.channel().remoteAddress() + "很久没写了,发送心跳ping..." + DateUtil.getCurrentTimeFullStr());
				Message message = new Message(Header.HEART_BEAT_CAMMAND_PING);
				ctx.writeAndFlush(message);// 发出心跳
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

}
