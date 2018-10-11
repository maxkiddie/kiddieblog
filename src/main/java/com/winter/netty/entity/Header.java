/**
 * 
 */
package com.winter.netty.entity;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 上午10:15:55
 */
public class Header {
	// 包长度志头
	public static final int HEAD_LENGHT = 10;
	// 标志头
	public static final byte PACKAGE_TAG = 0x01;

	public static final byte HEART_BEAT_CAMMAND_PING = 1;
	public static final byte HEART_BEAT_CAMMAND_PONG = 2;
	private byte tag;
	/* 编码 */ private byte encode;
	/* 加密 */ private byte encrypt;
	/* 其他字段 */ private byte extend1;
	/* 其他2 */ private byte extend2;
	/* 命令 */ private byte cammand;
	/* 包的长度 */ private int length;

	public Header() {
		this.tag = PACKAGE_TAG;
		this.encode = 0;
		this.encrypt = 0;
		this.extend1 = 0;
		this.extend2 = 0;
		this.cammand = 0;
		this.length = 0;
	}

	/**
	 * @param tag
	 * @param encode
	 * @param encrypt
	 * @param extend1
	 * @param extend2
	 * @param cammand
	 * @param length
	 */
	public Header(byte tag, byte encode, byte encrypt, byte extend1, byte extend2, byte cammand, int length) {
		super();
		this.tag = tag;
		this.encode = encode;
		this.encrypt = encrypt;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.cammand = cammand;
		this.length = length;
	}

	/**
	 * @return the tag
	 */
	public byte getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(byte tag) {
		this.tag = tag;
	}

	/**
	 * @return the encode
	 */
	public byte getEncode() {
		return encode;
	}

	/**
	 * @param encode
	 *            the encode to set
	 */
	public void setEncode(byte encode) {
		this.encode = encode;
	}

	/**
	 * @return the encrypt
	 */
	public byte getEncrypt() {
		return encrypt;
	}

	/**
	 * @param encrypt
	 *            the encrypt to set
	 */
	public void setEncrypt(byte encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * @return the extend1
	 */
	public byte getExtend1() {
		return extend1;
	}

	/**
	 * @param extend1
	 *            the extend1 to set
	 */
	public void setExtend1(byte extend1) {
		this.extend1 = extend1;
	}

	/**
	 * @return the extend2
	 */
	public byte getExtend2() {
		return extend2;
	}

	/**
	 * @param extend2
	 *            the extend2 to set
	 */
	public void setExtend2(byte extend2) {
		this.extend2 = extend2;
	}

	/**
	 * @return the cammand
	 */
	public byte getCammand() {
		return cammand;
	}

	/**
	 * @param cammand
	 *            the cammand to set
	 */
	public void setCammand(byte cammand) {
		this.cammand = cammand;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Header [tag=" + tag + ", encode=" + encode + ", encrypt=" + encrypt + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", cammand=" + cammand + ", length=" + length + "]";
	}

}
