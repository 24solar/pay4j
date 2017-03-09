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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 支付宝返回XML结果基础类
 * Created by rongtian on 16/7/6.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResult implements Serializable {

    private static final long serialVersionUID = -6185313616955051150L;

    private static final String T = "T";
    @XmlElement(name = "is_success")
    private String isSuccess;
    @XmlElement(name = "error")
    private String error;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean succ() {
        return T.equalsIgnoreCase(isSuccess);
    }
}
