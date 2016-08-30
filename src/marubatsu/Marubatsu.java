package marubatsu;
import gui.MarubatsuFrame;

import java.awt.Font;

public class Marubatsu {
	public static final int NONE = 0;
	public static final int MARU = 1;
	public static final int BATSU = 2;
	public static int playerfrag = MARU;

	public static void player(int x,  int y) {
		if (MarubatsuFrame.buttonfrag[x][y] == NONE) {//盤上になにもないから置いてOK
			if (playerfrag == MARU) {//○の番
				MarubatsuFrame.buttons[x][y].setText("○");
				MarubatsuFrame.buttons[x][y].setFont(new Font("MS ゴシック", Font.BOLD,50));
				MarubatsuFrame.buttonfrag[x][y] = MARU;
				playerfrag = BATSU;//次は×の番
				MarubatsuFrame.playerlabel();
			} else if (playerfrag == BATSU) {//×の番
				MarubatsuFrame.buttons[x][y].setText("×");
				MarubatsuFrame.buttons[x][y].setFont(new Font("MS ゴシック", Font.BOLD,70));
				MarubatsuFrame.buttonfrag[x][y] = BATSU;
				playerfrag = MARU;//次は○の番
				MarubatsuFrame.playerlabel();
			}
		}
	}
}


