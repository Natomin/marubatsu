package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;

import gui.MarubatsuFrame;
import marubatsu.Marubatsu;

public class ClientSocketRunnable implements Runnable {
	private int x;
	private int y;
	private Socket socket = null;
	private PrintWriter writer = null;
	private MarubatsuFrame flame;

	public ClientSocketRunnable(MarubatsuFrame flame) {
		this.flame = flame;
	}
	//TODO
	//frameを持っているべきではないので修正する

	@Override
	public void run() {
		// ソケット生成＆待機
		try {
			socket = new Socket("localhost", 8000);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
		while (true) {
			String line = null;
			try {
				line = reader.readLine();
				x = Integer.parseInt(line);// 文字列を数値に変更
				System.out.println("x(line)>>" + line);
				line = reader.readLine();
				y = Integer.parseInt(line);// 文字列を数値に変更
				System.out.println("y(line)>>" + line);
				flame.setPutPiece(Marubatsu.MARU, x, y);
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
	}

	public void write() {
		String input = null;
		input = String.valueOf(x);
		writer.println(input);
		input = String.valueOf(y);
		writer.println(input);
	}

	public void setPutPlace(int a, int b) {
		x = a;
		y = b;
	}

}
