package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.zmh.tieba_zimhy.R;
import com.example.zmh.tieba_zimhy.entities.LikeBar;
import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

/**
 * Created by zmh on 2015/12/2.
 */
public class BarActivity extends BaseActivity {
    private Button b_prePage;
    private Button b_refresh;
    private Button b_nextPage;
    private ScrollView contextContainer;
    private BaiduUtil baiduUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        b_prePage = (Button) findViewById(R.id.prePage);
        b_refresh = (Button) findViewById(R.id.refresh);
        b_nextPage = (Button) findViewById(R.id.nextPage);
        contextContainer = (ScrollView) findViewById(R.id.contentScroll);
        baiduUtil = BaiduUtil.getInstance();
        Intent intent = getIntent();
        LikeBar bar = baiduUtil.getLikeBars().get(intent.getIntExtra(LIKE_BAR_INDEX, 0)) ;
        if(bar.getPostThreads()==null)
        {
            Toast.makeText(this,"∑¢…˙¥ÌŒÛ£¨«Î÷ÿ ‘",Toast.LENGTH_SHORT) ;
            return;
        }
        //TODO
        Integer index_Bar = intent.getIntExtra(LIKE_BAR_INDEX, 0);

    }
}
