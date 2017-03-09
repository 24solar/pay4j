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
import com.fanmei.pay4j.alipay.domain.AlipayRefundResult;
import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.util.StringUtil;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Created by rongtian on 16/7/7.
 */
public class Map2ObjectTest {

    @Test
    public void map2ObjectTest() {
        Map<String, String> result = mockRefundResult();

        String json = JSON.toJSONString(result);
        System.out.println("json:" + json);
        AlipayRefundResult refund = JSON.parseObject(json, AlipayRefundResult.class);
        System.out.println("refund:" + refund);
        String value = StringUtil.toJoinForSign(refund, Constants.UTF_8, false);
        System.out.println("value:" + value);

        String text = JSON.toJSONString(refund);
        System.out.println("text:" + text);
        System.out.println("map:" + JSON.parseObject(text, Map.class));
        System.out.println("refund2:"+StringUtil.toJoinForSign(JSON.parseObject(text, Map.class),Constants.UTF_8,false));

    }

    private Map<String, String> mockRefundResult() {
        Map<String, String> result = Maps.newHashMap();
        result.put("sign", "sign");
        result.put("batch_no", "batch_no");
        result.put("success_num", "1");
        result.put("result_details", "result_details");
        result.put("notify_id", "notify_id");
        result.put("sign_type", "sign_type");
        result.put("notify_time", "notify_time");
        return result;
    }
}
