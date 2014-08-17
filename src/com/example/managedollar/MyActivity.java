package com.example.managedollar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.managedollar.http.httpRequest;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private EditText editUserName, editPassword;
    private TextView register;
    private Button login, quit;
    private ProgressDialog mpd;
    private String username, password;
    httpRequest mHttpRequest = new httpRequest();
    private String result = null;
    final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                result = msg.obj.toString();
                Log.i("登陆结果inMyActivity: ", result);
                if ("success".equals(result)) {
                    mpd.cancel();
                    Intent intent = new Intent(MyActivity.this, MainOfManage.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", username);
                    bundle.putString("password", password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    editUserName.setText("");
                    editUserName.setHint("登陆失败，请重新输入");
                    editPassword.setText("");
                    mpd.cancel();
                }
            } else {
                Log.i("MyActivity login : ", msg.obj.toString());
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setupViewComponent();
    }

    private void setupViewComponent() {

        editUserName = (EditText) findViewById(R.id.txt_main_userName);
        editPassword = (EditText) findViewById(R.id.txt_main_password);
        register = (TextView) findViewById(R.id.txt_main_register);
        login = (Button) findViewById(R.id.btn_main_login);
        quit = (Button) findViewById(R.id.btn_main_quit);

        login.setOnClickListener(this);
        quit.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_login:
                username = editUserName.getText().toString();
                password = editPassword.getText().toString();
                mpd = ProgressDialog.show(this, "提示", "正在登陆...");
                mHttpRequest.userLogin(mHandler, username, password);
                break;
            case R.id.btn_main_quit:
                System.exit(0);
                break;
            case R.id.txt_main_register:
                startActivity(new Intent(MyActivity.this, RegisterActivity.class));
                break;
            default:
        }
    }
}
