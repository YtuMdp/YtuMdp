package com.xiaoku.show;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.xiaoku.weixiaotong.R;

public class Fragment extends FragmentActivity implements View.OnClickListener {
    //得到fragment管理器
   FragmentManager fm=this.getSupportFragmentManager();
    TextView tx1,tx2,tx3,tx4,tx5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        tx1=(TextView)findViewById(R.id.tx1);
        tx2=(TextView)findViewById(R.id.tx2);
        tx3=(TextView)findViewById(R.id.tx3);
        tx4=(TextView)findViewById(R.id.tx4);
        tx5=(TextView)findViewById(R.id.tx5);
        tx1.setOnClickListener(this);
        tx2.setOnClickListener(this);
        tx3.setOnClickListener(this);
        tx4.setOnClickListener(this);
        tx5.setOnClickListener(this);


    }
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tx4:
                android.support.v4.app.FragmentTransaction  ft4=fm.beginTransaction();
                SchoolNet sn=new SchoolNet();
                ft4.replace(R.id.fl, sn);
                ft4.commit();
                break;
            case R.id.tx5:
                android.support.v4.app.FragmentTransaction  ft=fm.beginTransaction();
                Fragment1 fg1=new Fragment1();
                ft.replace(R.id.fl, fg1);
                ft.commit();
                break;
            default:
                break;
        }
    }


}
