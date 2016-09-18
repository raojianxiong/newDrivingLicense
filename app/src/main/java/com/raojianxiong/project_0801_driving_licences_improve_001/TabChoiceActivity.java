package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TabChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tv_chapter,tv_order,tv_random,tv_improve,tv_virtual,tv_note,tv_search,
    tv_folder,tv_mistake,tv_cheats;
    private TextView tv_tab_main;
    private Intent intent;
    private Intent mIntent;
    private int type;
    private String path;
    public static final int CHAPTER = 1,ORDER = 2,RANDOM = 3,IMPROVE = 4,
            VIRTUAL = 5,TEST = 6,SEARCH = 7,FOLDER = 8,MISTAKE = 9,CHEATS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_choice);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_test_title);
        tv_tab_main = (TextView) findViewById(R.id.tv_tab_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);

        intent = getIntent();
        type = intent.getIntExtra("type",-1);
        path = intent.getStringExtra("path");

        setMyTitle();

        initView();

        initData();

    }

    private void setMyTitle() {
        switch (type){
            case 1:
                break;
            case 4:
                tv_tab_main.setText("科目四文明驾驶");
                break;
        }
    }

    private void initData() {
        /**
         * 传递给下个页面时需要传递路径path
         * 那个科目的type
         * 该页面的哪个按钮
         */
        mIntent = new Intent();
        mIntent.putExtra("type",type);
        mIntent.putExtra("path",path);
    }

    private void initView() {
        tv_chapter = (TextView) findViewById(R.id.tv_test_chapter);
        tv_cheats = (TextView) findViewById(R.id.tv_test_cheats);
        tv_folder = (TextView) findViewById(R.id.tv_test_folder);
        tv_improve = (TextView) findViewById(R.id.tv_test_improve);
        tv_mistake = (TextView) findViewById(R.id.tv_test_mistake);
        tv_note = (TextView) findViewById(R.id.tv_test_note);
        tv_order = (TextView) findViewById(R.id.tv_test_order);
        tv_search = (TextView) findViewById(R.id.tv_test_search);
        tv_virtual = (TextView) findViewById(R.id.tv_test_virtual);
        tv_random = (TextView) findViewById(R.id.tv_test_random);

        tv_cheats.setOnClickListener(this);
        tv_random.setOnClickListener(this);
        tv_mistake.setOnClickListener(this);
        tv_virtual.setOnClickListener(this);
        tv_improve.setOnClickListener(this);
        tv_chapter.setOnClickListener(this);
        tv_folder.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        tv_note.setOnClickListener(this);
        tv_order.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_test_chapter:
                mIntent.putExtra("tab_id",CHAPTER);
                mIntent.setClass(this,ChapterListActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_cheats:
                /**
                 * 必过秘籍的实现借助科二界面
                 */
                mIntent.setClass(this,SubjectChapterActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_folder:
                mIntent.putExtra("tab_id",FOLDER);
                mIntent.setClass(this,CollectionActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_improve:
                /**
                 * 重新加载一个页面
                 * 从哪个页面跳转到ChapterListActivity页面
                 */
                mIntent.putExtra("tab_id",IMPROVE);
                mIntent.setClass(this,ImproveOuterActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_mistake:
                mIntent.putExtra("tab_id",MISTAKE);
                mIntent.setClass(this,CollectionActivity.class);
                Log.i("TabChoiceActivity","点击MISTAKE");
                startActivity(mIntent);
                break;
            case R.id.tv_test_note:
                mIntent.setClass(this,TestNoteActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_order:
                mIntent.putExtra("tab_id",ORDER);
                mIntent.setClass(this,QuestionTestActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_random:
                mIntent.putExtra("tab_id",RANDOM);
                mIntent.setClass(this,QuestionTestActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_search:
                mIntent.setClass(this,SearchMainActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_test_virtual:
                mIntent.setClass(this,TestStartActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
