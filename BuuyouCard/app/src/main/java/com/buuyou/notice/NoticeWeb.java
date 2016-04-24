package com.buuyou.notice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.buuyou.buuyoucard.R;

public class NoticeWeb extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_web);
        webView= (WebView) findViewById(R.id.show);
        String path=getIntent().getStringExtra("path");
        webView.loadUrl(path);
    }
}
