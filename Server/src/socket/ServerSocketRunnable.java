package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import gui.MarubatsuFrame;
import marubatsu.Marubatsu;

public class ServerSocketRunnable implements Runnable {
	private int x;
	private int y;
	ServerSocket server = null;
	private Socket socket = null;
	PrintWriter writer = null;
	private MarubatsuFrame frame;
	
	public ServerSocketRunnable(MarubatsuFrame frame){
		this.frame = frame;
	}
	//TODO
	//ここでframeを持っているべきではないので修正する（フラグを立ててflame側からx,yをgetするようにしたい）

	@Override
	public void run() {
		// serverソケット生成＆待機
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
		try {
			socket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// writer生成
		try {
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// reader生成
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {//read処理はループで常に行うwrite処理は必要な時にメソッドで行う
			String line = null;
			try {
				line = reader.readLine();
				System.out.println("x(line)>>" + line);
				x = Integer.parseInt(line);// 文字列を数値に変更
				line = reader.readLine();
				System.out.println("y(line)>>" + line);
				y = Integer.parseInt(line);// 文字列を数値に変更
				frame.setPutPiece(Marubatsu.BATSU, x, y);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line.equals("¥¥q")) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
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

	public void write() {
		String input = null;
		input = String.valueOf(x);
		writer.println(input);
		input = String.valueOf(y);
		writer.println(input);
	}

	public Socket getSocket() {
		return socket;
	}

	public void setPutPlace(int a, int b) {
		x = a;
		y = b;
	}
}
