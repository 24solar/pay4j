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

import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.util.StringUtil;

import java.nio.charset.Charset;

/**
 * Created by rongtian on 16/7/7.
 */
public abstract class AbstractSignature implements Signature {

    @Override
    public String sign(Object obj) {
        return sign(obj, Constants.UTF_8, false);
    }

    @Override
    public String signWithEncode(Object obj) {
        return sign(obj, Constants.UTF_8, true);
    }

    @Override
    public String sign(Object obj, boolean encode) {
        return sign(obj, Constants.UTF_8, encode);
    }

    /**
     * 拼接字符串
     *
     * @param obj
     * @return
     */
    protected StringBuilder join(Object obj, Charset charset, boolean encode) {
        return new StringBuilder(StringUtil.toJoinForSign(obj, charset, encode));
    }
}
