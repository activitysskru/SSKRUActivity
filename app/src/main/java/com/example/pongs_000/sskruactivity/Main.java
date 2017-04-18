package com.example.pongs_000.sskruactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sonthaya-csit on 4/18/17.
 */

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Button tonews = (Button)findViewById(R.id.button5);
        tonews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, News.class);
                startActivity(intent);

            }
        });

        Button tocalendar = (Button)findViewById(R.id.button);
        tocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Calendar.class);
                startActivity(intent);

            }
        });

        Button toprofile = (Button)findViewById(R.id.button6);
        toprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        Button toabout = (Button)findViewById(R.id.button2);
        toabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, About.class);
                startActivity(intent);

            }
        });

    }
}