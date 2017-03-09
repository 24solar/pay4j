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
package com.fanmei.pay4j.weixin.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fanmei.pay4j.util.DateUtil;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.type.BillType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by rongtian on 16/7/6.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BillParam extends CommonResult {
    /**
     * 对账单日期
     */
    @XmlElement(name = "bill_date")
    @JSONField(name = "bill_date")
    private String billDate;
    /**
     * 对账单类型
     */
    @XmlElement(name = "bill_type")
    @JSONField(name = "bill_type")
    private String billType;

    protected BillParam() {
    }

    private BillParam(@Nonnull Date billDate, @Nullable BillType billType) {
        this.billDate = DateUtil.format2yyyyMMdd(billDate);
        this.billType = billType != null ? billType.name() : BillType.ALL.name();
    }

    private BillParam(@Nonnull String billDate, @Nullable BillType billType) {
        this.billDate = billDate;
        this.billType = billType != null ? billType.name() : BillType.ALL.name();
    }

    public static BillParam of(@Nonnull Date billDate) {
        return new BillParam(billDate, null);
    }

    public static BillParam of(@Nonnull String billDate) {
        return new BillParam(billDate, null);
    }

    public static BillParam of(@Nonnull String billDate, @Nullable BillType billType) {
        return new BillParam(billDate, billType);
    }

    public static BillParam of(@Nonnull Date billDate, @Nullable BillType billType) {
        return new BillParam(billDate, billType);
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
