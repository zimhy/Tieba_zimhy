package activities;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import entities.PostReply;
import entities.PostThread;
import views.PostView;

public class PostRepliesActivity extends BaseActivity {
    private Button b_prePage;
    private Button b_reply;
    private Button b_nextPage;
    private ScrollView contextContainer;
    private LikeBar bar;
    private ReplyButtonListener buttonListener;


    private Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);


        baiduUtil = BaiduUtil.getInstance();
        post = baiduUtil.getLikeBars().get(index_selected_bar).
                getPostThreads().get(index_selected_thread).
                getPosts().get(index_selected_post);

            setTitle("回复： " + post.getContext());

        buttonListener = new ReplyButtonListener();
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
        index_selected_bar = intent.getIntExtra(SELECTED_BAR_INDEX, 0);
        index_selected_thread = intent.getIntExtra(SELECTED_THREAD_INDEX, 0);
        bar = baiduUtil.getLikeBars().get(index_selected_bar);

        Resources res = getResources();
        Integer index_Bar = intent.getIntExtra(SELECTED_BAR_INDEX, 0);


        List<PostThread> threads = bar.getPostThreads();

        displayReplies(post);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poet_replies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ReplyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (post != null) {
                switch (v.getId()) {
                    case R.id.prePage_thread:
                        if (post.getCurrentPage() <= 1) {
                            Toast.makeText(getApplicationContext(), "已经是第一页", Toast.LENGTH_SHORT).show();
                        } else {
                            LoadReplissTask loadPre = new LoadReplissTask();
                            loadPre.execute(post.getCurrentPage() - 1);
                        }
                        break;
                    case R.id.nextPage_thread:
                        if (post.getCurrentPage() == post.getTotalPage()) {
                            Toast.makeText(getApplicationContext(), "已经是最后一页", Toast.LENGTH_SHORT).show();
                        } else {
                            LoadReplissTask loadPre = new LoadReplissTask();
                            loadPre.execute(post.getCurrentPage() + 1);
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

    private class LoadReplissTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {

            Integer pageNum = params[0];
            if (post != null && baiduUtil.loadPostReplies(post, pageNum))
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
                displayReplies(post);
                contextContainer.invalidate();
            }
        }
    }

    private void displayReplies(Post post) {
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        List<PostReply> replies = post.getReplies();
        for (int i = 0; i < replies.size(); i++) {
            PostReply reply = replies.get(i);
            PostView PView = new PostView(getApplicationContext(), reply);
            LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT / 2, 40);
            linearParam.width = LinearLayout.LayoutParams.MATCH_PARENT;
            linearParam.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            PView.setLayoutParams(linearParam);
            PView.setTag(i);
            ll.addView(PView);
            //PView.setOnClickListener(postListener);
        }
        contextContainer.addView(ll);
    }

}
