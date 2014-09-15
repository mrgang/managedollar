package com.example.managedollar;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.managedollar.myfragments.FragAddRecord;
import com.example.managedollar.myfragments.FragHistoryB;
import com.example.managedollar.myfragments.FragNowT;

/**
 * Created by 刚 on 2014/7/25.
 */
public class MainOfManage extends Activity implements View.OnClickListener {
    private TextView topTip;
    private ImageButton addR, historyB, nowT;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    public static String keyuser;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        keyuser = bundle.getString("userName");
        setContentView(R.layout.mainofmanage);
        setupViewComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.about:
                break;
            case R.id.exit:
                System.exit(0);
        }
        return true;
    }

    private void setupViewComponent() {
        topTip = (TextView) findViewById(R.id.top_tip);
        addR = (ImageButton) findViewById(R.id.btn_main_addRecord);
        historyB = (ImageButton) findViewById(R.id.btn_main_historyBills);
        nowT = (ImageButton) findViewById(R.id.btn_main_nowTotal);

        addR.setOnClickListener(this);
        historyB.setOnClickListener(this);
        nowT.setOnClickListener(this);

        addR.callOnClick();
    }

    @Override
    public void onClick(View v) {
        if (v == addR) {
            topTip.setText("添加纪录");
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.view_of_main, new FragAddRecord());
            fragmentTransaction.commit();
        }
        if (v == historyB) {
            topTip.setText("历史账单");
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.view_of_main, new FragHistoryB());
            fragmentTransaction.commit();
        }
        if (v == nowT) {
            topTip.setText("当前收支");
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.view_of_main, new FragNowT());
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
