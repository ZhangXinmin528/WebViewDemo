package com.example.webviewdemo.callandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webviewdemo.R;

import java.sql.Time;
import java.util.HashMap;
import java.util.Set;

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
    private Context context;
    private WebView mWebView;

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        context = this;

        mWebView = (WebView) findViewById(R.id.webview_android);
        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //存在严重的漏洞问题：
        //参数1：Javascript对象名
        //参数2：Java对象名
        mWebView.addJavascriptInterface(new Model("Android"), "model");//AndroidtoJS类对象映射到js的test对象

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/callAndroid.html");

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {

                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        Log.i("tag==", "js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                    }

                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
