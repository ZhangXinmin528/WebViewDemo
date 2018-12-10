package com.example.webviewdemo.callandroid;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by ZhangXinmin on 2017/5/24.
 * Copyright (c) 2017 . All rights reserved.
 */

public class Model {
    private String name;

    public Model(String name) {
        this.name = name;
    }

    @JavascriptInterface
    public void printLog() {
        Log.i("tag==", "JS调用了Android中的printLog方法！");
    }

    @JavascriptInterface
    public void printString() {
        Log.i("tag==", "Model{" +
                "name='" + name + '\'' +
                '}');
    }
}
