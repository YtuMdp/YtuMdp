package com.buuyou.MineSbu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.buuyou.HttpConnect.myHttpConect;
import com.buuyou.buuyoucard.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelRate extends AppCompatActivity {
    ListView listview;
    SharedPreferences sp;
    private String result;
    String[] ChannelName;
    String[] ChannelRate;
    String[] ChannelState;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result_data= (String) msg.obj;
            try {
                JSONObject json=new JSONObject(result_data);
                String status=json.getString("status");
                if(status.equals("1")){
                    JSONArray myarray=json.getJSONArray("data");
                    ChannelName=new String[myarray.length()];
                    ChannelRate=new String[myarray.length()];
                    ChannelState=new String[myarray.length()];
                    for (int i=0;i<myarray.length();i++){
                        ChannelName[i]=myarray.getJSONObject(i).getString("ChannelName");
                        ChannelRate[i]=myarray.getJSONObject(i).getString("ChannelRate");
                        //判断是否为开启状态
                        if(myarray.getJSONObject(i).getString("ChannelState").equals("1")){
                            ChannelState[i]="开启";
                        }else if (myarray.getJSONObject(i).getString("ChannelState").equals("0")){
                            ChannelState[i]="关闭";
                        }
                    }
                    listview.setAdapter(new Myadapter());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_rate);
        listview=(ListView)findViewById(R.id.listview);

        sp=getSharedPreferences("data", Context.MODE_PRIVATE);
        connet();
    }
    public class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ChannelName.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=View.inflate(ChannelRate.this,R.layout.channel_listview,null);
            TextView lefttext= (TextView) view.findViewById(R.id.left);
            TextView centertext= (TextView) view.findViewById(R.id.center);
            TextView righttext= (TextView) view.findViewById(R.id.right);
            lefttext.setText(ChannelName[position]);
            centertext.setText(ChannelRate[position]);
            righttext.setText(ChannelState[position]);
            return view;
        }
    }


    //获取服务器数据
    public void  connet(){
          new  Thread(new Runnable() {
              @Override
              public void run() {
                  String email=sp.getString("email",null);
                  String pass=sp.getString("pass",null);
                  result = myHttpConect.urlconnect_channel(email, pass);
                  Message msg=Message.obtain();
                  msg.obj=result;
                  handler.sendMessage(msg);
              }
          }).start();
    }
}


