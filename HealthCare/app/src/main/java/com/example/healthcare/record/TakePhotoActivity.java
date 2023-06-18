package com.example.healthcare.record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import com.example.healthcare.R;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.example.healthcare.adapter.DetectFoodAdapter;
import com.example.healthcare.bean.DetectContent;
import com.example.healthcare.bean.DetectFood;
import com.example.healthcare.entity.DailyIntake;
import com.google.android.material.progressindicator.DeterminateDrawable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TakePhotoActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO_REQUEST = 101;
    public static final String TAG = "HealthCare";
    public static final int TAKE_PHOTO = 1;
    private Button takePhoto;
    private ImageView picture;
    private ImageView camera_image;
    private FrameLayout pictureLayout;
    private TakePhotoOnClickListener takePhotoOnClickListener = new TakePhotoOnClickListener();
    private ListView listView;
    private DailyIntake dailyIntake;
    private int calAllCalories=0;
    private boolean canEatFlag=true;
    Uri imageuri;
    Bitmap bitmap;
    List<DetectContent> detectList;  //从jsonString中获取的对象列表，尚未处理
    List<DetectFood> detectFoodList = new ArrayList<>();   //处理后的对象列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        initView();
        dailyIntake = (DailyIntake) getIntent().getSerializableExtra("mRecordFragment");
    }
    private void initView(){
        takePhoto = findViewById(R.id.takephoto_button);
        picture = findViewById(R.id.takephoto_picture);
        camera_image = findViewById(R.id.takephoto_camera_img);
        pictureLayout = findViewById(R.id.takephoto_PictureLayout);
        listView = findViewById(R.id.takephoto_listview);
        pictureLayout.setOnClickListener(takePhotoOnClickListener);
        takePhoto.setOnClickListener(takePhotoOnClickListener);
    }
    //点击事件
    private class TakePhotoOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.takephoto_PictureLayout:
                    // 申请相机权限
                    if (Build.VERSION.SDK_INT >= 30) {
                        if (!Environment.isExternalStorageManager()) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                            startActivity(intent);
                            return;
                        }
                    }
                    boolean camera_permission = ContextCompat.checkSelfPermission(TakePhotoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
                    if(camera_permission ){
                        Log.e("Permission","NO");
                        if(camera_permission){
                            Log.e("Permission_Camera","NO");
                            ActivityCompat.requestPermissions(TakePhotoActivity.this, new String[]{Manifest.permission.CAMERA},1);
                        }


                    } else{
                        callCamera();
                    }
                    break;
                case R.id.takephoto_button:
                    showPopUpWindow(takePhoto);
                    //Toast.makeText(TakePhotoActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    }

    //调用相机并想服务器发出请求
    public void callCamera(){   //调用相机，并返回图像
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24){
            imageuri = FileProvider.getUriForFile(TakePhotoActivity.this,"com.example.healthcare.fileprovider",outputImage);
        }else {
            imageuri = Uri.fromFile(outputImage);
        }
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        startActivityForResult(intent,TAKE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        //将拍摄的照片显示出来
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageuri));
                        picture.setImageBitmap(bitmap);
                        camera_image.setVisibility(View.INVISIBLE);
                        // 上传服务端，并显示结果
                        new TelnetRemoteServer().execute();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
    @Override
    //返回权限
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //callCamera();
                } else {
                    Toast.makeText(this, "请开启相机权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //callCamera();
                } else {
                    Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    // 点击提交后的PopupWindow
    public void showPopUpWindow(View view){
        View v = LayoutInflater.from(this).inflate(R.layout.popup_camera_submit,null);
        //实例化对象
        PopupWindow window = new PopupWindow(v,1000,400,true);
        //设置背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置能响应外部的点击事件
        window.setOutsideTouchable(true);
        //设置弹窗能响应点击事件
        window.setTouchable(true);
        v.findViewById(R.id.takephoto_popup_breakfast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dailyIntake.getBreakfast()!=-1){
                    dailyIntake.setBreakfast(dailyIntake.getBreakfast()+calAllCalories);
                }else{
                    dailyIntake.setBreakfast(calAllCalories);
                }
                Intent intent = new Intent();
                intent.putExtra("TakePhotoActivity",dailyIntake);
                setResult(RESULT_OK,intent);
                Toast.makeText(TakePhotoActivity.this, "提交到早餐", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.takephoto_popup_lunch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dailyIntake.getLunch()!=-1){
                    dailyIntake.setLunch(dailyIntake.getLunch()+calAllCalories);
                }else{
                    dailyIntake.setLunch(calAllCalories);
                }
                Intent intent = new Intent();
                intent.putExtra("TakePhotoActivity",dailyIntake);
                setResult(RESULT_OK,intent);
                Toast.makeText(TakePhotoActivity.this, "提交到午餐", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.takephoto_popup_dinner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dailyIntake.getDinner()!=-1){
                    dailyIntake.setDinner(dailyIntake.getDinner()+calAllCalories);
                }else{
                    dailyIntake.setDinner(calAllCalories);
                }
                Intent intent = new Intent();
                intent.putExtra("TakePhotoActivity",dailyIntake);
                setResult(RESULT_OK,intent);
                Toast.makeText(TakePhotoActivity.this, "提交到晚餐", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.takephoto_popup_extra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dailyIntake.getExtra()!=-1){
                    dailyIntake.setExtra(dailyIntake.getExtra()+calAllCalories);
                }else{
                    dailyIntake.setExtra(calAllCalories);
                }
                Intent intent = new Intent();
                intent.putExtra("TakePhotoActivity",dailyIntake);
                setResult(RESULT_OK,intent);
                Toast.makeText(TakePhotoActivity.this, "提交到加餐", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        //显示
        window.showAsDropDown(view);
    }

    // 废弃
    public void telnetRemote(){
        String hostname = "10.8.51.23";
        String username = "dzy42012208";
        String password = "123";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG","run Begin");
                try {
                    Log.e("TAG","JSch Begin");
                    JSch jsch = new JSch();
                    Session session = jsch.getSession(username, hostname, 21031);
                    session.setPassword(password);
                    session.setConfig("StrictHostKeyChecking", "no");
                    session.connect();
                    Log.e("TAG","JSch Connect");
                    ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                    InputStream in = channelExec.getInputStream();
                    channelExec.setCommand("ls");
                    channelExec.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Log.e("TAG", line);
                    }
                    channelExec.disconnect();
                    session.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    // 异步处理图像识别
    private class TelnetRemoteServer extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                // 创建一个JSch对象，用于创建SSH会话
                JSch jsch = new JSch();
                String SFTP_USER = "dzy42012208";
                String SFTP_HOST = "10.8.51.23";
                String SFTP_PASS = "123";
                String SFTP_DIR = "/home/dzy42012208/yolov5-master/my_data/images/test";
                Session session = jsch.getSession(SFTP_USER, SFTP_HOST, 21031);
                session.setPassword(SFTP_PASS);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setTimeout(10000);
                // 连接到SSH服务器，并检查是否成功
                session.connect();
                if (session.isConnected()) {
                    // 打开一个SFTP通道，并检查是否成功
                    Channel channel = session.openChannel("sftp");
                    channel.connect();
                    if (channel.isConnected()) {
                        ChannelSftp channelSftp = (ChannelSftp) channel;
                        channelSftp.cd(SFTP_DIR);

                        File file=new File(Environment.getExternalStorageDirectory() +"/image.jpg");
                        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        FileOutputStream baos=new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        baos.flush();
                        baos.close();

                        channelSftp.put(Environment.getExternalStorageDirectory()+"/image.jpg", "image.jpg");
                        // 关闭通道和会话
                        channelSftp.disconnect();
                        Log.e(TAG,"TakePhoto: sftp SUCCESS");

                        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
                        String cmd = "python /home/dzy42012208/yolov5-master/detect-database.py --weights /home/dzy42012208/yolov5-master/weights/best.pt --source /home/dzy42012208/yolov5-master/my_data/images/test --conf-thres 0.1 --iou-thres 0.1";
                        channelExec.setCommand(cmd);
                        channelExec.connect();
                        Log.e(TAG,"TakePhoto: ssh channel Connect Success");

                        channelExec.setCommand("cd /home/dzy42012208/propose && ls");
                        InputStream inn = channelExec.getInputStream();
                        channelExec.connect();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inn));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            Log.e("TAG", line);
                        }
                        channelExec.disconnect();

                        //下载检测结果json文件
                        Log.e(TAG,"TakePhoto: Begin sftp download...");
                        ChannelSftp channelSftp1 = (ChannelSftp) session.openChannel("sftp");
                        channelSftp1.connect();
                        String remoteFile = "/home/dzy42012208/propose/检测结果.json";

                        boolean fileExists = false;

                        for (int i=0; i<3; i++) { // 最多重试3次
                            Log.e(TAG,"TakePhoto:lstat try "+i);
                            try {
                                SftpATTRS attrs = channelSftp1.lstat(remoteFile); // 检查文件属性
                                if (attrs != null) {
                                    fileExists = true;
                                    break;
                                }
                            }catch (SftpException e){
                                Thread.sleep(3000); // 等待3秒再重试
                            }
                        }
                        String jsonString=null;
                        if(fileExists){
                            InputStream in = channelSftp1.get("/home/dzy42012208/propose/检测结果.json", ChannelSftp.OVERWRITE);
                            if (in != null) {
                                // create a byte array output stream to store the file content
                                ByteArrayOutputStream baoss = new ByteArrayOutputStream();
                                // create a buffer to store the file content
                                byte[] buffer = new byte[1024];
                                // read the file content from the input stream and write to output stream
                                int len;
                                while ((len = in.read(buffer)) > 0) {
                                    baoss.write(buffer, 0, len);
                                }
                                // close the streams
                                in.close();
                                baoss.close();
                                // convert the byte array output stream to a string
                                jsonString = baoss.toString("UTF-8");
                                Log.e(TAG,"TakePhoto:Download Success! JsonString: "+jsonString);
                            } else {
                                in.close();
                                Log.e(TAG,"TakePhoto:Download InputStream is null...");
                            }
                        }else {
                            Log.e(TAG,"TakePhoto: sftp file not found, retrying...");
                        }


                        channelSftp1.disconnect();
                        ChannelExec channelExec1 = (ChannelExec) session.openChannel("exec");
                        channelExec1.setCommand("cd /home/dzy42012208/propose && rm 检测结果.json && rm 检测结果.txt");
                        channelExec1.connect();
                        channelExec1.disconnect();
                        Log.e(TAG,"TakePhoto:Delete json and txt SUCCESS");
                        session.disconnect();
                        if(jsonString !=null){
                            // 正则表达式规范jsonString格式
                            Pattern pattern = Pattern.compile("\\}\\n\\{"); // 创建一个匹配}\n{的模式
                            Matcher matcher = pattern.matcher(jsonString); // 创建一个匹配器
                            jsonString = matcher.replaceAll("},\\{"); // 使用替换字符串替换所有匹配项
                            jsonString = "["+jsonString+"]";

                            if(jsonString!=null){
                                Gson gson = new Gson();
                                // create a Type object to represent the list of Content objects
                                Type type = new TypeToken<List<DetectContent>>(){}.getType();
                                detectList = gson.fromJson(jsonString, type);
                            }

                            // 标准化detectContent
                            for(int i=0;i<detectList.size();i++){
                                String content = detectList.get(i).get内容();
                                int index = content.indexOf("为");
                                int index1 = content.indexOf(",");
                                if(index1==-1){
                                    index1 = content.indexOf("，");
                                }
                                String name = content.substring(index+1,index1);
                                Log.e(TAG,"TakePhoto:standard name\t"+name);
                                if(name.equals("冰激凌")){
                                    Log.e(TAG,"TakePhoto OUTPUT ");
                                    canEatFlag = false;
                                    break;
                                }
                                index = content.indexOf("为",index+1);
                                index1 = content.indexOf("/");
                                String calories = content.substring(index+1,index1);
                                DetectFood food = new DetectFood(name,calories);
                                detectFoodList.add(food);
                                Log.e(TAG,"TakePhoto:standard name:"+name+"\tstandard calories:"+calories);
                            }

                            return true; // 返回true表示上传成功
                        }else{
                            return false;
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false; // 返回false表示上传失败
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                if(canEatFlag){
                    Toast.makeText(TakePhotoActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                    if(detectFoodList.size()!=0){
                        //BaseAdapter adapter = new DetectFoodAdapter(detectFoodList,TakePhotoActivity.this);
                        DetectFoodAdapter adapter = new DetectFoodAdapter(detectFoodList,TakePhotoActivity.this);
                        listView.setAdapter(adapter);
                        listView.setDivider(null);
                        listView.setDividerHeight(0);
                        calculateAllCalories(adapter);


                    }
                }else {
                    Toast.makeText(TakePhotoActivity.this,"食物中含有冰淇凌，不可食用！",Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(TakePhotoActivity.this, "上传出错啦", Toast.LENGTH_SHORT).show();
                Log.e(TAG,"TakePhoto : doInBackground return false...");
            }
        }
    }
    void calculateAllCalories(DetectFoodAdapter adapter){
        //接口回调，刷新计算总共卡路里
        adapter.setOndataChangedListener(new DetectFoodAdapter.onDataChangedListener() {
            @Override
            public void onDataChanged(List<DetectFood> detectFoodList) {
                calAllCalories = 0;
                for(int i=0;i<detectFoodList.size();i++){
                    DetectFood food = detectFoodList.get(i);
                    String ca= food.getCalories();
                    int index = ca.indexOf("c");
                    ca = ca.substring(0,index);
                    calAllCalories = Integer.valueOf(ca) + calAllCalories;
                }
            }
        });

    }





}