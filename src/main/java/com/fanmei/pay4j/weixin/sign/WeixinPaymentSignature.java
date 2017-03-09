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
package com.fanmei.pay4j.weixin.sign;

import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.sign.AbstractSignature;
import com.fanmei.pay4j.util.DigestUtil;

import java.nio.charset.Charset;

/**
 * 微信支付签名实现
 *
 * @see <a
 * href="https://pay.weixin.qq.com/wiki/doc/api/external/jsapi.php?chapter=4_3">支付签名说明</a>
 */
public class WeixinPaymentSignature extends AbstractSignature {
    /**
     * 支付密钥
     */
    private final String paySignKey;

    public WeixinPaymentSignature(String paySignKey) {
        this.paySignKey = paySignKey;
    }


    @Override
    public String sign(Object obj, Charset charset, boolean encode) {
        StringBuilder sb = join(obj, charset, encode).append("&key=").append(paySignKey);
        return DigestUtil.MD5(sb.toString()).toUpperCase();
    }


    @Override
    public boolean isValidSign(Object obj, String sign) {
        StringBuilder str = join(obj, Constants.UTF_8, false).append("&key=").append(paySignKey);
        return sign.equalsIgnoreCase(DigestUtil.MD5(str.toString()).toUpperCase());
    }

}
