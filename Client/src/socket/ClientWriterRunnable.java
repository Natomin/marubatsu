package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.MarubatsuFrame;
import marubatsu.Marubatsu;

public class ClientWriterRunnable implements Runnable {
	private int x;
	private int y;
	MarubatsuFrame flame;

	public ClientWriterRunnable(MarubatsuFrame flame) {
		this.flame = flame;
	}

	@Override
	public void run() {
		// ソケット生成＆待機
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8000);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// readerThread生成、開始
		Thread readerThread = new Thread(new ClientReaderRunnable(flame, socket));
		readerThread.start();

		// writer生成
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 書き込み処理
		while (true) {
			String input = null;
			input = String.valueOf(x);
			writer.println(input);
			input = String.valueOf(y);
			writer.println(input);
			if (input.equals("¥¥q")) {
				break;
			}
		}
	}

	public void setPutPlace(int a, int b) {
		x = a;
		y = b;
	}

}
