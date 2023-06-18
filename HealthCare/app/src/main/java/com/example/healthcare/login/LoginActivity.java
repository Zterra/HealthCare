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
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcare.MainActivity;
import com.example.healthcare.R;
import com.example.healthcare.entity.User;

public class LoginActivity extends AppCompatActivity {
    LoginClickListener loginClickListener = new LoginClickListener();
    Button btn_login;
    Button btn_register;
    TextView forget_psw;
    EditText edit_usr;
    EditText edit_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }
    public void initView(){
        //绑定
        btn_login = findViewById(R.id.login_loginbtn);
        btn_register = findViewById(R.id.login_registerbtn);
        forget_psw = findViewById(R.id.forget_psw);
        edit_usr = findViewById(R.id.login_usr_edit);
        edit_pwd = findViewById(R.id.login_psw_edit);

        //设置监听器
        btn_login.setOnClickListener(loginClickListener);
        btn_register.setOnClickListener(loginClickListener);
        forget_psw.setOnClickListener(loginClickListener);
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
    //创建用户
    private User createUsr(){
        String usrEmail = edit_usr.getText().toString();
        String psw = edit_pwd.getText().toString();
        if(!TextUtils.isEmpty(usrEmail) && !TextUtils.isEmpty(psw)){
            User usr = new User(usrEmail,usrEmail,psw);
            return usr;
        }else{
            Toast.makeText(this, "请输入完整信息！", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    //设置监听器内部类
    private class LoginClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_loginbtn:
                    Log.d("Login","Login Button Click");
                    // 大修！ 只是测试！ 没有连数据库加上检验密码！！！！！！！！！！！！！！！！！1
                    User usr = createUsr();
                    if(usr !=null){
                        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        loginIntent.putExtra("LoginUsr",usr);
                        startActivity(loginIntent);
                    }
                    break;
                case R.id.login_registerbtn:
                    Log.d("Login","Register Button Click");
                    Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.forget_psw:
                    Log.d("Login","Forget Psw Click");
                    break;
                default:
                    Log.e("Login","Null click");
            }
        }
    }

}