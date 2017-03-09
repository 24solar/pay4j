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
package com.fanmei.pay4j.weixin.model;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 微信账号信息
 * 
 */
public class WeixinAccount implements Serializable {

	private static final long serialVersionUID = -6001008896414323534L;

	/**
	 * 唯一的身份标识
	 */
	private String id;
	/**
	 * 调用接口的密钥
	 */
	private String secret;

	@JSONCreator
	public WeixinAccount(@JSONField(name = "id") String id,
			@JSONField(name = "secret") String secret) {
		this.id = id;
		this.secret = secret;
	}

	public String getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "id=" + id + ", secret=" + secret;
	}
}
