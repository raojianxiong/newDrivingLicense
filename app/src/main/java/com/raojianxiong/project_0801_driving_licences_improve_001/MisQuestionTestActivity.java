package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.fragment.TestFragment;
import com.raojianxiong.pojo.TableChapter;
import com.raojianxiong.pojo.WebContent;

import java.util.List;
import java.util.Random;

public class MisQuestionTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private List<WebContent> list;
    private Intent intent;
    private String path;
    private int type;
    private TableChapter tableChapter;
    private int position;
    private int tab_id;
    private TextView tv_title_chapter_test;
    private int lastPosition;//记录上个Activity传递过来的item位置
    private Random random;//用于产生随机数来实现随机效果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_question_test);

        intent = getIntent();
        path = intent.getStringExtra("path");
        type = intent.getIntExtra("type", -1);
        tab_id = intent.getIntExtra("tab_id",-1);
        lastPosition = intent.getIntExtra("position",-1);
        Log.i("QuestionTestActivity",path+":"+type);

        position = 0;

        initView();

        initData();

    }

    private void initData() {
        switch (tab_id){
            case TabChoiceActivity.CHAPTER:
                /**
                 * 按照章节显示相应的题目
                 */
                tableChapter = (TableChapter) intent.getSerializableExtra("TableChapter");
                list = DBUtils.getContentList(this,path,tableChapter);
                Log.i("QuestionTestActivity",list.size()+"===================");
                upQuestion(list.get(position));
                break;
            case TabChoiceActivity.RANDOM:
                /**
                 * 获取全部数据，通过random随机显示
                 */
                list = DBUtils.getAllContentList(this,path,type);
                random = new Random();
                position = random.nextInt(list.size());
                upQuestion(list.get(position));
                break;
            case TabChoiceActivity.ORDER:
                /**
                 * 顺序时获取科目type的全部数据
                 */
                list = DBUtils.getAllContentList(this,path,type);
                upQuestion(list.get(position));
                break;
            case TabChoiceActivity.CHEATS:
                break;
            case TabChoiceActivity.FOLDER:
                break;
            case TabChoiceActivity.IMPROVE:
                tv_title_chapter_test.setText("强化训练");
                tableChapter = (TableChapter) intent.getSerializableExtra("TableChapter");
                list = DBUtils.getContentListImprove(this,path,tableChapter);
                upQuestion(list.get(position));
                break;
            case TabChoiceActivity.MISTAKE:
                break;
            case TabChoiceActivity.TEST:
                break;
            case TabChoiceActivity.SEARCH:
                break;
            case TabChoiceActivity.VIRTUAL:
                break;
        }

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_chapter_test);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        tv_title_chapter_test = (TextView) findViewById(R.id.tv_title_chapter_test);

        findViewById(R.id.rb_test_next).setOnClickListener(this);
        findViewById(R.id.rb_text_last).setOnClickListener(this);
        findViewById(R.id.rb_test_collection).setOnClickListener(this);
        findViewById(R.id.rb_test_answer).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_text_last:
                if (tab_id == TabChoiceActivity.RANDOM){
                    position = random.nextInt(list.size());
                    Log.i("QuestionTestActivity",position+"-------");
                }
                if (position <= 0){
                    Toast.makeText(MisQuestionTestActivity.this, "已经是第一题了", Toast.LENGTH_SHORT).show();
                    break;
                }
                upQuestion(list.get(--position));
                break;
            case R.id.rb_test_answer:
                break;
            case R.id.rb_test_collection:
                break;
            case R.id.rb_test_next:
                if (tab_id == TabChoiceActivity.RANDOM){
                    position = random.nextInt(list.size());
                }
                if (position >= list.size() -1){
                    Toast.makeText(MisQuestionTestActivity.this, "已经是最后一题了", Toast.LENGTH_SHORT).show();
                    break;
                }
                upQuestion(list.get(++position));
                break;
        }
    }

    private void upQuestion(WebContent webContent) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        //Fragment显示需要传递的数据
        bundle.putSerializable("WebContent",webContent);
        bundle.putInt("position",position);
        bundle.putInt("sum",list.size());
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
