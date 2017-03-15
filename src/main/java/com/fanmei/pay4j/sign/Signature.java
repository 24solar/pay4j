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
package com.fanmei.pay4j.sign;

import javax.annotation.Nonnull;
import java.nio.charset.Charset;

/**
 * 签名
 */
public interface Signature {

    /**
     * 签名
     *
     * @param obj 签名对象
     * @param charset 字符集
     * @param encode 是否编码
     * @return 签名
     */
    String sign(@Nonnull Object obj, @Nonnull Charset charset, boolean encode);

    /**
     * UTF-8签名
     * @param obj 签名对象
     * @param encode 是否编码
     * @return UTF-8签名
     */
    String sign(@Nonnull Object obj, boolean encode);

    /**
     * UTF-8签名不编码
     * @param obj 签名对象
     * @return 签名
     */
    String sign(@Nonnull Object obj);

    /**
     * UTF-8签名编码
     * @param obj 签名对象
     * @return 签名
     */
    String signWithEncode(@Nonnull Object obj);

    /**
     * 验证签名是否一致
     * @param obj 签名对象
     * @param sign 签名
     * @return 签名是否一致
     */
    boolean isValidSign(@Nonnull Object obj, @Nonnull String sign);
}
