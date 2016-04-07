package com.buuyou.buuyoucard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buuyou.HttpConnect.MainActivity;
import com.buuyou.HttpConnect.myHttpConect;

import org.json.JSONException;
import org.json.JSONObject;

public class Mylogin extends AppCompatActivity {
    EditText phone,pass;
    Button login;
    String result_data;

    Handler handler=new Handler() {
        public  void handleMessage(Message msg){
      switch (msg.what) {
          case 3:

          try {
              JSONObject jsn = new JSONObject(result_data);
              String status = jsn.getString("status");
              if (status.equals("1")) {
                  Toast.makeText(Mylogin.this, "登陆成功！", Toast.LENGTH_SHORT);
                  Intent intent=new Intent(Mylogin.this, MainActivity.class);
                  startActivity(intent);

              } else if (status.equals("0")) {
                  Toast.makeText(Mylogin.this, "登录失败！", Toast.LENGTH_SHORT);
              }
          } catch (JSONException e) {
              e.printStackTrace();
          }
              break;
      }
        };

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }


    public void init(){
        final String myphone=phone.getText().toString().trim();
        final String mypass=pass.getText().toString().trim();
        new Thread(new Runnable() {

            @Override
            public void run() {
                result_data= myHttpConect.urlconnect(myphone, mypass);
                Log.v("in", result_data);
                handler.sendEmptyMessage(3);
            }
        }).start();
    }


}
