package views;

import android.content.Context;
import android.graphics.Canvas;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;



/**
 * Created by zmh on 2015/12/3.
 */
public class BaseView extends RelativeLayout {
    protected Integer text_size_small;
    protected Integer text_size_mid;
    protected Integer text_size_big;
/*    protected TextView firstLine ;
    protected TextView secondLine ;
    protected TextView thirdLine ;*/

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
       // setOrientation(VERTICAL);
       // calTextSize();

    }

    public BaseView(Context context) {
        super(context);

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
  /*  protected List<String> spiltByLength(String s_in ,int length )
    {
        int end  = length ;
        int lineCount  = 0   ;
        List<String >
        while (end < s_in.length())
        {

        }
        s_in.substring()
    }*/
}
