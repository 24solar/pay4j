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
package com.fanmei.pay4j.weixin.service;


import com.fanmei.pay4j.http.RequestExecutor;
import com.fanmei.pay4j.http.WeixinSSLRequestExecutor;
import com.fanmei.pay4j.sign.Signature;
import com.fanmei.pay4j.util.RandomUtil;
import com.fanmei.pay4j.weixin.config.WeixinConfig;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.model.WeixinPayAccount;
import com.fanmei.pay4j.weixin.sign.WeixinPaymentSignature;

/**
 * 商户支付
 *
 * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/index.html">商户支付平台</a>
 * @author rongtian
 * @author shice
 */
public class AbstractService extends BaseService {

    protected final WeixinPayAccount weixinAccount;
    protected final Signature weixinSignature;
    protected WeixinSSLRequestExecutor weixinSslRequestExecutor;

    public AbstractService(WeixinConfig weixinConfig, RequestExecutor requestExecutor, WeixinSSLRequestExecutor weixinSslRequestExecutor) {
        super(requestExecutor);
        weixinAccount = weixinConfig.getAccount();
        weixinSignature = new WeixinPaymentSignature(weixinAccount.getPaySignKey());
        this.weixinSslRequestExecutor = weixinSslRequestExecutor;
    }

    /**
     * 设置商户信息
     *
     * @param merchant
     */
    protected <T extends CommonResult> void declareMerchant(T merchant) {
        merchant.setAppId(weixinAccount.getId());
        merchant.setMchId(weixinAccount.getMchId());
        merchant.setDeviceInfo(weixinAccount.getDeviceInfo());
        merchant.setSubId(weixinAccount.getSubId());
        merchant.setSubMchId(weixinAccount.getSubMchId());
        merchant.setNonceStr(RandomUtil.generateString(16));
    }
}
