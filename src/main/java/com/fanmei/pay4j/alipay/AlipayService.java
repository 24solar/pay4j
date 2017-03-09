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
package com.fanmei.pay4j.alipay;

import com.fanmei.pay4j.alipay.config.AlipayConfig;
import com.fanmei.pay4j.alipay.domain.NotifyResult;
import com.fanmei.pay4j.alipay.domain.StatementResult;
import com.fanmei.pay4j.alipay.domain.XmlResult;
import com.fanmei.pay4j.alipay.param.*;
import com.fanmei.pay4j.alipay.sign.AlipaySignature;
import com.fanmei.pay4j.exception.AlipayException;
import com.fanmei.pay4j.http.RequestExecutor;
import com.fanmei.pay4j.http.response.FanmeiResponse;
import com.fanmei.pay4j.util.ConvertUtil;

import javax.annotation.Nonnull;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static com.fanmei.pay4j.common.Constants.ALIPAY_URL;

/**
 * @author rongtian
 * @author shice
 */
public class AlipayService {

    private final AlipayConfig alipayConfig;

    private final RequestExecutor requestExecutor;

    private final AlipaySignature alipaySignature;

    public AlipayService(@Nonnull AlipayConfig alipayConfig, @Nonnull RequestExecutor requestExecutor) {
        this.alipayConfig = alipayConfig;
        this.requestExecutor = requestExecutor;
        this.alipaySignature = new AlipaySignature(alipayConfig);
    }

    /**
     * 发起支付宝验证
     *
     * @param notifyId
     * @return
     */
    public boolean isAlipayResponse(@Nonnull String notifyId) throws AlipayException {
        VerifyParam verifyParam = VerifyParam.of(alipayConfig.getPartner(), notifyId);
        FanmeiResponse response = requestExecutor.get(ALIPAY_URL, ConvertUtil.convert2Map(verifyParam));
        return Boolean.parseBoolean(response.getContent());
    }

    /**
     * 发起退款请求
     *
     * @param refundParam
     * @return
     */
    public XmlResult applyRefund(@Nonnull RefundCreateParam refundParam) throws AlipayException {
        declareRequest(refundParam);
        refundParam.setSign(alipaySignature.sign(refundParam,
                Charset.forName(refundParam.getInputCharset()), false));
        FanmeiResponse response = requestExecutor.get(ALIPAY_URL, ConvertUtil.convert2Map(refundParam));
        return response.getAsObject(XmlResult.class);
    }

    /**
     * 分页查询对账流水
     * @param queryParam
     * @return
     * @throws AlipayException
     */
    public StatementResult queryStatementFlowList(@Nonnull StatementQueryParam queryParam) throws AlipayException {
        declareRequest(queryParam);
        queryParam.setSign(alipaySignature.sign(queryParam,
                Charset.forName(queryParam.getInputCharset()), false));
        FanmeiResponse response = requestExecutor.get(ALIPAY_URL, ConvertUtil.convert2Map(queryParam));
        return response.getAsObject(StatementResult.class);
    }

    /**
     * 查询单个订单信息
     * @param queryParam
     * @return
     * @throws AlipayException
     */
    public String querySingleOrder(@Nonnull OrderQueryParam queryParam) throws AlipayException {
        declareRequest(queryParam);
        queryParam.setSign(alipaySignature.sign(queryParam,
                Charset.forName(queryParam.getInputCharset()), false));
        FanmeiResponse response = requestExecutor.get(ALIPAY_URL, ConvertUtil.convert2Map(queryParam));
        return response.getContent();
    }

    /**
     * 生成客户端支付参数
     */
    public OrderCreateParam genOrderCreateParam(@Nonnull OrderBase orderBase) throws AlipayException {
        OrderCreateParam orderCreateParam = OrderCreateParam.create(orderBase, alipayConfig.getSellerMail());
        declareRequest(orderCreateParam);
        String sign = alipaySignature.signWithQuota(orderCreateParam,
                Charset.forName(orderCreateParam.getInputCharset()), false);
        try {
            orderCreateParam.setSign(URLEncoder.encode(sign, orderCreateParam.getInputCharset()));
        } catch (Exception e) {
            throw AlipayException.of(e);
        }

        return orderCreateParam;
    }

    /**
     * 对回调对象进行签名验证
     *
     * @param result
     * @param <T>
     * @return
     */
    public <T extends NotifyResult> boolean isValidSign(@Nonnull T result) {
        return alipaySignature.isValidSign(result, result.getSign());
    }

    /**
     * 设置公共请求参数
     *
     * @param request
     */
    protected <T extends RequestParam> void declareRequest(T request) {
        request.setPartner(alipayConfig.getPartner());
        request.setSignType(alipayConfig.getSignType().name());
    }
}
