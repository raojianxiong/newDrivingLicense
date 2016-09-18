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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImproveOuterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Toolbar toolbar;
    private List<Map<String,String>> list;
    private Intent intent;
    private int type;
    private String path;
    private int tab_id;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_outer);

        intent = getIntent();
        type = intent.getIntExtra("type",-1);
        tab_id = intent.getIntExtra("tab_id",-1);
        path = intent.getStringExtra("path");
        mIntent = new Intent();
        mIntent.putExtra("type",type);
        mIntent.putExtra("path",path);
        mIntent.putExtra("tab_id",tab_id);
        Log.i("ImproveOuterActivity",type+":type"+tab_id+":tab_id");
        initView();

        initData();


    }

    private void initData() {

        switch (type){
            case 1:
                list = getImproveData1();
                SimpleAdapter adapter = new SimpleAdapter(this,list,
                        R.layout.item_category_list,new String[]{"content"},
                        new int[]{R.id.tv_category_title});
                listView.setAdapter(adapter);
                break;
            case 4:
                list = getImproveData4();
                SimpleAdapter adapter4 = new SimpleAdapter(this,list,
                        R.layout.item_category_list,new String[]{"content"},
                        new int[]{R.id.tv_category_title});
                listView.setAdapter(adapter4);
                break;
        }


        listView.setOnItemClickListener(this);

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_view_improve_outer);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_improve_outer);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
    }

    public List<Map<String,String>> getImproveData1() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "法律法规");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "交通信号标志");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "安全行车文明驾驶");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "汽车仪表装置类");
        inner_list.add(map4);
        return inner_list;
    }

    public List<Map<String,String>> getImproveData4() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "违法行为判断与案例分析");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "车辆的安全行驶");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "交通标志和交警手势信号");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "行车礼让");
        inner_list.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("content", "特殊环境下的安全行驶");
        inner_list.add(map5);
        Map<String, String> map6 = new HashMap<String, String>();
        map6.put("content", "事故处理");
        inner_list.add(map6);
        Map<String, String> map7 = new HashMap<String, String>();
        map7.put("content", "紧急情况下的急救");
        inner_list.add(map7);
        return inner_list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mIntent.putExtra("position",i);
        mIntent.setClass(this,ChapterListActivity.class);
        startActivity(mIntent);
    }
}
