package com.buuyou.HttpConnect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/6.
 */
public class myHttpConect {


//传递URL
    public static String urlconnect(String myphone, String mypass) {

        String url=UrlPath.NET_API+UrlPath.loginType+UrlPath.data+myphone+","+mypass;
        String urlresult=HttpResult(url);
        return urlresult;
    }
    //得到验证码
    public static String urlconnect_pass(String email, String phone) {

        String url=UrlPath.NET_API+UrlPath.passType+UrlPath.data+email+","+phone;
        String urlresult=HttpResult(url);
        return urlresult;
    }
    //根据验证码码登录后台
    public static String urlconnect_getpass(String email, String valiCode) {

        String url=UrlPath.NET_API+UrlPath.getpassType+UrlPath.data+email+","+valiCode;
        String urlresult=HttpResult(url);
        return urlresult;
    }
    //根据验证码码登录后台
    public static String urlconnect_updatapass(String email, String valiCode,String newpass) {

        String url=UrlPath.NET_API+UrlPath.updatapassType+UrlPath.data+email+","+valiCode+","+newpass+2;
        String urlresult=HttpResult(url);
        return urlresult;
    }
//连接服务器
    public static String HttpResult(String url) {
        String httpresult="";
        try {
            URL myurl=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) myurl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            int code=connection.getResponseCode();
            if(code==200){
                InputStream in=connection.getInputStream();
                httpresult = HttpBuff(in);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpresult;
    }
//读入数据
   public static String HttpBuff(InputStream in) {
       String buffresult="";
        try {
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            byte[] bufe=new byte[1024];
            int len;
            while ((len=in.read(bufe))!= -1){
             out.write(bufe,0,len);
            }
            in.close();
            out.close();
             buffresult=new String(out.toByteArray());
            return buffresult;
        } catch (IOException e) {
            e.printStackTrace();
            return buffresult="";
        }

   }



}
