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
import com.fanmei.pay4j.weixin.type.RefundChannel;
import com.fanmei.pay4j.weixin.type.RefundStatus;
import com.fanmei.pay4j.xml.ListSuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 退款详细
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefundDetail extends CommonResult {

    private static final long serialVersionUID = -3687863914168618620L;

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
     * 退款渠道:ORIGINAL—原路退款,默认 BALANCE—退回到余额
     */
    @XmlElement(name = "refund_channel")
    @JSONField(name = "refund_channel")
    private String refundChannel;
    /**
     * 退款总金额,单位为分,可以做部分退款
     */
    @XmlElement(name = "refund_fee")
    @JSONField(name = "refund_fee")
    private Integer refundFee;
    /**
     * 退款金额
     */
    @XmlElement(name = "settlement_refund_fee")
    @JSONField(name = "settlement_refund_fee")
    private Integer settlementRefundFee;
    /**
     * 单个代金券或立减优惠支付金额
     */
    @XmlElement(name = "coupon_type")
    @JSONField(name = "coupon_type")
    private Integer couponType;
    /**
     * 退款入账账户
     */
    @XmlElement(name = "refund_recv_accout")
    @JSONField(name = "refund_recv_accout")
    private String refundRecvAccout;
    /**
     * 退款状态
     */
    @XmlElement(name = "refund_status")
    @JSONField(name = "refund_status")
    private String refundStatus;
    /**
     * 现金券退款金额&lg;=退款金额,退款金额-现金券退款金额为现金
     */
    @XmlElement(name = "coupon_refund_fee")
    @JSONField(name = "coupon_refund_fee")
    private Integer couponRefundFee;
    /**
     * 代金券或立减优惠使用数量 <span style="color:red">
     * 微信支付文档上写的coupon_count,而实际测试拿到的是coupon_refund_count,做个记号。
     * </span>
     */
    @XmlElement(name = "coupon_refund_count")
    @JSONField(name = "coupon_refund_count")
    private Integer couponRefundCount;
    /**
     * 代金券信息
     *
     * @see CouponRefund
     */
    @ListSuffixResult
    private List<CouponRefund> couponList;

    protected RefundDetail() {
        // jaxb required
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    @JSONField(serialize = false)
    public RefundChannel getFormatRefundChannel() {
        return refundChannel != null ? RefundChannel.valueOf(refundChannel
                .toUpperCase()) : null;
    }

    public Integer getRefundFee() {
        return refundFee;
    }


    public String getRefundStatus() {
        return refundStatus;
    }

    @JSONField(serialize = false)
    public RefundStatus getFormatRefundStatus() {
        return refundStatus != null ? RefundStatus.valueOf(refundStatus
                .toUpperCase()) : null;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }


    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public List<CouponRefund> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponRefund> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "RefundDetail{" +
                "outRefundNo='" + outRefundNo + '\'' +
                ", refundId='" + refundId + '\'' +
                ", refundChannel='" + refundChannel + '\'' +
                ", refundFee=" + refundFee +
                ", settlementRefundFee=" + settlementRefundFee +
                ", couponType=" + couponType +
                ", refundRecvAccout='" + refundRecvAccout + '\'' +
                ", refundStatus='" + refundStatus + '\'' +
                ", couponRefundFee=" + couponRefundFee +
                ", couponRefundCount=" + couponRefundCount +
                ", couponList=" + couponList +
                '}';
    }
}
