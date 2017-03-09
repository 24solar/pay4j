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
package com.fanmei.pay4j.weixin.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 调用接口返回JSON格式
 *
 * @see <a
 * href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234&token=&lang=zh_CN">公众平台全局返回码说明</a>
 * @see <a
 * href="http://qydev.weixin.qq.com/wiki/index.php?title=%E5%85%A8%E5%B1%80%E8%BF%94%E5%9B%9E%E7%A0%81%E8%AF%B4%E6%98%8E">企业号全局返回码说明</a>
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -6185313616955051150L;

    @JSONField(name = "errcode")
    private int code;
    @JSONField(name = "errmsg")
    private String desc;
    private String text;

    public JsonResult() {
        this.desc = "";
        this.text = "";
    }

    public JsonResult(int code, String desc, String text) {
        this.code = code;
        this.desc = desc;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JsonResult [code=" + code + ", desc=" + desc + ", text=" + text
                + "]";
    }
}
