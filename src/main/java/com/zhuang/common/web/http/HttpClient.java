package com.zhuang.common.web.http;

import com.zhuang.common.util.QueryStringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author zwb
 */
public class HttpClient {

    private String baseUrl;
    private String charsetName = "utf-8";

    public HttpClient() {
    }

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public HttpClient(String baseUrl, String charsetName) {
        this.baseUrl = baseUrl;
        this.charsetName = charsetName;
    }

    private String getFullUrl(String url) {
        String result = url == null ? "" : url;
        if (baseUrl != null && baseUrl != "") {
            if (!baseUrl.endsWith("/")) {
                baseUrl = baseUrl + "/";
            }
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            result = baseUrl + url;
        }
        return result;
    }

    public String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            url = getFullUrl(url);
            String urlNameString = url + ((param == null || param == "") ? "" : "?" + param);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charsetName));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new RuntimeException("发送GET请求出现异常！", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public String sendPost(String url, String param) {
        BufferedReader in = null;
        String result = "";
        try {
            url = getFullUrl(url);
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if (param != null && param != "") {
                conn.getOutputStream().write(param.getBytes(charsetName));
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charsetName));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new RuntimeException("发送 POST 请求出现异常！", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String sendGet(String url, Map<String, String> param) {
        return sendGet(url, QueryStringUtils.toString(param));
    }

    public String sendPost(String url, Map<String, String> param) {
        return sendPost(url, QueryStringUtils.toString(param));
    }

    public String sendGet(String url) {
        return sendGet(url, "");
    }

    public String sendPost(String url) {
        return sendPost(url, "");
    }

}
