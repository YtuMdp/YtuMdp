package com.buuyou.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buuyou.HttpConnect.myHttpConect;
import com.buuyou.buuyoucard.R;
import com.buuyou.notice.NoticeWeb;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link noticeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link noticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class noticeFragment extends Fragment {
    TextView title,time,titletime;
    ImageView imageView;
    RelativeLayout relativeLayout;
    String mytitle,mytime,myreade,myimage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public noticeFragment() {
        // Required empty public constructor
    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 0:
                    String result_data= (String) msg.obj;
                    try {
                        JSONObject json=new JSONObject(result_data);
                        String status=json.getString("status");
                        if (status.equals("1")){
                            JSONArray myarray=json.getJSONArray("data");
                            for (int i=0;i<myarray.length();i++){
                                mytitle=myarray.getJSONObject(i).getString("title");
                                mytime=myarray.getJSONObject(i).getString("datatime");
                                myimage=myarray.getJSONObject(i).getString("picpath");
                                myreade=myarray.getJSONObject(i).getString("website");
                            }
                            title.setText(mytitle);
                            time.setText(mytime);
                            titletime.setText(mytime);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    String path="https://www.buuyou.com"+myimage;
                                    try {
                                        URL ul = new URL(path);
                                        HttpURLConnection connection = (HttpURLConnection) ul.openConnection();
                                        connection.setConnectTimeout(3000);
                                        connection.setRequestMethod("GET");
                                        connection.setDoInput(true);
                                        int code = connection.getResponseCode();
                                        if (code == 200) {
                                            InputStream in = connection.getInputStream();
                                            Message msg=Message.obtain();
                                            msg.obj= BitmapFactory.decodeStream(in);
                                            msg.what=1;
                                            handler.sendMessage(msg);
                                            in.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                               }
                            }).start();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    Bitmap bitmap= (Bitmap) msg.obj;
                    imageView.setImageBitmap(bitmap);
                    break;
            }

        }
    };



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment noticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static noticeFragment newInstance(String param1, String param2) {
        noticeFragment fragment = new noticeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notice, container, false);
        title= (TextView) view.findViewById(R.id.title);
        time= (TextView) view.findViewById(R.id.time);
        titletime= (TextView) view.findViewById(R.id.titletime);
        imageView= (ImageView) view.findViewById(R.id.image);
        relativeLayout= (RelativeLayout) view.findViewById(R.id.read);
        //连接公告后台
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data ="sss";
                String result= myHttpConect.urlconnect_notice(data, data);
                Log.v("++++++++++", result);
                Message msg=Message.obtain();
                msg.obj=result;
                msg.what=0;
                handler.sendMessage(msg);
            }
        }).start();
       //获取公告图片


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getActivity(),myreade,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), NoticeWeb.class);
                intent.putExtra("path",myreade);
                startActivity(intent);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
