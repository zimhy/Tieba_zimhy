package entities;

/**
 * Created by zmh on 2015/12/5.
 */
public class PostReply {
    BaiduUser user;
    CharSequence context;
    String time;
    private String replyCount;


    public BaiduUser getUser() {
        return user;
    }

    public void setUser(BaiduUser user) {
        this.user = user;
    }

    public CharSequence getContext() {
        return context;
    }

    public void setContext(CharSequence context) {
        this.context = context;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

}
