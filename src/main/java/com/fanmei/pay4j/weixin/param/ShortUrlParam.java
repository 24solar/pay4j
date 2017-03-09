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
package com.fanmei.pay4j.weixin.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.exception.WeixinException;
import com.fanmei.pay4j.common.Constants;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URLEncoder;

/**
 * Created by rongtian on 16/7/5.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShortUrlParam extends CommonResult {
    /**
     * url
     */
    @XmlElement(name = "long_url")
    @JSONField(name = "long_url")
    private String url;

    protected ShortUrlParam() {

    }

    private ShortUrlParam(@Nonnull String url) {
        try {
            this.url = URLEncoder.encode(url, Constants.UTF_8.name());
        } catch (Exception e) {
            throw WeixinException.of(e);
        }
    }

    public static ShortUrlParam create(@Nonnull String url) {
        return new ShortUrlParam(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
