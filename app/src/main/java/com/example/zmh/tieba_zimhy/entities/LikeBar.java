package com.example.zmh.tieba_zimhy.entities;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class LikeBar {
    String url ;
    String name ;
    List<PostThread> postThreads ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PostThread> getPostThreads() {
        return postThreads;
    }

    public void setPostThreads(List<PostThread> postThreads) {
        this.postThreads = postThreads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
