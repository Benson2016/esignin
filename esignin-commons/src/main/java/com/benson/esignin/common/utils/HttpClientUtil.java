package com.benson.esignin.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.benson.esignin.common.cons.SysCons;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * HTTP请求工具类
 *
 * @author: Benson Xu
 * @date: 2016年06月07日 21:38
 */
public class HttpClientUtil {

    public static String post(String url, String json) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Content-type","application/json; charset=utf-8");

        httpPost.setHeader("Accept", "application/json");

        try {

            httpPost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("statusCode: " + statusCode);

            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Request Method failed:" + response.getStatusLine());
            }

            String respBody = EntityUtils.toString(response.getEntity());

            System.out.println("respBody: " + respBody);

            return respBody;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    /**
     * 请求百度接口
     * @param httpUrl
     * @param httpArgs
     * @return
     */
    public static String requestBaiduApiByGet(String httpUrl, String httpArgs) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 设置参数
        if (null != httpArgs) {
            httpUrl += "?" + httpArgs;
        }

        HttpGet httpGet = new HttpGet(httpUrl);

        httpGet.addHeader("apikey", SysCons.API_KEY);

        try {

            HttpResponse response = httpClient.execute(httpGet);

            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
                System.out.println("Request API Method failed:" + response.getStatusLine());
            }

            String respBody = EntityUtils.toString(response.getEntity());

            //System.out.println("respBody: " + respBody);

            return respBody;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "-1";
        } catch (IOException e) {
            e.printStackTrace();
            return "-1";
        }

    }

}
