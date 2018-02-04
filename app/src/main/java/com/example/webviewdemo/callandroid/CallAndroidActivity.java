package com.example.webviewdemo.callandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.webviewdemo.R;

/**
 * Created by ZhangXinmin on 2017/5/24.
 * Copyright (c) 2017 . All rights reserved.
 * JS调用Android代码：
 * 1.通过WebView的addJavascriptInterface（）进行对象映射
 * 2.通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
 * 3.通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）
 * 方法回调拦截JS对话框alert()、confirm()、prompt（） 消息
 */

public class CallAndroidActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        mWebView = (WebView) findViewById(R.id.webview_android);
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        mWebView.addJavascriptInterface(new Model("Android"), "model");//AndroidtoJS类对象映射到js的test对象

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/PrintLog.html");

    }
}
