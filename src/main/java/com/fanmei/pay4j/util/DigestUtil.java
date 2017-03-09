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

import com.fanmei.pay4j.common.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 签名工具类
 */
public final class DigestUtil {

    private static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * SHA1签名
     *
     * @param content 待签名字符串
     * @return 签名后的字符串
     */
    public static String SHA1(String content) {
        byte[] data = content.getBytes(Constants.UTF_8);
        return HexUtil.encodeHexString(getDigest(Constants.SHA1).digest(data));
    }

    /**
     * SHA签名
     *
     * @param content 待签名字符串
     * @return 签名后的字符串
     */
    public static String SHA(String content) {
        byte[] data = content.getBytes(Constants.UTF_8);
        return HexUtil.encodeHexString(getDigest(Constants.SHA).digest(data));
    }

    /**
     * MD5签名
     *
     * @param content 待签名字符串
     * @return 签名后的字符串
     */
    public static String MD5(String content) {
        byte[] data = content.getBytes(Constants.UTF_8);
        return HexUtil.encodeHexString(getDigest(Constants.MD5).digest(data));
    }
}
