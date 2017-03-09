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
 * 退款状态
 * 
 */
public enum RefundStatus {
	/**
	 * 退款成功
	 */
	SUCCESS,
	/**
	 * 退款失败
	 */
	FAIL,
	/**
	 * 退款处理中
	 */
	PROCESSING,
	/**
	 * 未确定,需要商户 原退款单号重新发起
	 */
	NOTSURE,
	/**
	 * 转入代发,退款到银行发现用户的卡作废或者冻结了,导致原路退款银行卡失败,资金回流到商户的现金帐号,需要商户人工干预,通过线下或者财付通转
	 * 账的方式进行退款。
	 */
	CHANGE;
}
