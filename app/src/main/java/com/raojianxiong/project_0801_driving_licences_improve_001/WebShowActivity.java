package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebShowActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private Intent intent;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_show);

        intent = getIntent();
        name = intent.getStringExtra("name");
        Log.i("WebShowActivity",name);

        initView();

        initData();
    }

    private void initData() {
        if (name.equals("倒车入库")){
            webView.loadUrl("file:///android_asset/subject2/daocheruku.html");
        }else if(name.equals("坡道定点")){
            webView.loadUrl("file:///android_asset/subject2/podaodingdian.html");
        }else if(name.equals("侧方停车")){
            webView.loadUrl("file:///android_asset/subject2/cefangtingche.html");
        }else if(name.equals("曲线行驶")){
            webView.loadUrl("file:///android_asset/subject2/quxianxingshi.html");
        }else if(name.equals("直角转弯")){
            webView.loadUrl("file:///android_asset/subject2/zhijiaozhuanwang.html");
        }else if(name.equals("电子路考详解")){
            webView.loadUrl("file:///android_asset/subject3/subject3.html");
        }else if(name.equals("电子路考模拟语音明细")){
            webView.loadUrl("file:///android_asset/subject3/voicedetail.html");
        }else if(name.equals("夜间灯光语音分布讲解")){
            webView.loadUrl("file:///android_asset/subject3/beforecar.html");
        }else if(name.equals("电子路考常规扣分项")){
            webView.loadUrl("file:///android_asset/subject3/koufen.html");
        }else if(name.equals("进入电子路考语音模拟系统")){
            webView.loadUrl("file:///android_asset/subject3/voicedetailoperate.html");
        }else if(name.equals("1、考驾照流程")){
            webView.loadUrl("file:///android_asset/baoming/xuecheliucheng.html");
        }else if(name.equals("2、年龄条件")){
            webView.loadUrl("file:///android_asset/baoming/nianningtiaojian.html");
        }else if(name.equals("3、体检事项")){
            webView.loadUrl("file:///android_asset/baoming/tijianshixiang.html");
        }else if(name.equals("4、报名材料")){
            webView.loadUrl("file:///android_asset/baoming/baomingchailiao.html");
        }else if(name.equals("5、学车费用")){
            webView.loadUrl("file:///android_asset/baoming/xuechefeiyong.html");
        }else if(name.equals("6、禁止报名事例")){
            webView.loadUrl("file:///android_asset/baoming/baomingxuzhi.html");
        }else if(name.equals("机动车驾驶证申领和使用规定")){
            webView.loadUrl("file:///android_asset/law/jidongche.html");
        }else if(name.equals("准驾车型及代号")){
            webView.loadUrl("file:///android_asset/law/allowCar.html");
        }else if(name.equals("道路交通安全违法记分分值")){
            webView.loadUrl("file:///android_asset/law/koufen.html");
        }else if(name.equals("酒驾新规")){
            webView.loadUrl("file:///android_asset/law/jiujia.html");
        }else if(name.equals("中华人民共和国道路交通安全法")){
            webView.loadUrl("file:///android_asset/law/zhonghua.html");
        }else if(name.equals("道路交通安全违法行为处理程序规定")){
            webView.loadUrl("file:///android_asset/law/weifachuli.html");
        }else if(name.equals("道路交通事故处理程序规定")){
            webView.loadUrl("file:///android_asset/law/shiguchuli.html");
        }else if(name.equals("道路交通安全违法行为记分分值")){
            webView.loadUrl("file:///android_asset/jiakaomiji/2013.html");
        }else if(name.equals("交规巧记忆")){
            webView.loadUrl("file:///android_asset/jiakaomiji/jiaogui.html");
        }else if(name.equals("八种交警手势信号口诀")){
            webView.loadUrl("file:///android_asset/jiakaomiji/bazhong.html");
        }else if(name.equals("处罚相关题巧记")){
            webView.loadUrl("file:///android_asset/jiakaomiji/chufa.html");
        }else if(name.equals("罚款金额题巧记")){
            webView.loadUrl("file:///android_asset/jiakaomiji/fakuan.html");
        }else if(name.equals("最低，最高时速题巧记")){
            webView.loadUrl("file:///android_asset/jiakaomiji/zuidi.html");
        }else if(name.equals("安全距离题巧记")){
            webView.loadUrl("file:///android_asset/jiakaomiji/anquan.html");
        }else if(name.equals("日期类题巧记")){
            webView.loadUrl("file:///android_asset/jiakaomiji/riqi.html");
        }else if(name.equals("易混淆知识")){
            webView.loadUrl("file:///android_asset/jiakaomiji/yihun.html");
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar_web_view_title);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
        webView = (WebView) findViewById(R.id.web_view_show);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
