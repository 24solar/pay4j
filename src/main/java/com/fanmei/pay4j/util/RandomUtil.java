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

import java.util.Random;
import java.util.UUID;

/**
 * 随机码工具类
 * 
 * @author jinyu(foxinmy@gmail.com)
 * @since JDK 1.6
 */
public class RandomUtil {

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBERCHAR = "0123456789";

	/**
	 * 返回一个定长的随机字符串(包含数字和大小写字母)
	 * 
	 * @param length
	 *            随机数的长度
	 * @return 一个定长的随机字符串
	 */
	public static String generateString(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯数字字符串(只包含数字)
	 * 
	 * @param length
	 *            随机数的长度
	 * @return 一个定长的随机字符串
	 */
	public static String generateStringByNumberChar(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机数的长度
	 * @return 一个定长的随机字符串
	 */
	public static String generateStringByLetterCharr(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机数的长度
	 * @return 一个定长的随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateStringByLetterCharr(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机数的长度
	 * @return 一个定长的随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateStringByLetterCharr(length).toUpperCase();
	}

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	public static void main(String[] args) {
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
	}
}
