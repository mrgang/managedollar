package com.example.managedollar.myadapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.managedollar.R;
import com.example.managedollar.entities.record;

import java.util.List;


/**
 * Created by 刚 on 2014/7/28.
 */
public class MyHistoryBillAdapter extends ArrayAdapter<record> {
    private int flag;
    private String describe;
    private String num;
    private List<record> mRecord;
    private LayoutInflater mInflater;
    private int mResourceId;
    private record mrecord;
    public MyHistoryBillAdapter(Context context, int resource, List<record> objects) {
        super(context, resource, objects);
        mInflater = (LayoutInflater.from(context));
        mRecord = objects;
        mResourceId = resource;
    }

    @Override
    public int getCount() {
        return mRecord.size();
    }

    @Override
    public record getItem(int position) {
        return mRecord.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mInflater.inflate(mResourceId, null);
        mrecord = mRecord.get(position);
        ImageView img = (ImageView)convertView.findViewById(R.id.get_or_use);
        TextView des = (TextView)convertView.findViewById(R.id.txt_describe_item);
        TextView num = (TextView)convertView.findViewById(R.id.txt_num_item);
        flag = mrecord.getFlag();
        if (flag == 1){
            img.setBackgroundResource(R.drawable.green);
            img.setImageResource(R.drawable.get);
            num.setTextColor(Color.GREEN);
            num.setText("+\t"+mrecord.getNum());
        }else
        if (flag == 0){
            img.setBackgroundResource(R.drawable.orange);
            img.setImageResource(R.drawable.use);
            num.setTextColor(Color.RED);
            num.setText("-\t"+mrecord.getNum());
        }
        des.setText(mrecord.getDate()+"\n"+mrecord.getDescribe());

        return convertView;
    }
}
