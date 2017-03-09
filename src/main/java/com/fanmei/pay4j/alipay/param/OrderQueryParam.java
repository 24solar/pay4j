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
package com.fanmei.pay4j.alipay.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fanmei.pay4j.common.Constants;

import javax.annotation.Nonnull;

/**
 * 单个订单查询
 * Created by rongtian on 16/8/22.
 */
public class OrderQueryParam extends RequestParam {
    /**
     * 商户网站唯一订单号(二选一)
     */
    @Nonnull
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 支付宝订单号(二选一)
     */
    @Nonnull
    @JSONField(name = "trade_no")
    private String tradeNo;

    public OrderQueryParam() {
        this.setService(Constants.SINGLE_TRADE_QUERY);
    }

    public static OrderQueryParam of(@Nonnull String outTradeNo) {
        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.outTradeNo = outTradeNo;
        return orderQueryParam;
    }

    @Nonnull
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(@Nonnull String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Nonnull
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(@Nonnull String tradeNo) {
        this.tradeNo = tradeNo;
    }
}


