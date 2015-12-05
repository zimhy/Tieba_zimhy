package activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.zmh.tieba_zimhy.R;
import entities.LikeBar;
import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class HomeActivity extends BaseActivity {

    private BaiduUtil baiduUtil;
    private ScrollView scrollView;
    private LikeBar selected_likeBar = null;
    private int selected_likeBarIndex = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        baiduUtil = BaiduUtil.getInstance();
        scrollView = (ScrollView) findViewById(R.id.scrollView_home);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        // ll.set
        List<LikeBar> likeBars = baiduUtil.getLikeBars();
        LikeBarListener listener = new LikeBarListener();
        for (int i = 0; i < likeBars.size(); i++) {
            LikeBar likeBar = likeBars.get(i);
            Button button = new Button(this);
            button.setText(likeBar.getName());
            button.setTag(i);
            button.setOnClickListener(listener);
            // LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT/2,40);
            // linearParam.width = LinearLayout.LayoutParams.MATCH_PARENT;
            // button.setLayoutParams(linearParam);
            ll.addView(button);
        }
        scrollView.addView(ll);


    }

    public class LikeBarListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            selected_likeBar = baiduUtil.getLikeBars().get((int) v.getTag());
            selected_likeBarIndex = (Integer)v.getTag() ;


            new LoadTask().execute() ;
            Toast.makeText(getApplicationContext(), "加载中",
                    Toast.LENGTH_SHORT).show();
        }


    }


    private class LoadTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            if (selected_likeBar != null && baiduUtil.loadBarContent(selected_likeBar, 1)) {
                return LOAD_SUCCESS ;
            } else {
                return null;
            }

        }

        @Override
        protected void onPostExecute(Integer result) {
            if (LOAD_SUCCESS.equals(result)) {
                Toast.makeText(getApplicationContext(), "加载成功",
                        Toast.LENGTH_SHORT).show();
                load_success();

            } else
                Toast.makeText(getApplicationContext(), "加载失败",
                        Toast.LENGTH_SHORT).show();

        }
    }

    private void load_success() {

        final Uri uri = Uri.parse("http://tieba.baidu.com/mo");
        Intent intent = new Intent(/*Intent.ACTION_VIEW,uri*/);



        intent.setAction("android.intent.action.Bar") ;
        intent.addCategory(Intent.CATEGORY_DEFAULT) ;
        intent.putExtra(LIKE_BAR_INDEX, selected_likeBarIndex) ;
        startActivity(intent);
    }

}


