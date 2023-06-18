package com.example.healthcare.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcare.MainActivity;
import com.example.healthcare.R;
import com.example.healthcare.entity.User;

public class RegisterActivity extends AppCompatActivity {
    RegisterClickListener registerClickListener = new RegisterClickListener();
    EditText usr_name;
    EditText email;
    EditText psw;
    EditText psw_again;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView(){
        //绑定控件
        usr_name = findViewById(R.id.register_edit_usr);
        email = findViewById(R.id.register_edit_email);
        psw = findViewById(R.id.register_edit_password);
        psw_again = findViewById(R.id.register_edit_passwordagain);
        btn = findViewById(R.id.register_btn);
        //设置点击监听器
        btn.setOnClickListener(registerClickListener);
    }
    //监听内部类
    private class RegisterClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.register_btn:
                    Log.d("Register","Register btn Click");
                    User usr = createUser();
                    if (usr != null) {
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("RegisterNewUsr",usr);
                        startActivity(intent);
                    }
                    break;
                default:
                    Log.e("Register","Null Click");
                    break;
            }
        }
    }
    //时间分发方法重写
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果是点击事件，获取点击的view，并判断是否要收起键盘
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //获取目前得到焦点的view
            View v = getCurrentFocus();
            //判断是否要收起并进行处理
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
                //当前view失去焦点
                v.clearFocus();
            }
        }
        //这个是activity的事件分发，一定要有，不然就不会有任何的点击事件了
        return super.dispatchTouchEvent(ev);
    }

    //判断是否要收起键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        //如果目前得到焦点的这个view是editText的话进行判断点击的位置
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            // 点击EditText的事件，忽略它。
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上
        return false;
    }

    //隐藏软键盘并让editText失去焦点
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            //这里先获取InputMethodManager再调用他的方法来关闭软键盘
            //InputMethodManager就是一个管理窗口输入的manager
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    private User createUser(){
        String mname = usr_name.getText().toString();
        String memail = email.getText().toString();
        String mpwd = psw.getText().toString();
        String magain = psw_again.getText().toString();
        if(!TextUtils.isEmpty(mname) && !TextUtils.isEmpty(memail) && !TextUtils.isEmpty(mpwd) && !TextUtils.isEmpty(magain)){
            if (mpwd.equals(magain)){
                User usr = new User(mname,memail,mpwd);
                Log.e("RegisterUsr","usr name:"+usr.getUsr_name());
                Log.e("RegisterUsr","usr email:"+usr.getEmail());
                Log.e("RegisterUsr","usr password:"+usr.getPassword());
                return usr;
            }else{
                Toast.makeText(this, "输入密码不一致！", Toast.LENGTH_SHORT).show();
                return null;
            }
        }else{
            Toast.makeText(this, "请输入完整信息！", Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}