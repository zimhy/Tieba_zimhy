package entities;

/**
 * Created by zmh on 2015/12/1.
 */
public class Post extends  PostReply{
    String replyCount;
    String replyListUrl;


    public String getReplyListUrl() {
        return replyListUrl;
    }

    public void setReplyListUrl(String url) {
        this.replyListUrl = url;
    }


    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

}
