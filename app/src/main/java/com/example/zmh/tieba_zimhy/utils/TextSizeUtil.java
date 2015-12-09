package com.example.zmh.tieba_zimhy.utils;

import android.graphics.Rect;

/**
 * Created by zmh on 2015/12/4.
 */
public class TextSizeUtil {
    public static Integer BIG_SIZE_TEXT_PER_LINE = 20;
    public static Integer MID_SIZE_TEXT_PER_LINE = 30;
    public static Integer SMALL_SIZE_TEXT_PER_LINE = 40;

    private float bigTextSize = 60;
    private float midTextSize = 40;
    private float smallTextSize = 30;
    private int screenDpi = 120;
    private int screenWidth;


    public static TextSizeUtil getInstance() {
        return sizeUtil;
    }

    private static final TextSizeUtil sizeUtil = new TextSizeUtil();


    private TextSizeUtil() {

    }

    private TextSizeUtil(int Dpi, Integer width) {
        calSize(Dpi, width);
    }

    public void calSize(Integer screenDpi, Integer width) {
        this.screenWidth = width;
        this.screenDpi = screenDpi;
        this.smallTextSize = (int) (screenDpi * 2/ SMALL_SIZE_TEXT_PER_LINE);

        this.midTextSize = (int) (screenDpi * 2/ MID_SIZE_TEXT_PER_LINE);

        this.bigTextSize = (int) (screenDpi * 2 / BIG_SIZE_TEXT_PER_LINE);

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

    public int getScreenDpi() {
        return screenDpi;
    }

    public void setScreenDpi(int screenDpi) {
        this.screenDpi = screenDpi;
    }


    public Rect getImageSize(int width, int height) {
        Rect rect = new Rect();
        if (width * this.screenDpi / 100 > this.screenWidth) {
            rect.set(0, 0, this.screenWidth, this.screenWidth / width * height);
        } else {
            rect.set(0, 0, width * this.screenDpi / 100, height * this.screenDpi / 100);
        }
        return rect;

    }
}
