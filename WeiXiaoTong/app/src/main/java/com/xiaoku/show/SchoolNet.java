package com.xiaoku.show;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoku.weixiaotong.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SchoolNet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchoolNet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolNet extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public SchoolNet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolNet.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolNet newInstance(String param1, String param2) {
        SchoolNet fragment = new SchoolNet();
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
        View view= inflater.inflate(R.layout.school_net, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.ab);
        toolbar.setTitle(" ");
       // setSupportActionBar(toolbar);
        GridView gd=(GridView)view.findViewById(R.id.gd);
        gd.setAdapter(new gdAdapter());
        ListView lv=(ListView)view.findViewById(R.id.lv);
        lv.setAdapter(new lisAdapter());
        fixListViewHeight(lv);
        return view;
    }

    //创建Listview适配器
    private class lisAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 8;
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
            View view=View.inflate(getActivity(),R.layout.gd_list_item,null);
            return view;
        }
    }
        //创建gridview适配器

    private class gdAdapter extends BaseAdapter{
        int[] imageid={R.drawable.gdfirst,R.drawable.gdsecond,R.drawable.gdthird,R.drawable.gdfour,
                R.drawable.gdfive,R.drawable.gdsix,R.drawable.gdseven,R.drawable.gdegit};
        String[] tex={"园区介绍","教师风采","今日食谱","宝宝秀场","校园招聘","兴趣班","在线请假","入学报名"};

        @Override
        public int getCount() {
            return 8;
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
            View view=View.inflate(getActivity(),R.layout.gd_item,null);
            //Toolbar toolbar=(Toolbar)view.findViewById(R.id.ab);

            ImageView imv=(ImageView)view.findViewById(R.id.ig);
            TextView txv=(TextView)view.findViewById(R.id.tx);
            imv.setImageResource(imageid[position]);
            txv.setText(tex[position]);
            return view;
        }
    }
    //设置ListView的高度

    public void fixListViewHeight(ListView listView) {

        // 如果没有设置数据适配器，则ListView没有子项，返回。

        ListAdapter listAdapter = listView.getAdapter();

        int totalHeight = 0;

        if (listAdapter == null) {

            return;

        }

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listViewItem = listAdapter.getView(i , null, listView);

            // 计算子项View 的宽高

            listViewItem.measure(0, 0);

            // 计算所有子项的高度和

            totalHeight += listViewItem.getMeasuredHeight();

        }



        ViewGroup.LayoutParams params = listView.getLayoutParams();

        // listView.getDividerHeight()获取子项间分隔符的高度

        // params.height设置ListView完全显示需要的高度


        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);

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
