package activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.zmh.tieba_zimhy.R;
import com.example.zmh.tieba_zimhy.entities.LikeBar;
import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class HomeActivity extends Activity {
    private BaiduUtil baiduUtil;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        baiduUtil = BaiduUtil.getInstance();
        scrollView = (ScrollView) findViewById(R.id.scrollView_home);
        LinearLayout ll = new LinearLayout(this);
        List<LikeBar> likeBars = baiduUtil.getLikeBars();
        LikeBarListener listener = new LikeBarListener();
        for (int i = 0; i < likeBars.size(); i++) {
            LikeBar likeBar = likeBars.get(i);
            Button button = new Button(this);
            button.setText(likeBar.getName());
            button.setTag(i);
            button.setOnClickListener(listener);
            ll.addView(button);
        }
        scrollView.addView(ll);


    }

    public class LikeBarListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            LikeBar likeBar = baiduUtil.getLikeBars().get((int) v.getTag());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(likeBar.getUrl()));
            startActivity(intent);
        }
    }
}


