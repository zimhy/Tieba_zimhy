package com.example.zmh.tieba_zimhy.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpUtil {
    private HttpClient mHttpClient;
    private CookieStore mCookieStore;
    private HttpContext mContext;
    private HttpPost post;
    private HttpGet get;

    public HttpUtil() {
        mHttpClient = new DefaultHttpClient();
        mCookieStore = new BasicCookieStore();
        mContext = new BasicHttpContext();
    }

    public HttpResponse post(String url, HttpEntity he)
            throws ClientProtocolException, IOException {
        post = new HttpPost(url);
        post.setEntity(he);
        mContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
        HttpResponse hr = mHttpClient.execute(post, mContext);
        return hr;
    }

    public String get(String url) throws ClientProtocolException, IOException {
        String result = null;
        get = new HttpGet(url);
        HttpResponse hr = mHttpClient.execute(get, mContext);
        result = EntityUtils.toString(hr.getEntity());
        return result;
    }
}
