package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.healthcare.adapter.BottomAdapter;
import com.example.healthcare.entity.DailyIntake;
import com.example.healthcare.entity.User;
import com.example.healthcare.ui.HomeFragment;
import com.example.healthcare.ui.PersonFragment;
import com.example.healthcare.ui.RecordFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "HealthCare";
    public static final int BASIC_INFOR_REQUEST = 1;
    public static final int ILLNESS_INFOR_REQUEST = 10;
    public static final int TAKE_PHOTO_REQUEST = 101;
    private User usr;
    private DailyIntake dailyIntake = new DailyIntake();

    private BottomNavigationView mNavView;
    private HomeFragment mhomeFragment = new HomeFragment();
    private RecordFragment mrecordFragment = new RecordFragment();
    private PersonFragment mpersonFragment = new PersonFragment();
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        processExtraData();
        getUsr();
        getDailyIntake();


    }
    public void initLayout(){
        mNavView=(BottomNavigationView) findViewById(R.id.nav_view);
        mViewPager=(ViewPager) findViewById(R.id.mvp);
       // BottomNavigationViewHelper.disableShiftMode(mNavView);
        //导航栏监听
        mNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_item_home:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.nav_item_record:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.nav_item_person:
                        mViewPager.setCurrentItem(2);
                        return true;

                }
                return false;
            }
        });

        setupViewPager(mViewPager);
        // 添加viewpager切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                onPagerSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }
    private void setupViewPager(ViewPager viewPager){
        BottomAdapter adapter = new BottomAdapter(getSupportFragmentManager());
        adapter.addFragment(mhomeFragment);
        adapter.addFragment(mrecordFragment);
        adapter.addFragment(mpersonFragment);
        viewPager.setAdapter(adapter);
    }
    private void onPagerSelected(int position){
        switch (position){
            case 0:
                mNavView.setSelectedItemId(R.id.nav_item_home);
                break;
            case 1:
                mNavView.setSelectedItemId(R.id.nav_item_record);
                break;
            case 2:
                mNavView.setSelectedItemId(R.id.nav_item_person);
                break;
            default:
                break;

        }
    }
    private void getUsr(){
        mhomeFragment.setUsr(usr);
        mpersonFragment.setUsr(usr);
        mrecordFragment.setUsr(usr);
        Log.e("MainActivityUsr","usr name:"+usr.getUsr_name());
        Log.e("MainActivityUsr","usr email:"+usr.getEmail());
        Log.e("MainActivityUsr","usr password:"+usr.getPassword());
    }
    private void getDailyIntake(){
        mrecordFragment.setDailyIntake(dailyIntake);
    }

    @Override
    protected void onNewIntent(Intent intent) { //singleTask 第二次跳转时不会调用oncreate，会调用此方芳获取intent
        super.onNewIntent(intent);
        setIntent(intent);
        Log.e("Intent","onNewIntent");
        processExtraData(); //获取数据
        getUsr();//刷新三个fragment页面usr数据
        getDailyIntake();
        mpersonFragment.setUserImformation(); //刷新个人页面显示数据
        mrecordFragment.setDailyIntakeInformation();
    }
    private void processExtraData(){ //获取intent中的用户数据

        User usrRegister = (User) getIntent().getSerializableExtra("RegisterNewUsr");
        User usrLogin = (User) getIntent().getSerializableExtra("LoginUsr");
        if(usrLogin !=null){
            usr = usrLogin;
        }else if(usrRegister!=null){
            usr = usrRegister;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "MainActivity:RequestCode:"+String.valueOf(requestCode));
        switch (requestCode) {
            case BASIC_INFOR_REQUEST:
                if (resultCode == RESULT_OK) {
                    usr = (User) data.getSerializableExtra("BasicInforUsr");
                    Log.e("Main", "usr name:" + usr.getUsr_name());
                    Log.e("Main", "usr email:" + usr.getEmail());
                    Log.e("Main", "usr password:" + usr.getPassword());
                    Log.e("Main", "usr sex:" + usr.getSex());
                    Log.e("Main", "usr phone:" + usr.getPhone());
                    setUsr();

                }
                break;
            case ILLNESS_INFOR_REQUEST:
                if (resultCode == RESULT_OK) {
                    usr = (User) data.getSerializableExtra("IllnessInforUsr");
                    Log.e("Main", "usr age:" + usr.getAge());
                    Log.e("Main", "usr height:" + usr.getHeight());
                    Log.e("Main", "usr weight:" + usr.getWeight());
                    Log.e("Main", "usr blood pressure:" + usr.getBlood_pressure());
                    Log.e("Main", "usr blood glucose:" + usr.getBlood_glucose());
                    setUsr();
                }
                break;
            case TAKE_PHOTO_REQUEST:
                if(resultCode == RESULT_OK){
                    dailyIntake = (DailyIntake) data.getSerializableExtra("TakePhotoActivity");
                    Log.e(TAG,"MainActivity:dailyIntake-breakfast:"+dailyIntake.getBreakfast());
                    Log.e(TAG,"MainActivity:dailyIntake-lunch:"+dailyIntake.getLunch());
                    Log.e(TAG,"MainActivity:dailyIntake-dinner:"+dailyIntake.getDinner());
                    Log.e(TAG,"MainActivity:dailyIntake-extra:"+dailyIntake.getExtra());
                    setDailyIntake();

                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
    private void setUsr(){
        mpersonFragment.setUsr(usr);
        mpersonFragment.setUserImformation();
        mhomeFragment.setUsr(usr);
        mrecordFragment.setUsr(usr);
    }
    private void setDailyIntake(){
        mrecordFragment.setDailyIntake(dailyIntake);
        mrecordFragment.setDailyIntakeInformation();
    }
}