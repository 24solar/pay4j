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

import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.type.TradeType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 预订单信息
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrePay extends CommonResult {

    private static final long serialVersionUID = -8430005768959715444L;

    /**
     * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，
     */
    @XmlElement(name = "trade_type")
    private TradeType tradeType;
    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XmlElement(name = "prepay_id")
    private String prepayId;
    /**
     * trade_type 为 NATIVE 是有 返回,此参数可直接生成二 维码展示出来进行扫码支付 可能为空
     */
    @XmlElement(name = "code_url")
    private String codeUrl;

    protected PrePay() {
        // jaxb required
    }

    public PrePay(String returnCode, String returnMsg) {
        super(returnCode, returnMsg);
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    @Override
    public String toString() {
        return "PrePay [tradeType=" + tradeType + ", prepayId=" + prepayId
                + ", codeUrl=" + codeUrl + ", " + super.toString() + "]";
    }
}
