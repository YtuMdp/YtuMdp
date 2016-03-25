package com.xiaoku.weixiaotong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.xiaoku.show.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp=getSharedPreferences("data",MODE_PRIVATE);
        String num=sp.getString("num",null);
        if(TextUtils.isEmpty(num)){
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                }
            };
            timer.schedule(task,3000);
        }else {
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this, Fragment.class);
                    startActivity(intent);
                }
            };
            timer.schedule(task,3000);
        }
    }
}
