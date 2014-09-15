package com.example.managedollar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.managedollar.http.httpRequest;

/**
 * Created by 刚 on 2014/7/25.
 */

public class RegisterActivity extends Activity {
    private EditText userName, password;
    private Button submit;
    private String name, psw;
    private ProgressDialog mpd;
    private Dialog ad;
    httpRequest mHttpRequest = new httpRequest();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String result = msg.obj.toString();
                Log.i("resss: ", result);
                if ("success".equals(result.trim())) {
                    mpd.cancel();
                    Intent intent = new Intent(RegisterActivity.this, MainOfManage.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", name);
                    bundle.putString("password", psw);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    userName.setText("");
                    password.setText("");
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
                    mpd.cancel();
                }
            } else {
                Log.i("错误代码", msg.obj.toString());
                mpd.dismiss();
                ad.show();
                ad.setCancelable(true);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg);
        setupComponent();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userName.getText().toString();

                psw = password.getText().toString();
                ad = new AlertDialog.Builder(RegisterActivity.this).
                        setTitle("登陆出错").
                        setMessage("服务器连接超时").
                        create();
                if (name.isEmpty()) {
                    userName.setHint("请输入用户名");
                } else if (psw.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    mpd = ProgressDialog.show(RegisterActivity.this, "提示", "正在注册...");
                    mHttpRequest.userRegister(handler, name, psw);

                }
            }
        });
    }

    @Override
    protected void onResume() {
        userName.setText("");
        password.setText("");
        super.onResume();
    }


    private void setupComponent() {
        userName = (EditText) findViewById(R.id.txt_reg_userName);
        password = (EditText) findViewById(R.id.txt_reg_password);
        submit = (Button) findViewById(R.id.btn_reg_submit);

    }
}
