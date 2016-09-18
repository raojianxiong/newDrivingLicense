package com.raojianxiong.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.raojianxiong.dbutils.DBUtils;
import com.raojianxiong.pojo.WebContent;
import com.raojianxiong.project_0801_driving_licences_improve_001.R;
import com.raojianxiong.project_0801_driving_licences_improve_001.TestContentActivity;

import java.io.IOException;

/**
 * Created by 饶建雄 on 2016/8/2
 */
public class TestMisFragmentVirtual extends Fragment implements View.OnClickListener {

    private View view;
    private Bundle bundle;
    private WebContent webContent;
    private CheckBox cb_A,cb_B,cb_C,cb_D;
    private TextView tv_question;
    private ImageView img_show;
    private int position;
    private int sum;
    public TextView explain,myexplain;
    private Button btn_sure;
    private StringBuilder sb;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmen_test_mis_list, null);

        initView();

        initData();

        return view;
    }

    private void initView() {
        cb_A = (CheckBox) view.findViewById(R.id.cb_A);
        cb_B = (CheckBox) view.findViewById(R.id.cb_B);
        cb_C = (CheckBox) view.findViewById(R.id.cb_C);
        cb_D = (CheckBox) view.findViewById(R.id.cb_D);
        tv_question = (TextView) view.findViewById(R.id.tv_test_mis_question);
        img_show = (ImageView) view.findViewById(R.id.img_test_mis_show);
        explain = (TextView) view.findViewById(R.id.tv_test_mis_explain);
        myexplain = (TextView) view.findViewById(R.id.tv_test_mis_myexplain);
        btn_sure = (Button) view.findViewById(R.id.btn_mis_sure);
    }

    private void initData() {
        intent = new Intent("update_question");
        sb = new StringBuilder();
        bundle = getArguments();
        webContent = (WebContent) bundle.getSerializable("WebContent");
        position = bundle.getInt("position");
        sum = bundle.getInt("sum");
        if (webContent.getSinaimg() == null){
            img_show.setVisibility(View.INVISIBLE);
        }else{
            try {
                img_show.setImageBitmap(BitmapFactory.decodeStream(getActivity().getAssets().open(webContent.getSinaimg())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * 设置当有视频时处理的情况
         */
        if (webContent.getVideo_url() == null){

        }else{

        }

        if (webContent.getType() == 1){
            cb_B.setVisibility(View.INVISIBLE);
            cb_D.setVisibility(View.INVISIBLE);
            cb_A.setText("正确");
            cb_C.setText("错误");
        }else if(webContent.getType() == 2){
            cb_A.setText(webContent.getAnswer1());
            cb_B.setText(webContent.getAnswer2());
            cb_C.setText(webContent.getAnswer3());
            cb_D.setText(webContent.getAnswer4());
        }
        tv_question.setText(((position+1)+"/"+sum+"："+webContent.getQustion()));

        btn_sure.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        sb.setLength(0);

        if (cb_A.isChecked())
            sb.append("1");
        if (cb_B.isChecked())
            sb.append("2");
        if (cb_C.isChecked())
            sb.append("3");
        if (cb_D.isChecked())
            sb.append("4");
        /**
         * 比较是否跟正确答案一样
         */
        if (sb.toString().equals(webContent.getAnswerTrue())){
            if (findPosition(position)) {
                Toast.makeText(getActivity(), "上题已经作答过了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "正确", Toast.LENGTH_SHORT).show();
                TestContentActivity.note.add(position);
                TestContentActivity.right_counts++;
                TestContentActivity.do_counts++;
            }
        }else{
            if (findPosition(position)) {
                Toast.makeText(getActivity(), "上题已经作答过了", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
                TestContentActivity.note.add(position);
                TestContentActivity.do_counts++;
            }
        }
        intent.putExtra("flag",0);
        getActivity().sendBroadcast(intent);
        if (!DBUtils.findMistakeId(getActivity(),webContent)){
            DBUtils.insertMistake(getActivity(),webContent,sb.toString());
        }

    }

    /**
     * 查看用于记录答题的list集合
     * @param position
     * @return
     */
    private boolean findPosition(int position) {
        boolean flag = false;
        for (Integer temp : TestContentActivity.note) {
            if (position == temp) {
                flag = true;
            }
        }
        return flag;
    }

}
