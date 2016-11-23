package com.example.sun.myapplication;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import org.json.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        System.out.println("START");
//        File sdCard = null;
//
//        //实例化SD卡的文件对象
//        if (checkSDCard()){
//            sdCard = getSDCard();
//        }
//
//        System.out.println(getSDCard());
//
//        if(sdCard != null){
//            getAllFiles(sdCard);
//        }else{
//            System.out.println("SDK");
//        }
//        System.out.println("END");
//    }

    /**
     * 判断内存卡是否存在
     * @return
     */
    public static boolean isSdCardExist() {

    if (Environment.getExternalStorageState().equals(
            Environment.MEDIA_MOUNTED)) {//判断是否已经挂载
        return true;
    }
    return false;
}

    DownloadManager downloadManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String path = Environment.getExternalStorageDirectory().getPath();
        final String url = "http://123.207.114.37/shell.txt";
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(path);
                Downloader.downloadFile(url, file, "qwe.zip");
                try {
                    Zipzip.UnZipFolder(path + "/qwe.zip",path + "/qwe");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


////        File file = new File(getApplicationContext().getFilesDir().getPath() + "/files/" + "wordpress.zip");
//        File file = new File(Environment.getExternalStorageDirectory() + "/qwe.zip");
////        File file = new File(Environment.getExternalStorageState() + "/qwe.zip");
//        System.out.println("文件大小 : " + file.getPath());
//        try {
//            KZipFile.unzip(file.getPath(),"./");
////            ZipUtil.unzip(file.getPath(),"./");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String zipFilePath = Environment.getExternalStorageDirectory() + "/wordpress.zip";
//
//        String destDir = Environment.getExternalStorageDirectory() + "/qwe";
//
//        TTT.unZipIt(zipFilePath, destDir);



//        try {
//
//
//            File inputFile = new File("utf-8-data.txt");
//            File outputFile = new File("latin-1-data.zip");
//            ZipEntry entry = new ZipEntry("latin-1-data.txt");
//            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//            ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(outputFile));
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(zipStream, Charset.forName("ISO-8859-1"))
//            );
//
//            zipStream.putNextEntry(entry);
//
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                writer.append(line).append('\n');
//            }
//            writer.flush();
//
//            zipStream.closeEntry();
//            zipStream.finish();
//
//            reader.close();
//            writer.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


////        DownloadManager downManager ;
//        DownloadManager downManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
//
//        DownloadManager.Request request =
//        new DownloadManager.Request(Uri.parse("http://gdown.baidu.com/data/wisegame/55dc62995fe9ba82/jinritoutiao_448.apk"));
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//        request.setAllowedOverRoaming(false);
//        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "mydown");
//
//        long id= downManager.enqueue(request);


//
//
////        String serviceString = Context.DOWNLOAD_SERVICE;
////        DownloadManager downloadManager;
////        downloadManager = (DownloadManager) getSystemService(serviceString);
////
////        Uri uri = Uri.parse("http://114.215.202.154/static/vocabularies/basketball.zip");
////        DownloadManager.Request request = new DownloadManager.Request(uri);
////        long reference = downloadManager.enqueue(request);
////        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
////
////        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
////
////        BroadcastReceiver receiver = new BroadcastReceiver() {
////            @Override
////            public void onReceive(Context context, Intent intent) {
////                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
////                if (myDownloadReference == reference) {
////
////                }
////            }
////        };
////        registerReceiver(receiver, filter);
//
//        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
//
//        String apkUrl = "http://114.215.202.154/static/vocabularies/basketball.zip";
//
//        DownloadManager.Request request = new
//
//                DownloadManager.Request(Uri.parse(apkUrl));
//
//        request.setDestinationInExternalPublicDir("dirType", "/1111111/QQ.apk");
//
//        // request.setTitle("TX QQ");
//
//        // request.setDescription("This is TX QQ");
//
//        // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//        //request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//
//        //
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
//
//        //request.setMimeType("application/cn.trinea.download.file");
//
//        long downloadId = downloadManager.enqueue(request);
//
//        downloadManager.getUriForDownloadedFile(downloadId);

//    }
//    public int[] getBytesAndStatus(long downloadId) {
//        int[] bytesAndStatus = new int[] { -1, -1, 0 };
//        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
//        Cursor c = null;
//        try {
//            c = downloadManager.query(query);
//            if (c != null && c.moveToFirst()) {
//                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
//                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
//                bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
//            }
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//        }
//        return bytesAndStatus;
//    }

//        System.out.println(isSdCardExist());

//        ZipUtil.UpZip()


//        // 测试通过
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String content = HTTPRequest.doGET("http://114.215.202.154/files/getAllInfo");
//                System.out.println(content);

//            }
//        }).start();

        //测试通过
//        //解析json
//        String content = "[{\"url\": \"/static/vocabularies/basketball.zip\", \"name\": \"篮球\", \"description\": \"篮球\"}, {\"url\": \"/static/vocabularies/computer.zip\", \"name\": \"计算机\", \"description\": \"计算机\"}, {\"url\": \"/static/vocabularies/DNF.zip\", \"name\": \"DNF\", \"description\": \"DNF\"}, {\"url\": \"/static/vocabularies/falv.zip\", \"name\": \"法律\", \"description\": \"法律\"}, {\"url\": \"/static/vocabularies/finance.zip\", \"name\": \"经济\", \"description\": \"经济\"}, {\"url\": \"/static/vocabularies/football.zip\", \"name\": \"足球\", \"description\": \"足球\"}, {\"url\": \"/static/vocabularies/gov.zip\", \"name\": \"政务\", \"description\": \"政务\"}, {\"url\": \"/static/vocabularies/lol.zip\", \"name\": \"英雄联盟\", \"description\": \"英雄联盟\"}, {\"url\": \"/static/vocabularies/songs.zip\", \"name\": \"歌曲\", \"description\": \"歌曲\"}, {\"url\": \"/static/vocabularies/Wow.zip\", \"name\": \"魔兽世界\", \"description\": \"魔兽世界\"}, {\"url\": \"/static/vocabularies/yiliao.zip\", \"name\": \"医疗\", \"description\": \"医疗\"}, {\"url\": \"/static/vocabularies/yinshi.zip\", \"name\": \"影视\", \"description\": \"影视\"}]";
//
//        JSONArray arr = null;
//        try {
//            arr = new JSONArray(content);
//            for (int i = 0; i < arr.length(); i++) {
//                JSONObject temp = (JSONObject) arr.get(i);
//                String url = temp.getString("url");
//                String name = temp.getString("name");
//                String description = temp.getString("description");
//                System.out.println("名称 : " + name + " 描述 : " + description + " 下载地址" + url);
//            };
//        } catch (JSONException e) {
//            e.printStackTrace();
////        }
//
//
//        //遍历路径 ://测试通过
////        getAllFiles(getSDCard());
//
//
//
//
////         测试通过
//    //启动线程
//    new DownloadUtil(new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
////                        Map<String, Long> data = (Map<String, Long>) msg.obj;
////                        long fileSize = data.get("fileSize");
////                        long total = data.get("total");
////
////                        将long按照比例转换成int
////                        int total_int = (int) (100 * (total / fileSize));
//            // TODO: 2016/8/6 如果整型不能满足需求呢?
////                progressDialog.setMax(msg.arg1);
////                progressDialog.setProgress(msg.arg2);
//            System.out.println(msg.arg2 + " / " + msg.arg1);
//            super.handleMessage(msg);
//        }
//    }, new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
////                startActivity((Intent) msg.obj);
//            //下载完成,关闭程序开始安装
//            System.out.println("下载完成");
//        }
//    }, "http://114.215.202.154/static/vocabularies/yiliao.zip").start();

        //
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DownloadUtil.downloadFile(getApplicationContext(), "http://114.215.202.154/static/vocabularies/yiliao.zip", getSDCard().getPath(),"下载测试","下载描述",false);
//            }
//        }).start();


//        DownloadUtil.downloadFile("");
////        button = (Button)findViewById(R.id.mybutton);
//
//        File path = null;
//        //检测SD卡是否存在
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            path = Environment.getExternalStorageDirectory();
//        }else{
//            Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
//            finish();
//        }
//        getAllFiles(path);
//
//
//        try {
//            JSONObject jsonObject = new JSONObject("[{\"url\": \"/static/vocabularies/basketball.zip\", \"name\": \"篮球\", \"description\": \"篮球\"}, {\"url\": \"/static/vocabularies/computer.zip\", \"name\": \"计算机\", \"description\": \"计算机\"}, {\"url\": \"/static/vocabularies/DNF.zip\", \"name\": \"DNF\", \"description\": \"DNF\"}, {\"url\": \"/static/vocabularies/falv.zip\", \"name\": \"法律\", \"description\": \"法律\"}, {\"url\": \"/static/vocabularies/finance.zip\", \"name\": \"经济\", \"description\": \"经济\"}, {\"url\": \"/static/vocabularies/football.zip\", \"name\": \"足球\", \"description\": \"足球\"}, {\"url\": \"/static/vocabularies/gov.zip\", \"name\": \"政务\", \"description\": \"政务\"}, {\"url\": \"/static/vocabularies/lol.zip\", \"name\": \"英雄联盟\", \"description\": \"英雄联盟\"}, {\"url\": \"/static/vocabularies/songs.zip\", \"name\": \"歌曲\", \"description\": \"歌曲\"}, {\"url\": \"/static/vocabularies/Wow.zip\", \"name\": \"魔兽世界\", \"description\": \"魔兽世界\"}, {\"url\": \"/static/vocabularies/yiliao.zip\", \"name\": \"医疗\", \"description\": \"医疗\"}, {\"url\": \"/static/vocabularies/yinshi.zip\", \"name\": \"影视\", \"description\": \"影视\"}]");
//            System.out.println(jsonObject.getString("url"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
//    private void getAllFiles(File root){
//        File files[] = root.listFiles();
//        if(files != null){
//            for (File f : files){
//                if(f.isDirectory()){
//                    getAllFiles(f);
//                }else{
//                    System.out.println(f);
//                }
//            }
//        }
//    }

//    /**
//     * 判断SD卡是否存在
//     * @return
//     */
//    public boolean checkSDCard(){
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            return true;
//        }else{
//            return false;
//        }
//    }
//
    /**
     * 获取SD卡的文件对象
     * @return
     */
    public File getSDCard(){
        return Environment.getExternalStorageDirectory();
    }

//    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
//    private void getAllFiles(File root){
//        System.out.println("AAA");
//        File files[] = root.listFiles();
//        if(files != null){
//            for (File f : files){
//                if(f.isDirectory()){
//                    getAllFiles(f);
//                }else{
//                    System.out.println(f);
//                }
//            }
//        }
//    }
}
