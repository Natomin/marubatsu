package marubatsu;
import gui.MarubatsuFrame;


public class Marubatsu {
	public static final int NONE = 0;
	public static final int MARU = 1;
	public static final int BATSU = 2;
	public int playerflag;
	
	//ボード上の状態を記録しておく配列
	private int[][] board = new int[MarubatsuFrame.BOARD_SIZE][MarubatsuFrame.BOARD_SIZE];
	public Marubatsu(){//コンストラクタ
		playerflag = MARU;
		for(int i=0; i<MarubatsuFrame.BOARD_SIZE; i++){
			for(int j=0; j<MarubatsuFrame.BOARD_SIZE; j++){
				board[i][j] = NONE;
			}
		}
	}
	
	public int getPlayer(){
		return playerflag;
	}

	public void putPiece(int x,  int y) {
		if (board[x][y] == NONE){//盤上になにもないから置いてOK
			if (playerflag == MARU) {//○の番
				board[x][y] = MARU;
				playerflag = BATSU;//次は×の番
			}else if(playerflag == BATSU){
				board[x][y] = BATSU;
				playerflag = MARU;//次は○の番
			}
		}
	}
	
	public int getBoardStat(int x, int y){
		return board[x][y];
	}
}
