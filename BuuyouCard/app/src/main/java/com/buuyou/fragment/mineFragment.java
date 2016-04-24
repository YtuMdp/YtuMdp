package com.buuyou.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buuyou.MineSbu.AboutBouu;
import com.buuyou.MineSbu.AccountInfo;
import com.buuyou.MineSbu.BasicInfo;
import com.buuyou.MineSbu.ChannelRate;
import com.buuyou.MineSbu.LoginLog;
import com.buuyou.MineSbu.SecurityCen;
import com.buuyou.MineSbu.Updata;
import com.buuyou.buuyoucard.R;
import com.buuyou.main.Mylogin;
import com.buuyou.main.findpassword;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mineFragment extends Fragment implements View.OnClickListener{
    TextView tv_fragmentmine_id,tv_fragmentmine_pwd;
    Button exit;
    SharedPreferences sp;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public mineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mineFragment newInstance(String param1, String param2) {
        mineFragment fragment = new mineFragment();
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
        sp=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        tv_fragmentmine_id=(TextView)view.findViewById(R.id.tv_fragmentmine_id);
        tv_fragmentmine_pwd=(TextView)view.findViewById(R.id.tv_fragmentmine_pwd);
        LinearLayout info=(LinearLayout)view.findViewById(R.id.info);
        info.setOnClickListener((View.OnClickListener) this);
        LinearLayout account_info=(LinearLayout)view.findViewById(R.id.account_info);
        account_info.setOnClickListener((View.OnClickListener) this);
        LinearLayout safenum=(LinearLayout)view.findViewById(R.id.safenum);
        safenum.setOnClickListener((View.OnClickListener) this);
        LinearLayout changepass=(LinearLayout)view.findViewById(R.id.changepass);
        changepass.setOnClickListener((View.OnClickListener) this);
        LinearLayout loginlog= (LinearLayout)view.findViewById(R.id.loginlog);
        loginlog.setOnClickListener(this);

        LinearLayout channel= (LinearLayout)view.findViewById(R.id.channel);
        channel.setOnClickListener(this);

        LinearLayout about= (LinearLayout)view.findViewById(R.id.about);
        about.setOnClickListener(this);

        LinearLayout updata= (LinearLayout)view.findViewById(R.id.updata);
        updata.setOnClickListener(this);


        exit=(Button)view.findViewById(R.id.exit);
        tv_fragmentmine_id.setText("ID:" + sp.getString("userid", null));
        tv_fragmentmine_pwd.setText("密钥:" + sp.getString("checkCode", null));
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("isboolean", true);
                editor.commit();
                Intent intent = new Intent(getActivity().getApplication(), Mylogin.class);
                startActivity(intent);
                getActivity().finish();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info :
                Intent intent=new Intent(getActivity(), BasicInfo.class);
                startActivity(intent);
                break;
            case R.id.account_info :
                Intent intent2=new Intent(getActivity(), AccountInfo.class);
                startActivity(intent2);
                break;
            case R.id.safenum :
                Intent intent3=new Intent(getActivity(), SecurityCen.class);
                startActivity(intent3);
                break;
            case R.id.changepass :
                Intent intent4=new Intent(getActivity(),findpassword.class);
                startActivity(intent4);
                break;
            case R.id.loginlog :
                Intent intent5=new Intent(getActivity(),LoginLog.class);
                startActivity(intent5);
                break;
            case R.id.channel :
                Intent intent6=new Intent(getActivity(),ChannelRate.class);
                startActivity(intent6);
                break;
            case R.id.about :
                Intent intent7=new Intent(getActivity(),AboutBouu.class);
                startActivity(intent7);
                break;
            case R.id.updata :
                Intent intent8=new Intent(getActivity(),Updata.class);
                startActivity(intent8);
                break;
        }
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
