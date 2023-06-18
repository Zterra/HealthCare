package com.example.healthcare.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.example.healthcare.R;
import com.example.healthcare.adapter.LikelyRecipeAdapter;
import com.example.healthcare.adapter.RecipeAdapter;
import com.example.healthcare.entity.Recipe;
import com.example.healthcare.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private User usr;
    private GridView healthEatList;  //健康饮食
    private List<Recipe> healthEatData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    public HomeFragment(User usr){
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
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        initView(v);
        initData();

        BaseAdapter healthEatAdapter = new RecipeAdapter(healthEatData,getActivity());
        healthEatList.setAdapter(healthEatAdapter);
//        healthEatList.setDivider(null);
//        healthEatList.setDividerHeight(0);
        healthEatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        startActivity(new Intent(getActivity(),RecipeActivity.class));
                        break;
                        case 0:
                            startActivity(new Intent(getActivity(),FoodActivity.class));
                            break;
                }
            }
        });
        return v;
    }
    public void initView(View v){
        healthEatList = v.findViewById(R.id.home_healtheat_list);


    }
    private void initData(){
        healthEatData = new ArrayList<>();
//        for(int i = 0; i < 8; i++){
//            Recipe recipe = new Recipe("地中海饮食健康食谱",i,"心血管","海鱼",i);
//            healthEatData.add(recipe);
//        }
        Recipe recipe = new Recipe("食物分类",0,"心血管","海鱼",0,R.drawable.food_recommend);
        healthEatData.add(recipe);
        Recipe recipe1 = new Recipe("推荐食谱",1,"心血管",
                "海鱼",1,R.drawable.recipe_record);
        healthEatData.add(recipe1);
    }
}