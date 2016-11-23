package com.example.sun.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.util.ArrayMap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
/**
 * 下载并安装新版本
 * Created by Sun on 2016/8/5.
 */
public class DownloadUtil extends Thread{
    Handler handler_progress;
    Handler handler_finish;
    String url;
    File file;
    //    Map<String, Long> map = new ArrayMap<>();
    public DownloadUtil(Handler handler_progress, Handler handler_finish, String url){
        this.handler_progress = handler_progress;
        this.handler_finish = handler_finish;
        this.url = url;
    }

    @Override
    public void run() {
        //下载文件
        downloadFile();
        super.run();
    }
    /**
     * 下载新版本到SD卡
     */
    private void downloadFile(){
        try {
            URL downloadURL = new URL(url);//创建URL对象
            HttpURLConnection httpURLConnection = (HttpURLConnection) downloadURL.openConnection();//使用URL对象打开HttpURLConnect对象
            httpURLConnection.setConnectTimeout(5000);//超时时间
            long fileSize = httpURLConnection.getContentLength();//获取文件总大小
            InputStream inputStream = httpURLConnection.getInputStream();
            file = new File(Environment.getExternalStorageDirectory(), "测试.zip");//创建文件对象
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int length;//每次缓冲的长度
            long total = 0;//已经下载的长度
            while((length = bufferedInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, length);
                total += length;
//                int totalInt = (int)(total / fileSize) * fileSizeInt;
//                map.put("fileSize", fileSize);
//                map.put("total", total);
//                Message message = new Message();
//                message.obj = map;
//                handler_progress.sendMessage(message);
//                map.clear();
                Message message = new Message();
                message.arg1 = (int) fileSize;
                message.arg2 = (int) total;
                handler_progress.sendMessage(message);
                //日志(下载进度)
                System.out.println("(" + total + "/" + fileSize + ")");
            }
            //关闭流与资源
            bufferedInputStream.close();
            fileOutputStream.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    // TODO: 2016/8/5 考虑安装位置
//    /**
//     * 安装新版本到手机
//     */
//    private void installNewAPK(){
//        Intent intent = new Intent();
//        //执行动作
//        intent.setAction(Intent.ACTION_VIEW);
//        //执行的数据类型
//        if (file.exists()){
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        }
//        Message message = new Message();
//        message.obj = intent;
//        handler_finish.sendMessage(message);
//    }
}
