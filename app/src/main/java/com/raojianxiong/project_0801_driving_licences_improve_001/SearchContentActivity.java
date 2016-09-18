package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.fragment.TestFragment;
import com.raojianxiong.fragment.TestMisFragment;
import com.raojianxiong.pojo.WebContent;

public class SearchContentActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Toolbar toolbar;
    private RadioButton rb_collection,rb_look;
    private RadioGroup rg_search_content;
    private Intent intent;
    private int type;
    private WebContent webContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_content);

        initView();

        initData();

    }

    private void initData() {
        intent = getIntent();
        type = intent.getIntExtra("type", -1);
        webContent = (WebContent) intent.getSerializableExtra("WebContent");
        rg_search_content.setOnCheckedChangeListener(this);
        upQuestion(webContent,type);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_search_content_title);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        rb_collection = (RadioButton) findViewById(R.id.rb_collection);
        rb_look = (RadioButton) findViewById(R.id.rb_look);
        rg_search_content = (RadioGroup) findViewById(R.id.rg_search_content);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_look:
                lookAnswer();
                break;
            case R.id.rb_collection:
                if (DBUtils.findCollectionId(this, webContent)) {
                    Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                } else {
                    DBUtils.insertCollection(this, webContent);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void upQuestion(WebContent webContent, int type) {
        switch (type) {
            case 1:
                TestFragment fragment = new TestFragment();
                Bundle bundle = new Bundle();
                //Fragment显示需要传递的数据
                bundle.putSerializable("WebContent", webContent);
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frame_layout_collection, fragment, "TestFragment");
                transaction.commit();
                break;
            case 4:
                TestMisFragment testFragment = new TestMisFragment();
                Bundle misBundle = new Bundle();
                //Fragment显示需要传递的数据
                misBundle.putSerializable("WebContent", webContent);
                testFragment.setArguments(misBundle);
                FragmentManager misManager = getSupportFragmentManager();
                FragmentTransaction misTransaction = misManager.beginTransaction();
                misTransaction.replace(R.id.frame_layout_collection, testFragment, "TestMisFragment");
                misTransaction.commit();
                break;
        }

    }

    private void lookAnswer() {
        TestFragment fragment = null;
        TestMisFragment misFragment = null;
        switch (type) {
            case 1:
                fragment = (TestFragment) getSupportFragmentManager().findFragmentByTag("TestFragment");
                fragment.explain.setText(webContent.getMyExplain());
                fragment.myexplain.setText(webContent.getExplain());
                break;
            case 4:
                misFragment = (TestMisFragment) getSupportFragmentManager().findFragmentByTag("TestMisFragment");
                misFragment.explain.setText(webContent.getMyExplain());
                misFragment.myexplain.setText(webContent.getExplain());
                break;
        }

    }

}
