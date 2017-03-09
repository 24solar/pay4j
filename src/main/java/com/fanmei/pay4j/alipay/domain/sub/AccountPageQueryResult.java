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
public class AccountPageQueryResult {
    /**
     * 是否有下一页。
     * T 代表有下一页,F 代表没有。
     */
    @XmlElement(name = "has_next_page")
    private String hasNextPage;
    /**
     * 当前页号
     */
    @XmlElement(name = "page_no")
    private String pageNo;
    /**
     * 每页个数
     */
    @XmlElement(name = "page_size")
    private String pageSize;

    @XmlElement(name = "account_log_list")
    private AccountLogListVO accountLogListVO;

    @XmlTransient
    public AccountLogListVO getAccountLogListVO() {
        return accountLogListVO;
    }

    public void setAccountLogListVO(AccountLogListVO accountLogListVO) {
        this.accountLogListVO = accountLogListVO;
    }

    @XmlTransient
    public String getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(String hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    @XmlTransient
    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @XmlTransient
    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<AccountQueryAccountLogVO> fetchFlowList() {
        if (null == accountLogListVO) {
            return null;
        }
        return accountLogListVO.getFlowList();
    }
}
