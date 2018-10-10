/**
 * 
 */
package com.winter.netty.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 上午10:16:14
 */
public class Message {
	private Header header;
	private String data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Message(Header header) {
		this.header = header;
	}

	public Message(Header header, String data) {
		this.header = header;
		this.data = data;
	}

	public byte[] toByte() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(Header.PACKAGE_TAG);
		out.write(header.getEncode());
		out.write(header.getEncrypt());
		out.write(header.getExtend1());
		out.write(header.getExtend2());
		out.write(header.getCammand());
		try {
			if (data == null || "".equals(data)) {
				out.write(intToByte(0));
			} else {
				byte[] bytes = data.getBytes("utf-8");
				out.write(intToByte(bytes.length));
				out.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static byte[] intToByte(int newint) {
		byte[] src = new byte[4];
		src[0] = (byte) ((newint >> 24) & 0xFF);
		src[1] = (byte) ((newint >> 16) & 0xFF);
		src[2] = (byte) ((newint >> 8) & 0xFF);
		src[3] = (byte) (newint & 0xFF);
		return src;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [header=" + header + ", data=" + data + "]";
	}

	public static void main(String[] args) {
		byte[] bs = intToByte(6);
		for (byte b : bs) {
			System.err.print(b);
		}
	}
}
