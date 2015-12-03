package entities;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class LikeBar {
   private  String url;
    private String name;

    private String prePageUrl = "";
    private String nextPageUrl = "";
    private String userLevel = "";


    private Integer pageCapacity = 20 ;
    private Integer totalPage = 0 ;
    private Integer currentPage = 0 ;
    private List<PostThread> postThreads;

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

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public Integer getPageCapacity() {
        return pageCapacity;
    }

    public void setPageCapacity(Integer pageCapacity) {
        this.pageCapacity = pageCapacity;
    }

    public String getPrePageUrl() {
        return prePageUrl;
    }

    public void setPrePageUrl(String prePageUrl) {
        this.prePageUrl = prePageUrl;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}
