package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TestStartActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tv_title;
    private TextView tv_01,tv_02,tv_03,tv_04;
    private Intent intent;
    private Intent mIntent;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_start);

        initView();

        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_test_start_title);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tv_title = (TextView) findViewById(R.id.tv_test_start_title);
        tv_01 = (TextView) findViewById(R.id.tv_test_start_01);
        tv_02 = (TextView) findViewById(R.id.tv_test_start_02);
        tv_03 = (TextView) findViewById(R.id.tv_test_start_03);
        tv_04 = (TextView) findViewById(R.id.tv_test_start_04);
        findViewById(R.id.btn_test_virtual).setOnClickListener(this);
    }

    private void initData() {
        intent = getIntent();
        type = intent.getIntExtra("type", -1);
        Log.i("TestStartActivity",type+"+++===============+++");

        mIntent = new Intent();
        mIntent.putExtra("type",type);

        setMyTitleAndContent();
    }

    private void setMyTitleAndContent() {
        switch (type){
            case 1:
                tv_01.setText("考试科目：科目一理论考试");
                tv_02.setText("考试车型：小车（C1，C2，C3）");
                tv_03.setText("合格标准：90分及格，100分满分");
                tv_04.setText("考试介绍：总题100题，每题1分，总时45分，已作答的题目不能修改");
                break;
            case 4:
                tv_title.setText("科目四模拟考试");
                tv_01.setText("考试科目：科目四安全文明考试");
                tv_02.setText("考试车型：小车（C1，C2，C3）");
                tv_03.setText("合格标准：90分及格，100分满分");
                tv_04.setText("考试介绍：总题50题，每题2分，总时45分，已作答的题目不能修改");
                break;
        }
    }



    @Override
    public void onClick(View view) {
        mIntent.setClass(this,TestContentActivity.class);
        startActivity(mIntent);
        finish();
    }
}
