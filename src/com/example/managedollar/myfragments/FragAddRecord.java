package com.example.managedollar.myfragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.managedollar.MainOfManage;
import com.example.managedollar.R;
import com.example.managedollar.http.httpRequest;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 刚 on 2014/7/25.
 */
public class FragAddRecord extends Fragment {
    RadioButton get, use;
    Spinner spinnerDescribe;
    CheckBox checkBoxIsSelfDescribe;
    EditText textSelfDescribe, textNum;
    Button buttonSubmit;
    private String keys = MainOfManage.keyuser;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_addrecord, null);

        get = (RadioButton) view.findViewById(R.id.rad_btn_get);
        use = (RadioButton) view.findViewById(R.id.rad_btn_use);
        spinnerDescribe = (Spinner) view.findViewById(R.id.sp_detail);
        checkBoxIsSelfDescribe = (CheckBox) view.findViewById(R.id.chk_is_self_describe);
        textSelfDescribe = (EditText) view.findViewById(R.id.txt_self_describe);
        buttonSubmit = (Button) view.findViewById(R.id.btn_submit_record);
        textNum = (EditText) view.findViewById(R.id.txt_num_of_money);

        String[] spdata = new String[]{"餐饮", "交通", "购物", "娱乐","工资","理财分红","投资"};

        spinnerDescribe.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, spdata));
        spinnerDescribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        checkBoxIsSelfDescribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxIsSelfDescribe.isChecked()) {
                    textSelfDescribe.setVisibility(View.VISIBLE);
                    textSelfDescribe.setCursorVisible(true);
                } else {
                    textSelfDescribe.setText("");
                    textSelfDescribe.setVisibility(View.INVISIBLE);
                }
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                if (get.isChecked()) {
                    flag = 1;
                } else if (use.isChecked()) {
                    flag = 0;
                }
                String describe = null;
                if (checkBoxIsSelfDescribe.isChecked()) {
                    describe = textSelfDescribe.getText().toString();
                } else
                    describe = spinnerDescribe.getSelectedItem().toString();
                //提交纪录数据。
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = format.format(new Date());

                String num = textNum.getText().toString();
                AjaxParams params = new AjaxParams();
                params.put("key",keys);
                params.put("flag",flag+"");
                params.put("describe",describe);
                params.put("num",num);
                params.put("date",strDate);
                FinalHttp finalHttp = new FinalHttp();
                finalHttp.post("http://"+ httpRequest.ip+":8080/servlets/userAddData",params,new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getActivity(),"添加纪录成功",Toast.LENGTH_LONG).show();
                        super.onSuccess(o);
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        Log.i("向服务器添加数据出错 ：错误代码：",errorNo+"");
                        super.onFailure(t, errorNo, strMsg);
                    }
                });
            }
        });
        return view;
    }

}
