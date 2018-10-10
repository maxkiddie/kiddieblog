/**
 * 
 */
package com.winter.netty.client;

import com.winter.netty.client.handler.ClientHandler;
import com.winter.netty.client.handler.ConnectionHandler;
import com.winter.netty.client.handler.HeartBeatPongHandler;
import com.winter.netty.coder.MessageDecoder;
import com.winter.netty.coder.MessageEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author xuzhaojie
 *
 *         2018年9月29日 上午9:56:06
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
	private static final HeartBeatPongHandler heartBeatHandler = new HeartBeatPongHandler();

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast("connection", new ConnectionHandler());
		p.addLast("decoder", new MessageDecoder());
		p.addLast("encoder", new MessageEncoder());
		p.addLast("heartBeatHandler", heartBeatHandler);
		p.addLast(new ClientHandler());
	}

}
