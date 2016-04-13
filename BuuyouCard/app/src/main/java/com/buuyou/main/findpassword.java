package com.buuyou.main;

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

import java.util.Timer;

public class findpassword extends AppCompatActivity {
    EditText email,phone, valiCode,newpass;
    private Button bt_next;
    private Button bt_yanzhengma;
    private String myemail,myphone,myvaliCode,result;
    //设置倒计时时间
    int count=60;
    Timer timer = new Timer();
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    bt_yanzhengma.setText("" + count+"s后重新获取");
                    bt_yanzhengma.setClickable(false);
                    break;
                case 1:
                    timer.cancel();
                    bt_yanzhengma.setClickable(true);
                    bt_yanzhengma.setText("重新获取");
                    count=30;break;
                case 2:
                    try {
                        JSONObject json=new JSONObject(result);
                        String status=json.getString("status");
                        if (status.equals("1")){
                            Toast.makeText(findpassword.this,"登陆后台成功",Toast.LENGTH_SHORT).show();
                        }else if(status.equals("0")){
                            Toast.makeText(findpassword.this,"登陆后台失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpassword);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        valiCode=(EditText)findViewById(R.id.valiCode);
        //newpass=(EditText)findViewById(R.id.newpass);
        bt_next= (Button) findViewById(R.id.bt_findpassword_next);
        bt_yanzhengma= (Button) findViewById(R.id.getvaliCode);

        //跳转到下一个界面
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                @Override
                    public void run() {
                        myvaliCode = valiCode.getText().toString().trim();
                        myemail = email.getText().toString().trim();
                        result = myHttpConect.urlconnect_getpass(myemail, myvaliCode);
                    Log.v("验证码",myvaliCode);
                    Log.v("邮箱",myemail);
                    Log.v("返回数据",result);
                        handler.sendEmptyMessage(2);
                   }
                }).start();
               // Intent intent =new Intent(findpassword.this,changepassword.class);
               // Bundle bundle=new Bundle();
                //传递name参数为tinyphp
              //  bundle.putString("eamil", myemail);
               // bundle.putString("valicode", myvaliCode);
              //  intent.putExtras(bundle);
               // startActivity(intent);

            }
        });
        //获取验证码
        bt_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 网络请求
                 */
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        myphone = phone.getText().toString().trim();
                        myemail = email.getText().toString().trim();
                       String data= myHttpConect.urlconnect_pass(myemail, myphone);
                        Log.v("mydata",data);
                    }
                }).start();
                /**
                 * 获取验证码后进行倒计时
                 */
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 60; i++) {
                            try {
                                count--;
                                handler.sendEmptyMessage(0);
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(1);
                    }
                }.start();
            }
        });


    }
}
