package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import entities.Post;
import entities.PostThread;

/**
 * Created by zmh on 2015/12/3.
 */
public class PostThreadView extends View{
    private Paint mPaint;

    private Rect mBounds;
    private  PostThread postThread ;
    private int mCount;
    public PostThreadView(Context context, AttributeSet attrs, PostThread pThread) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        this.postThread = pThread ;

    }
    public PostThreadView(Context context,PostThread pThread) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        this.postThread = pThread ;

    }
  /*  public PostThreadView( PostThread pThread) {
       // super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        this.postThread = pThread ;

    }*/

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //getBackground().setAlpha(80);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(16);
        String title = postThread.getTitle();


      /*  */
        canvas.drawText(title, 16, 16, mPaint);
        mPaint.setTextSize(10);
        mPaint.getTextBounds(postThread.getViewMessage(), 0, postThread.getViewMessage().length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(postThread.getViewMessage(), getWidth() - textWidth, getHeight() - 2
                , mPaint);
        // canvas.drawLine
    }
}
