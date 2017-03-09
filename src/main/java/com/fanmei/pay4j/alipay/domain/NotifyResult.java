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
package com.fanmei.pay4j.alipay.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fanmei.pay4j.util.DateUtil;
import com.google.common.base.Strings;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Date;

/**
 * 支付宝异步通知基本参数
 * Created by rongtian on 16/7/6.
 */
public class NotifyResult implements Serializable {
    protected static final long serialVersionUID = -2185313616955251150L;

    /**
     * 通知时间
     */
    @Nonnull
    @JSONField(name = "notify_time")
    protected String notifyTime;
    /**
     * 通知类型
     */
    @Nonnull
    @JSONField(name = "notify_type")
    protected String notifyType;
    /**
     * 通知校验ID
     */
    @Nonnull
    @JSONField(name = "notify_id")
    protected String notifyId;
    /**
     * 签名方式
     */
    @Nonnull
    @JSONField(name = "sign_type")
    protected String signType;
    /**
     * 签名
     */
    @Nonnull
    @JSONField(name = "sign")
    protected String sign;

    @Nonnull
    public String getNotifyTime() {
        return notifyTime;
    }

    @JSONField(serialize = false)
    public Date getNotifyDate() {
        if (Strings.isNullOrEmpty(notifyTime)) {
            return null;
        }
        return DateUtil.parse2NormalTime(notifyTime);
    }

    public void setNotifyTime(@Nonnull String notifyTime) {
        this.notifyTime = notifyTime;
    }

    @Nonnull
    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(@Nonnull String notifyType) {
        this.notifyType = notifyType;
    }

    @Nonnull
    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(@Nonnull String notifyId) {
        this.notifyId = notifyId;
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
        return "NotifyResult{" +
                "notifyTime='" + notifyTime + '\'' +
                ", notifyType='" + notifyType + '\'' +
                ", notifyId='" + notifyId + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
