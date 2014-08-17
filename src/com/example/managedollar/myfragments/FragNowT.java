package com.example.managedollar.myfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.managedollar.R;

/**
 * Created by 刚 on 2014/7/25.
 */
public class FragNowT extends Fragment {
    public static String totGet,totUse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_nowtotal,null);
        TextView totalGet,totalUse;
        totalGet = (TextView)view.findViewById(R.id.total_get);
        totalUse = (TextView)view.findViewById(R.id.total_use);
        totGet = ""+FragHistoryB.totalGet;
        totUse = ""+FragHistoryB.totalUse;
        totalGet.setText(totGet);
        totalUse.setText(totUse);

        return view;
    }
}
