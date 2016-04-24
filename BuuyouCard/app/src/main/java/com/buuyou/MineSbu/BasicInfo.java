package com.buuyou.MineSbu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buuyou.buuyoucard.R;

public class BasicInfo extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout emailcg,phonecg;
    TextView id,name,email,phone,QQ;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        sp=getSharedPreferences("data", Context.MODE_PRIVATE);
        emailcg=(RelativeLayout)findViewById(R.id.emailcg);
        phonecg=(RelativeLayout)findViewById(R.id.phonecg);
        id=(TextView)findViewById(R.id.id);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.myeamil);
        phone=(TextView)findViewById(R.id.myphone);
        QQ=(TextView)findViewById(R.id.QQ);
        id.setText(sp.getString("userid",null));
        name.setText(sp.getString("userRealName",null));
        email.setText(sp.getString("email",null));
        phone.setText(sp.getString("tel",null));
        QQ.setText(sp.getString("email",null));

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
