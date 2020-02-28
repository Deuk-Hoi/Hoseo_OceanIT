package com.oceanit.hoseo_oceanit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class PCwebsite extends AppCompatActivity {

    WebView pc_Web;
    Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcwebsite);
        tools = new Tools();
        pc_Web = (WebView) findViewById(R.id.pc_Web);
        WebSettings webset = pc_Web.getSettings(); //웹세팅
        webset.setJavaScriptEnabled(true);//자바스크립트 허용
        webset.setLoadWithOverviewMode(true);//컨텐츠가 웹뷰 보다 클경우 스크린크기에 맞춤
        pc_Web.setWebChromeClient(new WebChromeClient());
        pc_Web.loadUrl(tools.URL);

        pc_Web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)&&pc_Web.canGoBack())
        {
            pc_Web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
