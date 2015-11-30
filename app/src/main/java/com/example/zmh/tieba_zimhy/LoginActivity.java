package com.example.zmh.tieba_zimhy;
import android.app.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zmh.tieba_zimhy.utils.BaiduUtil;


public class LoginActivity extends Activity {
    public Integer LOGIN_SUCCESS = 1 ;


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mEmailLoginFormView;
    private Button mPlusSignInButton;
    private View mSignOutButtons;
    private View mLoginFormView;
    private BaiduUtil baiduUtil  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPlusSignInButton = (Button) findViewById(R.id.plus_sign_in_button);


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
       // populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailLoginFormView = findViewById(R.id.email_login_form);
        mSignOutButtons = findViewById(R.id.plus_sign_out_buttons);
        baiduUtil = new BaiduUtil(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean  attemptLogin(){
        new LoginTask().execute();

        return false ;
    }


    private class LoginTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {

            //����һ��Httpclient����
            String userName = mEmailView.getText().toString();
            String passWord = mPasswordView.getText().toString();
            baiduUtil.login(userName, passWord);
            boolean isLogined = baiduUtil.login(userName, passWord);
            if (isLogined) {
                //TODO
                baiduUtil.updateLikeBars() ;

                return  LOGIN_SUCCESS;
            }

            return null ;
        }
        @Override
        protected void onPostExecute(Integer result ) {
            if (LOGIN_SUCCESS.equals(result))
            Toast.makeText(getApplicationContext(), baiduUtil.getmLikeBars().toString(),
                    Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"登录失败",
                        Toast.LENGTH_SHORT).show();

        }
    }


    }
