/**
 * 
 */
package com.winter.netty.client;

import java.util.concurrent.TimeUnit;

import com.winter.netty.client.handler.ClientHandler;
import com.winter.netty.client.handler.ConnectionHandler;
import com.winter.netty.coder.MessageDecoder;
import com.winter.netty.coder.MessageEncoder;
import com.winter.netty.handler.HeartBeatHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author xuzhaojie
 *
 *         2018年9月29日 上午9:56:06
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
	private static final HeartBeatHandler heartBeatHandler = new HeartBeatHandler();

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast("connection", new ConnectionHandler());
		p.addLast("idleState", new IdleStateHandler(0, 61, 0, TimeUnit.SECONDS));
		p.addLast("decoder", new MessageDecoder());
		p.addLast("encoder", new MessageEncoder());
		p.addLast("heartBeatHandler", heartBeatHandler);
		p.addLast(new ClientHandler());
	}

}
