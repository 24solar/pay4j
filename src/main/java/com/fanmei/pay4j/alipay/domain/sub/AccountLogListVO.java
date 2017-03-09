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
package com.fanmei.pay4j.alipay.domain.sub;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by rongtian on 16/8/19.
 */
public class AccountLogListVO {

    @XmlElement(name = "AccountQueryAccountLogVO")
    private List<AccountQueryAccountLogVO> flowList;

    @XmlTransient
    public List<AccountQueryAccountLogVO> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<AccountQueryAccountLogVO> flowList) {
        this.flowList = flowList;
    }
}
