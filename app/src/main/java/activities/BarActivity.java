package activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.zmh.tieba_zimhy.R;

import entities.LikeBar;
import entities.PostThread;
import views.PostThreadView;

import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

import java.util.List;

/**
 * Created by zmh on 2015/12/2.
 */
public class BarActivity extends BaseActivity {

    private Button b_prePage;
    private Button b_refresh;
    private Button b_nextPage;
    private ScrollView contextContainer;
    private BaiduUtil baiduUtil;
    private LikeBar bar;
    private Integer index_selected_thread = 0;
    private Integer index_selected_bar = 0;
    private PostThread selected_thread;
    PThreadListener threadListener;
    PThreadButtonListener buttonListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        b_prePage = (Button) findViewById(R.id.prePage_thread);
        b_refresh = (Button) findViewById(R.id.refresh);
        b_nextPage = (Button) findViewById(R.id.nextPage_thread);
        b_prePage.setBackgroundColor(Color.parseColor("#4f6495ED"));
        b_nextPage.setBackgroundColor(Color.parseColor("#4f6495ED"));
        b_refresh.setBackgroundColor(Color.parseColor("#4f649511"));
        buttonListener = new PThreadButtonListener();
        b_prePage.setOnClickListener(buttonListener);
        b_nextPage.setOnClickListener(buttonListener);
        b_refresh.setOnClickListener(buttonListener);
        contextContainer = (ScrollView) findViewById(R.id.contentScroll);
        baiduUtil = BaiduUtil.getInstance();
        Intent intent = getIntent();

        bar = baiduUtil.getLikeBars().get(index_selected_bar);

            setTitle(bar.getName());

        threadListener = new PThreadListener();
        displayThreads(bar);


    }


    private class PThreadListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            index_selected_thread = (Integer) v.getTag();
            selected_thread = bar.getPostThreads().get(index_selected_thread);

            new LoadPostsTask().execute();
            Toast.makeText(getApplicationContext(), "加载中",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private class PThreadButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int currentPage = bar.getCurrentPage();
            int totalPage = bar.getTotalPage();
            switch (v.getId()) {
                case R.id.prePage_thread:
                    if (currentPage == 1) {
                        Toast.makeText(getApplicationContext(), "已经是第一页", Toast.LENGTH_SHORT);
                    } else {
                        new LoadThreadTask().execute(currentPage - 1);
                        Toast.makeText(getApplicationContext(), "加载中",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.nextPage_thread:
                    if (currentPage == totalPage) {
                        Toast.makeText(getApplicationContext(), "已经是最后一页", Toast.LENGTH_SHORT);
                    } else {
                        new LoadThreadTask().execute(currentPage + 1);
                        Toast.makeText(getApplicationContext(), "加载中",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.refresh:
                    new LoadThreadTask().execute(currentPage);
                    Toast.makeText(getApplicationContext(), "加载中",
                            Toast.LENGTH_SHORT).show();
                    break;


            }

        }
    }

    private class LoadThreadTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            Integer pageNum = params[0];

            if (bar != null && baiduUtil.loadBarContent(bar, pageNum))
                return 1;
            else
                return null;

        }

        @Override
        protected void onPostExecute(Integer result) {


            if (null == result) {
                Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT);
            } else {
                contextContainer.removeAllViews();
                displayThreads(bar);
                Toast.makeText(getApplicationContext(),
                        "当前第" + bar.getCurrentPage() + "页", Toast.LENGTH_SHORT).show();
                contextContainer.invalidate();
                contextContainer.fullScroll(ScrollView.FOCUS_UP) ;
            }
        }
    }


    private class LoadPostsTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {


            if (selected_thread != null && baiduUtil.loadThreadPost(selected_thread, 1))
                return 1;
            else
                return null;

        }

        @Override
        protected void onPostExecute(Integer result) {


            if (null == result) {
                Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT);
                Log.e("html", baiduUtil.getReturnMassage());
            } else {

                Intent intent = new Intent();
                intent.putExtra(SELECTED_BAR_INDEX, index_selected_bar);
                intent.putExtra(SELECTED_THREAD_INDEX, index_selected_thread);
                intent.setAction("android.intent.action.PostThread");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);

            }
        }
    }


    private void displayThreads(LikeBar bar) {

        if (bar == null || bar.getPostThreads() == null) {
            Toast.makeText(this, "发生错误，请重试", Toast.LENGTH_SHORT);
            return;
        }
        //TODO
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        List<PostThread> threads = bar.getPostThreads();
        for (int i = 0; i < threads.size(); i++) {
            PostThread postThread = threads.get(i);
            if (postThread.validCheck()) {
                PostThreadView PTView = new PostThreadView(getApplicationContext(), postThread);
                LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT / 2, 40);
                linearParam.width = LinearLayout.LayoutParams.MATCH_PARENT;
                linearParam.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                PTView.setLayoutParams(linearParam);
                PTView.setTag(i);
                ll.addView(PTView);
                PTView.setOnClickListener(threadListener);
            }
        }
        contextContainer.addView(ll);
    }
}
