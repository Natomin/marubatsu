package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import gui.MarubatsuFrame;

public class ServerWriterRunnable implements Runnable {
	private int x;
	private int y;
	private Thread readerThread;
	MarubatsuFrame frame;

	public ServerWriterRunnable(MarubatsuFrame frame) {
		this.frame = frame;
	}

	@Override
	public void run() {
		// serverソケット生成＆待機
		ServerSocket server = null;
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			server.bind(new InetSocketAddress("localhost", 8000));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Socket socket = null;
		try {
			socket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//readerのsubThread開始
		readerThread = new Thread(new ServerReaderRunnable(socket, frame));
		readerThread.start();
		// writer生成
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPutPlace(int a, int b) {
		x = a;
		y = b;
	}	
}
