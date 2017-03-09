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

import com.fanmei.pay4j.common.Constants;

import javax.annotation.Nonnull;

/**
 * Created by rongtian on 16/7/8.
 */
public class OrderBase {
    /**
     * 商户订单ID
     */
    private String outTradeNo;
    /**
     * 标题
     */
    private String subject;
    /**
     * 内容
     */
    private String body;
    /**
     * 金额,单位元
     */
    private String totalFee;
    /**
     * 回调地址
     */
    private String notifyUrl;
    /**
     * 交易超时时间
     */
    private String itBPay = Constants.TRADE_TIMEOUT;

    /**
     * 编码
     */
    private String inputCharset = Constants.UTF_8.name();

    public OrderBase(String outTradeNo, String subject, String body, String totalFee,
                     String notifyUrl) {
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.body = body;
        this.totalFee = totalFee;
        this.notifyUrl = notifyUrl;
    }

    public OrderBase(String outTradeNo, String subject, String body, String totalFee,
                     String notifyUrl, String inputCharset) {
        this(outTradeNo, subject, body, totalFee, notifyUrl);
        this.inputCharset = inputCharset;
    }

    public static OrderBase of(@Nonnull String outTradeNo, @Nonnull String subject,
                               @Nonnull String totalFee, @Nonnull String notifyUrl) {
        return new OrderBase(outTradeNo, subject, subject, totalFee, notifyUrl);
    }

    public static OrderBase of(@Nonnull String outTradeNo, @Nonnull String subject,
                               @Nonnull String totalFee, @Nonnull String notifyUrl,
                               @Nonnull String itBPay) {
        OrderBase orderBase = new OrderBase(outTradeNo, subject, subject, totalFee, notifyUrl);
        orderBase.setItBPay(itBPay);
        return orderBase;
    }

    public static OrderBase of(@Nonnull String outTradeNo, @Nonnull String subject,
                               @Nonnull String totalFee, @Nonnull String notifyUrl,
                               @Nonnull String inputCharset, @Nonnull String itBPay) {
        OrderBase orderBase = new OrderBase(outTradeNo, subject, subject, totalFee, notifyUrl,
                inputCharset);
        orderBase.setItBPay(itBPay);
        return orderBase;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    public String getItBPay() {
        return itBPay;
    }

    public void setItBPay(String itBPay) {
        this.itBPay = itBPay;
    }
}
