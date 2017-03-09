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
import com.fanmei.pay4j.weixin.type.TradeType;
import com.google.common.base.Strings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 请求订单参数
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PreOrderCreateParam extends OrderDetailParam {

    private static final long serialVersionUID = 8944928173669656177L;

    /**
     * 交易类型JSAPI、NATIVE、APP 必须
     */
    @XmlElement(name = "trade_type")
    @JSONField(name = "trade_type")
    private String tradeType;
    /**
     * 用户在商户 appid 下的唯一 标识, trade_type 为 JSAPI 时,此参数必传
     */
    @XmlElement(name = "openid")
    @JSONField(name = "openid")
    private String openId;

    /**
     * 只在 trade_type 为 NATIVE 且【模式一】 时需要填写 非必须
     */
    @XmlElement(name = "product_id")
    @JSONField(name = "product_id")
    private String productId;

    /**
     * 扫码支付授权码 ,设备读取用户微信中的条码或者二维码信息
     */
    @XmlElement(name = "auth_code")
    @JSONField(name = "auth_code")
    private String authCode;
    /**
     * 指定支付方式:no_credit--指定不能使用信用卡支付
     */
    @XmlElement(name = "limit_pay")
    @JSONField(name = "limit_pay")
    private String limitPay;
    /**
     * 服务商下的用户子标识 非必须
     */
    @XmlElement(name = "sub_openid")
    @JSONField(name = "sub_openid")
    private String subOpenId;

    protected PreOrderCreateParam() {
        // jaxb required
    }

    /**
     * 微信预付单
     *
     * @param body       支付详情 必填
     * @param outTradeNo 商户侧订单号 必填
     * @param totalFee   支付金额(单位元) 必填
     * @param notifyUrl  支付回调URL 必填
     * @param createIp   发起支付的IP地址 必填
     * @param tradeType  支付类型 必填
     * @param timeStart  创建时间
     * @param timeExpire 失效时间
     */
    private PreOrderCreateParam(String body, String outTradeNo, long totalFee,
                                String notifyUrl, String createIp, TradeType tradeType,
                                Date timeStart, Date timeExpire) {
        this(body, null, outTradeNo, totalFee, notifyUrl, createIp, tradeType,
                null, null, null, null,
                timeStart/*订单生成时间*/,
                timeExpire/*订单失效时间*/,
                null, null, null);
    }

    /**
     * 微信预付单
     *
     * @param title      支付详情 必填
     * @param tradeNo    商户侧订单号 必填
     * @param totalFee   支付金额(单位元) 必填
     * @param notifyUrl  支付回调URL 必填
     * @param createIp   发起支付的IP地址 必填
     * @param tradeType  支付类型 必填
     * @param createTime 创建时间
     * @param closeTime  失效时间
     */
    public static PreOrderCreateParam createPreOrderParam(String title, String tradeNo, long totalFee
            , String notifyUrl, String createIp, TradeType tradeType
            , Date createTime, Date closeTime) {
        return new PreOrderCreateParam(title, tradeNo, totalFee, notifyUrl, createIp, tradeType, createTime, closeTime);
    }

    /**
     * 微信支付
     *
     * @param body       支付详情 必填
     * @param outTradeNo 商户侧订单号 必填
     * @param totalFee   支付金额(单位元) 必填
     * @param notifyUrl  支付回调URL 必填
     * @param createIp   发起支付的IP地址 必填
     * @param tradeType  支付类型 必填
     * @param openId     用户唯一标识 公众号JSAPI支付必填
     * @param authCode   支付授权码 刷卡MICROPAY支付必填
     * @param productId  商品ID 扫码NATIVE支付必填
     * @param attach     支付时附加信息 非必填
     */
    public PreOrderCreateParam(String body, String outTradeNo, long totalFee,
                               String notifyUrl, String createIp, TradeType tradeType,
                               String openId, String authCode, String productId, String attach) {
        this(body, null, outTradeNo, totalFee, notifyUrl, createIp, tradeType,
                openId, authCode, productId, attach, null, null, null, null,
                null);
    }

    /**
     * 完整参数
     *
     * @param body       商品描述 <font color="red">必填项</font>
     * @param detail     商品名称明细列表 非必填项
     * @param outTradeNo 商户内部唯一订单号 <font color="red">必填项</font>
     * @param totalFee   商品总额 单位元 <font color="red">必填项</font>
     * @param notifyUrl  支付回调URL <font color="red">必填项</font>
     * @param createIp   订单生成的机器IP <font color="red">必填项</font>
     * @param tradeType  交易类型 <font color="red">必填项</font>
     * @param openId     用户ID <font color="red">tradeType=JSAPI时必填</font>
     * @param authCode   刷卡支付授权码 <font color="red">tradeType=MICROPAY时必填</font>
     * @param productId  产品ID <font color="red">tradeType=NATIVE时必填</font>
     * @param attach     附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 非必填项
     * @param timeStart  订单生成时间，格式为yyyyMMddHHmmss 非必填项
     * @param timeExpire 订单失效时间，格式为yyyyMMddHHmmss;注意：最短失效时间间隔必须大于5分钟 非必填项
     * @param goodsTag   商品标记，代金券或立减优惠功能的参数 非必填项
     * @param limitPay   指定支付方式:no_credit--指定不能使用信用卡支付 非必填项
     * @param subOpenId  用户在子商户appid下的唯一标识 非必填
     *                   openid和sub_openid可以选传其中之一，如果选择传sub_openid ,则必须传sub_appid
     */
    public PreOrderCreateParam(String body, String detail, String outTradeNo,
                               long totalFee, String notifyUrl, String createIp,
                               TradeType tradeType, String openId, String authCode,
                               String productId, String attach, Date timeStart, Date timeExpire,
                               String goodsTag, String limitPay, String subOpenId) {
        super(body, detail, outTradeNo, totalFee, notifyUrl, createIp, attach,
                timeStart, timeExpire, goodsTag);
        if (tradeType.isPayRequestParameter()) {
            this.tradeType = tradeType.name();
        }
        this.openId = openId;
        this.authCode = authCode;
        this.productId = productId;
        this.limitPay = limitPay;
        this.subOpenId = subOpenId;
    }

    public String getTradeType() {
        return tradeType;
    }

    @JSONField(serialize = false)
    public boolean existsTradeType() {
        return !Strings.isNullOrEmpty(tradeType);
    }

    /**
     * 刷卡支付
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isMicroPay() {
        return TradeType.MICROPAY.eq(tradeType);
    }

    /**
     * APP支付
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isAppPay() {
        return TradeType.APP.eq(tradeType);
    }

    /**
     * 扫码支付
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isNativePay() {
        return TradeType.NATIVE.eq(tradeType);
    }

    /**
     * JS支付
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isJsapiPay() {
        return TradeType.JSAPI.eq(tradeType);
    }

    /**
     * wap支付
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isWapPay() {
        return TradeType.WAP.eq(tradeType);
    }

    public String getOpenId() {
        return openId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getProductId() {
        return productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getSubOpenId() {
        return subOpenId;
    }

    public void setSubOpenId(String subOpenId) {
        this.subOpenId = subOpenId;
    }

    @Override
    public String toString() {
        return "PreOrderCreateParam [tradeType=" + tradeType + ", openId=" + openId
                + ", productId=" + productId + ", authCode=" + authCode
                + ", limitPay=" + limitPay + ", subOpenId=" + subOpenId + ", "
                + super.toString() + "]";
    }
}
