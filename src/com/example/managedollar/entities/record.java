package com.example.managedollar.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 刚 on 2014/7/28.
 */
public class record {
    private int flag;
    private String describe;
    private String num;
    private String date;
    public record(int flag, String num, String describe,String date) {
        this.flag = flag;
        this.num = num;
        this.describe = describe;
        this.date = date;
    }

    public record() {
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
