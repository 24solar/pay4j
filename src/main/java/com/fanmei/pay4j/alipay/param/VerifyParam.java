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
 * 验证是否支付宝发来的通知
 * Created by rongtian on 16/7/7.
 */
public class VerifyParam implements Serializable {
    private static final long serialVersionUID = -3185313616955251150L;
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
     * 通知校验ID
     */
    @Nonnull
    @JSONField(name = "notify_id")
    private String notifyId;

    private VerifyParam() {
        this.service = Constants.NOTIFY_VERIFY_SERVICE;
    }

    private VerifyParam(String partner, String notifyId) {
        this();
        this.partner = partner;
        this.notifyId = notifyId;
    }

    public static VerifyParam of(@Nonnull String partner, @Nonnull String notifyId) {
        return new VerifyParam(partner, notifyId);
    }

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
    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(@Nonnull String notifyId) {
        this.notifyId = notifyId;
    }

    @Override
    public String toString() {
        return "VerifyParam{" +
                "service='" + service + '\'' +
                ", partner='" + partner + '\'' +
                ", notifyId='" + notifyId + '\'' +
                '}';
    }
}
