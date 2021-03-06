package entities;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class PostThread {
    private static String ERROR_VIEW_MESSAGE = "点 回 1970-1-1";
    private String url;
    private String title;
    private String viewMessage;
    private Integer pageCapacity = 30;
    private Integer totalPage = 0;
    private Integer currentPage = 0;
    private String nextPageUrl;
    private String prePageUrl;
    private String tbs  ;
    private String ti ;
    private String z ;
    private String fid ;


    List<Post> Posts;


    public boolean validCheck() {
        return url != null && title != null && !ERROR_VIEW_MESSAGE.equals(viewMessage);
    }



    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getTbs() {
        return tbs;
    }

    public void setTbs(String tbs) {
        this.tbs = tbs;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

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
