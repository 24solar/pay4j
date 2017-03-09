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
 * Created by rongtian on 16/6/1.
 */
public enum RefundCode {
    SUCCESS("SUCCESS", "退款成功"),
    FAIL("FAIL", "退款失败"),
    CHANGE("CHANGE", "转入代发，需人工干预，通过线下或者财付通转账的方式进行退款"),
    PROCESSING("PROCESSING", "退款处理中"),
    NOTSURE("NOTSURE", "未确定，需要商户原退款单号重新发起");

    private String code;
    private String meaning;

    RefundCode(String code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public static RefundCode from(String code) {
        for (RefundCode resultCode : RefundCode.values()) {
            if (resultCode.code.equals(code)) {
                return resultCode;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMeaning() {
        return meaning;
    }
}
