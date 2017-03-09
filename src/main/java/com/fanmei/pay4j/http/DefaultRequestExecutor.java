/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.fanmei.pay4j.http;

import com.alibaba.fastjson.JSON;
import com.fanmei.pay4j.exception.RequestException;
import com.fanmei.pay4j.http.response.FanmeiResponse;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by rongtian on 16/7/4.
 */
public class DefaultRequestExecutor implements RequestExecutor {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected CloseableHttpClient httpClient;

    public DefaultRequestExecutor() {
        httpClient = HttpClients.custom().build();
    }

    @Override
    public FanmeiResponse get(String url) throws RequestException {
        return get(url, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse get(String url, Map<String, String> parameters) throws RequestException {
        return get(url, parameters, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse get(String url, List<NameValuePair> params) throws RequestException {
        return get(url, params, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse post(String url) throws RequestException {
        return post(url, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse post(String url, String body) throws RequestException {
        return post(url, body, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse post(String url, Map<String, String> parameters) throws RequestException {
        return post(url, parameters, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse post(String url, List<NameValuePair> params) throws RequestException {
        return post(url, params, Consts.UTF_8);
    }

    @Override
    public FanmeiResponse get(String url, Charset charset) throws RequestException {
        HttpGet getMethod = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(getMethod);
            return genFanmeiResponse(charset, response);
        } catch (Exception e) {
            throw RequestException.of(e);
        }
    }

    @Override
    public FanmeiResponse get(String url, Map<String, String> parameters, Charset charset) throws RequestException {
        if (parameters != null && !parameters.isEmpty()) {
            List<NameValuePair> params = Lists.newArrayList();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String value = entry.getValue();
                if (!Strings.isNullOrEmpty(value)) {
                    params.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
            return get(url, params, charset);
        }
        return null;
    }

    @Override
    public FanmeiResponse get(String url, List<NameValuePair> params, Charset charset) throws RequestException {
        try {
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, charset));
            return get(url + "?" + str);
        } catch (Exception e) {
            logger.error("params=" + JSON.toJSONString(params), e);
            throw RequestException.of(e);
        }
    }

    @Override
    public FanmeiResponse post(String url, Charset charset) throws RequestException {
        HttpPost httpPost = new HttpPost(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return genFanmeiResponse(charset, response);
        } catch (Exception e) {
            throw RequestException.of(e);
        }
    }

    @Override
    public FanmeiResponse post(String url, String body, Charset charset) throws RequestException {
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(body, ContentType.create(URLEncodedUtils.CONTENT_TYPE, charset)));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return genFanmeiResponse(charset, response);
        } catch (Exception e) {
            throw RequestException.of(e);
        }
    }

    @Override
    public FanmeiResponse post(String url, Map<String, String> parameters, Charset charset) throws RequestException {
        if (parameters != null && !parameters.isEmpty()) {
            List<NameValuePair> params = Lists.newArrayList();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String value = entry.getValue();
                if (!Strings.isNullOrEmpty(value)) {
                    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().trim()));
                }
            }
            return post(url, params, charset);
        }
        return null;
    }

    @Override
    public FanmeiResponse post(String url, List<NameValuePair> params, Charset charset) throws RequestException {
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, charset));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return genFanmeiResponse(charset, response);
        } catch (Exception e) {
            throw RequestException.of(e);
        }
    }

    private FanmeiResponse genFanmeiResponse(Charset charset, CloseableHttpResponse response) throws RequestException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            return FanmeiResponse.of(response, charset);
        } else {
            throw RequestException.of(status, JSON.toJSONString(response));
        }
    }
}
