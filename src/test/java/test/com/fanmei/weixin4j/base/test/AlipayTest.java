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
package test.com.fanmei.weixin4j.base.test;

import com.alibaba.fastjson.JSON;
import com.fanmei.pay4j.alipay.AlipayService;
import com.fanmei.pay4j.alipay.config.AlipayConfig;
import com.fanmei.pay4j.alipay.domain.StatementResult;
import com.fanmei.pay4j.alipay.param.OrderQueryParam;
import com.fanmei.pay4j.alipay.param.StatementQueryParam;
import com.fanmei.pay4j.common.SignType;
import com.fanmei.pay4j.http.DefaultRequestExecutor;
import com.fanmei.pay4j.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by rongtian on 16/8/19.
 */
public class AlipayTest {
    protected AlipayService alipayService;

    @Before
    public void init() {
        AlipayConfig config = new AlipayConfig();
        config.setPartner("");
        config.setPrivateKey("");
        config.setPublicKey("");
        config.setSellerMail("");
        config.setSignType(SignType.of("RSA"));
        alipayService = new AlipayService(config, new DefaultRequestExecutor());
    }


    @Test
    public void testStatement() {
        StatementQueryParam query = StatementQueryParam.of(1, DateUtil.parse2NormalTime("2016-10-17 00:00:00"), DateUtil.parse2NormalTime("2016-10-18 00:00:00"));
        StatementResult content = alipayService.queryStatementFlowList(query);
        System.out.println(JSON.toJSONString(content));
    }

    @Test
    public void testOrder() {
        OrderQueryParam query = OrderQueryParam.of("2016072222548114005");
        String content = alipayService.querySingleOrder(query);
        System.out.println(JSON.toJSONString(content));
    }
}
