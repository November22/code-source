package com.httpclient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author sen.huang
 * @date 2018/5/8.
 */
public class HttpTest {
    public static void main(String[] args) throws Exception {
        HttpGet get = new HttpGet("");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpClient.execute(get);
    }
}
