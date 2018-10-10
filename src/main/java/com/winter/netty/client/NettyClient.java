/**
 * 
 */
package com.winter.netty.client;

import java.util.Timer;
import java.util.TimerTask;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author xuzhaojie
 *
 *         2018年9月30日 下午2:31:21
 */
public class NettyClient {

	public static final String HOST = "localhost";
	public static final Integer PORT = 8989;

	private static final byte TRY_ATTEMPTS = 12;
	public static byte attempts = 0;

	private static Timer timer = new Timer();

	public static void main(String[] args) {
		connect();
	}

	public static void connect() {
		Bootstrap boot = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			boot.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ClientChannelInitializer());
			ChannelFuture future = null;
			synchronized (NettyClient.class) {
				try {
					future = boot.connect(HOST, PORT).sync();
					attempts = 0;
					future.channel().closeFuture().sync();
				} catch (Exception e) {
					System.err.println(HOST + ":" + PORT + "远程连接失败...");
					attempts++;
					if (attempts <= TRY_ATTEMPTS) {
						timer.schedule(new TimerTask() {
							public void run() {
								System.err.println("服务端链接不上，开始第" + attempts + "次重连操作...");
								NettyClient.connect();
							}
						}, 1000);
					} else {
						timer.cancel();
						System.out.println("尝试次数已够,关闭客户端！");
					}
				}
			}
		} finally {
			group.shutdownGracefully();
		}
	}

}
