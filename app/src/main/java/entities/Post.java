package entities;

import java.util.List;

/**
 * Created by zmh on 2015/12/1.
 */
public class Post extends PostReply {

    private String replyListUrl;
    private Integer pageCapacity;
    private Integer totalPage = 1;
    private List<PostReply> replies;
    private int currentPage;

    public List<PostReply> getReplies() {
        return replies;
    }

    public void setReplies(List<PostReply> replies) {
        this.replies = replies;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageCapacity() {
        return pageCapacity;
    }

    public void setPageCapacity(Integer pageCapacity) {
        this.pageCapacity = pageCapacity;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getReplyListUrl() {
        return replyListUrl;
    }

    public void setReplyListUrl(String url) {
        this.replyListUrl = url;
    }




    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
