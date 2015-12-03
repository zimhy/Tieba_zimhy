package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import entities.Post;
import entities.PostThread;

/**
 * Created by zmh on 2015/12/3.
 */
public class PostView extends BaseView {

    private Post post;

    /*    public PostView(Context context, AttributeSet attrs, Post post) {
            super(context, attrs);

            this.post = post ;
            // this.setAlpha(40);

        }*/
    public PostView(Context context, Post post) {
        super(context);
        this.post = post;
        TextView content = new TextView(context);
        content.setText(post.getContext());

        LinearLayout userName_time_reply = new LinearLayout(context);
        /*userName_time_reply.setOrientation(HORIZONTAL);*/
        TextView userName_time = new TextView(context);
        userName_time.setGravity(Gravity.LEFT);
        userName_time.setText(post.getUser() + "   " + post.getTime());
        TextView reply = new TextView(context);
        reply.setGravity(Gravity.RIGHT);
        reply.setText(post.getReplyCount());
        userName_time_reply.addView(userName_time);
        userName_time_reply.addView(reply);
        addView(content);
        addView(userName_time_reply);

    }
  /*  public PostThreadView( PostThread pThread) {
       // super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        this.postThread = pThread ;

    }*/

   /* protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //getBackground().setAlpha(40);

        mPaint.setColor(Color.GRAY);
        mPaint.setAlpha(102);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setAlpha(200);
        mPaint.setTextSize(text_size_big);
        String context = post.getContext();


      *//*  *//*
        canvas.drawText(context, (int)(text_size_big*1.3), (int)(text_size_big*1.3), mPaint);
        mPaint.setTextSize(text_size_mid);
        mPaint.getTextBounds(post.getReplyCount(), 0, post.getReplyCount().length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(post.getUser().getName(), 10, getHeight() - 2
                , mPaint);
        canvas.drawText(post.getReplyCount(), getWidth()-mBounds.width() - 5, getHeight() - 2
                , mPaint);
        // canvas.drawLine
    }*/
}
