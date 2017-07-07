package com.example.pongs_000.sskruactivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.pongs_000.sskruactivity.Login.MY_PREFS;

public class ProfileActivity extends Activity {


    TextView name, stdid, department, faculty;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button back = (Button)findViewById(R.id.backmainact);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Font fontChanger = new Font(getAssets(), "Itim-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        name = (TextView) findViewById(R.id.name);
        stdid = (TextView) findViewById(R.id.studentID);
        department = (TextView) findViewById(R.id.Branch);
        faculty = (TextView) findViewById(R.id.faculty);


       shared = getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);

        setValue();
    }

    private void setValue() {

//        editor.putString("username",username);
//        editor.putString("department_name",department_name);
//        editor.putString("faculty_name",faculty_name);
//        editor.putString("faculty_id",faculty_id);

        name.setText(shared.getString("name",""));
        stdid.setText(shared.getString("username",""));
        department.setText(shared.getString("department_name",""));
        faculty.setText(shared.getString("faculty_name",""));

    }
}
