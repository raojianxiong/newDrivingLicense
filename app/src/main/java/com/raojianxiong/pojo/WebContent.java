package com.raojianxiong.pojo;

import java.io.Serializable;

/**
 * Created by 饶健雄 on 2016/8/01.
 */
public class WebContent implements Serializable{

    private int id;
    private int type;
    private String intNumber;
    private String strType;
    private String strTppe;
    private String qustion;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answerTrue;
    private String subject;
    private String explain;
    private String myExplain;
    private String sinaimg;
    private String video_url;

    public WebContent() {
    }

    public WebContent(int id,int type, String intNumber,
                      String strType, String strTppe, String qustion,
                      String answer1, String answer2, String answer3,
                      String answer4, String answerTrue, String subject,
                      String explain, String myExplain, String sinaimg,String video_url) {
        this.id = id;
        this.type = type;
        this.intNumber = intNumber;
        this.strType = strType;
        this.strTppe = strTppe;
        this.qustion = qustion;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answerTrue = answerTrue;
        this.subject = subject;
        this.explain = explain;
        this.myExplain = myExplain;
        this.sinaimg = sinaimg;
        this.video_url = video_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(String intNumber) {
        this.intNumber = intNumber;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public String getStrTppe() {
        return strTppe;
    }

    public void setStrTppe(String strTppe) {
        this.strTppe = strTppe;
    }

    public String getQustion() {
        return qustion;
    }

    public void setQustion(String qustion) {
        this.qustion = qustion;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(String answerTrue) {
        this.answerTrue = answerTrue;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getMyExplain() {
        return myExplain;
    }

    public void setMyExplain(String myExplain) {
        this.myExplain = myExplain;
    }

    public String getSinaimg() {
        return sinaimg;
    }

    public void setSinaimg(String sinaimg) {
        this.sinaimg = sinaimg;
    }

    @Override
    public String toString() {
        return "WebContent{" +
                "id=" + id +
                "，type=" + type +
                ", intNumber='" + intNumber + '\'' +
                ", strType='" + strType + '\'' +
                ", strTppe='" + strTppe + '\'' +
                ", qustion='" + qustion + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", answerTrue='" + answerTrue + '\'' +
                ", subject='" + subject + '\'' +
                ", explain='" + explain + '\'' +
                ", myExplain='" + myExplain + '\'' +
                ", sinaimg='" + sinaimg + '\'' +
                ", video_url='" + video_url + '\'' +
                '}';
    }
}
