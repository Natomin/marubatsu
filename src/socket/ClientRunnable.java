package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRunnable implements Runnable{// Threadのコンストラクタの引数にする
	private int x;
	private int y;
	public int catchFlag = 1;

	@Override
	public void run(){
		//ソケット生成＆待機
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8000);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
		
		//書き込み、読み込み処理のループserver側と逆にする）
		while(true){
			String input = null;
			while(catchFlag == 1){
				//待機用のループ
			}
			if(catchFlag == 0){
				input = String.valueOf(x);
				System.out.println("x>>" + input);
				writer.println(input);
				input = String.valueOf(y);
				System.out.println("y>>" + input);
				writer.println(input);
				if(input.equals("¥¥q")){break;}
			}
			String line = null;
			try {
				line = reader.readLine();
				x = Integer.parseInt(line);//文字列を数値に変更
				line = reader.readLine();
				y = Integer.parseInt(line);//文字列を数値に変更
				catchFlag = 1;
			} catch (IOException e) {
				e.printStackTrace();
			} 
			System.out.println("client>>" + line);
			System.out.println("input>>");
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
	
	public int getCatchFlag(){
		return catchFlag;
	}
	
	public void setCatchFlag(int flag){
		catchFlag = flag;
	}

}