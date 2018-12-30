package com.thinkgem.jeesite.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.*;


import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

/**
 * Created by root on 2017/10/12.
 */
public class HttpReqUtils {

    public static String getReq(String url, String token) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            if (!StringUtils.isEmpty(token)) {
                httpget.addHeader("Authorization", "Bearer " + token);
            }
            System.out.println("Executing request " + httpget.getRequestLine());
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new HttpReqUtils().new HResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            httpclient.close();
            return responseBody;
        } catch (IOException e) {
            return null;
        }
    }

    public static String postReq(String url, Map<String, String> params, String token) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (!StringUtils.isEmpty(token)){
            httpPost.addHeader("Authorization", token);
        }
        StringEntity entity = new StringEntity(JSONObject.toJSONString(params),
                ContentType.create("application/json", Consts.UTF_8));
        entity.setChunked(true);

//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        for (Map.Entry<String,String> entry : params.entrySet()){
//            nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
//        }
        try {
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpPost.setEntity(entity);
            ResponseHandler<String> responseHandler = new HttpReqUtils().new HResponseHandler();
            String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            httpclient.close();
            return responseBody;
        } catch (IOException e) {
            return null;
        }
    }

    public static String delReq(String url, String token) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        if (!StringUtils.isEmpty(token)) {
            httpDelete.addHeader("Authorization", token);
        }
        try {
            ResponseHandler<String> responseHandler = new HttpReqUtils().new HResponseHandler();
            String responseBody = httpclient.execute(httpDelete, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            httpclient.close();
            return responseBody;
        } catch (IOException e) {
            return null;
        }
    }

    private class HResponseHandler implements ResponseHandler<String> {

        @Override
        public String handleResponse(final HttpResponse response)
                throws ClientProtocolException, IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap<String, String>();

        String s = HttpReqUtils.postReq("http://a1.easemob.com/1122161011178276/testapp/token", m, "");
        System.out.println(s);
    }
}

