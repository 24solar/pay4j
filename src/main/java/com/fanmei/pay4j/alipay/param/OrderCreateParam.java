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

/**
 * 创建订单
 * Created by rongtian on 16/7/7.
 */
public class OrderCreateParam extends RequestParam {

    /**
     * 商户网站唯一订单号
     */
    @Nonnull
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 商品名称
     */
    @Nonnull
    @JSONField(name = "subject")
    private String subject;
    /**
     * 支付类型
     */
    @Nonnull
    @JSONField(name = "payment_type")
    private String paymentType;
    /**
     * 卖家支付宝账号
     */
    @Nonnull
    @JSONField(name = "seller_id")
    private String sellerId;
    /**
     * 总金额,单位(元)
     */
    @Nonnull
    @JSONField(name = "total_fee")
    private String totalFee;
    /**
     * 商品详情
     */
    @Nonnull
    @JSONField(name = "body")
    private String body;
    /**
     * 交易超时时间
     */
    @Nonnull
    @JSONField(name = "it_b_pay")
    private String itBPay;
    /**
     * return地址
     */
    @Nonnull
    @JSONField(name = "return_url")
    private String returnUrl;

    public OrderCreateParam() {
        this.setService(Constants.PAY_SERVICE);
        this.setPaymentType(Constants.PAYMENT_TYPE);
        this.setReturnUrl(Constants.RETURN_URL);
    }

    public OrderCreateParam(@Nonnull OrderBase orderBase, @Nonnull String sellerId) {
        this();
        this.outTradeNo = orderBase.getOutTradeNo();
        this.subject = orderBase.getSubject();
        this.body = orderBase.getBody();
        this.totalFee = orderBase.getTotalFee();
        this.sellerId = sellerId;
        setNotifyUrl(orderBase.getNotifyUrl());
        setInputCharset(orderBase.getInputCharset());
        setItBPay(orderBase.getItBPay());
    }

    public static OrderCreateParam create(@Nonnull OrderBase orderBase, @Nonnull String sellerId) {
        return new OrderCreateParam(orderBase, sellerId);
    }

    @Nonnull
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(@Nonnull String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Nonnull
    public String getSubject() {
        return subject;
    }

    public void setSubject(@Nonnull String subject) {
        this.subject = subject;
    }

    @Nonnull
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(@Nonnull String paymentType) {
        this.paymentType = paymentType;
    }

    @Nonnull
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(@Nonnull String sellerId) {
        this.sellerId = sellerId;
    }

    @Nonnull
    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(@Nonnull String totalFee) {
        this.totalFee = totalFee;
    }

    @Nonnull
    public String getBody() {
        return body;
    }

    public void setBody(@Nonnull String body) {
        this.body = body;
    }

    @Nonnull
    public String getItBPay() {
        return itBPay;
    }

    public void setItBPay(@Nonnull String itBPay) {
        this.itBPay = itBPay;
    }

    @Nonnull
    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(@Nonnull String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
