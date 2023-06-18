package com.example.healthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcare.R;
import com.example.healthcare.bean.DetectFood;

import java.util.List;



public class DetectFoodAdapter extends BaseAdapter {
    private List<DetectFood> list;
    private Context ctx;

    public DetectFoodAdapter(List<DetectFood> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.item_detect_food,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (EditText) view.findViewById(R.id.item_detect_name);
            viewHolder.calories = (EditText) view.findViewById(R.id.item_detect_calories);
            viewHolder.delete_btn = (Button) view.findViewById(R.id.item_detect_delete);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        DetectFood detectFood = list.get(i);

        viewHolder.name.setText(detectFood.getName());

        viewHolder.calories.setText(detectFood.getCalories());
        viewHolder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(i);
                //Toast.makeText(v.getContext(),"删除成功！",Toast.LENGTH_SHORT).show();
            }
        });
        if(onDataChangedListener!=null){
            onDataChangedListener.onDataChanged(list);
        }
        return view;
    }
    public void remove(int position){
        list.remove(position); //删除对应位置的数据项
        notifyDataSetChanged(); //刷新列表
//        if(onDataChangedListener !=null){
//            onDataChangedListener.onDataChanged(list);
//        }

    }
    public interface onDataChangedListener{
        void onDataChanged(List <DetectFood> detectFoodList);
    }
    public onDataChangedListener onDataChangedListener;
    public void setOndataChangedListener(onDataChangedListener onDataChangedListener){
        this.onDataChangedListener = onDataChangedListener;
    }

    public List<DetectFood> getList() {
        return list;
    }

    private class ViewHolder{
        EditText name;
        EditText calories;
        Button delete_btn;
    }
}
