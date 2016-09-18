package com.raojianxiong.project_0801_driving_licences_improve_001;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_k1,img_k2,img_k3,img_k4;
    private ImageView img_register,img_law,img_setting;
    private Toolbar toolbar;
    private Intent mIntent;
    private String path;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.query("Chapter", null, "kemu=? and Fid=?",
                    new String[]{String.valueOf(1), String.valueOf(0)}, null, null, null);
//            while(cursor.moveToNext()){
//                Log.i("MainActivity",cursor.getString(2)+"=================");
//            }
            initData();
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_start_title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File("data/data/"+getPackageName()+"/databases");
                if (!file.exists()){
                    file.mkdir();
                    try {
                        InputStream is = getAssets().open("jxedt_user.db");
                        FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile()+"/jxedt_user.db");
                        int len;
                        byte[] bytes = new byte[1024];
                        while((len = is.read(bytes)) != -1){
                            fos.write(bytes,0,len);
                        }
                        is.close();
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                path = file.getAbsolutePath()+"/jxedt_user.db";
                handler.sendEmptyMessage(0);
            }
        }).start();


        initView();

        initData();
    }


    private void initData() {
        img_k1.setOnClickListener(this);
        img_k2.setOnClickListener(this);
        img_k3.setOnClickListener(this);
        img_k4.setOnClickListener(this);
        img_register.setOnClickListener(this);
        img_law.setOnClickListener(this);
        img_setting.setOnClickListener(this);
        mIntent = new Intent();

    }

    private void initView() {
        img_k1 = (ImageView) findViewById(R.id.img_start_k1);
        img_k2 = (ImageView) findViewById(R.id.img_start_k2);
        img_k3 = (ImageView) findViewById(R.id.img_start_k3);
        img_k4 = (ImageView) findViewById(R.id.img_start_k4);
        img_register = (ImageView) findViewById(R.id.img_start_register);
        img_law = (ImageView) findViewById(R.id.img_start_law);
        img_setting = (ImageView) findViewById(R.id.img_start_setting);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_start_k1:
                mIntent.setClass(this,TabChoiceActivity.class);
                mIntent.putExtra("type",1);
                mIntent.putExtra("path",path);
                startActivity(mIntent);
                break;
            case R.id.img_start_k2:
                mIntent.setClass(this,SubjectChapterActivity.class);
                mIntent.putExtra("type",2);
                startActivity(mIntent);
                break;
            case R.id.img_start_k3:
                mIntent.setClass(this,SubjectChapterActivity.class);
                mIntent.putExtra("type",3);
                startActivity(mIntent);
                break;
            case R.id.img_start_k4:
                mIntent.setClass(this,TabChoiceActivity.class);
                mIntent.putExtra("type",4);
                startActivity(mIntent);
                break;
            case R.id.img_start_register:
                mIntent.setClass(this,SubjectChapterActivity.class);
                mIntent.putExtra("type",5);
                startActivity(mIntent);
                break;
            case R.id.img_start_law:
                mIntent.setClass(this,SubjectChapterActivity.class);
                mIntent.putExtra("type",6);
                startActivity(mIntent);
                break;
            case R.id.img_start_setting:
                break;
        }
    }
}
