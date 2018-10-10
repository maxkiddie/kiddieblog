/**
 * 
 */
package com.winter.netty.server.handler;

import java.util.Iterator;

import com.winter.netty.entity.Message;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 上午10:22:28
 */
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
	private final static ChannelGroup group = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + "进来了....");
		group.add(ctx.channel());
		Iterator<Channel> it = group.iterator();
		while (it.hasNext()) {
			System.out.println("成员:" + it.next().remoteAddress());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
		System.out.println(ctx.channel().remoteAddress() + "下线了....");
		group.remove(ctx.channel());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Message m = (Message) msg;
		System.out.println("服务端收到信息:" + m);
		group.writeAndFlush(m);
		ReferenceCountUtil.release(msg);
	}
}
