package com.example.pongs_000.sskruactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by sonthaya-csit on 4/18/17.
 */

public class News extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        getSupportActionBar().setTitle(" ข่าวประชาสัมพันธ์");
        getSupportActionBar().setSubtitle(" มหาวิทยาลัยราชภัฏศรีสะเกษ");
        
        WebView myWebView = (WebView) findViewById(R.id.webview01);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://www.sskru.ac.th/2017/");
    }
}