package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.pojo.TableChapter;

import java.util.List;
import java.util.Map;

public class ChapterListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private TextView category_title;
    private ListView listView;
    private Intent intent;
    private Intent mIntent;
    private int type;
    private int tab_id;
    private String path;
    private List<TableChapter> list;
    private List<Map<String, String>> data;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_chapter);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        intent = getIntent();
        tab_id = intent.getIntExtra("tab_id", -1);
        path = intent.getStringExtra("path");
        type = intent.getIntExtra("type", -1);
        position = intent.getIntExtra("position",-1);
        Log.i("ChapterListActivity",tab_id+":"+path+":"+type+":"+position);

        initView();

        initData();
    }

    private void initData() {
        setCategoryTitle();
        mIntent = new Intent();
        switch (tab_id) {
            case TabChoiceActivity.CHAPTER:
                category_title.setText("章节练习");
                break;
            case TabChoiceActivity.IMPROVE:
                category_title.setText("强化练习");
                break;
        }
        if (position != -1){
            list = DBUtils.getChapterList(this, path, tab_id, type,position);
            data = DBUtils.getMapFromTableChapter(list);
        }else{
            list = DBUtils.getChapterList(this, path, tab_id, type);
            data = DBUtils.getMapFromTableChapter(list);
        }
        SimpleAdapter adapter =
                new SimpleAdapter(this, data, R.layout.item_category_list,
                        new String[]{"content", "counts"},
                        new int[]{R.id.tv_category_title, R.id.tv_category_counts});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void setCategoryTitle() {
        switch (tab_id) {
            case TabChoiceActivity.CHAPTER:
                category_title.setText("章节练习");
                break;
            case TabChoiceActivity.IMPROVE:
                category_title.setText("强化联系");
                break;
        }
    }

    private void initView() {
        category_title = (TextView) findViewById(R.id.tv_title_chapter);
        listView = (ListView) findViewById(R.id.list_view_category);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /**
         * 需要传递哪一章list.get(i)，那个tab_id，路径path，科目几type
         */
        mIntent.putExtra("TableChapter", list.get(i));
        mIntent.setClass(this, QuestionTestActivity.class);
        mIntent.putExtra("path", path);
        mIntent.putExtra("type", type);
        mIntent.putExtra("position",(i+1));
        mIntent.putExtra("tab_id", tab_id);
        startActivity(mIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


}
