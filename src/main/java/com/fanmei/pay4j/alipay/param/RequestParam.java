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
package com.fanmei.pay4j.alipay.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fanmei.pay4j.common.Constants;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * 向支付宝发起请求的基本类
 * Created by rongtian on 16/7/6.
 */
public class RequestParam implements Serializable {
    private static final long serialVersionUID = -2185313616955251150L;

    /**
     * 接口名称
     */
    @Nonnull
    @JSONField(name = "service")
    private String service;
    /**
     * 合作者身份ID
     */
    @Nonnull
    @JSONField(name = "partner")
    private String partner;
    /**
     * 参数编码字符集
     */
    @Nonnull
    @JSONField(name = "_input_charset")
    private String inputCharset = Constants.UTF_8.name();
    /**
     * 服务器异步通知页面路径
     */
    @Nonnull
    @JSONField(name = "notify_url")
    private String notifyUrl;
    /**
     * 签名方式
     */
    @Nonnull
    @JSONField(name = "sign_type")
    private String signType ;
    /**
     * 签名
     */
    @Nonnull
    @JSONField(name = "sign")
    private String sign;

    @Nonnull
    public String getService() {
        return service;
    }

    public void setService(@Nonnull String service) {
        this.service = service;
    }

    @Nonnull
    public String getPartner() {
        return partner;
    }

    public void setPartner(@Nonnull String partner) {
        this.partner = partner;
    }

    @Nonnull
    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(@Nonnull String inputCharset) {
        this.inputCharset = inputCharset;
    }

    @Nonnull
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(@Nonnull String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Nonnull
    public String getSignType() {
        return signType;
    }

    public void setSignType(@Nonnull String signType) {
        this.signType = signType;
    }

    @Nonnull
    public String getSign() {
        return sign;
    }

    public void setSign(@Nonnull String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "service='" + service + '\'' +
                ", partner='" + partner + '\'' +
                ", inputCharset='" + inputCharset + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
