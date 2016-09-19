package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import gui.MarubatsuFrame;
import marubatsu.Marubatsu;

public class ServerReaderRunnable implements Runnable {
	private Socket socket;
	private MarubatsuFrame frame;
	private int x;
	private int y;

	public ServerReaderRunnable(Socket soc, MarubatsuFrame frame) {
		socket = soc;
		this.frame = frame;
	}

	@Override
	public void run() {
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

		}

	}
}
