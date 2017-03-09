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
import com.fanmei.pay4j.weixin.type.CurrencyType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 发起退款参数
 * Created by rongtian on 16/7/5.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundCreateParam extends CommonResult {

    /**
     * 微信订单号 {微信订单号和商户订单号二选一必填}
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
     * 商户退款单号 非空
     */
    @XmlElement(name = "out_refund_no")
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 订单总金额,单位为分,不能带小数点 必须
     */
    @XmlElement(name = "total_fee")
    @JSONField(name = "total_fee")
    private String totalFee;
    /**
     * 退款金额,单位为分,不能带小数点 必须
     */
    @XmlElement(name = "refund_fee")
    @JSONField(name = "refund_fee")
    private String refundFee;
    /**
     * 货币种类 可空
     */
    @XmlElement(name = "refund_fee_type")
    @JSONField(name = "refund_fee_type")
    private String refundFeeType;
    /**
     * 操作员 必须
     */
    @XmlElement(name = "op_user_id")
    @JSONField(name = "op_user_id")
    private String opUserId;
    /**
     * 退款资金来源 可空
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    @XmlElement(name = "refund_account")
    @JSONField(name = "refund_account")
    private String refundAccount;

    protected RefundCreateParam() {

    }

    /**
     * 商户订单号的退款参数
     *
     * @param outTradeNo
     * @param outRefundNo
     * @param totalFee
     * @param refundFee
     * @param opUserId
     * @param refundFeeType
     * @return
     */
    public static RefundCreateParam createWithOutTradeNo(@Nonnull String outTradeNo, @Nonnull String outRefundNo
            , long totalFee, long refundFee, @Nonnull String opUserId, @Nonnull CurrencyType refundFeeType) {
        return new RefundCreateParam(outTradeNo, null, outRefundNo, totalFee, refundFee, opUserId, refundFeeType);
    }

    public static RefundCreateParam witchUnsettledFunds(@Nonnull String outTradeNo, @Nonnull String outRefundNo
            , long totalFee, long refundFee, @Nonnull String opUserId) {
        return new RefundCreateParam(outTradeNo, null, outRefundNo, totalFee, refundFee, opUserId, null);
    }

    public static RefundCreateParam witchRechargeFunds(@Nonnull String outTradeNo, @Nonnull String outRefundNo
            , long totalFee, long refundFee, @Nonnull String opUserId) {
        RefundCreateParam refundCreateParam = new RefundCreateParam(outTradeNo, null, outRefundNo, totalFee, refundFee, opUserId, null);
        refundCreateParam.refundAccount = "REFUND_SOURCE_RECHARGE_FUNDS";
        return refundCreateParam;
    }

    /**
     * 微信订单号的退款参数
     *
     * @param transactionId
     * @param outRefundNo
     * @param totalFee
     * @param refundFee
     * @param opUserId
     * @param refundFeeType
     * @return
     */
    public static RefundCreateParam createWithTransactionId(@Nonnull String transactionId, @Nonnull String outRefundNo
            , long totalFee, long refundFee, @Nonnull String opUserId, @Nonnull CurrencyType refundFeeType) {
        return new RefundCreateParam(null, transactionId, outRefundNo, totalFee, refundFee, opUserId, refundFeeType);
    }

    public static RefundCreateParam createWithTransactionId(@Nonnull String transactionId, @Nonnull String outRefundNo
            , long totalFee, long refundFee, @Nonnull String opUserId) {
        return new RefundCreateParam(null, transactionId, outRefundNo, totalFee, refundFee, opUserId, null);
    }

    private RefundCreateParam(@Nullable String outTradeNo, @Nullable String transactionId, @Nonnull String outRefundNo,
                              long totalFee, long refundFee, @Nonnull String opUserId, @Nullable CurrencyType refundFeeType) {
        this.outTradeNo = outTradeNo;
        this.transactionId = transactionId;
        this.outRefundNo = outRefundNo;
        this.totalFee = String.valueOf(totalFee);
        this.refundFee = String.valueOf(refundFee);
        this.opUserId = opUserId;
        this.refundFeeType = refundFeeType != null ? refundFeeType.name() : CurrencyType.CNY.name();
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

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    @Override
    public String toString() {
        return "RefundCreateParam{" +
                "transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outRefundNo='" + outRefundNo + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", refundFee='" + refundFee + '\'' +
                ", refundFeeType='" + refundFeeType + '\'' +
                ", opUserId='" + opUserId + '\'' +
                '}';
    }
}
