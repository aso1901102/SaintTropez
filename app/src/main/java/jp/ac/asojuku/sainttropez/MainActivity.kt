package jp.ac.asojuku.sainttropez

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        //WecViewの利用
        //Webviewのjavascriptを有効にする
        webView.settings.javaScriptEnabled = true;

        //wecviewにwebページをURL指定でLoad
        webView.loadUrl("file:///android_asset/html/index.html");

        //コンテキストメニューをWebViewに紐づける
        this.registerForContextMenu(webView);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //選んだアイテムによって処理を分岐
        when(item?.itemId){
            //itemIdがtopの時
            R.id.top -> {
                //index.htmlを再読み込み
                webView.loadUrl("file:///android_asset/html/index.html");
            }

            //lunch01:エビフライの時
            R.id.lunch01 -> {
                webView.loadUrl("file:///android_asset/html/lunch01.html");
            }
            //lunch02:鶏の赤ワイン煮の時
            R.id.lunch02 -> {
                webView.loadUrl("file:///android_asset/html/lunch02.html");
            }
            //dinner01:海の幸の時
            R.id.dinner01 -> {
                webView.loadUrl("file:///android_asset/html/dinner01.html");
            }
            //dinner02:牛フィレ肉の時
            R.id.dinner02 -> {
                webView.loadUrl("file:///android_asset/html/dinner02.html");
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //コンテキストメニューのインスタンスが生成されるときに呼ばれるメソッド
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        this.menuInflater.inflate(R.menu.context,menu);
    }

    //コンテキストメニューをタップした時のメソッド
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            //SMS送信する
            R.id.sms -> {
                val number = ""/*送りたい番号*/
                val uri = Uri.parse("sms:$number");
                var intent = Intent(Intent.ACTION_VIEW)//SMSの送信アクション
                intent.data = uri;
                this.startActivity(intent);
                return true
            }
            //メール送信する
            R.id.mail -> {
                val email = "1901102@s.asojuku.ac.jp";       //送信先メールアドレスを定数で定義
                val subject = "予約問い合わせ";                 //件名
                val text = "以下の通り予約希望します。";         //本文の冒頭文字列
                val uri = Uri.parse("mailto:")  //URIとしてメール送信のプロトコルを指定
                //メール送信のアクションを指定したインテントを生成
                val intent = Intent(Intent.ACTION_SENDTO)
                //暗黙のインテントとしてdataとextraを追加
                //dataにURI（プロトコル）を設定
                intent.data = uri;
                //キーワードと値を指定しておまけデータ（Extra）を設定
                //メールアドレスは複数の可能性があるので配列にする
                intent.putExtra(Intent.EXTRA_EMAIL,arrayOf(email))
                        //メソッドチェーンで続けて件名のキーワードと値を設定
                      .putExtra(Intent.EXTRA_SUBJECT,subject)
                        //文章のキーワードと値を設定
                      .putExtra(Intent.EXTRA_TEXT,text);

                //アクションに対応するアプリが存在するか判定（今回はメールアプリの有無）
                if(intent.resolveActivity(this.packageManager) != null){
                    //対応するアプリが存在すればアプリを起動（今回はメールアプリ）
                    this.startActivity(intent);
                }
                return true;
            }

            //共有するとき
            R.id.shere -> {
                val text = "おいしいレストランを紹介します。";    //共有する文字列
                val intent = Intent(Intent.ACTION_SEND)         //共有するときのアクションを設定
                intent.type = "text/plain";                     //プレーン文字列として共有
                //インテントのおまけ情報（Extra）に共有文字列を設定
                intent.putExtra(Intent.EXTRA_TEXT,text);
                //共有方法をユーザーに選択させるためのインテント（chooser）を用意する
                val chooser = Intent.createChooser(intent,null);

                if(intent.resolveActivity(this.packageManager) != null){
                    //chooserの起動
                    this.startActivity(intent);
                }
                return true;
            }

            //ウェブサイトで開くとき
            R.id.brows -> {
                val url ="https://www.yahoo.co.jp";         //開きたいサイトのURL
                val intent = Intent(Intent.ACTION_VIEW)     //プラウザのアクション
                //ブラウザを開くためのプロトコルを指定
                intent.data = Uri.parse(url);               //httpプロトコルとして設定
                if(intent.resolveActivity(this.packageManager) != null){
                    //ブラウザを起動
                    this.startActivity(intent)
                }
                return true;
            }

        }
        return super.onContextItemSelected(item)
    }
}
