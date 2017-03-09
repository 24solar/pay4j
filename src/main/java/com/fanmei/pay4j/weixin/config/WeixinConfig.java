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
package com.fanmei.pay4j.weixin.config;


import com.fanmei.pay4j.weixin.model.WeixinPayAccount;

/**
 * 微信配置相关
 */
public class WeixinConfig {
    /**
     * 账号信息
     */
    private final WeixinPayAccount account;
    /**
     * 系统临时目录
     */
    private String tmpdir;
    /**
     * 支付接口需要的证书文件(*.p12)
     */
    private String certificateFile;

    public WeixinConfig(WeixinPayAccount account) {
        this.account = account;
    }

    public WeixinConfig(WeixinPayAccount account, String certificateFile) {
        this.account = account;
        this.certificateFile = certificateFile;
    }

    public WeixinPayAccount getAccount() {
        return account;
    }

    public String getTmpdir() {
        return tmpdir;
    }

    public void setTmpdir(String tmpdir) {
        this.tmpdir = tmpdir;
    }

    public String getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    @Override
    public String toString() {
        return "WeixinConfig [account=" + account + ", tmpdir=" + tmpdir
                + ", certificateFile=" + certificateFile + "]";
    }
}