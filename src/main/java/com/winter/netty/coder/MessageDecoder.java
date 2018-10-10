/**
 * 
 */
package com.winter.netty.coder;

import java.util.List;

import com.winter.netty.entity.Header;
import com.winter.netty.entity.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 上午10:18:17
 */
public class MessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		buffer.markReaderIndex();
		if (buffer.readableBytes() < Header.HEAD_LENGHT) {
			System.out.println("包长度问题");
			buffer.resetReaderIndex();
			return;
		}
		byte tag = buffer.readByte();
		if (tag != Header.PACKAGE_TAG) {
			System.out.println("标志错误");
			return;
		}
		byte encode = buffer.readByte();
		byte encrypt = buffer.readByte();
		byte extend1 = buffer.readByte();
		byte extend2 = buffer.readByte();
		byte cammand = buffer.readByte();
		int length = buffer.readInt();
		if (buffer.readableBytes() < length) {
			System.out.println("接收数据长度不足够");
			buffer.resetReaderIndex();
			return;
		}
		Header header = new Header(tag, encode, encrypt, extend1, extend2, cammand, length);
		byte[] data = null;
		Message message = null;
		if (length > 0) {
			data = new byte[length];
			buffer.readBytes(data);
			message = new Message(header, new String(data, "UTF-8"));
		} else {
			message = new Message(header);
		}
		out.add(message);
	}
}
