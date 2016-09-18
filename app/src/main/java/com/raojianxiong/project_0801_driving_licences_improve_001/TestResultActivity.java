package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TestResultActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tv_result;
    private ImageView img_result;
    private Intent intent;
    private Intent mIntent;
    private int right_counts;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        intent = getIntent();
        right_counts = intent.getIntExtra("right_counts", -1);
        type = intent.getIntExtra("type",-1);
        Log.i("TestResultActivity",type+":type");
        mIntent = new Intent();
        mIntent.putExtra("type",type);

        initView();

        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_result_title);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
        tv_result = (TextView) findViewById(R.id.tv_result);
        img_result = (ImageView) findViewById(R.id.img_result);
        findViewById(R.id.btn_continue).setOnClickListener(this);
    }

    private void initData() {
        if (right_counts < 90){
            img_result.setBackgroundResource(R.mipmap.bujige);
        }else if(right_counts == 90){
            img_result.setBackgroundResource(R.mipmap.success_90);
        }else if(right_counts < 94){
            img_result.setBackgroundResource(R.mipmap.success_91);
        }else if(right_counts < 96){
            img_result.setBackgroundResource(R.mipmap.success_94);
        }else if(right_counts <100){
            img_result.setBackgroundResource(R.mipmap.success_100);
        }
        tv_result.setText("考试成绩为："+right_counts);
    }

    @Override
    public void onClick(View view) {
        mIntent.setClass(this,TestStartActivity.class);
        startActivity(mIntent);
        finish();
    }
}
