/**
 * 
 */
package com.winter.netty.server;

import java.util.concurrent.TimeUnit;

import com.winter.netty.coder.MessageDecoder;
import com.winter.netty.coder.MessageEncoder;
import com.winter.netty.server.handler.HeartBeatPingHandler;
import com.winter.netty.server.handler.ServerHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author xuzhaojie
 *
 *         2018年9月29日 上午9:56:06
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

	private static final HeartBeatPingHandler heartBeatHandler = new HeartBeatPingHandler();

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast("idleState", new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
				.addLast("encoder", new MessageEncoder()).addLast("decoder", new MessageDecoder())
				.addLast("heartBeat", heartBeatHandler).addLast("serverHandler", new ServerHandler());
	}

}
