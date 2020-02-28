package com.oceanit.hoseo_oceanit;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;


public class Oceandata_hompage extends FragmentActivity {

    private FragmentTabHost mTabHost;
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oceandata);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("DashBored", null),
                SK_FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Network", null),
                SK_FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Log", null),
                SK_FragmentTab.class, null);
    }

    @Override
    public void onBackPressed() {
        web = (WebView)findViewById(R.id.webView);
        if(web.canGoBack())
        {
            web.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
