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
package com.fanmei.pay4j.alipay.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.util.DateUtil;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Created by rongtian on 16/8/19.
 */
public class StatementQueryParam extends RequestParam {

    /**
     * 查询页号,1开始
     */
    @Nonnull
    @JSONField(name = "page_no")
    private String pageNo;
    /**
     * 账务查询开始时间。 格式为 yyyy-MM-dd HH:mm:ss。
     * 开始时间不能大于当前时间和查询结束时间,并且与账务查询结束时间的间隔不能大于1天。
     * 开始时间最早为当前时间前3年。
     * 当查询条件含有账务流水号、支付宝交易号、商户订单号、充值网银流水号中任意一个时,本参数可空,否则不可空。
     * 查询结果数据包含该时间点数据。
     */
    @JSONField(name = "gmt_start_time")
    private String startTime;
    /**
     * 账务查询结束时间。 格式为 yyyy-MM-dd HH:mm:ss。
     * 开始时间不能大于当前时间和查询结束时间,并且与账务查询结束时间的间隔不能大于1天。
     * 当查询条件含有账务流水号、支付宝交易号、商户订单号、充值网银流水号中任意一个时,本参数可空,否则不可空。
     * 查询结果数据不包含该时间点数据。
     */
    @JSONField(name = "gmt_end_time")
    private String endTime;
    /**
     * 交易收款账户
     */
    @JSONField(name = "logon_id")
    private String logonId;
    /**
     * 财务流水号
     */
    @JSONField(name = "iw_account_log_id")
    private String iwAccountLogId;
    /**
     * 业务流水号
     */
    @JSONField(name = "trade_no")
    private String tradeNo;
    /**
     * 商户订单号
     */
    @JSONField(name = "merchant_out_order_no")
    private String outOrderNo;
    /**
     * 充值网银流水号
     */
    @JSONField(name = "deposit_bank_no")
    private String depositBankNo;
    /**
     * 每页记录数。
     * 小于等于 5000 的正整数。
     * 为空或者大于 5000 时,默认 为 5000。
     */
    @JSONField(name = "page_size")
    private String pageSize;
    /**
     * 交易类型代码,参见“7.3 交 易类型代码”。多个交易类型代码之间以半 角逗号“,”分隔。
     */
    @JSONField(name = "trans_code")
    private String transCode;

    public StatementQueryParam(){
        this.setService(Constants.ACCOUNT_PAGE_QUERY);
    }

    public static StatementQueryParam of(int currentPage, @Nonnull Date startTime, @Nonnull Date endTime) {
        StatementQueryParam query = new StatementQueryParam();
        if (currentPage < 1) {
            currentPage = 1;
        }
        query.pageNo = String.valueOf(currentPage);
        query.startTime = DateUtil.format2NormalTime(startTime);
        query.endTime = DateUtil.format2NormalTime(endTime);
        return query;
    }

    public String getDepositBankNo() {
        return depositBankNo;
    }

    public void setDepositBankNo(String depositBankNo) {
        this.depositBankNo = depositBankNo;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIwAccountLogId() {
        return iwAccountLogId;
    }

    public void setIwAccountLogId(String iwAccountLogId) {
        this.iwAccountLogId = iwAccountLogId;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    @Nonnull
    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(@Nonnull String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
