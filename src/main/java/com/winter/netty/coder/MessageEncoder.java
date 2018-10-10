/**
 * 
 */
package com.winter.netty.coder;

import com.winter.netty.entity.Header;
import com.winter.netty.entity.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 上午10:19:08
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
		Header header = msg.getHeader();
		out.writeByte(Header.PACKAGE_TAG);
		out.writeByte(header.getEncode());
		out.writeByte(header.getEncrypt());
		out.writeByte(header.getExtend1());
		out.writeByte(header.getExtend2());
		out.writeByte(header.getCammand());
		if (msg.getData() == null || "".equals(msg.getData())) {
			out.writeInt(0);
		} else {
			out.writeInt(header.getLength());
			out.writeBytes(msg.getData().getBytes("UTF-8"));
		}
	}
}
