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
package com.fanmei.pay4j.http.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fanmei.pay4j.exception.WeixinException;
import com.fanmei.pay4j.xml.XmlStream;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by rongtian on 16/7/4.
 */
public class FanmeiResponse {
    private final HttpResponse response;
    private String content;
    private Charset charset = Consts.UTF_8;

    private FanmeiResponse(HttpResponse response, Charset charset) {
        this.response = response;
        this.charset = charset;
    }

    public static FanmeiResponse of(HttpResponse response, Charset charset) {
        return new FanmeiResponse(response, charset);
    }

    public <T> T getAsObject(Class<T> clazz) {
        if (null == getContent() || !response.containsHeader(HttpHeaders.CONTENT_TYPE)) {
            return null;
        }
        String contentType = response.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue();
        if (contentType.contains(ContentType.APPLICATION_JSON.getMimeType())) {
            return JSON.parseObject(content, clazz);
        }
        if (contentType.contains(ContentType.APPLICATION_XML.getMimeType())
                || contentType.contains(ContentType.TEXT_XML.getMimeType())) {
            return XmlStream.fromXML(content, clazz);
        } else if (contentType.contains(ContentType.TEXT_PLAIN.getMimeType())
                || contentType.contains(ContentType.TEXT_HTML.getMimeType())) {
            try {
                return JSON.parseObject(content, clazz);
            } catch (JSONException e) {

            }
            try {
                return XmlStream.fromXML(content, clazz);
            } catch (IllegalArgumentException ex) {

            }
            throw WeixinException.of(content);
        }
        return null;
    }

    public String getContent() {
        if (null != content) {
            return content;
        }
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            try {
                content = EntityUtils.toString(entity, charset);
            } catch (IOException e) {

            }
        }
        return content;
    }

    public InputStream getBody() throws IOException {
        return response.getEntity().getContent();
    }

}
