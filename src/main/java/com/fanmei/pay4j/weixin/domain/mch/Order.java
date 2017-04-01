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
import com.fanmei.pay4j.util.DateUtil;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.type.BankType;
import com.fanmei.pay4j.weixin.type.CurrencyType;
import com.fanmei.pay4j.weixin.type.TradeState;
import com.fanmei.pay4j.weixin.type.TradeType;
import com.fanmei.pay4j.xml.ListSuffixResult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order extends CommonResult {

    private static final long serialVersionUID = 5636828325595317079L;
    /**
     * 交易状态
     *
     * @see TradeState
     */
    @XmlElement(name = "trade_state")
    @JSONField(name = "trade_state")
    private String tradeState;
    /**
     * 用户的openid
     */
    @XmlElement(name = "openid")
    @JSONField(name = "openid")
    private String openId;
    /**
     * 用户是否关注公众账号,Y- 关注,N-未关注,仅在公众 账号类型支付有效
     */
    @XmlElement(name = "is_subscribe")
    @JSONField(name = "is_subscribe")
    private String isSubscribe;
    /**
     * 交易类型
     *
     * @see TradeType
     */
    @XmlElement(name = "trade_type")
    @JSONField(name = "trade_type")
    private String tradeType;
    /**
     * 银行类型
     */
    @XmlElement(name = "bank_type")
    @JSONField(name = "bank_type")
    private String bankType;
    /**
     * 订单总金额,单位为分
     */
    @XmlElement(name = "total_fee")
    @JSONField(name = "total_fee")
    private Integer totalFee;
    /**
     * 现金券支付金额&lg;=订单总金 额,订单总金额-现金券金额 为现金支付金额
     */
    @XmlElement(name = "coupon_fee")
    @JSONField(name = "coupon_fee")
    private Integer couponFee;
    /**
     * 代金券或立减优惠使用数量
     */
    @XmlElement(name = "coupon_count")
    @JSONField(name = "coupon_count")
    private Integer couponCount;
    /**
     * 代金券信息 验证签名有点麻烦
     */
    @ListSuffixResult
    private List<CouponInfo> couponList;
    /**
     * 现金支付金额
     */
    @XmlElement(name = "cash_fee")
    @JSONField(name = "cash_fee")
    private Integer cashFee;
    /**
     * 货币类型,符合 ISO 4217 标准的三位字母代码,默认人民币:CNY
     */
    @XmlElement(name = "fee_type")
    @JSONField(name = "fee_type")
    private String feeType;
    /**
     * 微信支付订单号
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
     * 商家数据包
     */
    private String attach;
    /**
     * 支付完成时间,格式为 yyyyMMddhhmmss
     */
    @XmlElement(name = "time_end")
    @JSONField(name = "time_end")
    private String timeEnd;
    /**
     * 交易状态描述
     */
    @XmlElement(name = "trade_state_desc")
    @JSONField(name = "trade_state_desc")
    private String tradeStateDesc;

    /**
     * 用户在子商户下的openid
     */
    @XmlElement(name = "sub_openid")
    @JSONField(name = "sub_openid")
    private String subOpenId;
    /**
     * 是否关注子公众账号,Y- 关注,N-未关注,仅在公众 账号类型支付有效
     */
    @XmlElement(name = "sub_is_subscribe")
    @JSONField(name = "sub_is_subscribe")
    private String subIsSubscribe;

    protected Order() {
        // jaxb required
    }

    @JSONField(serialize = false)
    public TradeState getFormatTradeState() {
        return tradeState != null ? TradeState
                .valueOf(tradeState.toUpperCase()) : null;
    }

    public String getOpenId() {
        return openId;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    @JSONField(serialize = false)
    public boolean getFormatIsSubscribe() {
        return isSubscribe != null && isSubscribe.equalsIgnoreCase("y");
    }

    @JSONField(serialize = false)
    public TradeType getFormatTradeType() {
        return tradeType != null ? TradeType.valueOf(tradeType.toUpperCase())
                : null;
    }

    public String getBankType() {
        return bankType;
    }

    @JSONField(serialize = false)
    public BankType getFormatBankType() {
        return bankType != null ? BankType.valueOf(bankType.toUpperCase())
                : null;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * <span style="color:red">调用接口获取单位为分,get方法转换为元方便使用</span>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatTotalFee() {
        return totalFee != null ? totalFee / 100d : 0d;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    /**
     * <span style="color:red">调用接口获取单位为分,get方法转换为元方便使用</span>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatCouponFee() {
        return couponFee != null ? couponFee / 100d : 0d;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    /**
     * <span style="color:red">调用接口获取单位为分,get方法转换为元方便使用</span>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatCashFee() {
        return cashFee != null ? cashFee / 100d : 0d;
    }

    @JSONField(serialize = false)
    public CurrencyType getFormatFeeType() {
        return feeType != null ? CurrencyType.valueOf(feeType.toUpperCase())
                : null;
    }

    public String getTradeState() {
        return tradeState;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getFeeType() {
        return feeType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    @JSONField(serialize = false)
    public Date getFormatTimeEnd() {
        return timeEnd != null ? DateUtil.parse2yyyyMMddHHmmss(timeEnd) : null;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public List<CouponInfo> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponInfo> couponList) {
        this.couponList = couponList;
    }

    public String getSubOpenId() {
        return subOpenId;
    }

    public String getSubIsSubscribe() {
        return subIsSubscribe;
    }

    @JSONField(serialize = false)
    public boolean getFormatSubIsSubscribe() {
        return subIsSubscribe != null && subIsSubscribe.equalsIgnoreCase("y");
    }

    @Override
    public String toString() {
        return "Order [tradeState=" + tradeState + ", openId=" + openId
                + ", isSubscribe=" + isSubscribe + ", tradeType=" + tradeType
                + ", bankType=" + bankType + ", feeType=" + feeType
                + ", transactionId=" + transactionId + ", outTradeNo="
                + outTradeNo + ", attach=" + attach + ", timeEnd=" + timeEnd
                + ", totalFee=" + getFormatTotalFee() + ", couponFee="
                + getFormatCouponFee() + ", couponCount=" + couponCount
                + ", couponList=" + couponList + ", cashFee="
                + getFormatCashFee() + ", timeEnd=" + getFormatTimeEnd()
                + ", tradeStateDesc=" + tradeStateDesc + ", subOpenId="
                + subOpenId + ", subIsSubscribe=" + subIsSubscribe
                + super.toString() + "]";
    }
}
