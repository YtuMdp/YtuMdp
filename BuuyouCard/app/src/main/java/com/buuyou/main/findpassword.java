package com.buuyou.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buuyou.HttpConnect.myHttpConect;
import com.buuyou.buuyoucard.R;

import org.json.JSONException;
import org.json.JSONObject;


public class findpassword extends AppCompatActivity {
    private Button bt_next;
    EditText ed_phone;
    SharedPreferences sp;
    String email;
    String result_data;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 0:

                   try {
                       JSONObject jsn = new JSONObject(result_data);
                       String status = jsn.getString("status");
                       if(status.equals("1")){
                           Toast.makeText(findpassword.this, "验证成功！", Toast.LENGTH_SHORT).show();
                       }else if (status.equals("0")){
                           Toast.makeText(findpassword.this, "验证失败！", Toast.LENGTH_SHORT).show();
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
                   break;
           }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpassword);
        sp=getSharedPreferences("data", Context.MODE_PRIVATE);
        ed_phone=(EditText)findViewById(R.id.ed_findpassword);
        bt_next= (Button) findViewById(R.id.bt_findpassword_next);
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone=ed_phone.getText().toString().trim();
                         email=sp.getString("phone",null);
                        result_data= myHttpConect.urlconnect_pass(email, phone);
                        Log.v("info",result_data);
                        handler.sendEmptyMessage(0);
                    }
                }).start();
                //Intent intent =new Intent(findpassword.this,securitycode.class);
               // startActivity(intent);

            }
        });
    }
}
