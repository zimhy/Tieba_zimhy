package activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.zmh.tieba_zimhy.R;
import com.example.zmh.tieba_zimhy.utils.BaiduUtil;

/**
 * Created by zmh on 2015/12/29.
 */
public class WriteReplyActivity extends  BaseActivity {
    private Button submit ;
    private EditText replyText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);



    }

}
