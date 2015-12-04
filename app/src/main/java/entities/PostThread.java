package entities;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class PostThread {
    private String url ;
    private String title ;
    private String viewMessage ;
    private Integer pageCapacity  = 30;
    private Integer totalPage = 0 ;
    private Integer currentPage = 0 ;
    private String nextPageUrl ;
    private  String prePageUrl ;

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

    public Integer getPageCapacity() {
        return pageCapacity;
    }

    public void setPageCapacity(Integer pageCapacity) {
        this.pageCapacity = pageCapacity;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrePageUrl() {
        return prePageUrl;
    }

    public void setPrePageUrl(String prePageUrl) {
        this.prePageUrl = prePageUrl;
    }
}
