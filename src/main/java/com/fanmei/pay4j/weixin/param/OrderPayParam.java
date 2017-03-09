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

import javax.annotation.Nonnull;

/**
 * 订单创建成功,返回对象
 * Created by rongtian on 16/5/16.
 */
public class OrderPayParam {

    @JSONField(name = "appid")
    private String appId;

    @JSONField(name = "partnerid")
    private String partnerId;

    @JSONField(name = "package")
    private String pkg;

    @JSONField(name = "prepayid")
    private String prepayId;

    @JSONField(name = "noncestr")
    private String nonceStr;

    @JSONField(name = "timestamp")
    private String timeStamp;

    @JSONField(name = "sign")
    private String sign;

    private OrderPayParam(String prepayId) {
        this.prepayId = prepayId;
    }

    public static OrderPayParam of(@Nonnull String prepayId) {
        return new OrderPayParam(prepayId);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
