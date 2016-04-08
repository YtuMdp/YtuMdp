package com.buuyou.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.buuyou.buuyoucard.R;


public class changepassword extends AppCompatActivity {
    private ImageView iv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        iv_back= (ImageView) findViewById(R.id.iv_changepassword_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
