package com.example.zmh.tieba_zimhy.utils;

import android.webkit.WebSettings;

import java.util.ArrayList;

import entities.LikeBar;

/**
 * Created by zmh on 2015/12/4.
 */
public class TextSizeUtil {
    public static Integer BIG_SIZE_TEXT_PER_LINE = 20;
    public static Integer MID_SIZE_TEXT_PER_LINE = 30;
    public static Integer SMALL_SIZE_TEXT_PER_LINE = 40;

    private float bigTextSize = 60;




    private float midTextSize = 40;
    private float smallTextSize  = 30;


    public static TextSizeUtil getInstance() {
        return baiduUtil;
    }

    private static final TextSizeUtil baiduUtil = new TextSizeUtil();


    private TextSizeUtil() {

    }

    private TextSizeUtil(Integer width) {
        calSize(width);
    }

    public void calSize(Integer width) {
        smallTextSize = width / SMALL_SIZE_TEXT_PER_LINE;
        midTextSize = width / MID_SIZE_TEXT_PER_LINE;
        bigTextSize = width / BIG_SIZE_TEXT_PER_LINE;
    }

    public float getBigTextSize() {
        return bigTextSize;
    }

    public void setBigTextSize(Integer bigTextSize) {
        this.bigTextSize = bigTextSize;
    }

    public float getMidTextSize() {
        return midTextSize;
    }

    public void setMidTextSize(Integer midTextSize) {
        this.midTextSize = midTextSize;
    }

    public float getSmallTextSize() {
        return smallTextSize;
    }
    public void setSmallTextSize(Integer smallTextSize) {
        this.smallTextSize = smallTextSize;
    }
}
