package socket;

public class Server implements Runnable{//Threadのコンストラクタの引数にする

	@Override
	public void run() {
		//書き込み、読み込み処理のループclient側と逆にする）
	}

}

//メモ
//MarubatsuFrame内でsubThreadを作成、開始
//serverまたはclientを引数にする
//ボタンが押されたらmarubatsu1を書き換え→データを送信、Frame1に反映→marubatsu2の同期→Frame2に反映
