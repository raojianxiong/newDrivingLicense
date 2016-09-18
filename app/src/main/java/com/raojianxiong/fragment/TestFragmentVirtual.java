package com.raojianxiong.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.pojo.WebContent;
import com.raojianxiong.project_0801_driving_licences_improve_001.R;
import com.raojianxiong.project_0801_driving_licences_improve_001.TestContentActivity;

import java.io.IOException;

/**
 * Created by 饶建雄 on 2016/8/1
 */
public class TestFragmentVirtual extends Fragment implements RadioGroup.OnCheckedChangeListener {

    public View view;
    private Bundle bundle;
    private WebContent webContent;
    private RadioButton rb_A, rb_B, rb_C, rb_D;
    private TextView tv_question;
    private ImageView img_show;
    private int position;
    private int sum;
    public TextView explain, myexplain;
    private RadioGroup rg_choise;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_content, null);

        initView();

        initData();

        return view;
    }

    private void initData() {
        intent = new Intent("update_question");
        bundle = getArguments();
        webContent = (WebContent) bundle.getSerializable("WebContent");
        position = bundle.getInt("position");
        sum = bundle.getInt("sum");
        Log.i("TestFragment", position + ":" + sum);
        if (webContent.getSinaimg() == null) {
            img_show.setVisibility(View.INVISIBLE);
        } else {
//            Log.i("TestFragment","===========================");
            try {
                img_show.setImageBitmap(BitmapFactory.decodeStream(getActivity().getAssets().open(webContent.getSinaimg())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (webContent.getType() == 1) {
            rb_B.setVisibility(View.INVISIBLE);
            rb_D.setVisibility(View.INVISIBLE);
            rb_A.setText("正确");
            rb_C.setText("错误");
        } else if (webContent.getType() == 2) {
            rb_A.setText(webContent.getAnswer1());
            rb_B.setText(webContent.getAnswer2());
            rb_C.setText(webContent.getAnswer3());
            rb_D.setText(webContent.getAnswer4());
        }
        tv_question.setText((position + 1) + "/" + sum + "：" + webContent.getQustion());

        rg_choise.setOnCheckedChangeListener(this);

    }

    private void initView() {
        rg_choise = (RadioGroup) view.findViewById(R.id.rg_test_choice);
        rb_A = (RadioButton) view.findViewById(R.id.rb_A);
        rb_B = (RadioButton) view.findViewById(R.id.rb_B);
        rb_C = (RadioButton) view.findViewById(R.id.rb_C);
        rb_D = (RadioButton) view.findViewById(R.id.rb_D);
        tv_question = (TextView) view.findViewById(R.id.tv_test_question);
        img_show = (ImageView) view.findViewById(R.id.img_test_show);
        explain = (TextView) view.findViewById(R.id.tv_test_explain);
        myexplain = (TextView) view.findViewById(R.id.tv_test_myexplain);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i != getMyId()) {
            if (findPosition(position)) {
                Toast.makeText(getActivity(), "上题已经作答过了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
                TestContentActivity.note.add(position);
                TestContentActivity.do_counts++;
            }
        } else {
            if (findPosition(position)) {
                Toast.makeText(getActivity(), "上题已经作答过了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "正确", Toast.LENGTH_SHORT).show();
                TestContentActivity.note.add(position);
                TestContentActivity.right_counts++;
                TestContentActivity.do_counts++;
            }
        }
//        Log.i("TestFragmentVirtual",TestContentActivity.note.toString());
//        Log.i("TestFragmentVirtual",position+"===============");
//        Log.i("TestFragmentVirtual",TestContentActivity.right_counts+":"+"right_counts");
        intent.putExtra("flag", 0);
        getActivity().sendBroadcast(intent);
        if (!DBUtils.findMistakeId(getActivity(), webContent)) {
            DBUtils.insertMistake(getActivity(), webContent, getMyAnswer(i));
        }
//        Log.i("TestFragmentVirtual",TestContentActivity.right_counts+"====");
    }

    private boolean findPosition(int position) {
        boolean flag = false;
        for (Integer temp : TestContentActivity.note) {
            if (position == temp) {
                flag = true;
            }
        }
        return flag;
    }

    private int getMyAnswer(int i) {
        switch (i) {
            case R.id.rb_A:
                return 1;
            case R.id.rb_B:
                return 2;
            case R.id.rb_C:
                return 3;
            case R.id.rb_D:
                return 4;
        }
        return 0;
    }

    private int getMyId() {
        switch (Integer.parseInt(webContent.getAnswerTrue())) {
            case 1:
                return R.id.rb_A;
            case 2:
                return R.id.rb_B;
            case 3:
                return R.id.rb_C;
            case 4:
                return R.id.rb_D;
        }
        return 0;
    }
//    ////
//    public void setText(String name){
//        explain.setText(name);
//    }


}
