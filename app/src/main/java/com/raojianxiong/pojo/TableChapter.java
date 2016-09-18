package com.raojianxiong.pojo;

import java.io.Serializable;

/**
 * Created by 饶健雄 on 2016/8/01.
 */
public class TableChapter implements Serializable{

    private String content;
    private int mid;
    private int fid;
    private int counts;
    private int subject;

    public TableChapter() {
    }

    public TableChapter(String content, int mid, int fid, int counts, int subject) {
        this.content = content;
        this.mid = mid;
        this.fid = fid;
        this.counts = counts;
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "TableChapter{" +
                "content='" + content + '\'' +
                ", mid=" + mid +
                ", fid=" + fid +
                ", counts=" + counts +
                ", subject=" + subject +
                '}';
    }
}
