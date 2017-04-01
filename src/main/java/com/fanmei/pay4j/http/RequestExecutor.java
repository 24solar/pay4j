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
package com.fanmei.pay4j.http;

import com.fanmei.pay4j.exception.RequestException;
import com.fanmei.pay4j.http.response.FanmeiResponse;
import org.apache.http.NameValuePair;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * request执行器
 * Created by rongtian on 16/7/4.
 */
public interface RequestExecutor {

    /**
     * 无参get请求,默认编码UTF8
     *
     * @param url url
     * @return FanmeiResponse FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse get(String url) throws RequestException;

    /**
     * 有参get请求,默认编码UTF8
     *
     * @param url url
     * @param parameters get参数
     * @return FanmeiResponse FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse get(String url, Map<String, String> parameters)
            throws RequestException;

    /**
     * 有参get请求,默认编码UTF8
     *
     * @param url url
     * @param params get参数
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse get(String url, List<NameValuePair> params)
            throws RequestException;

    /**
     * 无参post,默认编码UTF8
     *
     * @param url url
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url) throws RequestException;

    /**
     * 有参post请求,默认编码UTF8
     *
     * @param url url
     * @param body post body
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, String body) throws RequestException;

    /**
     * 有参post请求,默认编码UTF8
     *
     * @param url url
     * @param parameters post参数
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, Map<String, String> parameters)
            throws RequestException;

    /**
     * 有参post请求,默认编码UTF8
     *
     * @param url url
     * @param params post参数
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, List<NameValuePair> params)
            throws RequestException;

    /**
     * 无参get请求
     *
     * @param url url
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse get(String url, Charset charset) throws RequestException;

    /**
     * 有参get请求
     *
     * @param url url
     * @param parameters get参数
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse get(String url, Map<String, String> parameters, Charset charset)
            throws RequestException;

    /**
     * 有参get请求
     *
     * @param url url
     * @param params get参数
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse get(String url, List<NameValuePair> params, Charset charset)
            throws RequestException;

    /**
     * 无参post
     *
     * @param url url
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, Charset charset) throws RequestException;

    /**
     * 有参post请求
     *
     * @param url url
     * @param body body
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, String body, Charset charset) throws RequestException;

    /**
     * 有参post请求
     *
     * @param url url
     * @param parameters 参数
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, Map<String, String> parameters, Charset charset)
            throws RequestException;

    /**
     * 有参post请求
     *
     * @param url url
     * @param params 参数
     * @param charset charset
     * @return FanmeiResponse
     * @throws RequestException 请求异常
     */
    FanmeiResponse post(String url, List<NameValuePair> params, Charset charset)
            throws RequestException;
}
