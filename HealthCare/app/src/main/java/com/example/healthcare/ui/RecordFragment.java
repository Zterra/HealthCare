package com.example.healthcare.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.healthcare.R;
import com.example.healthcare.entity.DailyIntake;
import com.example.healthcare.entity.User;
import com.example.healthcare.record.RecommendFoodActivity;
import com.example.healthcare.record.TakePhotoActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment {
    public static final String TAG="HealthCare";
    public static final int TAKE_PHOTO_REQUEST = 101;
    private User usr;
    private DailyIntake dailyIntake;
    RecordClickListener recordClickListener = new RecordClickListener();
    //TextView date;
    TextView todaytake;
    TextView cantake;
    TextView breakfast_num;
    TextView lunch_num;
    TextView dinner_num;
    TextView extra_num;
    Button take_camera;
    Button analyse;
    RelativeLayout breakfastLayout;
    RelativeLayout lunchLayout;
    RelativeLayout dinnerLayout;
    RelativeLayout extraLayout;
    ImageView breakfast_pic;
    ImageView lunch_pic;
    ImageView dinner_pic;
    ImageView extra_pic;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecordFragment() {
        // Required empty public constructor
    }
    public RecordFragment(User usr){
        this.usr = usr;
    }
    public RecordFragment(User usr,DailyIntake dailyIntake){
        this.usr = usr;
        this.dailyIntake = dailyIntake;
    }



    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public void setDailyIntake(DailyIntake dailyIntake) {
        this.dailyIntake = dailyIntake;
    }

    public void setDailyIntakeInformation(){
        if(dailyIntake !=null){
            int all =0;
            if(dailyIntake.getBreakfast()!=-1){
                all = all+dailyIntake.getBreakfast();
                breakfast_num.setText(dailyIntake.getBreakfast()+"cal");
            }
            if(dailyIntake.getLunch()!=-1){
                all = all+dailyIntake.getLunch();
                lunch_num.setText(dailyIntake.getLunch()+"cal");
            }
            if(dailyIntake.getDinner()!=-1){
                all = all + dailyIntake.getDinner();
                dinner_num.setText(dailyIntake.getDinner()+"cal");
            }
            if(dailyIntake.getExtra()!=-1){
                all = all +dailyIntake.getExtra();
                extra_num.setText(dailyIntake.getExtra()+"cal");
            }
            todaytake.setText(all+"cal");
            int can = 3000-all;
            cantake.setText(can+"cal");
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_record, container, false);
        initView(v);
        return v;
    }
    public void initView(View view){
        //date = view.findViewById(R.id.record_calendar);
        todaytake = view.findViewById(R.id.record_todaytake_num);
        cantake = view.findViewById(R.id.record_cantake_num);
        breakfast_num = view.findViewById(R.id.record_breakfast_number);
        lunch_num = view.findViewById(R.id.record_lunch_number);
        dinner_num = view.findViewById(R.id.record_dinner_number);
        extra_num = view.findViewById(R.id.record_extra_number);
        take_camera = view.findViewById(R.id.record_takecamera_btn);
        analyse = view.findViewById(R.id.record_analyse_btn);
        breakfastLayout = view.findViewById(R.id.record_breakfast_layout);
        lunchLayout = view.findViewById(R.id.record_lunch_layout);
        dinnerLayout = view.findViewById(R.id.record_dinner_layout);
        extraLayout = view.findViewById(R.id.record_extra_layout);
        breakfast_pic = view.findViewById(R.id.record_breakfast_pic);
        lunch_pic = view.findViewById(R.id.record_lunch_pic);
        dinner_pic = view.findViewById(R.id.record_dinner_pic);
        extra_pic = view.findViewById(R.id.record_extra_pic);



        breakfastLayout.setOnClickListener(recordClickListener);
        lunchLayout.setOnClickListener(recordClickListener);
        dinnerLayout.setOnClickListener(recordClickListener);
        extraLayout.setOnClickListener(recordClickListener);
        //date.setOnClickListener(recordClickListener);
        take_camera.setOnClickListener(recordClickListener);
        analyse.setOnClickListener(recordClickListener);

        Glide.with(this).load(R.drawable.breakfast).into(breakfast_pic);
        Glide.with(this).load(R.drawable.lunch).into(lunch_pic);
        Glide.with(this).load(R.drawable.dinner).into(dinner_pic);
        Glide.with(this).load(R.drawable.extra).into(extra_pic);
    }
    public class RecordClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.record_breakfast_layout:
                    break;
                case R.id.record_lunch_layout:
                    break;
                case R.id.record_dinner_layout:
                    break;
                case R.id.record_extra_layout:
                    break;
                case R.id.record_takecamera_btn:
                    Intent intent = new Intent(getActivity(), TakePhotoActivity.class);
                    intent.putExtra("mRecordFragment",dailyIntake);
                    Log.e(TAG,"RecordFragment:dailyIntake-breakfast:"+dailyIntake.getBreakfast());
                    getActivity().startActivityForResult(intent,TAKE_PHOTO_REQUEST);
                    //startActivityForResult();
                    break;
                case R.id.record_analyse_btn:
                    Intent intent1 = new Intent(getActivity(), RecommendFoodActivity.class);
                    getActivity().startActivity(intent1);
                    break;
//                case R.id.record_calendar:
//                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    }

}