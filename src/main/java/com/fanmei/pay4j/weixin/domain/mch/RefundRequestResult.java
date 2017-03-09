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
import com.fanmei.pay4j.xml.ListSuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 退款申请结果
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundRequestResult extends CommonResult {

    private static final long serialVersionUID = -3687863914168618620L;

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
     * 商户退款单号
     */
    @XmlElement(name = "out_refund_no")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 微信退款单号
     */
    @XmlElement(name = "refund_id")
    @JSONField(name = "refund_id")
    private String refundId;
    /**
     * 退款渠道
     */
    @XmlElement(name = "refund_channel")
    @JSONField(name = "refund_channel")
    private String refundChannel;
    /**
     * 退款金额
     */
    @XmlElement(name = "refund_fee")
    @JSONField(name = "refund_fee")
    private int refundFee;
    /**
     * 订单总金额
     */
    @XmlElement(name = "total_fee")
    @JSONField(name = "total_fee")
    private int totalFee;
    /**
     * 应结订单金额
     */
    @XmlElement(name = "settlement_total_fee")
    @JSONField(name = "settlement_total_fee")
    private String settlementTotalFee;
    /**
     * 现金退款金额
     */
    @XmlElement(name = "cash_refund_fee")
    @JSONField(name = "cash_refund_fee")
    private String cashRefundFee;
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
     * 退款详情
     *
     * @see com.fanmei.pay4j.weixin.domain.mch.RefundDetail
     */
    @ListSuffixResult({"^out_refund_no(_\\d)$", "^refund_.*(_\\d)$"})
    private List<RefundDetail> refundList;

    protected RefundRequestResult() {
        // jaxb required
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(String settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(String cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    public List<RefundDetail> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<RefundDetail> refundList) {
        this.refundList = refundList;
    }

    @Override
    public String toString() {
        return "RefundRequestResult{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", refundChannel='" + refundChannel + '\'' +
                ", refundFee=" + refundFee +
                ", totalFee=" + totalFee +
                ", settlementTotalFee='" + settlementTotalFee + '\'' +
                ", cashRefundFee='" + cashRefundFee + '\'' +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", refundList=" + refundList +
                '}';
    }
}
