package com.example.emcc.testh5map;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView=findViewById(R.id.webview);
        mWebView.loadUrl("file:///android_asset/test.html");

        WebSettings settings = mWebView.getSettings();
// 允许调用 JS，因为网页地图使用的是 JS 定位
        settings.setJavaScriptEnabled(true);
// 允许使用数据库
        settings.setDatabaseEnabled(true);
        settings.setGeolocationEnabled(true);
        String dir = getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath(); settings.setGeolocationDatabasePath(dir);
        settings.setDomStorageEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                // 这里是处理是否同意定位权限，可以在这里写一个 AlertDialog 来模仿浏览器弹出来的定位权限申请。
                //public void invoke(String origin, boolean allow, boolean retain);
                callback.invoke(origin, true, false);
            }

        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });




    }
}
