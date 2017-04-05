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
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rongtian on 16/7/4.
 */
public abstract class FanmeiResponse {

    public <T> T getAsObject(Class<T> clazz) {
        String content = getContent();
        String contentType = getContentType();
        if (null == content || null == contentType) {
            return null;
        }
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

    public abstract String getContent();

    public abstract String getContentType();

    public abstract InputStream getBody() throws IOException;

}
