package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;

import java.util.List;
import java.util.Map;

public class TestNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ListView listView;
    private Intent intent;
    private int type;
    private List<Map<String, String>> testNote;
    private TextView tv_delete;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_note);

        initView();

        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_note_title);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list_view_note);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(this);
    }

    private void initData() {
        intent = getIntent();
        type = intent.getIntExtra("type", -1);
        testNote = DBUtils.getTestNote(this, type);
        adapter = new SimpleAdapter(this,testNote,R.layout.item_category_list,
                new String[]{"use_time","score"},new int[]{R.id.tv_category_title,R.id.tv_category_counts});
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        testNote.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(TestNoteActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        finish();
        DBUtils.deleteTestNote(this,type);
    }
}
