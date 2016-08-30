package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import marubatsu.Marubatsu;

public class MarubatsuFrame extends JFrame {
	public static final int BUTTON_SIZE = 120;
	public static final int BOARD_SIZE = 3;
	public static JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	private static JLabel label = new JLabel();
	public static int[][] buttonfrag = new int[BOARD_SIZE][BOARD_SIZE];

	// コンストラクタ
	public MarubatsuFrame() {
		// frameの設定
		this.setBounds(50, 50, 375, 450);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// labelの設定
		label.setBounds(0, BUTTON_SIZE * BOARD_SIZE, 200, 50);
		this.add(label);
		// buttonsの設定
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				buttons[i][j] = new JButton(); // ボタン生成
				buttons[i][j].setBounds(i * BUTTON_SIZE, j * BUTTON_SIZE,
						BUTTON_SIZE, BUTTON_SIZE);
				buttonfrag[i][j] = Marubatsu.NONE; // buttonfragの初期化

				final int x = i;
				final int y = j;

				buttons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Marubatsu.player(x, y);// Event発生時の処理
					}
				});
				this.add(buttons[i][j]);
			}
		}
	}

	// プレイヤーをラベルに表示
	public static void playerlabel() {
		String text = null;
		if (Marubatsu.playerfrag == Marubatsu.MARU) {
			text = "次は○の番です。";
		} else if (Marubatsu.playerfrag == Marubatsu.BATSU) {
			text = "次は×の番です。";
		}
		label.setText(text);
		label.setFont(new Font("MS ゴシック", Font.BOLD,20));
	}

}
