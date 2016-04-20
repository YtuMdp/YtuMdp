package com.buuyou.MineSbu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.buuyou.buuyoucard.R;

public class SecurityCen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_cen);
        RelativeLayout safenum=(RelativeLayout)findViewById(R.id.safenum);
        safenum.setOnClickListener(this);
        RelativeLayout safeemail=(RelativeLayout)findViewById(R.id.safeemail);
        safeemail.setOnClickListener(this);
        RelativeLayout safephone=(RelativeLayout)findViewById(R.id.safephone);
        safephone.setOnClickListener(this);
        RelativeLayout myidentity=(RelativeLayout)findViewById(R.id.myidentity);
        myidentity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.safenum :
                Intent intent=new Intent(SecurityCen.this,ChangeSn.class);
                startActivity(intent);
            break;
            case R.id.safeemail :
                Intent intent2=new Intent(SecurityCen.this,ExchangeEmail.class);
                startActivity(intent2);
                break;
            case R.id.safephone :
                Intent intent3=new Intent(SecurityCen.this,ExchangePhone.class);
                startActivity(intent3);
                break;
            case R.id.myidentity :
                Intent intent4=new Intent(SecurityCen.this,Authentication.class);
                startActivity(intent4);
                break;
        }

    }
}
