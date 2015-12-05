package entities;

/**
 * Created by zmh on 2015/12/5.
 */
public class PostReply {
    BaiduUser user;
    String context;
    String time;


    public BaiduUser getUser() {
        return user;
    }

    public void setUser(BaiduUser user) {
        this.user = user;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
