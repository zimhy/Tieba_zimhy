package activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.zmh.tieba_zimhy.R;

/**
 * Created by zmh on 2015/12/2.
 */
public class BaseActivity extends Activity {
    protected static final Integer LOGIN_SUCCESS = 1 ;
    protected static final Integer  LOAD_SUCCESS = 2;
    protected static final String LIKE_BAR_INDEX = "like_bar_index" ;
    protected static final String SELECTED_THREAD_INDEX = "selected_thread";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
    }
}
