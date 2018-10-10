/**
 * 
 */
package com.winter.netty;

/**
 * @author xuzhaojie
 *
 *         2018年10月9日 上午10:25:40
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.winter.netty.coder.MessageDecoder;
import com.winter.netty.entity.Header;
import com.winter.netty.entity.Message;

public class TestClient {

	public static void main(String[] args) throws InterruptedException {
		try {
			// 连接到服务器
			Socket socket = new Socket("127.0.0.1", 8989);

			try {
				// 向服务器端发送信息的DataOutputStream
				OutputStream out = socket.getOutputStream();
				// 装饰标准输入流，用于从控制台输入
				Scanner scanner = new Scanner(System.in);
				while (true) {
					String send = scanner.nextLine();
					System.out.println("客户端：" + send);
					byte[] by = send.getBytes("UTF-8");
					System.out.println(send + "的字节长度为" + by.length);
					Header header = new Header((byte) Header.PACKAGE_TAG, (byte) 2, (byte) 3, (byte) 4, (byte) 5,
							(byte) 6, by.length);
					Message message = new Message(header, send);
					for (Byte b : message.toByte()) {
						out.write(b);
						System.out.println(b);
						Thread.sleep(1000);
					}
					out.flush();
				}

			} finally {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
