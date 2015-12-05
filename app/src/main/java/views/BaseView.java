package views;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;



/**
 * Created by zmh on 2015/12/3.
 */
public class BaseView extends LinearLayout {
    protected Integer text_size_small;
    protected Integer text_size_mid;
    protected Integer text_size_big;
/*    protected TextView firstLine ;
    protected TextView secondLine ;
    protected TextView thirdLine ;*/

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);

    }

    public BaseView(Context context) {
        super(context);
        setOrientation(VERTICAL);
       // calTextSize();
    }

    public void calTextSize ()
    {
        text_size_big = getWidth()/20 ;
        text_size_mid = getWidth()/30 ;
        text_size_small = getWidth()/40 ;
    }
   protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calTextSize();
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        // 获取布局控件宽高
        int width = getWidth();
        int height = getHeight();
        // 创建画笔
        Paint mPaint = new Paint();
        mPaint.setAlpha(120);
        // 设置画笔的各个属性
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        // 创建矩形框
        Rect mRect = new Rect(0, 0, width, height);
        // 绘制边框
        canvas.drawRect(mRect, mPaint);
        // 最后必须调用父类的方法
        super.dispatchDraw(canvas);
    }
}
