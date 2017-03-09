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

import javax.annotation.Nonnull;

/**
 * Created by rongtian on 16/7/7.
 */
public class RefundCreateParam extends RequestParam {
    /**
     * 卖家支付宝账号{二选一}
     */
    @Nonnull
    @JSONField(name = "seller_email")
    private String sellerEmail;
    /**
     * 卖家用户ID{二选一}
     */
    @Nonnull
    @JSONField(name = "seller_user_id")
    private String sellerUserId;
    /**
     * 退款请求的当前时间。 格式为：yyyy-MM-dd HH:mm:ss。
     */
    @Nonnull
    @JSONField(name = "refund_date")
    private String refundDate;
    /**
     * 退款批次号
     * 格式为：退款日期（8位）+流水号（3～24位）。
     */
    @Nonnull
    @JSONField(name = "batch_no")
    private String batchNo;
    /**
     * 总笔数
     */
    @Nonnull
    @JSONField(name = "batch_num")
    private String batchNum;
    /**
     * 单笔数据集
     */
    @Nonnull
    @JSONField(name = "detail_data")
    private String detailData;

    public RefundCreateParam(){
        setService(Constants.REFUND_SERVICE);
    }

    @Nonnull
    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(@Nonnull String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Nonnull
    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(@Nonnull String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    @Nonnull
    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(@Nonnull String refundDate) {
        this.refundDate = refundDate;
    }

    @Nonnull
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(@Nonnull String batchNo) {
        this.batchNo = batchNo;
    }

    @Nonnull
    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(@Nonnull String batchNum) {
        this.batchNum = batchNum;
    }

    @Nonnull
    public String getDetailData() {
        return detailData;
    }

    public void setDetailData(@Nonnull String detailData) {
        this.detailData = detailData;
    }
}
