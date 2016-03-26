package com.xiaoku.weixiaotong;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BabySpace.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BabySpace#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BabySpace extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BabySpace() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BabySpace.
     */
    // TODO: Rename and change types and number of parameters
    public static BabySpace newInstance(String param1, String param2) {
        BabySpace fragment = new BabySpace();
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
       View view= inflater.inflate(R.layout.baby_space, container, false);
        GridView gd=(GridView)view.findViewById(R.id.gd);
        gd.setAdapter(new myAdapter());
        GridView gd2=(GridView)view.findViewById(R.id.gd2);
        gd2.setAdapter(new myAdapter2());
        return view;
    }

    public  class myAdapter extends BaseAdapter{
        int[] imageid={R.drawable.email,R.drawable.picture,R.drawable.bb_grow,R.drawable.bb_queren,R.drawable.checklist,R.drawable.bb_parent,R.drawable.shipu,R.drawable.clock};
       String[] name={"消息","宝宝相册","成长档案","代接确认","在线请假","家长叮嘱","食谱","考勤"};

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

            View view=View.inflate(getActivity(),R.layout.baby_gd_item1,null);
            TextView tx=(TextView)view.findViewById(R.id.tx);
            ImageView im=(ImageView)view.findViewById(R.id.im);
            im.setImageResource(imageid[position]);
            tx.setText(name[position]);
            return view;
        }
    }

    //建立第二个Gridview的适配器
    public  class myAdapter2 extends BaseAdapter{
        int[] imageid={R.drawable.bb_9,R.drawable.bb_10,R.drawable.bb_11,R.drawable.bb_12,R.drawable.bb_13,R.drawable.bb_14,R.drawable.bb_15,R.drawable.bb_16};
        String[] names={"老师点评","宝宝老师","宝宝好友","通知公告","宝宝课件","班级课程","班级活动","宝宝乐园"};

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
            View view=View.inflate(getActivity(),R.layout.baby_gd_item1,null);
            TextView tx=(TextView)view.findViewById(R.id.tx);
            ImageView im=(ImageView)view.findViewById(R.id.im);
            im.setImageResource(imageid[position]);
            tx.setText(names[position]);
            return view;
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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
