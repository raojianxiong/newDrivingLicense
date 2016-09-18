package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.fragment.TestFragment;
import com.raojianxiong.pojo.WebContent;

public class CollectionAndMisListActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Toolbar toolbar;
    private RadioGroup rg_collection;
    private TextView tv_collection_list;
    private Intent intent;
    private int type;
    private int tab_id;
    private WebContent webContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_and_mis_list);

        intent = getIntent();
        type = intent.getIntExtra("type", -1);
        tab_id = intent.getIntExtra("tab_id", -1);
        webContent = (WebContent) intent.getSerializableExtra("WebContent");

        initView();

        initData();

    }

    private void initData() {
        switch (tab_id){
            case TabChoiceActivity.FOLDER:
                tv_collection_list.setText("我的收藏夹");
                break;
            case TabChoiceActivity.MISTAKE:
                tv_collection_list.setText("我的错题");
                break;
        }
        upQuestion(webContent);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_collection_list);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        tv_collection_list = (TextView) findViewById(R.id.tv_collection_list);
        setSupportActionBar(toolbar);
        rg_collection = (RadioGroup) findViewById(R.id.rg_collection);
        rg_collection.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_delete:
                delete();
                break;
            case R.id.rb_look:
                break;
        }
    }

    private void delete() {
        switch (tab_id){
            case TabChoiceActivity.FOLDER:
                DBUtils.deleteCollection(this,webContent);
                Toast.makeText(CollectionAndMisListActivity.this, "已移除", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case TabChoiceActivity.MISTAKE:
                DBUtils.deleteMistake(this,webContent);
                Toast.makeText(CollectionAndMisListActivity.this, "已移除", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void upQuestion(WebContent webContent) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        //Fragment显示需要传递的数据
        bundle.putSerializable("WebContent",webContent);
        bundle.putInt("position",webContent.getId());
        //因为要用同一个Fragment所以为其传递科一的所有条目，暂且用数字
        bundle.putInt("sum",1536);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout_collection,fragment);
        transaction.commit();
    }
}
