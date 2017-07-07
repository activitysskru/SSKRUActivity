package com.example.pongs_000.sskruactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by sonthaya-csit on 4/18/17.
 */

public class Calendar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        Button back = (Button)findViewById(R.id.backmaincalendar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Font fontChanger = new Font(getAssets(), "Itim-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        WebView myWebView = (WebView) findViewById(R.id.webview02);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://csit.itsisaket.com/calendar_csit.php");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
}