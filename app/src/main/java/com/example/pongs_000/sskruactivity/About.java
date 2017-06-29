package com.example.pongs_000.sskruactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sonthaya-csit on 4/18/17.
 */

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        getSupportActionBar().setTitle(" ทีมพัฒนา");
        getSupportActionBar().setSubtitle(" ระบบตรวจเช็คกิจกรรม");

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