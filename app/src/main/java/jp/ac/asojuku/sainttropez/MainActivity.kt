package jp.ac.asojuku.sainttropez

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

        }
        return super.onContextItemSelected(item)
    }
}
