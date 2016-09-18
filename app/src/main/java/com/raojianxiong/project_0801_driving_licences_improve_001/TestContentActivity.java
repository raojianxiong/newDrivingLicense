package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.fragment.TestFragmentVirtual;
import com.raojianxiong.fragment.TestMisFragmentVirtual;
import com.raojianxiong.pojo.WebContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestContentActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Intent intent;
    private Intent mIntent;
    private int type;
    private RadioButton rb_next, rb_last, rb_submit;
    private RadioGroup rg_botton;
    private TextView tv_time;
    private List<WebContent> list;
    public static List<Integer> note;
    private int position;
    public static int right_counts;//用于记录作对的题目数量
    public static int do_counts;//用于记录做过的题目总数
    private MyReceiver receiver;
    private boolean flag;
    private long time;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            tv_time.setText(undateTime(time));
            return true;
        }
    });


    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            nextQuestion();
//            list.remove(position);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content);

        intent = getIntent();
        type = intent.getIntExtra("type", -1);

        mIntent = new Intent();

        initView();

        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_test_start_content_title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        rb_next = (RadioButton) findViewById(R.id.rb_test_next);
        rb_last = (RadioButton) findViewById(R.id.rb_text_last);
        rb_submit = (RadioButton) findViewById(R.id.rb_test_collection);
        rg_botton = (RadioGroup) findViewById(R.id.rg_test_bottom);
        tv_time = (TextView) findViewById(R.id.tv_time);
        rb_last.setOnClickListener(this);
        rb_next.setOnClickListener(this);
        rb_submit.setOnClickListener(this);
    }

    private void initData() {
        note = new ArrayList<Integer>();
        position = 0;
        flag = true;
        time = 45 * 60 * 1000;
        Log.i("TestContentActivity", type + "++++++++++++++++++");
        list = DBUtils.RandomGetQuestion(this, type);
        upQuestion(list.get(position), type);

        //注册用于更新题目的广播
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("update_question");
        registerReceiver(receiver, filter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag && time >= 0) {
                    try {
                        time = time - 1000;
                        handler.sendEmptyMessage(0);
                        Thread.sleep(1000);
//                        Log.i("info", time + "ppppppppppppp");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        switch (type) {
            case 1:
                break;
            case 4:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    private void nextQuestion() {
        if (position >= list.size() - 1) {
            Toast.makeText(this, "已经是最后一题了", Toast.LENGTH_SHORT).show();
            return;
        }
        upQuestion(list.get(++position), type);
    }

    private void lastQuestion() {
        if (position <= 0) {
            Toast.makeText(this, "已经是第一题了", Toast.LENGTH_SHORT).show();
            return;
        }
        upQuestion(list.get(--position), type);
    }


    private void upQuestion(WebContent webContent, int type) {
        switch (type) {
            case 1:
                TestFragmentVirtual fragment = new TestFragmentVirtual();
                Bundle bundle = new Bundle();
                //Fragment显示需要传递的数据
                bundle.putSerializable("WebContent", webContent);
                bundle.putInt("position", position);
                bundle.putInt("sum", list.size());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame_layout_test_content, fragment, "TestFragment");
                transaction.commit();
                break;
            case 4:
                TestMisFragmentVirtual testFragment = new TestMisFragmentVirtual();
                Bundle misBundle = new Bundle();
                //Fragment显示需要传递的数据
                misBundle.putSerializable("WebContent", webContent);
                misBundle.putInt("position", position);
                misBundle.putInt("sum", list.size());
                testFragment.setArguments(misBundle);
                FragmentManager misManager = getSupportFragmentManager();
                FragmentTransaction misTransaction = misManager.beginTransaction();
                misTransaction.replace(R.id.frame_layout_test_content, testFragment, "TestMisFragment");
                misTransaction.commit();
                break;
        }

    }

    private String undateTime(long time) {
        long minuter = time / 1000 / 60;
        long second = (time / 1000) % 60;
        return minuter + ":" + second;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_test_next:
                nextQuestion();
                break;
            case R.id.rb_text_last:
                lastQuestion();
                break;
            case R.id.rb_test_collection:
                String indicate = "";
                switch (type) {
                    case 1:
                        indicate = indicate + "您还有" + (100 - do_counts) + "道题没答，确认交卷？";
                        break;
                    case 4:
                        indicate = indicate + "您还有" + (50 - do_counts) + "道题没答，确认交卷？";
                        break;

                }
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage(indicate)
                        .setNegativeButton("再做一会", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mIntent.putExtra("right_counts", right_counts);
                        mIntent.putExtra("type",type);
                        mIntent.setClass(TestContentActivity.this, TestResultActivity.class);
                        startActivity(mIntent);
                        Date date = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        if (type == 4){
                            right_counts = right_counts * 2;
                        }
                        DBUtils.insertTest(TestContentActivity.this,format.format(date),right_counts,type);
                        finish();
                        right_counts = 0;
                        do_counts = 0;
                    }
                }).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        flag = false;
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
