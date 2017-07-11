package com.example.pongs_000.sskruactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sonthaya-csit on 4/18/17.
 */

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

//        getSupportActionBar().setTitle(" ทีมพัฒนา");
//        getSupportActionBar().setSubtitle(" ระบบตรวจเช็คกิจกรรม");
        ImageView back = (ImageView)findViewById(R.id.backmainabout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Font fontChanger = new Font(getAssets(), "Itim-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        TextView csit = (TextView)findViewById(R.id.csit56);
        csit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(About.this ,Fbcsit.class );
                startActivity(facebookIntent);
            }
        });
        TextView csit1 = (TextView)findViewById(R.id.textView5);
        csit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(About.this ,Fbcsit.class );
                startActivity(facebookIntent);
            }
        });
    }
}