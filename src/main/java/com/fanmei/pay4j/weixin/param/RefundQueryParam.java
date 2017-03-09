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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 查询退款
 * Created by rongtian on 16/7/5.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundQueryParam extends CommonResult {

    /**
     * 微信订单号 {四选一必填}
     */
    @XmlElement(name = "transaction_id")
    @JSONField(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号 {四选一必填}
     */
    @XmlElement(name = "out_trade_no")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 商户退款单号 {四选一必填}
     */
    @XmlElement(name = "out_refund_no")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 微信退款单号 {四选一必填}
     */
    @XmlElement(name = "refund_id")
    @JSONField(name = "refund_id")
    private String refundId;

    protected RefundQueryParam() {

    }

    /**
     * 商户订单号的退款参数
     *
     * @param outTradeNo
     * @return
     */
    public static RefundQueryParam createWithOutTradeNo(@Nonnull String outTradeNo) {
        return new RefundQueryParam(outTradeNo, null, null, null);
    }

    /**
     * 微信订单号的退款参数
     *
     * @param transactionId
     * @return
     */
    public static RefundQueryParam createWithTransactionId(@Nonnull String transactionId) {
        return new RefundQueryParam(null, transactionId, null, null);
    }

    /**
     * 微信订单号的退款参数
     *
     * @param outRefundNo
     * @return
     */
    public static RefundQueryParam createWithOutRefundNo(@Nonnull String outRefundNo) {
        return new RefundQueryParam(null, null, outRefundNo, null);
    }

    /**
     * 微信订单号的退款参数
     *
     * @param refundId
     * @return
     */
    public static RefundQueryParam createWithRefundId(@Nonnull String refundId) {
        return new RefundQueryParam(null, null, null, refundId);
    }

    private RefundQueryParam(@Nullable String outTradeNo, @Nullable String transactionId,
                             @Nullable String outRefundNo, @Nullable String refundId) {
        this.outTradeNo = outTradeNo;
        this.transactionId = transactionId;
        this.outRefundNo = outRefundNo;
        this.refundId = refundId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
}
