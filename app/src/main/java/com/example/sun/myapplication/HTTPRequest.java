package com.example.sun.myapplication;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sun on 2016/11/19.
 */

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HTTPRequest {

    /**
     * 使用GET访问去访问网络
     * @return 服务器返回的结果
     */
    public static String doGET(String path){
        HttpURLConnection conn=null;
        try {
            URL url =new URL(path);
            conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.connect();
            int code=conn.getResponseCode();
            if(code==200){
                InputStream is=conn.getInputStream();
                String content=getStringFromInputStream(is);
                return content;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn!=null){
                conn.disconnect();
            }
        }
        return null;
    }

    /**
     * 根据输入流返回一个字符串
     * @param is
     * @return
     * @throws Exception
     */
    private static String getStringFromInputStream(InputStream is) throws Exception{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte[] buff=new byte[1024];
        int len=-1;
        while((len=is.read(buff))!=-1){
            baos.write(buff, 0, len);
        }
        is.close();
        String html=baos.toString();
        baos.close();
        return html;
    }
}
