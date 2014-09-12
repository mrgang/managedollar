package com.example.managedollar.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * Created by 刚 on 2014/7/25.
 */
public class httpRequest {
    public static String ip = "112.193.203.117";
    public void userLogin(final Handler handler, String userName, String password) {

        AjaxParams params = new AjaxParams();
        params.put("userName", userName);
        params.put("password", password);

        FinalHttp fh = new FinalHttp();
        fh.configTimeout(5000);
        fh.post("http://" + ip + ":8080/servlets/userLogin", params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String ss = o.toString();
                ss = ss.trim();
                Log.i("登陆返回结果：", ss);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = ss;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("httprequest.login 错误代码:", errorNo + "");
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    public void userRegister(final Handler handler, String userName, String password) {

        AjaxParams params = new AjaxParams();
        params.put("userName", userName);
        params.put("password", password);
        FinalHttp fh = new FinalHttp();
        fh.configTimeout(5000);
        fh.post("http://" + ip + ":8080/servlets/userRegister", params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                String ss = o.toString();
                ss = ss.trim();
                Log.i("注册返回结果：", ss);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = ss;
                msg.sendToTarget();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.i("httprequest.login 错误代码:", errorNo + "");
                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = errorNo;
                msg.sendToTarget();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    public void userGetHistoryB(final Handler handler, String name) {

        AjaxParams params = new AjaxParams();
        params.put("userName", name);
        FinalHttp fh = new FinalHttp();
        fh.post("http://" + ip + ":8080/servlets/userGetHistoryB", params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = o;
                handler.sendMessage(msg);Log.i("ssss",o.toString());
                super.onSuccess(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
