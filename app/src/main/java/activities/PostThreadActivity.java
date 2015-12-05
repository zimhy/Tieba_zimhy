package activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
        b_prePage = (Button) findViewById(R.id.prePage_thread);
        b_reply = (Button) findViewById(R.id.refresh);
        b_reply.setText("回复");
        b_nextPage = (Button) findViewById(R.id.nextPage_thread);
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
        displayPosts(threads.get(index_selected_thread));

    }

    class PostListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //TODO
        }
    }


    class PostButtonListener implements View.OnClickListener {
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
          /*  Button thread = new Button(this) ;
            thread.setText(postThread.getTitle());
            ll.addView(thread);*/

        }
        contextContainer.addView(ll);
    }
}