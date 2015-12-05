package com.example.zmh.tieba_zimhy.utils;

/**
 * Created by zmh on 2015/11/29.
 */

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import entities.BaiduUser;
import entities.LikeBar;
import entities.Post;
import entities.PostThread;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * @author jingchen
 */
public class BaiduUtil {
    private static final BaiduUtil baiduUtil = new BaiduUtil();


    private static final String mLoginUrl = "http://wappass.baidu.com/passport/login";

    private static final String mIndexUrl = "http://tieba.baidu.com/mo";

    private static final String mUrlHead = "http://tieba.baidu.com";

    private boolean isAuth = false;
    private Toast toast = null;
    private Context context = null;
    private String barBaseUrl = null;
    private String returnMassage;
    private HttpUtil httpUtil;


    private List<LikeBar> likeBars;

    public static BaiduUtil getInstance() {
        return baiduUtil;
    }

    private BaiduUtil() {
        likeBars = new ArrayList<LikeBar>();
        httpUtil = new HttpUtil();

        //this.context = context;
    }

    public boolean login(String username, String passwd) {
        isAuth = false;
        likeBars.clear();
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
                print("需要验证码");
                return false;
            } else if (firstresult.contains("error_area")) {
                print("账号密码错误");
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
        if (likeBars.size() != 0) {
            likeBars.clear();
        }
        if (!updateLikeBars())
            return false;
        try {
            for (int i = 0; i < likeBars.size(); i++) {
                String barview = getWebContent(likeBars.get(i).getUrl());
                if (!barview.contains("sign"))
                    print(likeBars.get(i) + "已签到");
                else {
                    Elements signurl = Jsoup.parse(barview)
                            .getElementsByAttributeValueMatching("href",
                                    ".*sign.*");
                    getWebContent(mUrlHead + signurl.attr("href"));
                    print(/*mLikeBars.get(i)*/"签到成功");
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
        /*    mLikeBarsUrls.add(mUrlHead + e.attr("href"));
            mLikeBars.add(e.text());*/
            LikeBar likeBar = new LikeBar();
            likeBar.setUrl(mUrlHead + e.attr("href"));
            likeBar.setName(e.text());
            likeBars.add(likeBar);
        }
        if (likebars.size() == 0)
            return false;
        return true;
    }

    /**
     * 获取网页内容
     *
     * @param url 链接地址
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

        Log.i("info", s);
        returnMassage = s;

    }

    public boolean loadBarContent(LikeBar likeBar, int pageNum) {
        if ("".equals(likeBar.getUrl()) || null == likeBar.getUrl()) {
            return false;
        }
        String barIndexUrl = likeBar.getUrl();
        barBaseUrl = barIndexUrl.substring(0, barIndexUrl.lastIndexOf('/'));
        if (pageNum > 1) {
            barIndexUrl += "&pn=" + (pageNum - 1) * 20;
        }
        String s_Page = getWebContent(barIndexUrl);

        Document d_Page = Jsoup.parse(s_Page);
        Element totalPage = d_Page.select("[name=pnum]").first();

        likeBar.setTotalPage(Integer.parseInt(totalPage.attr("value")));
        Element skipPage = d_Page.select("div[class=bc p]").first();
        String prePageUrl = null;
        String nextPageUrl = null;

        if (skipPage.getElementsByAttribute("accesskey").size() == 2) {
            prePageUrl = skipPage.getElementsByAttribute("accesskey").get(0).attr("href");
            nextPageUrl = skipPage.getElementsByAttribute("accesskey").get(1).attr("href");
        } else {
            if (pageNum == 1) {
                nextPageUrl = skipPage.getElementsByAttribute("accesskey").get(0).attr("href");
            } else {
                prePageUrl = skipPage.getElementsByAttribute("accesskey").get(0).attr("href");
            }
        }
        likeBar.setPrePageUrl(barBaseUrl + prePageUrl);
        likeBar.setNextPageUrl(barBaseUrl + nextPageUrl);
        likeBar.setCurrentPage(pageNum);


        Elements e_PostThreads = d_Page.getElementsByClass("i");
        if (likeBar.getPostThreads() == null) {
            likeBar.setPostThreads(new ArrayList<PostThread>());
        } else {
            likeBar.getPostThreads().clear();
        }

        for (Element e_PostThread : e_PostThreads) {
            PostThread pThread = new PostThread();
            pThread.setTitle(e_PostThread.select("a").text());
            pThread.setUrl(barBaseUrl + e_PostThread.select("a").attr("href"));
            pThread.setViewMessage(e_PostThread.select("p").text());
            likeBar.getPostThreads().add(pThread);
        }


        return true;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public List<LikeBar> getLikeBars() {
        return likeBars;
    }

    public void setLikeBars(List<LikeBar> likeBars) {
        this.likeBars = likeBars;
    }

    public String getReturnMassage() {
        return returnMassage;
    }

    public void setReturnMassage(String returnMassage) {
        this.returnMassage = returnMassage;
    }

    public boolean isAuth() {
        return isAuth;
    }


    public boolean loadThreadPost(PostThread selected_thread, Integer pageNum) {
        if ("".equals(selected_thread.getUrl()) || null == selected_thread.getUrl()) {
            return false;
        }
        String fetchUrl = selected_thread.getUrl();
        if (pageNum > 1) {
            fetchUrl += ("&pn=" + (selected_thread.getPageCapacity() * (pageNum - 1)));
        }
        String s_Page = getWebContent(fetchUrl);
        Document d_Page = Jsoup.parse(s_Page);

        try {


            Element skipPage = d_Page.select("div[class=h]").last();

            Element totalPage = d_Page.select("[name=pnum]").first();
            if (totalPage == null) {
                selected_thread.setTotalPage(1);
            } else {
                selected_thread.setTotalPage(Integer.parseInt(totalPage.attr("value")));
            }

            String prePageUrl = null;
            String nextPageUrl = null;

            if (skipPage != null) {
                if (skipPage.getElementsByAttribute("accesskey").size() == 2) {
                    prePageUrl = skipPage.getElementsByAttribute("accesskey").get(0).attr("href");
                    nextPageUrl = skipPage.getElementsByAttribute("accesskey").get(1).attr("href");
                } else {
                    if (pageNum == 1) {
                        nextPageUrl = skipPage.getElementsByAttribute("accesskey").get(0).attr("href");
                    } else {
                        prePageUrl = skipPage.getElementsByAttribute("accesskey").get(0).attr("href");
                    }
                }
            }

            Elements e_posts = d_Page.getElementsByClass("i");
            if (selected_thread.getPosts() == null) {
                selected_thread.setPosts(new ArrayList<Post>());
            } else {
                selected_thread.getPosts().clear();
            }
            List<Post> posts = selected_thread.getPosts();
            for (Element e_post : e_posts) {
                Post post = new Post();
                post.setUrl(barBaseUrl + "/" + e_post.getElementsByClass("reply_to").attr("href"));
                post.setReplyCount(e_post.getElementsByClass("reply_to").text());


                Element e_time = e_post.select("span[class=b]").first();
                post.setTime(e_time.text());

                Element e_user_name = e_post.select("span[class=g]").first();
                BaiduUser user = new BaiduUser();

                user.setName(e_user_name.getElementsByTag("a").first().text());
                post.setUser(user);
                Elements e_images = e_post.getElementsByTag("a") ;
                for(Element e_image:e_images)
                {

                }
                String s_post = e_post.html().replaceAll("(?i)<br[^>]*>", "br2n");
                String text = Jsoup.parse(s_post).text();
                text = text.replaceAll("br2n", "\n");
                post.setContext(text.substring(0, text.lastIndexOf('\n')));

              //  post.setContext(e_post.html().replaceAll(""));
                posts.add(post);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            returnMassage = "出错的HTML：" + s_Page;
            return false;
        }

    }
}
