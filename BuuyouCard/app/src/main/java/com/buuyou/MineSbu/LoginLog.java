package com.buuyou.MineSbu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.buuyou.buuyoucard.R;

public class LoginLog extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_log);
        listview=(ListView)findViewById(R.id.listview);
        listview.setAdapter(new Myadapter());
    }

    public class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(LoginLog.this,R.layout.log_listview,null);
            TextView adress= (TextView) view.findViewById(R.id.adress);
            TextView time= (TextView) view.findViewById(R.id.adress);
            return view;
        }
    }
}
