package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.MarubatsuFrame;
import marubatsu.Marubatsu;

public class ClientReaderRunnable implements Runnable{
	private int x;
	private int y;
	private MarubatsuFrame frame;
	private Socket socket;
	
	public ClientReaderRunnable(MarubatsuFrame frame, Socket socket) {
		this.frame = frame;
		this.socket = socket;
	}

	@Override
	public void run(){
		
		//reader生成
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//書き込み、読み込み処理のループserver側と逆にする）
		while(true){
			String line = null;
			try {
				line = reader.readLine();
				x = Integer.parseInt(line);//文字列を数値に変更
				System.out.println("x(line)>>" + line);
				line = reader.readLine();
				y = Integer.parseInt(line);//文字列を数値に変更
				System.out.println("y(line)>>" + line);
				frame.setPutPiece(Marubatsu.MARU, x, y);
			} catch (IOException e) {
				e.printStackTrace();
			} 
			if(line.equals("¥¥q")){break;}
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPutPlace(int a, int b){
		x = a;
		y = b;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
