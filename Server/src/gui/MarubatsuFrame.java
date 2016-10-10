package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import marubatsu.Marubatsu;
import socket.ServerSocketRunnable;

public class MarubatsuFrame extends JFrame {
	public static final int BUTTON_SIZE = 120;
	public static final int BOARD_SIZE = 3;
	public JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	private JLabel label = new JLabel();
	public int[][] buttonflag = new int[BOARD_SIZE][BOARD_SIZE];
	private Marubatsu marubatsu;
	private ServerSocketRunnable serverRun;
	private Thread subThread;
	private int a, b;

	// コンストラクタ
	public MarubatsuFrame() {
		marubatsu = new Marubatsu(this);
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
				buttons[i][j].setBounds(i * BUTTON_SIZE, j * BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
				buttonflag[i][j] = Marubatsu.NONE; // buttonflagの初期化

				final int x = i;
				final int y = j;

				buttons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						marubatsu.putPiece(Marubatsu.MARU, x, y);// Event発生時の処理
						serverRun.setPutPlace(x, y);
						serverRun.write();
						playerlabel();
					}
				});
				this.add(buttons[i][j]);
			}
		}
		update();
		playerlabel();
		// サブスレッドでserverのrunメソッドを動かす
		subThread = new Thread(serverRun = new ServerSocketRunnable(this));
		subThread.start();
	}

	// プレイヤーをラベルに表示
	public void playerlabel() {
		String text = null;
		if (marubatsu.getPlayer() == Marubatsu.MARU) {
			text = "次は○の番です。";
		} else if (marubatsu.getPlayer() == Marubatsu.BATSU) {
			text = "次は×の番です。";
		}
		label.setText(text);
		label.setFont(new Font("MS ゴシック", Font.BOLD, 20));
	}

	public void update() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				int stat = marubatsu.getBoardStat(i, j);
				if (stat == Marubatsu.MARU) {
					buttons[i][j].setText("○");
					buttons[i][j].setFont(new Font("MS ゴシック", Font.BOLD, 50));
				} else if (stat == Marubatsu.BATSU) {
					buttons[i][j].setText("×");
					buttons[i][j].setFont(new Font("MS ゴシック", Font.BOLD, 70));
				} else if (stat == Marubatsu.NONE) {
					buttons[i][j].setText(" ");
				}
			}
		}
	}

	public void setPutPiece(int player, int x, int y) {
		a = x;
		b = y;
		marubatsu.putPiece(player, a, b);
	}
}
