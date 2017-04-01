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
package com.fanmei.pay4j.weixin.domain.mch;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 代金券信息(订单)
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CouponInfo implements Serializable {

    private static final long serialVersionUID = -8744999305258786901L;

    /**
     * 代金券或立减优惠批次ID
     */
    @XmlElement(name = "coupon_batch_id")
    @JSONField(name = "coupon_batch_id")
    private String couponBatchId;
    /**
     * 代金券或立减优惠ID
     */
    @XmlElement(name = "coupon_id")
    @JSONField(name = "coupon_id")
    private String couponId;
    /**
     * 单个代金券或立减优惠支付金额
     */
    @XmlElement(name = "coupon_fee")
    @JSONField(name = "coupon_fee")
    private Integer couponFee;
    /**
     * 单个代金券或立减优惠支付金额
     */
    @XmlElement(name = "coupon_type")
    @JSONField(name = "coupon_type")
    private Integer couponType;

    public CouponInfo() {

    }

    public String getCouponBatchId() {
        return couponBatchId;
    }

    public String getCouponId() {
        return couponId;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    /**
     * <span style="color:red">调用接口获取单位为分,get方法转换为元方便使用</span>
     *
     * @return 元单位
     */
    @JSONField(serialize = false)
    public double getFormatCouponFee() {
        return couponFee != null ? couponFee.doubleValue() / 100d : 0d;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return "couponBatchId=" + couponBatchId + ", couponId=" + couponId
                + ", couponFee=" + couponFee;
    }
}
