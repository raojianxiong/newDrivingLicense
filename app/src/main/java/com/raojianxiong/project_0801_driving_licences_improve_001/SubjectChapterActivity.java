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

public class SubjectChapterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView listView;
    /**
     * intent是上个页面传递过来的
     * mIntent是该页面跳转下个页面使用的
     */
    private Intent intent;
    private Intent mIntent;
    private int type;
    private List<Map<String,String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_chapter);

        intent = getIntent();
        type = intent.getIntExtra("type", -1);

        mIntent = new Intent();

        initView();

        initData();

    }

    private void initData() {
        //在该界面不实现初始化会在点击条目时出错空指针
        list = new ArrayList<Map<String,String>>();
        //根据type类型来处理相关事件
        switch (type){
            case 2:
                list = getSubjectData2();
                break;
            case 3:
                list = getSubjectData3();
                break;
            case 5:
                list = getSubjectData4();
                break;
            case 6:
                list = getSubjectData5();
                break;
            default:
                list = getSubjectData6();
                break;
        }

        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.item_category_list,
                new String[]{"content"},new int[]{R.id.tv_category_title});
        Log.i("SubjectChapterActivity",list.toString());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_subject_title);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.list_view_subject);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mIntent.putExtra("name",list.get(i).get("content"));
        Log.i("SubjectChapterActivity",list.get(i).get("content"));
        mIntent.setClass(this,WebShowActivity.class);
        startActivity(mIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public List<Map<String,String>> getSubjectData2() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "倒车入库");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "坡道定点");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "侧方停车");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "曲线行驶");
        inner_list.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("content", "直角转弯");
        inner_list.add(map5);
        return inner_list;
    }

    public List<Map<String,String>> getSubjectData3() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "电子路考详解");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "电子路考模拟语音明细");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "夜间灯光语音分布讲解");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "电子路考常规扣分项");
        inner_list.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("content", "进入电子路考语音模拟系统");
        inner_list.add(map5);
        return inner_list;
    }

    public List<Map<String, String>> getSubjectData4() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "1、考驾照流程");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "2、年龄条件");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "3、体检事项");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "4、报名材料");
        inner_list.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("content", "5、学车费用");
        inner_list.add(map5);
        Map<String, String> map6 = new HashMap<String, String>();
        map6.put("content", "6、禁止报名事例");
        inner_list.add(map6);
        return inner_list;
    }

    public List<Map<String, String>> getSubjectData5() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "机动车驾驶证申领和使用规定");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "准驾车型及代号");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "道路交通安全违法记分分值");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "酒驾新规");
        inner_list.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("content", "中华人民共和国道路交通安全法");
        inner_list.add(map5);
        Map<String, String> map6 = new HashMap<String, String>();
        map6.put("content", "道路交通安全违法行为处理程序规定");
        inner_list.add(map6);
        Map<String, String> map7 = new HashMap<String, String>();
        map7.put("content", "道路交通事故处理程序规定");
        inner_list.add(map7);
        return inner_list;
    }

    public List<Map<String, String>> getSubjectData6() {
        List<Map<String,String>> inner_list = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("content", "道路交通安全违法行为记分分值");
        inner_list.add(map1);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("content", "交规巧记忆");
        inner_list.add(map2);
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("content", "八种交警手势信号口诀");
        inner_list.add(map3);
        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("content", "处罚相关题巧记");
        inner_list.add(map4);
        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("content", "罚款金额题巧记");
        inner_list.add(map5);
        Map<String, String> map6 = new HashMap<String, String>();
        map6.put("content", "最低，最高时速题巧记");
        inner_list.add(map6);
        Map<String, String> map7 = new HashMap<String, String>();
        map7.put("content", "安全距离题巧记");
        inner_list.add(map7);
        Map<String, String> map8 = new HashMap<String, String>();
        map8.put("content", "日期类题巧记");
        inner_list.add(map8);
        Map<String, String> map9 = new HashMap<String, String>();
        map9.put("content", "易混淆知识");
        inner_list.add(map9);
        return inner_list;
    }
}
