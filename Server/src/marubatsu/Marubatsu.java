package marubatsu;
import gui.MarubatsuFrame;


public class Marubatsu {
	public static final int NONE = 0;
	public static final int MARU = 1;
	public static final int BATSU = 2;
	private MarubatsuFrame frame;
	private int playerflag = MARU;
	
	//ボード上の状態を記録しておく配列
	private int[][] board = new int[MarubatsuFrame.BOARD_SIZE][MarubatsuFrame.BOARD_SIZE];
	public Marubatsu(MarubatsuFrame frame){//コンストラクタ
		this.frame = frame;
		for(int i=0; i<MarubatsuFrame.BOARD_SIZE; i++){
			for(int j=0; j<MarubatsuFrame.BOARD_SIZE; j++){
				board[i][j] = NONE;
			}
		}
	}

	public void putPiece(int player, int x,  int y) {
		if (board[x][y] == NONE){//盤上になにもないから置いてOK
			if (player == MARU) {//○の番
				board[x][y] = MARU;
				playerflag = BATSU;
			}else if(player == BATSU){
				board[x][y] = BATSU;
				playerflag = MARU;
			}
		}
		frame.update();
		frame.playerlabel();
	}
	
	public int getBoardStat(int x, int y){
		return board[x][y];
	}
	
	public int getPlayer(){
		return playerflag;
	}
}