package activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.zmh.tieba_zimhy.R;
import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

/**
 * Created by zmh on 2015/12/2.
 */
public class BaseActivity extends ActionBarActivity {
    protected static final Integer LOGIN_SUCCESS = 1;
    protected static final Integer LOAD_SUCCESS = 2;
    protected static final String SELECTED_BAR_INDEX = "like_bar_index";
    protected static final String SELECTED_THREAD_INDEX = "selected_thread";
    protected static final String SELECTED_POST_INDEX = "selected_post";

    protected Integer index_selected_post = 0;
    protected Integer index_selected_thread = 0;
    protected Integer index_selected_bar = 0;
    protected BaiduUtil baiduUtil;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        Intent intent = getIntent();
        index_selected_bar = intent.getIntExtra(SELECTED_BAR_INDEX, 0);
        index_selected_thread = intent.getIntExtra(SELECTED_THREAD_INDEX, 0);
        index_selected_post = intent.getIntExtra(SELECTED_POST_INDEX, 0);

        //   getActionBar().show();
    }
}
