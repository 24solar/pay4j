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
package com.fanmei.pay4j.weixin.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 调用商户平台接口返回的公用字段
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommonResult extends XmlResult {

    private static final long serialVersionUID = -8430005768959715444L;

    /**
     * 微信分配的公众账号 ID商户号 非空
     */
    @XmlElement(name = "appid")
    @JSONField(name = "appid")
    private String appId;
    /**
     * 微信支付分配的商户号 非空
     */
    @XmlElement(name = "mch_id")
    @JSONField(name = "mch_id")
    private String mchId;
    /**
     * 微信支付分配的终端设备号 可能为空
     */
    @XmlElement(name = "device_info")
    @JSONField(name = "device_info")
    private String deviceInfo;
    /**
     * 随机字符串 非空
     */
    @XmlElement(name = "nonce_str")
    @JSONField(name = "nonce_str")
    private String nonceStr;
    /**
     * 签名 <span styple="color:red">调用者无需关心</span>
     */
    private String sign;
    /**
     * 微信分配的子商户公众账号ID 非必须
     */
    @XmlElement(name = "sub_id")
    @JSONField(name = "sub_id")
    private String subId;
    /**
     * 微信支付分配的子商户号 非必须
     */
    @XmlElement(name = "sub_mch_id")
    @JSONField(name = "sub_mch_id")
    private String subMchId;
    /**
     * 签名类型 默认MD5
     */
    @XmlElement(name = "sign_type")
    @JSONField(name = "sign_type")
    private String signType;
    /**
     * 是否需要继续调用接口 Y- 需要,N-不需要
     */
    private String recall;

    protected CommonResult() {
        // jaxb required
    }

    public CommonResult(String returnCode, String returnMsg) {
        super(returnCode, returnMsg);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getRecall() {
        return recall;
    }

    public void setRecall(String recall) {
        this.recall = recall;
    }

    @Override
    public String toString() {
        return "appId=" + appId + ", mchId=" + mchId + ", subId=" + subId
                + ", subMchId=" + subMchId + ", nonceStr=" + nonceStr
                + ", sign=" + sign + ", deviceInfo=" + deviceInfo + ", recall="
                + recall + ", " + super.toString();
    }
}
