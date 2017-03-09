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
import java.util.Date;

import static com.fanmei.pay4j.common.Constants.*;

/**
 * 订单支付结果回调
 * Created by rongtian on 16/7/7.
 */
public class AlipayPayResult extends NotifyResult {

    /**
     * 商户网站唯一订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 商品名称
     */
    @JSONField(name = "subject")
    private String subject;
    /**
     * 支付类型
     */
    @JSONField(name = "payment_type")
    private String paymentType;
    /**
     * 支付宝交易号
     */
    @Nonnull
    @JSONField(name = "trade_no")
    private String tradeNo;
    /**
     * 交易状态
     */
    @Nonnull
    @JSONField(name = "trade_status")
    private String tradeStatus;
    /**
     * 卖家支付宝用户号
     */
    @Nonnull
    @JSONField(name = "seller_id")
    private String sellerId;
    /**
     * 卖家支付宝账号
     */
    @Nonnull
    @JSONField(name = "seller_email")
    private String sellerEmail;
    /**
     * 买家支付宝用户号
     */
    @Nonnull
    @JSONField(name = "buyer_id")
    private String buyerId;
    /**
     * 买家支付宝账号
     */
    @Nonnull
    @JSONField(name = "buyer_email")
    private String buyerEmail;
    /**
     * 交易金额
     */
    @Nonnull
    @JSONField(name = "total_fee")
    private String totalFee;
    /**
     * 购买数量
     */
    @JSONField(name = "quantity")
    private String quantity;
    /**
     * 商品单价(元)
     */
    @JSONField(name = "price")
    private String price;
    /**
     * 商品描述
     */
    @JSONField(name = "body")
    private String body;
    /**
     * 交易创建时间
     */
    @JSONField(name = "gmt_create")
    private String gmtCreate;
    /**
     * 交易付款时间
     */
    @JSONField(name = "gmt_payment")
    private String gmtPayment;
    /**
     * 是否调整总价
     */
    @JSONField(name = "is_total_fee_adjust")
    private String isTotalFeeAdjust;
    /**
     * 是否使用红包买家
     */
    @JSONField(name = "use_coupon")
    private String useCoupon;
    /**
     * 折扣
     */
    @JSONField(name = "discount")
    private String discount;
    /**
     * 退款状态
     */
    @JSONField(name = "refund_status")
    private String refundStatus;
    /**
     * 退款时间
     */
    @JSONField(name = "gmt_refund")
    private String gmtRefund;
    /**
     * 关闭时间
     */
    @JSONField(name="gmt_close")
    private String gmtClose;

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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Nonnull
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(@Nonnull String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Nonnull
    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(@Nonnull String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Nonnull
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(@Nonnull String sellerId) {
        this.sellerId = sellerId;
    }

    @Nonnull
    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(@Nonnull String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Nonnull
    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(@Nonnull String buyerId) {
        this.buyerId = buyerId;
    }

    @Nonnull
    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(@Nonnull String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    @Nonnull
    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(@Nonnull String totalFee) {
        this.totalFee = totalFee;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public String getIsTotalFeeAdjust() {
        return isTotalFeeAdjust;
    }

    public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
        this.isTotalFeeAdjust = isTotalFeeAdjust;
    }

    public String getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(String gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    @JSONField(serialize = false)
    public boolean isTradeFinshed() {
        return TRADE_FINISHED.equals(tradeStatus);
    }

    @JSONField(serialize = false)
    public boolean isPaySuccess() {
        return TRADE_SUCCESS.equals(tradeStatus);
    }

    @JSONField(serialize = false)
    public boolean isTradeClosed() {
        return TRADE_CLOSED.equals(tradeStatus);
    }

    @JSONField(serialize = false)
    public Date getPayTime() {
        if (Strings.isNullOrEmpty(gmtPayment)) {
            return null;
        }
        return DateUtil.parse2NormalTime(gmtPayment);
    }

    public String getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(String gmtClose) {
        this.gmtClose = gmtClose;
    }

    @Override
    public String toString() {
        return "AlipayPayResult{" +
                "body='" + body + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", subject='" + subject + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", buyerEmail='" + buyerEmail + '\'' +
                ", totalFee='" + totalFee + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtPayment='" + gmtPayment + '\'' +
                ", isTotalFeeAdjust='" + isTotalFeeAdjust + '\'' +
                ", useCoupon='" + useCoupon + '\'' +
                ", discount='" + discount + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", gmtRefund='" + gmtRefund + '\'' +
                ", gmtClose='" + gmtClose + '\'' +
                "} " + super.toString();
    }
}
