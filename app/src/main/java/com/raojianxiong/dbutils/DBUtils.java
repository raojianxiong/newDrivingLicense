package com.raojianxiong.dbutils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.raojianxiong.pojo.TableChapter;
import com.raojianxiong.pojo.WebContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by 饶建雄 on 2016/8/1
 */
public class DBUtils {

    public static String myPath = "/data/data/com.bornlotus.project_0713_driving_licences_improve_001/databases/jxedt_user.db";

    /**
     * 根据传递的type和tab_id来实现对表Chapter的查询
     *
     */
    public static List<TableChapter> getChapterList(Context context, String path, int tab_id, int type) {
        List<TableChapter> list = new ArrayList<TableChapter>();
        SQLiteDatabase sd = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sd.query("Chapter", null, "kemu=? and Fid=?",
                new String[]{String.valueOf(type), String.valueOf((tab_id - 1))}, null, null, null);
        while (cursor.moveToNext()) {
            int mid = cursor.getInt(1);
            String content = cursor.getString(2);
            int fid = cursor.getInt(3);
            int subject = cursor.getInt(4);
            int counts = cursor.getInt(5);
            TableChapter chapter = new TableChapter(content, mid, fid, counts, subject);
            list.add(chapter);

        }
        return list;
    }

    public static List<Map<String, String>> getMapFromTableChapter(List<TableChapter> list) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (TableChapter chapter : list) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("content", chapter.getContent());
            map.put("mid", String.valueOf(chapter.getMid()));
            map.put("fid", String.valueOf(chapter.getFid()));
            map.put("counts", String.valueOf(chapter.getCounts()));
            map.put("subject", String.valueOf(chapter.getSubject()));
            data.add(map);
        }
        return data;
    }

    /**
     * 根据strTppe和kemu获取响应的数据
     *
     */
    public static List<WebContent> getContentList(Context context, String path, TableChapter tableChapter) {
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("web_note", null, "strTppe=? and kemu=?",
                new String[]{"0" + tableChapter.getMid(), String.valueOf(tableChapter.getSubject())}, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        return list;
    }

    public static List<WebContent> getContentListImprove(Context context, String path, TableChapter tableChapter) {
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("web_note", null, "intNumber like ? and kemu=?",
                new String[]{tableChapter.getFid() + "_" + tableChapter.getMid() + "%", String.valueOf(tableChapter.getSubject())}, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        return list;
    }


    public static List<WebContent> getAllContentList(Context context, String path, int mtype) {
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("web_note", null, "kemu=?",
                new String[]{String.valueOf(mtype)}, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        return list;
    }

    public static List<TableChapter> getChapterList(Context context, String path, int tab_id, int type, int position) {
        List<TableChapter> list = new ArrayList<TableChapter>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("Chapter", null, "kemu=? and Fid=?",
                new String[]{String.valueOf(type), String.valueOf((position + 1))}, null, null, null);
        while (cursor.moveToNext()) {
            int mid = cursor.getInt(1);
            String content = cursor.getString(2);
            int fid = cursor.getInt(3);
            int subject = cursor.getInt(4);
            int counts = cursor.getInt(5);
            TableChapter chapter = new TableChapter(content, mid, fid, counts, subject);
            list.add(chapter);

        }
        return list;
    }

    /**
     * 插入数据，id不会重复，以第一次插入的为基准
     *
     */
    public static void insertMistake(Context context, WebContent webContent, String myAnswer) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("insert into test_do(test_id,my_answer,true_answer,kemu_type) values(?,?,?,?)",
                new Object[]{webContent.getId(), myAnswer, webContent.getAnswerTrue(), webContent.getSubject()});
    }

    public static void insertMistake(Context context, WebContent webContent, int myAnswer) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("insert into test_do(test_id,my_answer,true_answer,kemu_type) values(?,?,?,?)",
                new Object[]{webContent.getId(), myAnswer, webContent.getAnswerTrue(), webContent.getSubject()});
        Log.i("DBUtils", webContent.getId() + ":" + myAnswer + ":" + webContent.getAnswerTrue() + ":" + webContent.getSubject());
    }

    public static void insertCollection(Context context, WebContent webContent) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("insert into test_collect(test_id,kemu_type) values(?,?)",
                new Object[]{webContent.getId(), webContent.getSubject()});
        Log.i("DBUtils", webContent.getId() + webContent.getSubject());
    }

    public static boolean findCollectionId(Context context, WebContent webContent) {
        boolean flag = false;
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("test_collect", new String[]{"test_id", "kemu_type"}, "test_id=?",
                new String[]{String.valueOf(webContent.getId())}, null, null, null);
        while (cursor.moveToNext()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据不同科目type获取收藏夹里的内容
     *
     */
    public static List<WebContent> getCollectContents(Context context, int mType) {
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from web_note where ID in (select test_id from test_collect where kemu_type = ?)",
                new String[]{String.valueOf(mType)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        return list;
    }

    /**
     * 获取所有错误的内容
     *
     */
    public static List<WebContent> getMistakeContents(Context context, int mType) {
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from web_note where ID in(select a.test_id from test_do a,test_do b where a.my_answer!=b.true_answer and a.test_id=b.test_id and a.kemu_type = ?)",
                new String[]{String.valueOf(mType)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        return list;
    }

    public static void deleteCollection(Context context, WebContent webContent) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("delete from test_collect where test_id = ? and kemu_type = ?",
                new Object[]{webContent.getId(), webContent.getSubject()});
    }

    public static void deleteMistake(Context context, WebContent webContent) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("delete from test_do where test_id = ? and kemu_type = ?",
                new Object[]{webContent.getId(), webContent.getSubject()});
    }

    public static boolean findMistakeId(Context context, WebContent webContent) {
        boolean flag = false;
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.query("test_do", null, "test_id=?",
                new String[]{String.valueOf(webContent.getId())}, null, null, null);
        while (cursor.moveToNext()) {
            flag = true;
        }
        return flag;
    }


    /**
     * 根据输入的search搜索问题
     *
     */
    public static List<WebContent> queryMyCondition(Context context, String search, int mType) {
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from web_note where Question like ? and kemu = ?",
                new String[]{"%" + search + "%", String.valueOf(mType)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        return list;
    }

    /**
     * 获取错误题目的数目
     *
     */
    public static int getMistakeCounts(Context context, int mType) {
        int counts = 0;
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from web_note where ID in(select a.test_id from test_do a,test_do b where a.my_answer!=b.true_answer and a.test_id=b.test_id and a.kemu_type = ?)",
                new String[]{String.valueOf(mType)});
        while (cursor.moveToNext()) {
            counts++;
        }
        return counts;
    }

    public static int getAllDoCounts(Context context, int mType) {
        int counts = 0;
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from test_do",
                null);
        while (cursor.moveToNext()) {
            counts++;
        }
        return counts;
    }

    public static List<WebContent> RandomGetQuestion(Context context, int mType) {
        int sum = 0;
        if (mType == 1) {
            sum = 100;
        } else {
            sum = 50;
        }
        List<WebContent> list = new ArrayList<WebContent>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from web_note where kemu = ?",
                new String[]{String.valueOf(mType)});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String intNumber = cursor.getString(2);
            String strTppe = cursor.getString(3);
            String strType = cursor.getString(4);
            String question = cursor.getString(6);
            String answer1 = cursor.getString(7);
            String answer2 = cursor.getString(8);
            String answer3 = cursor.getString(9);
            String answer4 = cursor.getString(10);
            String answerTrue = cursor.getString(14);
            String explain = cursor.getString(15);
            String subject = cursor.getString(17);
            String myExplain = cursor.getString(18);
            String sinaimg = cursor.getString(21);
            String video_url = cursor.getString(22);
            WebContent webContent = new WebContent(id, type, intNumber, strType, strTppe,
                    question, answer1, answer2, answer3, answer4, answerTrue, subject, explain, myExplain, sinaimg, video_url);
            list.add(webContent);
        }
        //随机取其中的一百道题或五十道题
        List<WebContent> data = new ArrayList<WebContent>();
        for (int i = 0; i < sum; i++) {
            int flag = new Random().nextInt(list.size());
            data.add(list.get(flag));
        }
        return data;
    }

    public static void insertTest(Context context, String format, int right_counts, int type) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("insert into exam_result(score,use_time,kemu_type) values(?,?,?)", new Object[]{right_counts, format, type});
    }

    public static List<Map<String, String>> getTestNote(Context context, int type) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("select score,use_time from exam_result where kemu_type = ?",
                new String[]{String.valueOf(type)});
        while(cursor.moveToNext()){
            Map<String,String> map = new HashMap<String,String>();
            int score = cursor.getInt(cursor.getColumnIndex("score"));
            String use_time = cursor.getString(cursor.getColumnIndex("use_time"));
            map.put("score",String.valueOf(score));
            map.put("use_time",use_time);
            list.add(map);
        }
        return list;
    }

    public static void deleteTestNote(Context context, int type) {
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase(myPath, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("delete from exam_result where kemu_type = ?",new Object[]{type});
    }
}
