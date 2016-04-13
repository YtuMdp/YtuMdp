package com.buuyou.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.buuyou.HttpConnect.myHttpConect;
import com.buuyou.buuyoucard.R;


public class changepassword extends AppCompatActivity {
    private ImageView iv_back;
    EditText newpass,againnewpass;
    String myeamil,myvaliCode,mynewpass,myagain;
    Button sure;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        iv_back= (ImageView) findViewById(R.id.iv_changepassword_back);
        newpass=(EditText)findViewById(R.id.newpass);
        againnewpass=(EditText)findViewById(R.id.againnewpass);
        sure=(Button)findViewById(R.id.bt_changepassword_sure);
        //得到输入的新密码
        mynewpass=newpass.getText().toString().trim();
        myagain=againnewpass.getText().toString().trim();
        //得到上个界面的邮箱地址和验证码
        Bundle bundle = this.getIntent().getExtras();
        myeamil = bundle.getString("eamil");
        myvaliCode = bundle.getString("valicode");
        //判断输入的新密码是否一致
        if(mynewpass.equals(myagain)){
            //新密码一致，执行下面操作
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  new Thread(new Runnable() {
                      @Override
                      public void run() {
                          String data=myHttpConect.urlconnect_updatapass(myeamil, myvaliCode, mynewpass);
                          Log.v("xinxi",data);
                      }
                  }).start();
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),"亲输入的密码一致!",Toast.LENGTH_SHORT).show();
        }

    }
}
