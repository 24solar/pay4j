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

/**
 * 交易状态
 * 
 */
public enum TradeState {
	/**
	 * 支付成功
	 */
	SUCCESS,
	/**
	 * 转入退款
	 */
	REFUND,
	/**
	 * 未支付
	 */
	NOTPAY,
	/**
	 * 已关闭
	 */
	CLOSED,
	/**
	 * 已撤销
	 */
	REVOKED,
	/**
	 * 用户支付中
	 */
	USERPAYING,
	/**
	 * 未支付(输入密码或 确认支付超时)
	 */
	NOPAY,
	/**
	 * 支付失败(其他 原因,如银行返回失败)
	 */
	PAYERROR;
}
