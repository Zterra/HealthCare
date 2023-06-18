package com.example.healthcare.ui;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.healthcare.R;
import com.example.healthcare.entity.User;
import com.example.healthcare.login.LoginActivity;
import com.example.healthcare.person.BasicImforActivity;
import com.example.healthcare.person.LikelyRecipeActivity;
import com.example.healthcare.person.SuggestionActivity;
import com.example.healthcare.person.illnessInforActivity;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    public static final int BASIC_INFOR_REQUEST = 1;
    public static final int ILLNESS_INFOR_REQUEST = 10;




    private User usr;
    PersonListener personListener = new PersonListener();
    private TextView usrname;
    private TextView usrid;
    private Button signin;
    private RelativeLayout user_rect;
    private LinearLayout basic_infor;
    private LinearLayout basic_illness_infor;
    private LinearLayout liky_recipe;
    private LinearLayout health_test;
    private LinearLayout heeath_method;
    private LinearLayout suggestion;
    private CircleImageView head_portrait;

    private ImageView basic_infor_pic;
    private ImageView illness_infor_pic;
    private ImageView recipe_pic;
    private ImageView icon1;
    private ImageView icon2;
    private ImageView icon3;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonFragment() {
        // Required empty public constructor
    }
    public PersonFragment(User usr){
        this.usr = usr;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
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
        View v = inflater.inflate(R.layout.fragment_person, container, false);
        initView(v);


        return v;
    }
    private void initView(View view){
        usrid = view.findViewById(R.id.person_id);
        usrname = view.findViewById(R.id.person_name);
        signin = view.findViewById(R.id.signin);
        user_rect = view.findViewById(R.id.person_userrect);
        basic_infor = view.findViewById(R.id.person_basicImformation);
        basic_illness_infor = view.findViewById(R.id.person_illnessInformation);
        liky_recipe = view.findViewById(R.id.person_Recipe);
        health_test = view.findViewById(R.id.person_health_test);
        heeath_method = view.findViewById(R.id.person_health_method);
        suggestion = view.findViewById(R.id.person_suggestion);

        head_portrait = view.findViewById(R.id.person_head_portrait);
        basic_infor_pic = view.findViewById(R.id.person_basic_infor_pic);
        illness_infor_pic = view.findViewById(R.id.person_basic_illness_infor_pic);
        recipe_pic = view.findViewById(R.id.person_recipe_pic);
        icon1 = view.findViewById(R.id.person_icon1);
        icon2 = view.findViewById(R.id.person_icon2);
        icon3 = view.findViewById(R.id.person_icon3);

        signin.setOnClickListener(personListener);
        user_rect.setOnClickListener(personListener);
        basic_infor.setOnClickListener(personListener);
        basic_illness_infor.setOnClickListener(personListener);
        liky_recipe.setOnClickListener(personListener);
        health_test.setOnClickListener(personListener);
        heeath_method.setOnClickListener(personListener);
        suggestion.setOnClickListener(personListener);

        Glide.with(getActivity()).load(R.drawable.person_header_boy).into(head_portrait);
        Glide.with(getActivity()).load(R.drawable.person_basic_infor).into(basic_infor_pic);
        Glide.with(getActivity()).load(R.drawable.person_illness_infor).into(illness_infor_pic);
        Glide.with(getActivity()).load(R.drawable.person_recipe).into(recipe_pic);
        Glide.with(getActivity()).load(R.drawable.person_heathtest).into(icon1);
        Glide.with(getActivity()).load(R.drawable.person_heathmethod).into(icon2);
        Glide.with(getActivity()).load(R.drawable.person_suggestion).into(icon3);

        setUserImformation();  //设置用户信息




    }
    public void setUserImformation(){
        if(usr!=null){
            if(usr.getHeaders()!=null){
                Glide.with(getActivity()).load(usr.getHeaders()).into(head_portrait);
            }
            if(usr.getUsr_name()!=null){
                usrname.setText(usr.getUsr_name());
            }
            if(usr.getId()!=null){
                usrid.setText(usr.getId());
            }
        }
    }

    private class PersonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.signin:
                    Log.d("Person","click SignIn Button");
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                    //getActivity().finish();
                    break;
                case R.id.person_userrect: //进入基本信息页面
                    Log.d("Person","click user_rect Button");
                   // Intent intent1 = new Intent(getActivity(), BasicImforActivity.class);
                   // startActivity(intent1);
                    break;
                case R.id.person_basicImformation: //进入基本信息页面
                    Log.d("Person","click basic_infor Button");
                    Intent intent2 = new Intent(getActivity(), BasicImforActivity.class);
                    intent2.putExtra("PersonFragementUsr",usr);
                    getActivity().startActivityForResult(intent2,BASIC_INFOR_REQUEST);
                    break;
                case R.id.person_illnessInformation: //进入基本疾病页面
                    Log.d("Person","click basic_illness_infor Button");
                    Intent intent3 = new Intent(getActivity(),illnessInforActivity.class);
                    intent3.putExtra("PersonFragmentUsr",usr);
                    getActivity().startActivityForResult(intent3,ILLNESS_INFOR_REQUEST);
                    break;
                case R.id.person_Recipe: //进入收藏食谱页面
                    Log.d("Person","click liky_recipe Button");
                    Intent intent4 = new Intent(getActivity(), LikelyRecipeActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.person_health_test:
                    Log.d("Person","click health_test Button");
                    Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.person_health_method:
                    Log.d("Person","click heeath_method Button");
                    Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.person_suggestion:
                    Log.d("Person","click suggestion Button");
                    Intent intent7 = new Intent(getActivity(), SuggestionActivity.class);
                    startActivity(intent7);
                    break;


                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        //super.onActivityResult( requestCode,  resultCode,  data);
//        switch (requestCode){
//            case BASIC_INFOR_REQUEST:
//                if(resultCode == RESULT_OK){
//                    usr = (User)data.getSerializableExtra("BasicInforUsr");
//                    Log.e("PersonFragment","usr name:"+usr.getUsr_name());
//                    Log.e("PersonFragment","usr email:"+usr.getEmail());
//                    Log.e("PersonFragment","usr password:"+usr.getPassword());
//                    Log.e("PersonFragment","usr sex:"+usr.getSex());
//                    Log.e("PersonFragment","usr phone:"+usr.getPhone());
//                    setUserImformation();
//                }
//                break;
//            case ILLNESS_INFOR_REQUEST:
//                if (resultCode == RESULT_OK){
//                    usr = (User) data.getSerializableExtra("IllnessInforUsr");
//                    Log.e("PersonFragment","usr age:"+usr.getAge());
//                    Log.e("PersonFragment","usr height:"+usr.getHeight());
//                    Log.e("PersonFragment","usr weight:"+usr.getWeight());
//                    Log.e("PersonFragment","usr blood pressure:"+usr.getBlood_pressure());
//                    Log.e("PersonFragment","usr blood glucose:"+usr.getBlood_glucose());
//                }
//
//            default:
//                throw new IllegalStateException("Unexpected value: " + requestCode);
//        }
//    }
}