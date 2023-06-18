package com.example.healthcare.person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.healthcare.R;
import com.example.healthcare.entity.User;

public class illnessInforActivity extends AppCompatActivity {
    User usr;
    private LinearLayout returnback;
    private EditText age;
    private EditText height;
    private EditText weight;
    private EditText blood_pressure;
    private EditText blood_glucose;
    private Button save_btn;
    private IllnessInforClickListener illnessInforClickListener = new IllnessInforClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_infor);

        initView();
        manifestIllnessInfor();
    }
    private void initView(){
        returnback = findViewById(R.id.illnessinfor_return);
        age = findViewById(R.id.illnessinfor_age_edit);
        height = findViewById(R.id.illnessinfor_height_edit);
        weight = findViewById(R.id.illnessinfor_weight_edit);
        blood_pressure = findViewById(R.id.illnessinfor_bloodpressure_edit);
        blood_glucose = findViewById(R.id.illnessinfor_bloodglucose_edit);
        save_btn = findViewById(R.id.illnessinfor_save_btn);

        returnback.setOnClickListener(illnessInforClickListener);
        save_btn.setOnClickListener(illnessInforClickListener);


    }

    private class IllnessInforClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.illnessinfor_return:
                    Log.d("IllnessInfor","Click return Button");
                    finish();
                    break;
                case R.id.illnessinfor_save_btn:
                    Log.d("IllnessInfor","Click Save Button");
                    updateIllnessInfor();
                    Toast.makeText(illnessInforActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }

        }
    }
    private void manifestIllnessInfor(){
        usr = (User) getIntent().getSerializableExtra("PersonFragmentUsr");
        if(usr.getAge()!= 0){
            age.setText(Integer.toString(usr.getAge()));
        }
        if(usr.getHeight()!=0){
            height.setText(Integer.toString(usr.getHeight()));
        }
        if(usr.getWeight()!=0){
            weight.setText(Integer.toString(usr.getWeight()));
        }
        if(usr.getBlood_glucose()!=0){
            blood_glucose.setText(Integer.toString(usr.getBlood_glucose()));
        }
        if(usr.getBlood_pressure()!=0){
            blood_pressure.setText(Integer.toString(usr.getBlood_pressure()));
        }

    }
    private void updateIllnessInfor(){
        //缺失上传数据库
        String mage = age.getText().toString();
        String mheight = height.getText().toString();
        String mweight = weight.getText().toString();
        String mblood_glucose = blood_glucose.getText().toString();
        String mblood_pressure = blood_pressure.getText().toString();
        if(!TextUtils.isEmpty(mage) && !TextUtils.isEmpty(mheight) && !TextUtils.isEmpty(mweight) && !TextUtils.isEmpty(mblood_glucose) && !TextUtils.isEmpty(mblood_pressure) ){
            usr.setAge(Integer.parseInt(mage));
            usr.setHeight(Integer.parseInt(mheight));
            usr.setWeight(Integer.parseInt(mweight));
            usr.setBlood_glucose(Integer.parseInt(mblood_glucose));
            usr.setBlood_pressure(Integer.parseInt(mblood_pressure));
        }else{
            Toast.makeText(this, "请输入完整的信息！", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent();
        intent.putExtra("IllnessInforUsr",usr);
        setResult(RESULT_OK,intent);


    }
}