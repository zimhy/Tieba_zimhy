package com.example.zmh.tieba_zimhy.entities;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class PostThread {
    String url ;
    String title ;
    String viewMessage ;
    List<Post> Posts ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Post> getPosts() {
        return Posts;
    }

    public void setPosts(List<Post> posts) {
        Posts = posts;
    }

    public String getViewMessage() {
        return viewMessage;
    }

    public void setViewMessage(String viewMessage) {
        this.viewMessage = viewMessage;
    }
}
