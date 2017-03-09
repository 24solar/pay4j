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

import com.google.common.base.Strings;

/**
 * 币种
 */
public enum CurrencyType {
    CNY("人民币"), HKD("港元"), TWD("台币"), EUR("欧元"), USD("美元"), GBP("英镑"), JPY("日元"), CAD(
            "加拿大元"), AUD("澳大利亚元"), NZD("新西兰元"), KRW("韩元"), THB("泰铢");

    private String desc;

    CurrencyType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static CurrencyType of(String type) {
        if (Strings.isNullOrEmpty(type)) {
            return CNY;
        }
        for (CurrencyType currencyType : CurrencyType.values()) {
            if (currencyType.desc.equals(type)) {
                return currencyType;
            }
        }
        return CNY;
    }
}
