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

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by rongtian on 16/7/4.
 */
public class DefaultFanmeiResponse extends FanmeiResponse{
    private final HttpResponse response;
    private String content;
    private Charset charset = Consts.UTF_8;

    public DefaultFanmeiResponse(HttpResponse response, Charset charset) {
        this.response = response;
        this.charset = charset;
    }

    public String getContentType() {
        if (!response.containsHeader(HttpHeaders.CONTENT_TYPE)) {
            return null;
        }
        return response.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue();
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
