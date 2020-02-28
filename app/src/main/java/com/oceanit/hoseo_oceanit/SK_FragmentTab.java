package com.oceanit.hoseo_oceanit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

public class SK_FragmentTab extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        Log.e("this.getTag()",this.getTag());
        WebView web = (WebView) v.findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        WebSettings webset = web.getSettings(); //웹세팅
        webset.setJavaScriptEnabled(true);//자바스크립트 허용
        webset.setLoadWithOverviewMode(true);//컨텐츠가 웹뷰 보다 클경우 스크린크기에 맞춤
        webset.setUseWideViewPort(true); //웹뷰가 wide viewport를 사용하도록 설정
        webset.setBuiltInZoomControls(true); //안드로이드에서 제공하는 줌 아이콘을 사용할 수 있도록 설정
        webset.setSupportZoom(true); //확대 축소 기능을 사용할 수 있도록 설정하는 속성
        if(this.getTag().equals("tab1")) {
            web.loadUrl("http://ec2-15-164-231-37.ap-northeast-2.compute.amazonaws.com:8080/deviceMain.do");
        }
        else if(this.getTag().equals("tab2"))
        {
            web.loadUrl("http://ec2-15-164-231-37.ap-northeast-2.compute.amazonaws.com:8080/deviceManagement.do");
        }
        else
        {
            web.loadUrl("http://ec2-15-164-231-37.ap-northeast-2.compute.amazonaws.com:8080/deviceLog.do");
        }
        return v;

    }
}