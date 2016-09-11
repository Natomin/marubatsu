package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunnable implements Runnable{//Threadのコンストラクタの引数にする
	private int x;
	private int y;
	private int catchFlag = 0;

	@Override
	public void run(){
		//serverソケット生成＆待機
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
		//writer生成
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//reader生成
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//書き込み、読み込み処理のループclient側と逆にする）
		while(true){
			String line = null;
			try {
				line = reader.readLine();
				System.out.println("x(line)>>" + line);
				x = Integer.parseInt(line);//文字列を数値に変更
				line = reader.readLine();
				System.out.println("y(line)>>" + line);
				y = Integer.parseInt(line);//文字列を数値に変更
				catchFlag = 1;
			} catch (IOException e) {
				e.printStackTrace();
			} 
			if(line.equals("¥¥q")){break;}
			String input = null;
			while(catchFlag == 1){
				//待機用のループ
			}
			if(catchFlag == 0){
				input = String.valueOf(x);
				writer.println(input);
				input = String.valueOf(y);
				writer.println(input);
				if(input.equals("¥¥q")){break;}
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
	
	public int getCatchFlag(){
		return catchFlag;
	}
	
	public void setCatchFlag(int flag){
		catchFlag = flag;
	}

}

//メモ
//MarubatsuFrame内でsubThreadを作成、開始
//serverまたはclientを引数にする
//ボタンが押されたらmarubatsu1を書き換え→データを送信、Frame1に反映→marubatsu2の同期→Frame2に反映
