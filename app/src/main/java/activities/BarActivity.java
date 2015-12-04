package activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        b_prePage = (Button) findViewById(R.id.prePage_thread);
        b_refresh = (Button) findViewById(R.id.refresh);
        b_nextPage = (Button) findViewById(R.id.nextPage_thread);
        contextContainer = (ScrollView) findViewById(R.id.contentScroll);
        baiduUtil = BaiduUtil.getInstance();
        Intent intent = getIntent();
        index_selected_bar = intent.getIntExtra(LIKE_BAR_INDEX, 0);
        bar = baiduUtil.getLikeBars().get(index_selected_bar);
        PThreadListener listener = new PThreadListener();
        if (bar.getPostThreads() == null) {
            Toast.makeText(this, "发生错误，请重试", Toast.LENGTH_SHORT);
            return;
        }
        //TODO
        Integer index_Bar = intent.getIntExtra(LIKE_BAR_INDEX, 0);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        List<PostThread> threads = bar.getPostThreads();
        for (int i = 0; i < threads.size(); i++) {
            PostThread postThread = threads.get(i);
            PostThreadView PTView = new PostThreadView(getApplicationContext(), postThread);
            LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT / 2, 40);
            linearParam.width = LinearLayout.LayoutParams.MATCH_PARENT;
            linearParam.height = 100;
            PTView.setLayoutParams(linearParam);
            PTView.setTag(i);
            ll.addView(PTView);
            PTView.setOnClickListener(listener);
          /*  Button thread = new Button(this) ;
            thread.setText(postThread.getTitle());
            ll.addView(thread);*/

        }
        contextContainer.addView(ll);


    }


    private class PThreadListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            index_selected_thread = (Integer) v.getTag();
            selected_thread = bar.getPostThreads().get(index_selected_thread);

            new LoadTask().execute();
            Toast.makeText(getApplicationContext(), "������",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class LoadTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {

            if (selected_thread != null && baiduUtil.loadThreadPost(selected_thread, 1))
                return 1;
            else
                return null;

        }

        @Override
        protected void onPostExecute(Integer result) {
            if(null==result)
            {
                Toast.makeText(getApplicationContext(),"����ʧ�ܣ�������",Toast.LENGTH_SHORT);
            }else
            {
                Intent intent = new Intent() ;
                intent.putExtra(LIKE_BAR_INDEX,index_selected_bar) ;
                intent.putExtra(SELECTED_THREAD_INDEX,index_selected_thread) ;
                intent.setAction("android.intent.action.PostThread") ;
                intent.addCategory(Intent.CATEGORY_DEFAULT) ;
                startActivity(intent);
            }
        }
    }
}
