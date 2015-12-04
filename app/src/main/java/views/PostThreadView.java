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
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zmh.tieba_zimhy.utils.TextSizeUtil;

import entities.Post;
import entities.PostThread;

/**
 * Created by zmh on 2015/12/3.
 */
public class PostThreadView extends BaseView {

    private PostThread postThread;


/*    public PostThreadView(Context context, AttributeSet attrs, PostThread pThread) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        this.postThread = pThread;


    }*/

    public PostThreadView(Context context, PostThread pThread) {
        super(context);
        TextSizeUtil sizeUtil = TextSizeUtil.getInstance() ;
        this.postThread = pThread;
        TextView blank = new TextView(context);
        blank.setHeight(10);
        this.addView(blank);

        TextView title = new TextView(context);

        title.setText(pThread.getTitle());
        title.setTextSize(sizeUtil.getMidTextSize());
        title.setEllipsize(TextUtils.TruncateAt.END);
        LayoutParams params_title  = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT) ;
        params_title.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params_title.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        this.addView(title, params_title);

        TextView messages = new TextView(context);
       // messages.text

        messages.setText(pThread.getViewMessage());
        messages.setTextSize(sizeUtil.getSmallTextSize() + 3);
        LayoutParams params_message  = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) ;
        params_message.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params_message.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params_message.addRule(RelativeLayout.ALIGN_PARENT_END);
        messages.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        messages.setGravity(Gravity.LEFT);
        this.addView(messages,params_message);

    /*    params.
        params.bottomMargin
        title.setLayoutParams();
        TextView message = new TextView(context);
        message.setText(pThread.getViewMessage());
        this.addView(title);
        this.addView(message);*/


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
        String title = postThread.getTitle();


      *//*  *//*
        canvas.drawText(title, (int)(text_size_big*1.3), (int)(text_size_big*1.3), mPaint);
        mPaint.setTextSize(text_size_mid);
        mPaint.getTextBounds(postThread.getViewMessage(), 0, postThread.getViewMessage().length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(postThread.getViewMessage(), getWidth() - textWidth, getHeight() - 2
                , mPaint);
        // canvas.drawLine
    }*/
}
