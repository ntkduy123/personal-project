package com.duynguyen.personal.personalproject.util;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SendRequest {

    public static String sendHttpGetRequest(String enpointUrl, Map<String, String> params, Map<String, String> header) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            try {

                URIBuilder builder = new URIBuilder(enpointUrl);
                if (params != null) {
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        builder.addParameter(entry.getKey(), entry.getValue());
                    }
                }
                HttpGet httpget = new HttpGet(builder.toString());
                if (header != null) {
                    for (Map.Entry<String, String> entry : header.entrySet()) {
                        httpget.addHeader(entry.getKey(), entry.getValue());
                    }
                }
                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    HttpEntity entity = response.getEntity();
                    String rs = EntityUtils.toString(entity);

                    StatusLine statusLine = response.getStatusLine();

                    return rs;
                } finally {
                    response.close();
                }
            } catch (IOException ex) {
                throw new IOException(ex);
            } finally {
                httpclient.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();;
        }

        return "";
    }

    public static String sendHttpPostRequest(String enpointUrl, JsonObject params, Map<String, String> header) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            try {
                HttpPut httpPost = new HttpPut(enpointUrl);
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                if (header != null) {
                    for (Map.Entry<String, String> entry : header.entrySet()) {
                        httpPost.addHeader(entry.getKey(), entry.getValue());
                    }
                }
                StringEntity paramEntity = new StringEntity(params.toString());
                httpPost.setEntity(paramEntity);
                CloseableHttpResponse response = httpclient.execute(httpPost);
                try {
                    HttpEntity entity = response.getEntity();
                    return EntityUtils.toString(entity);
                } finally {
                    response.close();
                }
            } catch (IOException ex) {
                throw new IOException(ex);
            } finally {
                httpclient.close();
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
