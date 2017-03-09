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
package com.fanmei.pay4j.weixin.domain.mch;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 代金券信息(退款中体现)
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponRefund implements Serializable {

    private static final long serialVersionUID = -8744999305258786901L;

    /**
     * 退款代金券或立减优惠批次ID
     */
    @XmlElement(name = "coupon_refund_batch_id")
    @JSONField(name = "coupon_refund_batch_id")
    private String couponRefundBatchId;
    /**
     * 退款代金券ID
     */
    @XmlElement(name = "coupon_refund_id")
    @JSONField(name = "coupon_refund_id")
    private String couponRefundId;
    /**
     * 单个退款代金券或立减优惠支付金额
     */
    @XmlElement(name = "coupon_refund_fee")
    @JSONField(name = "coupon_refund_fee")
    private Integer couponRefundRFee;

    public CouponRefund() {

    }

    public String getCouponRefundBatchId() {
        return couponRefundBatchId;
    }

    public void setCouponRefundBatchId(String couponRefundBatchId) {
        this.couponRefundBatchId = couponRefundBatchId;
    }

    public String getCouponRefundId() {
        return couponRefundId;
    }

    public void setCouponRefundId(String couponRefundId) {
        this.couponRefundId = couponRefundId;
    }

    public Integer getCouponRefundRFee() {
        return couponRefundRFee;
    }

    public void setCouponRefundRFee(Integer couponRefundRFee) {
        this.couponRefundRFee = couponRefundRFee;
    }

    @Override
    public String toString() {
        return "CouponRefund{" +
                "couponRefundBatchId='" + couponRefundBatchId + '\'' +
                ", couponRefundId='" + couponRefundId + '\'' +
                ", couponRefundRFee=" + couponRefundRFee +
                '}';
    }
}
