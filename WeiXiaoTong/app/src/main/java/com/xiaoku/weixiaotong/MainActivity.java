package com.xiaoku.weixiaotong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoku.show.Fragment;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    SharedPreferences sp;
    String st1,st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle(" ");
       // setSupportActionBar(toolbar);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        sp=getSharedPreferences("data", MODE_PRIVATE);

    }
    public void bu(View view){
        SharedPreferences.Editor editor=sp.edit();
        st1=ed1.getText().toString().trim();
        st2=ed2.getText().toString().trim();
        editor.putString("num", st1);
        editor.putString("pass", st2);
        editor.commit();
        if (TextUtils.isEmpty(st1)||TextUtils.isEmpty(st2)){
            Toast.makeText(this,"账号和密码不得为空！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, Fragment.class);
            startActivity(intent);
        }
    }


}
