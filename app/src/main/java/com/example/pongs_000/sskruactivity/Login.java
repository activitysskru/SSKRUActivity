package com.example.pongs_000.sskruactivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Login extends AppCompatActivity {

    private EditText user, pass;
    private TextInputLayout inputLayoutUsername, inputLayoutPassword;
    private Button btnLogin;

    private static String TAG = "MyService";
    private static String Server = new Server().name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getSupportActionBar().setTitle(" SSKRU ACTIVITY");
        getSupportActionBar().setSubtitle(" ลงชื่อเข้าใช้");

        if( getIntent().getBooleanExtra("Exit me", false)){
            return;
        }

        final DB myDb = new DB(this);
        myDb.getWritableDatabase(); // First method

        List<DB.sMembers> MebmerList = myDb.ReadLogin();
        if(MebmerList != null)
        {
            for (DB.sMembers mem : MebmerList) {

                inputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
                inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
                EditText user = (EditText) findViewById(R.id.username);
                EditText pass = (EditText) findViewById(R.id.password);



                user.setText(mem.gUser());
                pass.setText(mem.gPass());
                new onLoad().execute(Server + "login.json.php?user=" + user.getText().toString() + "&pass=" + pass.getText().toString());
            }
        }


        Button button = (Button)findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                submitForm();

                EditText user = (EditText) findViewById(R.id.username);
                EditText pass = (EditText) findViewById(R.id.password);

                user.addTextChangedListener(new MyTextWatcher(user));
                pass.addTextChangedListener(new MyTextWatcher(pass));
                myDb.DeleteLogin();
                myDb.WriteLogin(user.getText().toString(), pass.getText().toString());

                new onLoad().execute(Server + "login.json.php?user=" + user.getText().toString() + "&pass=" + pass.getText().toString());
            }
        });

//        submitForm();
    }

    private void submitForm() {
        if (!validateUsername()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
    }


    private boolean validateUsername() {
        if (user.getText().toString().trim().isEmpty()) {
            inputLayoutUsername.setError(getString(R.string.err_msg_name));
            requestFocus(user);
            return false;
        } else {
            inputLayoutUsername.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (pass.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(pass);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.username:
                    validateUsername();
                    break;
                case R.id.password:
//                    validatePassword();
                    break;
            }
        }
    }

    private class onLoad extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog pd;
        boolean con = false;
        String id = null;
        String name = null;
        String sername = null;
        private String message;

        @Override
        protected void onPreExecute()
        {
            pd = new ProgressDialog(Login.this);
            pd.setTitle("กำลังทำงาน");
            pd.setMessage("โหลดข้อมูล...");
            pd.show();
        }

        @Override
        protected Void doInBackground(String... params)
        {
            String jString;
            try {
                jString = getJsonFromUrl(params[0]);

                if (jString != null) {

                    JSONArray jArray = new JSONArray(jString);
                    for (int i=0; i < jArray.length() ; i++ ) {
                        JSONObject jObj = jArray.getJSONObject(i);
                        id = jObj.getString("user_id");
                        name = jObj.getString("name");
                        sername = jObj.getString("lname");
                    }// for
                    con = true;
                }// if

            } catch (IOException e) {
                //MessageBox("การเชื่อมต่อผิดพลาด");
                message = e.toString();
            } catch (JSONException e) {
                //MessageBox("การรับส่งผิดพลาด");
                message = e.toString();
            }catch (Exception e) {
                //Log.d(TAG, "Problem reading " +  ex.getLocalizedMessage());
                //MessageBox("การเชื่อมต่อผิดพลาด");
                message = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v)
        {
            pd.dismiss();
            if(con!=false && id!=null)
            {
                Intent i = new Intent(Login.this, Main.class);
                startActivity(i);
                 Toast toast = Toast.makeText(Login.this,"ยินดีต้อนรับคุณ "+name +" "+ sername, Toast.LENGTH_LONG);
                 toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 610);
                i.putExtra("fullname",name);
                i.putExtra("username",sername);
                toast.show();

            }
            else if (con == false){
                MessageBox("การเชื่อมต่อผิดพลาด");
                //MessageBox(message);
            }
            else {
                MessageBox("รหัสผ่านผิด");
            }
        }
    }

    private String getJsonFromUrl(String strUrl) throws IOException {

        URL url = new URL(strUrl);
        try {
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setConnectTimeout(6*1000);
            httpCon.connect();

            int responseCode = httpCon.getResponseCode();
            //Log.d(TAG, "The response is: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK){
                //Log.d(TAG, " size: " + httpCon.getContentLength());

                InputStream ins = httpCon.getInputStream();
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(ins,"UTF-8"));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append("\n");
                    //Log.d(TAG, line);
                }
                rd.close();
                return response.toString();
            }

        } catch (Exception ex) {
            //Log.d(TAG, "Problem reading " +  ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
        return null;
    }

    public void MessageBox(String str) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(str);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }

    protected void onActivityResult ( int requestCode, int resultCode, Intent intent )
    {
        super.onActivityResult ( requestCode, resultCode, intent );
        if ( requestCode == 1)
        {
            finish();
        }
    }
    public void onBackPressed() {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("ออกจากแอป...");
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
