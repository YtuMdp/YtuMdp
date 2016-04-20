package com.buuyou.MineSbu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.buuyou.buuyoucard.R;

public class BasicInfo extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout emailcg,phonecg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        emailcg=(RelativeLayout)findViewById(R.id.emailcg);
        phonecg=(RelativeLayout)findViewById(R.id.phonecg);
        emailcg.setOnClickListener(this);
        phonecg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.emailcg:
                Intent intent=new Intent(BasicInfo.this,ExchangeEmail.class);
                startActivity(intent);
                break;
            case R.id.phonecg:
                Intent intent1=new Intent(BasicInfo.this,ExchangePhone.class);
                startActivity(intent1);
                break;
        }
    }
}
