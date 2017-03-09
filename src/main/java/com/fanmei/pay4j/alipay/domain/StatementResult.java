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
package com.fanmei.pay4j.alipay.domain;

import com.fanmei.pay4j.alipay.domain.sub.AccountPageQueryResult;
import com.fanmei.pay4j.alipay.domain.sub.StatementResponseVO;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by rongtian on 16/8/19.
 */
public class StatementResult extends XmlResult {
    /**
     * 签名方式
     */
    @Nonnull
    @XmlElement(name = "sign_type")
    protected String signType;
    /**
     * 签名
     */
    @Nonnull
    @XmlElement(name = "sign")
    protected String sign;

    @XmlElement(name = "response")
    protected StatementResponseVO response;

    public StatementResponseVO getResponse() {
        return response;
    }

    public void setResponse(@Nonnull StatementResponseVO response) {
        this.response = response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(@Nonnull String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(@Nonnull String signType) {
        this.signType = signType;
    }

    public AccountPageQueryResult fetchAccountPageQueryResult() {
        if (null == response) {
            return null;
        }
        return response.getResult();
    }
}
