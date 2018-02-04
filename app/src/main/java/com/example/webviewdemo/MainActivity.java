package com.example.webviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.webviewdemo.callandroid.CallAndroidActivity;
import com.example.webviewdemo.calljs.CallJSActicity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initParamsAndValues();
        initViews();
    }

    private void initParamsAndValues() {
        mContext = this;
    }

    private void initViews() {
        findViewById(R.id.btn_android).setOnClickListener(this);
        findViewById(R.id.btn_js).setOnClickListener(this);
        findViewById(R.id.btn_text).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_android://调用Android
                Intent android = new Intent(mContext, CallAndroidActivity.class);
                startActivity(android);
                break;
            case R.id.btn_js://调用JS
                Intent js = new Intent(mContext, CallJSActicity.class);
                startActivity(js);
                break;
            case R.id.btn_text:
                Intent text = new Intent(mContext, CallJSActicity.class);
                startActivity(text);
                break;
        }
    }
}
