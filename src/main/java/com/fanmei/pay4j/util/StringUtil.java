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

import com.alibaba.fastjson.JSONObject;
import com.fanmei.pay4j.xml.ListSuffixResultSerializer;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class StringUtil {

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;

    public static String unCapitalize(final String str) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }

        char firstChar = str.charAt(0);
        if (Character.isLowerCase(firstChar)) {
            return str;
        }
        int strLen = str.length();
        return new StringBuilder(strLen)
                .append(Character.toLowerCase(firstChar))
                .append(str.substring(1)).toString();
    }

    public static String capitalize(final String str) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }

        char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            return str;
        }
        int strLen = str.length();
        return new StringBuilder(strLen)
                .append(Character.toTitleCase(firstChar))
                .append(str.substring(1)).toString();
    }

    public static String substringBefore(final String str,
                                         final String separator) {
        if (Strings.isNullOrEmpty(str) || separator == null) {
            return str;
        }
        if (separator.isEmpty()) {
            return EMPTY;
        }
        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static String substringAfter(final String str, final String separator) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        final int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 连接字符串
     *
     * @param object  对象
     * @param charset 字符集
     * @param encode  是否编码
     * @return
     */
    public static String toJoinForSign(Object object, Charset charset, boolean encode) {
        return toJoinForSign(object, charset, encode, false);
    }

    /**
     * 连接字符串
     *
     * @param object  对象
     * @param charset 字符集
     * @param encode  是否编码
     * @return
     */
    public static String toJoinForSign(Object object, Charset charset, boolean encode, boolean withQuota) {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject obj = null;
        if (object instanceof String) {
            obj = JSONObject.parseObject((String) object);
        } else {
            obj = ListSuffixResultSerializer.serializeToJSON(object);
        }
        for (String key : obj.keySet()) {
            String value = obj.getString(key);
            if (Strings.isNullOrEmpty(value)) {
                continue;
            }
            map.put(key, value);
        }
        return toJoinForSign(map, charset, encode, withQuota);
    }

    /**
     * 拼接字符串
     *
     * @param map     对象
     * @param charset 字符集
     * @param encode  是否编码
     * @return
     */
    public static String toJoinForSign(Map<String, String> map, Charset charset, boolean encode, boolean withQuota) {
        map.remove("sign");
        map.remove("sign_type");
        map = new TreeMap<String, String>(map);
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> set = map.entrySet();
        try {

            for (Map.Entry<String, String> entry : set) {
                if (Strings.isNullOrEmpty(entry.getValue())) {
                    continue;
                }
                String value = entry.getValue();
                if (withQuota) {
                    value = new StringBuilder("\"").append(value).append("\"").toString();
                }
                sb.append(entry.getKey()).append("=");
                if (encode) {
                    sb.append(URLEncoder.encode(value, charset.name()));
                } else {
                    sb.append(value);
                }
                sb.append("&");
            }
        } catch (UnsupportedEncodingException e) {

        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 连接字符串
     *
     * @param object    对象
     * @param withQuota 是否加引号
     * @return
     */
    public static String toJoinString(Object object, boolean withQuota) {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject obj = null;
        if (object instanceof String) {
            obj = JSONObject.parseObject((String) object);
        } else {
            obj = ListSuffixResultSerializer.serializeToJSON(object);
        }
        for (String key : obj.keySet()) {
            String value = obj.getString(key);
            if (Strings.isNullOrEmpty(value)) {
                continue;
            }
            map.put(key, value);
        }
        return toJoinString(map, withQuota);
    }

    public static String toJoinString(Map<String, String> map, boolean withQuota) {
        map = new TreeMap<String, String>(map);
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            if (Strings.isNullOrEmpty(entry.getValue())) {
                continue;
            }
            String value = entry.getValue();
            if (withQuota) {
                value = new StringBuilder("\"").append(value).append("\"").toString();
            }
            sb.append(entry.getKey()).append("=").append(value).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }


}