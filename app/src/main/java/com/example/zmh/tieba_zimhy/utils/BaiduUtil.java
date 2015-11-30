package com.example.zmh.tieba_zimhy.utils;

/**
 * Created by zmh on 2015/11/29.
 */
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**

 * @author jingchen
 *
 */
public class BaiduUtil {

    private String mLoginUrl = null;

    private String mIndexUrl = null;

    private String mUrlHead = null;

    private boolean isAuth = false;
    private Toast toast = null ;
    private Context context  = null  ;

    private String retuurnMassage ;
    private HttpUtil httpUtil;


    private List<String> mLikeBars;

    private List<String> mLikeBarsUrls;

    public BaiduUtil(Context context ) {
        mLikeBars = new ArrayList<String>();
        mLikeBarsUrls = new ArrayList<String>();
        httpUtil = new HttpUtil();
        mLoginUrl = "http://wappass.baidu.com/passport/login";
        mIndexUrl = "http://tieba.baidu.com/mo";
        mUrlHead = "http://tieba.baidu.com";
        this.context = context ;
    }

    public boolean login(String username, String passwd) {
        isAuth = false;
        mLikeBars.clear();
        mLikeBarsUrls.clear();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", passwd));
        HttpEntity he;
        try {
            he = new UrlEncodedFormEntity(params, "UTF-8");
            HttpResponse hr = httpUtil.post(mLoginUrl, he);
            String firstresult = EntityUtils.toString(hr.getEntity());
            if (firstresult.contains("verifycode")) {

                isAuth = true;
                print("需要验证码") ;
                return false;
            } else if (firstresult.contains("error_area")) {
                print("账号密码错误") ;
                return false;
            }
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
        print("登录成功");
            return true;
    }

    public boolean signUp() {
        if (isAuth) {
            print("���ȵ�¼");

            return false;
        }
        print("signUp...");
        if (mLikeBars.size() != 0) {
            mLikeBars.clear();
            mLikeBarsUrls.clear();
        }
        if (!updateLikeBars())
            return false;
        try {
            for (int i = 0; i < mLikeBars.size(); i++) {
                String barview = getWebContent(mLikeBarsUrls.get(i));
                if (!barview.contains("sign"))
                    print(mLikeBars.get(i) + "已签到");
                else {
                    Elements signurl = Jsoup.parse(barview)
                            .getElementsByAttributeValueMatching("href",
                                    ".*sign.*");
                    getWebContent(mUrlHead + signurl.attr("href"));
                    print(/*mLikeBars.get(i)*/ "签到成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            print("fail in signUp!");
            return false;
        }
        return true;
    }

    /**
     * 获取关注的贴
     *
     * @return
     */
    public boolean updateLikeBars() {
        print("getLikeBars...");
        String indexresult = getWebContent(mIndexUrl);
        if (indexresult == null)
            return false;
        Document document = Jsoup.parse(indexresult);
        Elements likebars = document.select("div.my_love_bar a");
        for (Element e : likebars) {
            mLikeBarsUrls.add(mUrlHead + e.attr("href"));
            mLikeBars.add(e.text());
        }
        if (mLikeBars.size() == 0)
            return false;
        return true;
    }

    /**
     * 获取网页内容
     *
     * @param url
     *            链接地址
     * @return 网页内容
     */
    private String getWebContent(String url) {
        print("getWebContent...");
        String result = null;
        try {
            result = httpUtil.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public void print(String s) {
      /*  toast = Toast.makeText(context, s,
                Toast.LENGTH_SHORT);*/
        Log.i("info",s) ;
        retuurnMassage = s ;

    }

    public List<String> getmLikeBars() {
        return mLikeBars;
    }

    public List<String> getmLikeBarsUrls() {
        return mLikeBarsUrls;
    }

    public boolean isAuth() {
        return isAuth;
    }
}
