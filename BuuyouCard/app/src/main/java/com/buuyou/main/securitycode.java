package com.buuyou.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.buuyou.buuyoucard.R;

public class securitycode extends AppCompatActivity {
    private Button bt_next;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securitycode);
        bt_next= (Button) findViewById(R.id.bt_securitycode_next);
        iv_back= (ImageView) findViewById(R.id.iv_security_back);
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(securitycode.this,changepassword.class);
                startActivity(intent);

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
