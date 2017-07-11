package com.example.pongs_000.sskruactivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

import static com.example.pongs_000.sskruactivity.Login.MY_PREFS;

public class ProfileActivity extends Activity {


    TextView name, stdid, department, faculty;
    SharedPreferences shared;
    ArrayList<HashMap<String, String>> MyArrList;
    ArrayList<HashMap<String, String>> MyArrListFac;

    ListView actclub,actfac;
    ProgressDialog progress;

    private static String Server = new Server().name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView back = (ImageView)findViewById(R.id.backmainact);
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
       shared = getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);

        actclub = (ListView) findViewById(R.id.lsclub);
        actfac = (ListView) findViewById(R.id.lsfac);
        getActclub();
//        getActFac();





        setValue();
    }

    private void setValue() {
        name.setText("ชื่อ " + shared.getString("prefix","") + shared.getString("name","") + "  "+shared.getString("sername",""));
        stdid.setText("รหัสนักศึกษา " + shared.getString("username",""));
        department.setText(shared.getString("department_name",""));
        faculty.setText(shared.getString("faculty_name",""));
    }


    public void getActclub() {
        RequestParams params = new RequestParams();
        params.put("username",shared.getString("username",""));

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Server+"pro_club_act.json.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = null;
                try {
                    MyArrList = new ArrayList<HashMap<String, String>>();
                    HashMap<String, String> map;
                    result = new String(responseBody, "UTF-8");
                    //Log.d("Check",result);
                    //content is json response that can be parsed.

                    if(result.equals("0"))
                    {
                        //lang = "ไม่มีคำศัพท์";
                        Log.d("Check","ไม่มีข้อมูลกิจกรรม");
//                        Toast.makeText(ProfileActivity.this, "ไม่มีข้อมูลกิจกรรม", Toast.LENGTH_SHORT).show();
                    }else{
                        JSONArray readerArray = new JSONArray(result);
                        //JSONObject obj = (JSONObject) readerArray.get(0);
                        //Log.d("Check",obj.getString("Translation"));
                        //lang = obj.getString("Translation");

                        for(int i = 0; i < readerArray.length(); i++){
                            JSONObject obj = readerArray.getJSONObject(i);
                            map = new HashMap<String, String>();
                            map.put("time_start",obj.getString("time_start"));
                            map.put("activity_name",obj.getString("activity_name"));
                            map.put("status","-");
                            MyArrList.add(map);
                        }
                    }


                    progress.dismiss();
                    SimpleAdapter sAdap;
                    sAdap = new SimpleAdapter(ProfileActivity.this, MyArrList, R.layout.profile_item,
                            new String[] {"time_start", "activity_name", "status"}, new int[] {R.id.tvdate, R.id.tvact, R.id.tvstatus});
                    actclub.setAdapter(sAdap);

                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                progress = ProgressDialog.show(ProfileActivity.this, "กำลังประมวลผล",
                        "รอสักครู่...กำลังโหลดข้อมูล", true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void getActFac() {
        RequestParams params = new RequestParams();
        params.put("username",shared.getString("username",""));
        params.put("faculty_id",shared.getString("faculty_name",""));
        params.put("department_id",shared.getString("department_id",""));



        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Server+"get_fac_act.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result2 = null;
                try {
                    MyArrListFac = new ArrayList<HashMap<String, String>>();
                    HashMap<String, String> map;
                    result2 = new String(responseBody, "UTF-8");
                    //Log.d("Check",result);
                    //content is json response that can be parsed.

                    if(result2.equals("0"))
                    {
                        //lang = "ไม่มีคำศัพท์";
                        Log.d("Check","ไม่มีข้อมูลกิจกรรม");
//                        Toast.makeText(ProfileActivity.this, "ไม่มีข้อมูลกิจกรรม", Toast.LENGTH_SHORT).show();
                    }else{
                        JSONArray readerArray = new JSONArray(result2);
                        //JSONObject obj = (JSONObject) readerArray.get(0);
                        //Log.d("Check",obj.getString("Translation"));
                        //lang = obj.getString("Translation");

                        for(int i = 0; i < readerArray.length(); i++){
                            JSONObject obj = readerArray.getJSONObject(i);
                            map = new HashMap<String, String>();
                            map.put("time_start",obj.getString("time_start"));
                            map.put("activity_name",obj.getString("activity_name"));
                            map.put("status","-");
                            MyArrListFac.add(map);
                        }
                    }


                    progress.dismiss();
                    SimpleAdapter sAdap;
                    sAdap = new SimpleAdapter(ProfileActivity.this, MyArrListFac, R.layout.profile_item,
                            new String[] {"time_start", "activity_name", "status"}, new int[] {R.id.tvdate, R.id.tvact, R.id.tvstatus});
                    actfac.setAdapter(sAdap);

                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                progress = ProgressDialog.show(ProfileActivity.this, "กำลังประมวลผล",
                        "รอสักครู่...กำลังโหลดข้อมูล", true);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
