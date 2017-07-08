package com.example.pongs_000.sskruactivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import static com.example.pongs_000.sskruactivity.Login.MY_PREFS;

/**
 * Created by sonthaya-csit on 4/18/17.
 */

public class Main extends Activity {

    String year, faculty , department;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        shared = getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);

        year = shared.getString("username","");
        faculty = shared.getString("faculty_id","");
        department = shared.getString("depatment_id","");

        String category = year.substring(0,3) + faculty + department;
        Toast.makeText(Main.this,category, Toast.LENGTH_LONG).show();


        FirebaseMessaging.getInstance().subscribeToTopic(category);


        Font fontChanger = new Font(getAssets(), "Itim-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        Button back = (Button)findViewById(R.id.backmain);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        ImageView tonews = (ImageView) findViewById(R.id.button5);
        tonews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, News.class);
                startActivity(intent);

            }
        });

        ImageView tocalendar = (ImageView)findViewById(R.id.button);
        tocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Calendar.class);
                startActivity(intent);

            }
        });

        ImageView toprofile = (ImageView)findViewById(R.id.button6);
        toprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        ImageView toabout = (ImageView)findViewById(R.id.button2);
        toabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, About.class);
                startActivity(intent);

            }
        });

    }

    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ออกจากแอป ?");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage("ต้องการออกจากแอปพลิเคชัน หรือไม่  ?");
        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setAction(Intent.ACTION_MAIN);
                startActivity(intent);
            }
        });

        dialog.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}