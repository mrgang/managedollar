package com.example.managedollar.myfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.managedollar.MainOfManage;
import com.example.managedollar.R;
import com.example.managedollar.entities.record;
import com.example.managedollar.http.httpRequest;
import com.example.managedollar.myadapters.MyHistoryBillAdapter;
import com.example.managedollar.mylistview.XListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 刚 on 2014/7/25.
 */
public class FragHistoryB extends Fragment implements XListView.IXListViewListener {
    private XListView mylv;
    httpRequest mHttpRequest = new httpRequest();
    List<record> data;
    public static double totalGet = 0, totalUse = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i("handler get :", msg.obj.toString());
            JSONArray jsonA;
            try {
                jsonA = new JSONArray(msg.obj.toString());
                totalGet = 0;
                totalUse = 0;
                data = new ArrayList<record>();
                for (int i = 0; i < jsonA.length(); i++) {
                    JSONObject object = jsonA.getJSONObject(i);
                    int flag = Integer.parseInt(object.getString("flag"));
                    String num = object.getString("num");
                    String mDescribe = object.getString("mdescribe");
                    String strDate = object.getString("date");
                    if (flag == 1) totalGet += Integer.parseInt(num);
                    else totalUse += Integer.parseInt(num);
                    data.add(new record(flag, num, mDescribe, strDate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mylv.setAdapter(new MyHistoryBillAdapter(getActivity(),
                    R.layout.fg_historyb_item, data));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_historybills, null);
        mylv = (XListView) view.findViewById(R.id.fg_historyB_main);
        mylv.setXListViewListener(this);
        mHttpRequest.userGetHistoryB(handler, MainOfManage.keyuser);
        return view;
    }

    private void onLoad() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mylv.stopRefresh();
        mylv.stopLoadMore();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        String strDate = format.format(new Date());
        mylv.setRefreshTime(strDate);
    }

    @Override
    public void onRefresh() {
        mHttpRequest.userGetHistoryB(handler, MainOfManage.keyuser);
        onLoad();
    }

    @Override
    public void onLoadMore() {

    }
}
