package entities;

/**
 * Created by zmh on 2015/12/1.
 */
public class Post {
    BaiduUser user ;
    String replyCount ;
    String url ;
    String context ;
    String time ;

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

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
