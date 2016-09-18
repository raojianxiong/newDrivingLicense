package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.pojo.WebContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchMainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private Toolbar toolbar;
    private Button btn_search;
    private List<WebContent> list;
    private String search;
    private EditText et_search;
    private Intent intent;
    private int type;
    private List<Map<String,String>> data;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        intent = getIntent();
        type = intent.getIntExtra("type", -1);

        mIntent = new Intent();
        mIntent.putExtra("type",type);

        initView();

        initData();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_search_title);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        listView = (ListView) findViewById(R.id.list_view_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        et_search = (EditText) findViewById(R.id.et_search);
        btn_search.setOnClickListener(this);
    }

    private void initData() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        search = et_search.getText().toString();
        if (TextUtils.isEmpty(search)){
            return;
        }
        list = DBUtils.queryMyCondition(this,search,type);
        data = getMapFromWebContent(list);
        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.item_category_list,
                new String[]{"question"},new int[]{R.id.tv_category_title});
        listView.setAdapter(adapter);
    }

    private List<Map<String, String>> getMapFromWebContent(List<WebContent> list) {
        List<Map<String, String>> mList = new ArrayList<Map<String,String>>();
        for (WebContent webContent:list){
            Map<String,String> map = new HashMap<String,String>();
            map.put("question",webContent.getQustion());
            mList.add(map);
        }
        return mList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mIntent.setClass(this,SearchContentActivity.class);
        mIntent.putExtra("WebContent",list.get(i));
        startActivity(mIntent);
    }
}
