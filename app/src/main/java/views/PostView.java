package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zmh.tieba_zimhy.utils.TextSizeUtil;

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
        TextSizeUtil sizeUtil = TextSizeUtil.getInstance() ;
        TextView content = new TextView(context);
        content.setTextSize(sizeUtil.getMidTextSize());
        content.setText(post.getContext());
        content.setEllipsize(TextUtils.TruncateAt.END);
        //content.setId((int)1);
        LayoutParams params_content  = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT) ;
        params_content.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params_content.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        addView(content,params_content);



        LinearLayout userName_time_reply = new LinearLayout(context);
        /*userName_time_reply.setOrientation(HORIZONTAL);*/
        TextView userName_time = new TextView(context);
        userName_time.setGravity(Gravity.LEFT);
        userName_time.setText(post.getUser().getName() + "   " + post.getTime());
        TextView reply = new TextView(context);
        reply.setGravity(Gravity.RIGHT);
        reply.setText(post.getReplyCount());
        userName_time_reply.addView(userName_time);
        userName_time_reply.addView(reply);
        //userName_time_replys.setTextSize(sizeUtil.getSmallTextSize() + 3);
        LayoutParams params_userNames  = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT) ;
        params_userNames.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params_userNames.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
      //  params_userNames.addRule(RelativeLayout.A,content.getId());

        addView(userName_time_reply,params_userNames);

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
