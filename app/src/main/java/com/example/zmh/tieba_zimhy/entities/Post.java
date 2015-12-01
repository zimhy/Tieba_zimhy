package com.example.zmh.tieba_zimhy.entities;

/**
 * Created by zmh on 2015/12/1.
 */
public class Post {
    BaiduUser user ;
    String url ;
    String context ;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BaiduUser getUser() {
        return user;
    }

    public void setUser(BaiduUser user) {
        this.user = user;
    }
}
