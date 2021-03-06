package marubatsu;
import gui.MarubatsuFrame;

import java.awt.Font;

public class Marubatsu {
	public static final int NONE = 0;
	public static final int MARU = 1;
	public static final int BATSU = 2;
	public int playerfrag;
	
	//ボード上の状態を記録しておく配列
	private int[][] board = new int[MarubatsuFrame.BOARD_SIZE][MarubatsuFrame.BOARD_SIZE];
	public Marubatsu(){//コンストラクタ
		playerfrag = MARU;
		for(int i=0; i<MarubatsuFrame.BOARD_SIZE; i++){
			for(int j=0; j<MarubatsuFrame.BOARD_SIZE; j++){
				board[i][j] = NONE;
			}
		}
	}
	
	public int getPlayer(){
		return playerfrag;
	}

	public void player(int x,  int y) {
		if (board[x][y] == NONE){//盤上になにもないから置いてOK
			if (playerfrag == MARU) {//○の番
				board[x][y] = MARU;
				playerfrag = BATSU;//次は×の番
			}else if(playerfrag == BATSU){
				board[x][y] = BATSU;
				playerfrag = MARU;//次は○の番
			}
		}
	}
	
	public int getBoardStat(int x, int y){
		return board[x][y];
	}
}
