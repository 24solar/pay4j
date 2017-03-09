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
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.type.CurrencyType;
import com.fanmei.pay4j.xml.ListSuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 退款记录
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundRecord extends CommonResult {

    private static final long serialVersionUID = -2971132874939642721L;

    /**
     * 微信订单号
     */
    @XmlElement(name = "transaction_id")
    @JSONField(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 订单总金额
     */
    @XmlElement(name = "total_fee")
    @JSONField(name = "total_fee")
    private int totalFee;
    /**
     * 订单金额货币种类
     *
     * @see com.fanmei.pay4j.weixin.type.CurrencyType
     */
    @XmlElement(name = "fee_type")
    @JSONField(name = "fee_type")
    private String feeType;
    /**
     * 现金支付金额
     */
    @XmlElement(name = "cash_fee")
    @JSONField(name = "cash_fee")
    private int cashFee;
    /**
     * 现金支付金额货币种类
     *
     * @see com.fanmei.pay4j.weixin.type.CurrencyType
     */
    @XmlElement(name = "cash_fee_type")
    @JSONField(name = "cash_fee_type")
    private String cashFeeType;
    /**
     * 退款总金额
     */
    @XmlElement(name = "refund_fee")
    @JSONField(name = "refund_fee")
    private int refundFee;
    /**
     * 代金券或立减优惠退款金额=订单金额-现金退款金额，注意：满立减金额不会退回
     */
    @XmlElement(name = "coupon_refund_fee")
    @JSONField(name = "coupon_refund_fee")
    private Integer couponRefundFee;
    /**
     * 退款笔数
     */
    @XmlElement(name = "refund_count")
    @JSONField(name = "refund_count")
    private int refundCount;
    /**
     * 退款详情
     *
     * @see com.fanmei.pay4j.weixin.domain.mch.RefundDetail
     */
    @ListSuffixResult({"^out_refund_no(_\\d)$", "^refund_.*(_\\d)$"})
    private List<RefundDetail> refundList;

    protected RefundRecord() {
        // jaxb required
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * <font color="red">调用接口获取单位为分,get方法转换为元方便使用</font>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatCashFee() {
        return cashFee / 100d;
    }

    public int getCashFee() {
        return cashFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    @JSONField(serialize = false)
    public CurrencyType getFormatFeeType() {
        return feeType != null ? CurrencyType.valueOf(feeType.toUpperCase())
                : null;
    }

    @JSONField(serialize = false)
    public CurrencyType getFormatCashFeeType() {
        return cashFeeType != null ? CurrencyType.valueOf(cashFeeType
                .toUpperCase()) : null;
    }

    /**
     * <font color="red">调用接口获取单位为分,get方法转换为元方便使用</font>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatCouponRefundFee() {
        return couponRefundFee != null ? couponRefundFee.intValue() / 100d : 0d;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    /**
     * <font color="red">调用接口获取单位为分,get方法转换为元方便使用</font>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatTotalFee() {
        return totalFee / 100d;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public int getRefundCount() {
        return refundCount;
    }

    public List<RefundDetail> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<RefundDetail> refundList) {
        this.refundList = refundList;
    }

    public int getRefundFee() {
        return refundFee;
    }

    /**
     * <font color="red">调用接口获取单位为分,get方法转换为元方便使用</font>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatRefundFee() {
        return refundFee / 100d;
    }

    @Override
    public String toString() {
        return "RefundRecord [transactionId=" + transactionId + ", outTradeNo="
                + outTradeNo + ", totalFee=" + getFormatTotalFee()
                + ", feeType=" + feeType + ", cashFee=" + getFormatCashFee()
                + ", cashFeeType=" + cashFeeType + ", refundFee="
                + getFormatRefundFee() + ", couponRefundFee="
                + getFormatCouponRefundFee() + ", refundCount=" + refundCount
                + ", refundList=" + refundList + ", " + super.toString() + "]";
    }
}
