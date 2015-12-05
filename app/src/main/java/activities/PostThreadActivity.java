package activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.zmh.tieba_zimhy.R;
import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

import java.util.List;

import entities.LikeBar;
import entities.Post;
import entities.PostThread;
import views.PostThreadView;
import views.PostView;

/**
 * Created by zmh on 2015/12/3.
 */
public class PostThreadActivity extends BaseActivity {

    private Button b_prePage;
    private Button b_reply;
    private Button b_nextPage;
    private ScrollView contextContainer;
    private BaiduUtil baiduUtil;
    private LikeBar bar;
    private Integer index_selected_thread = 0;
    private Integer index_selected_bar = 0;
    private PostThread selected_thread;
    private PostListener postListener;
    private PostButtonListener buttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        buttonListener = new PostButtonListener();
        b_prePage = (Button) findViewById(R.id.prePage_thread);
        b_reply = (Button) findViewById(R.id.refresh);
        b_reply.setText("回复");

        b_nextPage = (Button) findViewById(R.id.nextPage_thread);
        b_prePage.setBackgroundColor(Color.parseColor("#4f6495ED"));
        b_nextPage.setBackgroundColor(Color.parseColor("#4f6495ED"));
        b_reply.setBackgroundColor(Color.parseColor("#4f649511"));
        b_reply.setOnClickListener(buttonListener);
        b_prePage.setOnClickListener(buttonListener);
        b_nextPage.setOnClickListener(buttonListener);
        contextContainer = (ScrollView) findViewById(R.id.contentScroll);
        b_prePage.getBackground().setAlpha(40);
        b_nextPage.getBackground().setAlpha(40);
        b_reply.getBackground().setAlpha(40);
        baiduUtil = BaiduUtil.getInstance();
        Intent intent = getIntent();
        index_selected_bar = intent.getIntExtra(LIKE_BAR_INDEX, 0);
        index_selected_thread = intent.getIntExtra(SELECTED_THREAD_INDEX, 0);
        bar = baiduUtil.getLikeBars().get(index_selected_bar);
        postListener = new PostListener();
        Resources res = getResources();
        Integer index_Bar = intent.getIntExtra(LIKE_BAR_INDEX, 0);


        List<PostThread> threads = bar.getPostThreads();
        selected_thread = threads.get(index_selected_thread);
        displayPosts(selected_thread);

    }

    class PostButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (selected_thread != null) {
                switch (v.getId()) {
                    case R.id.prePage_thread:
                        if (selected_thread.getCurrentPage() <= 1) {
                            Toast.makeText(getApplicationContext(), "已经是第一页", Toast.LENGTH_SHORT).show();
                        } else {
                            LoadPostsTask loadPre = new LoadPostsTask();
                            loadPre.execute(selected_thread.getCurrentPage() - 1);
                        }
                        break;
                    case R.id.nextPage_thread:
                        if (selected_thread.getCurrentPage() == selected_thread.getTotalPage()) {
                            Toast.makeText(getApplicationContext(), "已经是最后一页", Toast.LENGTH_SHORT).show();
                        } else {
                            LoadPostsTask loadPre = new LoadPostsTask();
                            loadPre.execute(selected_thread.getCurrentPage() + 1);
                        }
                        break;
                    case R.id.refresh:
                        break;
                }
            } else {
                Toast.makeText(getApplicationContext(), "当前楼为空", Toast.LENGTH_SHORT);
            }
        }
    }


    private class LoadPostsTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {

            Integer pageNum = params[0];
            if (selected_thread != null && baiduUtil.loadThreadPost(selected_thread, pageNum))
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
                contextContainer.removeAllViews();
                displayPosts(selected_thread);
                contextContainer.invalidate();
            }
        }
    }


    class PostListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //TODO
        }
    }

    private void displayPosts(PostThread postThread) {
        //Resources res = getResources();
        // Integer index_Bar = intent.getIntExtra(LIKE_BAR_INDEX, 0);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        List<Post> posts = postThread.getPosts();
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            PostView PView = new PostView(getApplicationContext(), post);
            LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT / 2, 40);
            linearParam.width = LinearLayout.LayoutParams.MATCH_PARENT;
            linearParam.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            PView.setLayoutParams(linearParam);
            PView.setTag(i);

            ll.addView(PView);
            PView.setOnClickListener(postListener);


        }
        contextContainer.addView(ll);
    }
}