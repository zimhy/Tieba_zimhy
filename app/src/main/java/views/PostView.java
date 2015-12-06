package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zmh.tieba_zimhy.utils.TextSizeUtil;

import entities.Post;
import entities.PostReply;
import entities.PostThread;

/**
 * Created by zmh on 2015/12/3.
 */
public class PostView extends BaseView {

    private PostReply post;

    /*    public PostView(Context context, AttributeSet attrs, Post post) {
            super(context, attrs);

            this.post = post ;
            // this.setAlpha(40);

        }*/
    public PostView(Context context, PostReply post) {
        super(context);
        this.post = post;
        TextView blank = new TextView(context);
        blank.setHeight(20);
        this.addView(blank);

        TextSizeUtil sizeUtil = TextSizeUtil.getInstance() ;
        TextView content = new TextView(context);

        content.setTextSize(TypedValue.COMPLEX_UNIT_PX,sizeUtil.getBigTextSize());
        content.setText(post.getContext());
        content.setEllipsize(TextUtils.TruncateAt.END);
        content.setTextColor(Color.BLACK);
        //content.setId((int)1);
        LayoutParams params_content  = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT) ;
     /*   params_content.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params_content.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);*/

        addView(content, params_content);

        TextView blank2 = new TextView(context);
        blank.setHeight(15);
        addView(blank2);


        RelativeLayout userName_time_reply = new RelativeLayout(context);
        /*userName_time_reply.setOrientation(HORIZONTAL);*/
        RelativeLayout.LayoutParams params_left = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT) ;
        params_left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        TextView userName_time = new TextView(context);
        userName_time.setTextColor(Color.BLACK);
        userName_time.setText(post.getUser().getName() + "   " + post.getTime());
        userName_time_reply.addView(userName_time, params_left);
        RelativeLayout.LayoutParams params_right = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT) ;
        params_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        TextView reply = new TextView(context);
        reply.setText(post.getReplyCount());

        userName_time_reply.addView(reply,params_right);
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
