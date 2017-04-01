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
package com.fanmei.pay4j.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    private static final String yyyyMMdd = "yyyyMMdd";
    private static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static final String NORMAL_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String format2yyyyMMdd(Date date) {
        if (null == date) {
            return null;
        }
        return new SimpleDateFormat(yyyyMMdd).format(date);
    }

    public static String format2NormalTime(Date date) {
        if (null == date) {
            return null;
        }
        return new SimpleDateFormat(NORMAL_TIME).format(date);
    }

    public static String format2yyyyMMddHHmmss(Date date) {
        if (null == date) {
            return null;
        }
        return new SimpleDateFormat(yyyyMMddHHmmss).format(date);
    }

    public static Date parse2yyyyMMddHHmmss(String date) {
        try {
            return new SimpleDateFormat(yyyyMMddHHmmss).parse(date);
        } catch (ParseException e) {
            ;
        }
        return null;
    }
    /**
     * yyyymmddhhmmss形式的字符串转换为日期对象
     *
     * @param date 日期字符串
     * @return Date对象
     */
    public static Date parse2NormalTime(String date) {
        try {
            return new SimpleDateFormat(NORMAL_TIME).parse(date);
        } catch (ParseException e) {
            ;
        }
        return null;
    }

    public static String timestamp2string() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
