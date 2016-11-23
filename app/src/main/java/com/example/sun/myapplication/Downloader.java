package com.example.sun.myapplication;

/**
 * Created by Sun on 2016/11/23.
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件下载工具类
 * Created by 王一航 on 2016/11/19.
 */
public class Downloader{
    /**
     * 下载文件到SD卡
     */
    public static void downloadFile(String url, File dir, String fileName){
        try {
            URL downloadURL = new URL(url);//创建URL对象
            HttpURLConnection httpURLConnection = (HttpURLConnection) downloadURL.openConnection();//使用URL对象打开HttpURLConnect对象
            httpURLConnection.setConnectTimeout(5000);//超时时间
            long fileSize = httpURLConnection.getContentLength();//获取文件总大小
            InputStream inputStream = httpURLConnection.getInputStream();
            File file = new File(dir, fileName);//创建文件对象
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int length;//每次缓冲的长度
            long total = 0;//已经下载的长度
            while((length = bufferedInputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, length);
                total += length;
                System.out.println("(" + total + " / " + fileSize + ")");
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
}
