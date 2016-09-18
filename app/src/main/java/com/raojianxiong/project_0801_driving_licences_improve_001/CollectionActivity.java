package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.pojo.WebContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private TextView tv_collection;
    private ListView listView;
    private List<WebContent> list;
    private List<Map<String, String>> data;
    private Intent intent;
    private Intent mIntent;
    private int type;
    private int tab_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        intent = getIntent();
        type = intent.getIntExtra("type", -1);
        tab_id = intent.getIntExtra("tab_id",-1);
        mIntent = new Intent();
        mIntent.putExtra("type",type);
        mIntent.putExtra("tab_id",tab_id);

        initView();

        initData();
    }

    private void initData() {

        switch (tab_id){
            case TabChoiceActivity.FOLDER:
                list = DBUtils.getCollectContents(this, type);
                data = getMapFromWebContent(list);
                break;
            case TabChoiceActivity.MISTAKE:
                list = DBUtils.getMistakeContents(this,type);
                data = getMapFromWebContent(list);
                tv_collection.setText("我的错题库");
                break;
        }



        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_category_list,
                new String[]{"question"}, new int[]{R.id.tv_category_title});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    private List<Map<String, String>> getMapFromWebContent(List<WebContent> list) {
        List<Map<String, String>> mList = new ArrayList<Map<String, String>>();
        for (WebContent webContent : list) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("question", webContent.getQustion());
            mList.add(map);
        }
        return mList;
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_collection_main);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        tv_collection = (TextView) findViewById(R.id.tv_collection_main);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list_view_collection);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mIntent.putExtra("WebContent",list.get(i));
        mIntent.setClass(this,CollectionAndMisListActivity.class);
        startActivity(mIntent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
