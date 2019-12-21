package com.zhuang.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class HttpUtils {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static HttpResponse get(String host, String path, Map<String, String> mapQueryString) {
        return get(host, path, mapQueryString, null);
    }

    public static HttpResponse get(String host, String path, Map<String, String> mapQueryString, Map<String, String> mapHeader) {
        HttpClient httpClient = createHttpClient(host);
        String url = buildUrl(host, path, mapQueryString);
        HttpGet request = new HttpGet(url);
        fillHeader(request, mapHeader);
        try {
            return httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException("HttpUtils.get", e);
        }
    }

    public static HttpResponse postForm(String host, String path, Map<String, String> mapForm) {
        return postForm(host, path, mapForm, null, null);
    }

    public static HttpResponse postForm(String host, String path, Map<String, String> mapForm, Map<String, String> mapQueryString, Map<String, String> mapHeader) {
        try {
            HttpClient httpClient = createHttpClient(host);
            String url = buildUrl(host, path, mapQueryString);
            HttpPost request = new HttpPost(url);
            fillHeader(request, mapHeader);
            fillForm(request, mapForm);
            return httpClient.execute(request);
        } catch (Exception e) {
            throw new RuntimeException("HttpUtils.postForm", e);
        }
    }

    public static HttpResponse postJson(String host, String path, String strJson) {
        return postJson(host, path, strJson, null, null);
    }

    public static HttpResponse postJson(String host, String path, String strJson, Map<String, String> mapQueryString, Map<String, String> mapHeader) {
        try {
            HttpClient httpClient = createHttpClient(host);
            String url = buildUrl(host, path, mapQueryString);
            HttpPost request = new HttpPost(url);
            fillHeader(request, mapHeader);
            fillJson(request, strJson);
            return httpClient.execute(request);
        } catch (Exception e) {
            throw new RuntimeException("HttpUtils.postJson", e);
        }
    }

    public static HttpResponse postFile(String host, String path, Map<String, Object> mapForm) {
        return postFile(host, path, mapForm, null, null);
    }

    public static HttpResponse postFile(String host, String path, Map<String, Object> mapForm, Map<String, String> mapQueryString, Map<String, String> mapHeader) {
        try {
            HttpClient httpClient = createHttpClient(host);
            String url = buildUrl(host, path, mapQueryString);
            HttpPost request = new HttpPost(url);
            fillHeader(request, mapHeader);
            fillFile(request, mapForm);
            return httpClient.execute(request);
        } catch (Exception e) {
            throw new RuntimeException("HttpUtils.postFile", e);
        }
    }

    public static String buildUrl(String host, String path, Map<String, String> mapQueryString) {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != mapQueryString) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : mapQueryString.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        try {
                            sbQuery.append(URLEncoder.encode(query.getValue(), DEFAULT_CHARSET));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }

    public static String toString(HttpResponse response, String charset) {
        try {
            return EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            throw new RuntimeException("HttpUtils.toString", e);
        }
    }

    public static String toString(HttpResponse response) {
        return toString(response, DEFAULT_CHARSET);
    }

    public static Map<String, String> createBasicAuthHeader(String userName, String password) {
        Map<String, String> result = new HashMap<>();
        result.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((userName + ":" + password).getBytes()));
        return result;
    }

    private static void fillHeader(HttpRequestBase request, Map<String, String> mapHeader) {
        if (mapHeader != null) {
            for (Map.Entry<String, String> e : mapHeader.entrySet()) {
                request.addHeader(e.getKey(), e.getValue());
            }
        }
    }

    private static void fillForm(HttpEntityEnclosingRequestBase request, Map<String, String> mapFormBody) {
        if (mapFormBody == null) return;
        try {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for (String key : mapFormBody.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, mapFormBody.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, DEFAULT_CHARSET);
            formEntity.setContentType("application/x-www-form-urlencoded; charset=" + DEFAULT_CHARSET);
            request.setEntity(formEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillJson(HttpEntityEnclosingRequestBase request, String strJsonData) {
        if (strJsonData != null) {
            StringEntity entity = new StringEntity(strJsonData, DEFAULT_CHARSET);
            //entity.setContentEncoding(DEFAULT_CHARSET);
            entity.setContentType("application/json; charset=" + DEFAULT_CHARSET);
            request.setEntity(entity);
        }
    }

    private static void fillFile(HttpEntityEnclosingRequestBase request, Map<String, Object> mapFileOrString) {
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for (Map.Entry<String, Object> entry : mapFileOrString.entrySet()) {
            if (entry.getValue() instanceof File) {
                FileBody fileBody = new FileBody((File) entry.getValue());
                multipartEntityBuilder.addPart(entry.getKey(), fileBody);
            } else if (entry.getValue() instanceof FileBody) {
                multipartEntityBuilder.addPart(entry.getKey(), (FileBody) entry.getValue());
            } else if (entry.getValue() instanceof InputStreamBody) {
                multipartEntityBuilder.addPart(entry.getKey(), (InputStreamBody) entry.getValue());
            } else {
                StringBody stringBody = new StringBody(entry.getValue() == null ? null : entry.getValue().toString(), ContentType.create("text/plain", DEFAULT_CHARSET));
                multipartEntityBuilder.addPart(entry.getKey(), stringBody);
            }
        }
        HttpEntity httpEntity = multipartEntityBuilder.build();
        request.setEntity(httpEntity);
    }

    private static HttpClient createHttpClient(String host) {
        return createHttpClient(host, false);
    }

    private static HttpClient createHttpClient(String host, boolean usePooling) {
        try {
            HttpClient httpClient;
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            RegistryBuilder registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();
            registryBuilder.register("http", new PlainConnectionSocketFactory());
            if (isHttps(host)) {
                SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
                sslContextBuilder.loadTrustMaterial(null, (TrustStrategy) (x509Certificates, s) -> true);
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build(), null, null, NoopHostnameVerifier.INSTANCE);
                httpClientBuilder.setSSLSocketFactory(sslConnectionSocketFactory);
                registryBuilder.register("https", sslConnectionSocketFactory);
            }
            if (usePooling) {
                Registry<ConnectionSocketFactory> registry = registryBuilder.build();
                PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
                poolingHttpClientConnectionManager.setMaxTotal(200);
                httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager)
                        .setConnectionManagerShared(true);
            }
            //httpClient = HttpClients.createDefault();
            httpClient = httpClientBuilder.build();
            return httpClient;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static boolean isHttps(String host) {
        return host.startsWith("https://");
    }

}
