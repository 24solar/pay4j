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
package com.fanmei.pay4j.common;

import java.nio.charset.Charset;

/**
 * 常量类
 */
public final class Constants {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset GBK = Charset.forName("GBK");
    public static final String SUCCESS_UPPER = "SUCCESS";
    public static final String FAIL_UPPER = "FAIL";
    public static final String SunX509 = "SunX509";
    public static final String JKS = "JKS";
    public static final String PKCS12 = "PKCS12";
    public static final String TLS = "TLS";
    public static final String X509 = "X.509";
    public static final String AES = "AES";
    public static final String MD5 = "MD5";
    public static final String RSA = "RSA";
    public static final String SHA = "SHA";
    public static final String SHA1 = "SHA-1";
    public static final String PROTOCOL_FILE = "file";
    public static final String PROTOCOL_JAR = "jar";

    public static final String MCH_BASE_URL = "https://api.mch.weixin.qq.com";

    public static final String ORDER_CREATE_URI = MCH_BASE_URL + "/pay/unifiedorder";
    public static final String ORDER_QUERY_URI = MCH_BASE_URL + "/pay/orderquery";
    public static final String ORDER_CLOSE_URI = MCH_BASE_URL + "/pay/closeorder";
    public static final String DOWNLOADBILL_URI = MCH_BASE_URL + "/pay/downloadbill";
    public static final String REFUND_QUERY_URI = MCH_BASE_URL + "/pay/refundquery";
    public static final String REFUND_APPLY_URI = MCH_BASE_URL + "/secapi/pay/refund";
    public static final String ORDER_REVERSE_URI = MCH_BASE_URL + "/secapi/pay/reverse";
    public static final String LONGURL_CONVERT_URI = MCH_BASE_URL + "/tools/shorturl";

    // 签名方式 不需修改
    public static final String SIGN_TYPE = RSA;

    /**
     * 支付宝通用接口
     */
    public static String ALIPAY_URL = "https://mapi.alipay.com/gateway.do";

    // 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
    public static final String TRANSPORT = "http";

    //交易pay接口
    public static final String PAY_SERVICE = "mobile.securitypay.pay";

    public static final String PAYMENT_TYPE = "1";
    //交易超时时间
    public static final String TRADE_TIMEOUT = "15m";

    public static final String PACKAGE = "Sign=WXPay";

    public static final String RETURN_URL = "m.alipay.com";

    public static final String PROCESSING = "PROCESSING";

    public static final String SUCCESS = "success";

    public static final String FAIL = "fail";

    public static final String TRADE_FINISHED = "TRADE_FINISHED";
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    public static final String TRADE_CLOSED = "TRADE_CLOSED";

    /**
     * 无密退款service
     */
    public static final String REFUND_SERVICE = "refund_fastpay_by_platform_nopwd";
    /**
     * 账务明细分页查询service
     */
    public static final String ACCOUNT_PAGE_QUERY = "account.page.query";
    /**
     * 单订单查询service
     */
    public static final String SINGLE_TRADE_QUERY = "single_trade_query";
    /**
     * 支付宝验证service
     */
    public static final String NOTIFY_VERIFY_SERVICE = "notify_verify";
}
