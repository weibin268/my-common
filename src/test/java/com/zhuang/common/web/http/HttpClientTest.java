package com.zhuang.common.web.http;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpClientTest {

    @Test
    public void sendGet() throws Exception {

        HttpClient httpClient=new HttpClient();

        System.out.println(httpClient.sendGet("https://www.baidu.com"));
    }

}