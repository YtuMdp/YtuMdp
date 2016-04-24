package com.buuyou.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.buuyou.HttpConnect.myHttpConect;
import com.buuyou.buuyoucard.R;
import com.buuyou.fragment.BlankFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mylogin extends AppCompatActivity {
    EditText phone,pass;
    Button login;
    TextView findpassword;
    String result_data;
    SharedPreferences sp;

    Handler handler=new Handler() {
        public  void handleMessage(Message msg){
      switch (msg.what) {
          case 3:

          try {
              JSONObject json = new JSONObject(result_data);
              String status = json.getString("status");
           //登陆成功   status等于1
              if (status.equals("1")) {
                  SharedPreferences.Editor editor=sp.edit();
                  Toast.makeText(Mylogin.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(Mylogin.this, BlankFragment.class);
                  startActivity(intent);
                 //获得json中的data数据
                  JSONArray data=json.getJSONArray("data");

                  for (int i=0;i<data.length();i++){
                      JSONObject temp = (JSONObject) data.get(i);
                      editor.putString("userid",temp.getString("Userid"));
                      editor.putString("checkCode",temp.getString("CheckCode"));
                      editor.putString("lastTimes", temp.getString("LastTimes"));
                      editor.putString("userRealName", (String) temp.get("UserRealName"));
                      editor.putString("QQ",(String) temp.get("MsnQQ"));
                      editor.putString("tel", (String) temp.get("RealTel"));
                      editor.putString("email", (String) temp.get("Email"));
                      editor.commit();
                  }
                  finish();
                  //登录失败 status等于0
              } else if (status.equals("0")) {
                  Toast.makeText(Mylogin.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
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
        sp=getSharedPreferences("data", Context.MODE_PRIVATE);
        boolean bl=sp.getBoolean("isboolean",true);
        //第一次进入应用
        if(bl){
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("isboolean", false);
            editor.commit();
           init();
        }else {
            Intent intent=new Intent(Mylogin.this,BlankFragment.class);
            startActivity(intent);
            finish();
        }
    }


    public void init(){
        findpassword=(TextView)findViewById(R.id.findpass);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //输入的账号和密码
                final  String  myphone = phone.getText().toString().trim();
                final String mypass=pass.getText().toString().trim();
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("email",myphone);
                editor.putString("pass",mypass);
                editor.commit();
                //判断账号格式是否正确 如果错误给出提示
                if (TextUtils.isEmpty(myphone)||TextUtils.isEmpty(mypass)){
                    Toast.makeText(Mylogin.this,"请输入正确账号或密码!",Toast.LENGTH_SHORT).show();
                }
                else{
                    //账号正确开启子线程，连接数据库，解析数据
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        //调用myHttpConect的urlconnect方法
                        result_data= myHttpConect.urlconnect(myphone, mypass);
                        handler.sendEmptyMessage(3);
                    }
                }).start();
                }
            }
        });
        findpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mylogin.this, findpassword.class);
                startActivity(intent);
            }
        });
    }


}
