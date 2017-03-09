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

import com.fanmei.pay4j.common.Constants;

import java.util.List;

/**
 * Created by rongtian on 16/8/24.
 */
public class WeixinStatementDetail {
    //交易时间
    private String transDate;
    //公众账号ID
    private String appId;
    //商户号
    private String mchId;
    //子商户号
    private String subMchId;
    //设备号
    private String deviceInfo;
    //微信订单号
    private String transactionId;
    //商户订单号
    private String outTradeNo;
    //用户标识
    private String openId;
    //交易类型
    private String tradeType;
    /**
     * 交易状态:SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String tradeState;
    //付款银行
    private String bankType;
    //货币种类
    private String feeType;
    //总金额
    private String totalFee;
    //企业红包金额
    private String couponFee;
    //微信退款单号
    private String refundId;
    //商户退款单号
    private String outRefundNo;
    //退款金额
    private String refundFee;
    //企业红包退款金额
    private String couponRefundFee;
    //退款渠道:ORIGINAL—原路退款,默认 BALANCE—退回到余额
    private String refundChannel;
    /**
     * 退款状态：
     * SUCCESS—退款成功
     * FAIL—退款失败
     * PROCESSING—退款处理中
     * NOTSURE—未确定，需要商户原退款单号重新发起
     * CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
     */
    private String refundStatus;
    //商品名称
    private String title;
    //商户数据包
    private String attach;
    //手续费
    private String serviceFee;
    //费率
    private String serviceFeeRatio;

    public static WeixinStatementDetail of(List<String> records) {
        WeixinStatementDetail detail = new WeixinStatementDetail();
        int index = 0;
        detail.transDate = records.get(index++).replace("`", "");
        detail.appId = records.get(index++).replace("`", "");
        detail.mchId = records.get(index++).replace("`", "");
        detail.subMchId = records.get(index++).replace("`", "");
        detail.deviceInfo = records.get(index++).replace("`", "");
        detail.transactionId = records.get(index++).replace("`", "");
        detail.outTradeNo = records.get(index++).replace("`", "");
        detail.openId = records.get(index++).replace("`", "");
        detail.tradeType = records.get(index++).replace("`", "");
        detail.tradeState = records.get(index++).replace("`", "");
        detail.bankType = records.get(index++).replace("`", "");
        detail.feeType = records.get(index++).replace("`", "");
        detail.totalFee = records.get(index++).replace("`", "");
        detail.couponFee = records.get(index++).replace("`", "");
        detail.refundId = records.get(index++).replace("`", "");
        detail.outRefundNo = records.get(index++).replace("`", "");
        detail.refundFee = records.get(index++).replace("`", "");
        detail.couponRefundFee = records.get(index++).replace("`", "");
        detail.refundChannel = records.get(index++).replace("`", "");
        detail.refundStatus = records.get(index++).replace("`", "");
        detail.title = records.get(index++).replace("`", "");
        detail.attach = records.get(index++).replace("`", "");
        detail.serviceFee = records.get(index++).replace("`", "");
        detail.serviceFeeRatio = records.get(index++).replace("`", "");
        return detail;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }

    public String getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(String couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getServiceFeeRatio() {
        return serviceFeeRatio;
    }

    public void setServiceFeeRatio(String serviceFeeRatio) {
        this.serviceFeeRatio = serviceFeeRatio;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public boolean isTradeSuccess() {
        return Constants.SUCCESS_UPPER.equalsIgnoreCase(tradeState);
    }

    public boolean isRefundSuccess() {
        return Constants.SUCCESS_UPPER.equalsIgnoreCase(refundStatus);
    }

    public boolean isRefunding() {
        return Constants.PROCESSING.equalsIgnoreCase(refundStatus);
    }

    public String fetchUniqueId() {
        if (!"0".equals(outRefundNo)) {
            return outRefundNo;
        }
        return outTradeNo;
    }
}
