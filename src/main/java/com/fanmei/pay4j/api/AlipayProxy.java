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
package com.fanmei.pay4j.api;

import com.fanmei.pay4j.alipay.AlipayService;
import com.fanmei.pay4j.alipay.config.AlipayConfig;
import com.fanmei.pay4j.alipay.domain.NotifyResult;
import com.fanmei.pay4j.alipay.domain.StatementResult;
import com.fanmei.pay4j.alipay.domain.XmlResult;
import com.fanmei.pay4j.alipay.param.*;
import com.fanmei.pay4j.exception.AlipayException;
import com.fanmei.pay4j.http.RequestExecutor;

import javax.annotation.Nonnull;

/**
 *
 * @author rongtian
 * @author shice
 */
public class AlipayProxy {

    private final AlipayService alipayService;

    public AlipayProxy(@Nonnull AlipayConfig alipayConfig, @Nonnull RequestExecutor requestExecutor) {
        this.alipayService = new AlipayService(alipayConfig,requestExecutor);
    }

    /**
     * 发起支付宝验证
     *
     * @param notifyId
     * @return
     */
    public boolean isAlipayResponse(@Nonnull String notifyId) throws AlipayException {
        return alipayService.isAlipayResponse(notifyId);
    }

    /**
     * 发起退款请求
     *
     * @param refundParam
     * @return
     */
    public XmlResult applyRefund(@Nonnull RefundCreateParam refundParam) throws AlipayException {
        return alipayService.applyRefund(refundParam);
    }

    /**
     * 分页获取对账流水
     *
     * @param queryParam
     * @return
     * @throws AlipayException
     */
    public StatementResult queryStatementFlowList(@Nonnull StatementQueryParam queryParam) throws AlipayException {
        return alipayService.queryStatementFlowList(queryParam);
    }

    /**
     * 查询单个订单信息
     * @param queryParam
     * @return
     * @throws AlipayException
     */
    public String querySingleOrder(@Nonnull OrderQueryParam queryParam) throws AlipayException{
        return alipayService.querySingleOrder(queryParam);
    }

    /**
     * 生成客户端支付参数
     */
    public OrderCreateParam genOrderCreateParam(@Nonnull OrderBase orderBase) throws AlipayException {
        return alipayService.genOrderCreateParam(orderBase);
    }

    /**
     * 对回调对象进行签名验证
     *
     * @param result
     * @param <T>
     * @return
     */
    public <T extends NotifyResult> boolean isValidSign(@Nonnull T result) {
        return alipayService.isValidSign(result);
    }
}
