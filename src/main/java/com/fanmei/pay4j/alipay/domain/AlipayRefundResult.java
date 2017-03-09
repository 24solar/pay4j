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

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 退款结果回调
 * Created by rongtian on 16/7/7.
 */
public class AlipayRefundResult extends NotifyResult {
    /**
     * 退款批次号
     */
    @JSONField(name = "batch_no")
    private String batchNo;
    /**
     * 退款成功总数
     */
    @JSONField(name = "success_num")
    private String successNum;
    /**
     * 退款结果明细
     */
    @JSONField(name = "result_details")
    private String resultDetails;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(String successNum) {
        this.successNum = successNum;
    }

    public String getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(String resultDetails) {
        this.resultDetails = resultDetails;
    }

    @JSONField(serialize = false)
    public boolean isRefundSuccess(int num) {
        return String.valueOf(num).equals(successNum);
    }

    @Override
    public String toString() {
        return "AlipayRefundResult{" +
                "batchNo='" + batchNo + '\'' +
                ", successNum='" + successNum + '\'' +
                ", resultDetails='" + resultDetails + '\'' +
                "} " + super.toString();
    }
}
