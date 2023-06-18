package com.example.healthcare.person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthcare.R;
import com.example.healthcare.entity.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class BasicImforActivity extends AppCompatActivity {
    private User usr;
    private TextView id;
    private EditText sex;
    private EditText email;
    private Button save_btn;
    private LinearLayout return_back;
    private EditText usr_name;
    private EditText phone;
    private CircleImageView header;

    private BasicImforCLickListener basicImforCLickListener = new BasicImforCLickListener();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_imfor);
        initView();
        manifestInfor();
    }

    private void initView(){
        id = findViewById(R.id.basicinfor_id_text);
        sex = findViewById(R.id.basicinfor_sex_text);
        email = findViewById(R.id.basicinfor_email_text);
        save_btn = findViewById(R.id.basicinfor_save_btn);
        return_back = findViewById(R.id.basicinfor_return);
        usr_name = findViewById(R.id.basicinfor_usrname_text);
        header = findViewById(R.id.basicinfor_header_img);
        phone = findViewById(R.id.basicinfor_phone_text);

        Glide.with(this).load(R.drawable.person_header_boy).into(header);

        return_back.setOnClickListener(basicImforCLickListener);
        save_btn.setOnClickListener(basicImforCLickListener);




    }

    private class BasicImforCLickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.basicinfor_return:
                    Log.d("BasicInfor","Click return button");
                    finish();
                    break;
                case R.id.basicinfor_save_btn:
                    Log.d("BasicInfor","Click save button");
                    updateUsrInfor();
                    Toast.makeText(BasicImforActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();

                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    }
    private void manifestInfor(){
        usr = (User) getIntent().getSerializableExtra("PersonFragementUsr");
        if(usr.getUsr_name()!=null){
            usr_name.setText(usr.getUsr_name());
        }
        if(usr.getHeaders()!=null){
            Glide.with(this).load(usr.getHeaders()).into(header);
        }
        if(usr.getId()!=null){
            id.setText(usr.getId());
        }
        if(usr.getEmail()!=null){
            email.setText(usr.getEmail());
        }
        if(usr.getPhone()!=null){
            phone.setText(usr.getPhone());
        }
    }
    private void updateUsrInfor(){
        String mname = usr_name.getText().toString();
        String msex = sex.getText().toString();
        String memail = email.getText().toString();
        String mphone = phone.getText().toString();
        //头像占位
        if(!TextUtils.isEmpty(msex) && !TextUtils.isEmpty(msex) && !TextUtils.isEmpty(memail) &&!TextUtils.isEmpty(mphone) ){
            if(msex.equals("男") || msex.equals("女")){
                usr.setSex(msex);
            }else {
                Toast.makeText(this, "请输入正确的性别", Toast.LENGTH_SHORT).show();
            }
            usr.setEmail(memail);
            usr.setPhone(mphone);
            usr.setUsr_name(mname);

        }else{
            Toast.makeText(this, "请完善信息后提交！", Toast.LENGTH_SHORT).show();
        }
        //此处缺失上传数据
        Intent intent = new Intent();
        intent.putExtra("BasicInforUsr",usr);
        setResult(RESULT_OK,intent);
    }




}