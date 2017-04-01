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
package com.fanmei.pay4j.weixin.type;

/**
 * 微信支付类型
 */
public enum TradeType {
    /**
     * JS支付
     */
    JSAPI(true),
    /**
     * 刷卡支付:不需要设置TradeType参数
     */
    MICROPAY(false),
    /**
     * 扫码支付
     */
    NATIVE(true),
    /**
     * APP支付
     */
    APP(true),
    /**
     * WAP支付
     */
    WAP(true);

    boolean isPayRequestParameter;

    private TradeType(boolean isPayRequestParameter) {
        this.isPayRequestParameter = isPayRequestParameter;
    }

    /**
     * 是否作为支付请求参数
     *
     * @return boolean
     */
    public boolean isPayRequestParameter() {
        return isPayRequestParameter;
    }

    public boolean eq(String type) {
        return this.name().equalsIgnoreCase(type);
    }
}
