package com.zhuang.common.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilsTest {

    private static final String HOST = "http://127.0.0.1:8088";

    @Test
    public void get() throws IOException {
        Map<String, String> mapQueryString = new HashMap<>();
        mapQueryString.put("name", "zwb");
        String result = HttpUtils.toString(HttpUtils.get(HOST, null, mapQueryString));
        System.out.println(result);
    }

    @Test
    public void postFile() throws IOException {
        Map<String, Object> mapFileOrString = new HashMap<>();
        mapFileOrString.put("file", new File("D:\\test.txt"));
        mapFileOrString.put("userName", "庄伟斌");
        String result = HttpUtils.toString(HttpUtils.postFile(HOST, "/upms/test/postFile", mapFileOrString), "utf-8");
        System.out.println(result);
    }

    @Test
    public void postJson() throws IOException {
        String strJson = "{\"name\":\"zwb\"}";
        Map<String, String> mapHeader = new HashMap<>();
        mapHeader.put("appCode", "123456");
        String result = HttpUtils.toString(HttpUtils.postJson(HOST, "/upms/test/postJson", strJson, null, mapHeader), "utf-8");
        System.out.println(result);
    }

    @Test
    public void buildUrl() throws IOException {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("name", "zwb");
        stringStringMap.put("age", "123");
        System.out.println(HttpUtils.buildUrl(HOST, "/upms/test/postJson", stringStringMap));
    }

    @Test
    public void createBasicAuthHeader(){
        System.out.println(HttpUtils.createBasicAuthHeader("zwb","123"));
    }
}