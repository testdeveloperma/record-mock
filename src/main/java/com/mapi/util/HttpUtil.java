package com.mapi.util;


import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import com.mapi.record.bean.ResponseData;
import com.mysql.fabric.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {

	/*public  int statusCode = 200;  //httpÇëÇó×´Ì¬Âë
	public  Header[] responseHeaders = null;*/
	
    public static byte[] getPostData(InputStream input) {
        ByteArrayOutputStream output = null;
        byte[] buffer = new byte[8096];
        int n = 0;
        try {
            output = new ByteArrayOutputStream();
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String sendGet(String url, Map<String, String> requestPropMap) throws Exception {
        return new String(sendGetRequest(url, requestPropMap), "UTF-8");
    }

    public static String sendGet(String url, int timoout) throws Exception {
        return new String(send(url, null, null, "GET", timoout), "GBK");
    }

    public static byte[] sendGetRequest(String url, Map<String, String> requestPropMap) throws Exception {
        return send(url, null, requestPropMap, "GET", 2000);
    }

    public static byte[] sendPostRequest(String url, byte[] data, Map<String, String> requestPropMap) throws Exception {
        return send(url, data, requestPropMap, "POST", 2000);
    }

    public static byte[] sendPostRequest(String url, byte[] data, Map<String, String> requestPropMap, int timeout) throws Exception {
        return send(url, data, requestPropMap, "POST", timeout);
    }

    public static String sendPostRequest(String url, String data, int timeout) throws Exception {
        return sendPostRequest(url, data, null, timeout);
    }

    public static String sendPostRequest(String url, String data, Map<String, String> headers, int timeout) throws Exception {
        byte[] dataBytes = null;
        if (data != null) {
            dataBytes = data.getBytes("UTF-8");
        }
        byte[] result = sendPostRequest(url, dataBytes, headers, timeout);
        return new String(result, "UTF-8");
    }

    public static byte[] send(String serverUrl, byte[] data, Map<String, String> headers, String method, int timeout) throws Exception {

        HttpRequestBase requestBase = new HttpGet();
        RequestConfig requestConfig1 = RequestConfig.custom().setConnectionRequestTimeout(timeout).
                setConnectTimeout(timeout).
                setSocketTimeout(timeout).build();
        requestBase.setConfig(requestConfig1);
        InputStream in = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            if ("GET".equals(method)) {
                requestBase = new HttpGet(serverUrl);
            } else if ("POST".equals(method)) {
                HttpPost post = new HttpPost(serverUrl);
                if (data != null) {
                    post.setEntity(new StringEntity(new String(data), "utf-8"));
                }
                requestBase = post;
            }
            
            if (headers != null && headers.size() > 0) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    requestBase.setHeader(key, value);
                }
            }
            
            httpclient = HttpClients.createDefault();
           // httpclient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);   
            response = httpclient.execute(requestBase);
            in = response.getEntity().getContent();
           
            return getPostData(in);
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
            if (response != null) {
                response.close();
            }
        }
    }
    
    public  static ResponseData sendAndGetData(String serverUrl, byte[] data, Map<String, String> headers, String method, int timeout) throws Exception {
    	ResponseData responseData = new ResponseData();
    	
        HttpRequestBase requestBase = new HttpGet();
        RequestConfig requestConfig1 = RequestConfig.custom().setConnectionRequestTimeout(timeout).
                setConnectTimeout(timeout).
                setSocketTimeout(timeout).build();
        requestBase.setConfig(requestConfig1);
        InputStream in = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            if ("GET".equals(method)) {
                requestBase = new HttpGet(serverUrl);
            } else if ("POST".equals(method)) {
                HttpPost post = new HttpPost(serverUrl);
                if (data != null) {
                    post.setEntity(new StringEntity(new String(data), "utf-8"));
                }
                requestBase = post;
            }
            
            if (headers != null && headers.size() > 0) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    requestBase.setHeader(key, value);
                }
            }
            
            httpclient = HttpClients.createDefault();
           // httpclient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);   
            response = httpclient.execute(requestBase);
            in = response.getEntity().getContent();
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("statusCode: " + statusCode);
            responseData.setStatusCode(statusCode);
            Header[] responseHeaders = response.getAllHeaders();
            Map<String, String> responseHeadersMap = new HashMap<String, String>();    		 			
    			for (int i = 0; i < responseHeaders.length; i++) {
    				Header header = responseHeaders[i];
    				responseHeadersMap.put(header.getName(), header.getValue());
    			}
                System.out.println("responseHeadersMap: " + responseHeadersMap);
            responseData.setHeaders(responseHeadersMap);
            responseData.setResponseStream(getPostData(in));
            return responseData;
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * »ñÈ¡http×´Ì¬Âë
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static int getResponseCode(String url) throws Exception {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            int code = conn.getResponseCode();
            return code;
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


}
