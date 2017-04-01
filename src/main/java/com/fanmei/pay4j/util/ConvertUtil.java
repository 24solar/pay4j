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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by rongtian on 16/7/7.
 */
public class ConvertUtil {

    public static Map<String, String> convert2Map(@Nonnull Object object) {
        String text = JSON.toJSONString(object);
        return JSON.parseObject(text, Map.class);
    }

    public static <T> T convert2Object(@Nonnull Map<String, String> map, @Nonnull TypeReference<T> typeReference) {
        Class<T> clazz = (Class<T>) typeReference.getType();
        String json = JSON.toJSONString(map);
        T result = JSON.parseObject(json, clazz);
        return result;
    }

    public static Map<String, String> convert2Map(@Nonnull Map<String, String[]> sourceMap) {
        Map<String, String> params = Maps.newHashMap();
        for (Map.Entry<String, String[]> entry : sourceMap.entrySet()) {
            String key = entry.getKey();
            List<String> values = Arrays.asList(entry.getValue());
            if (values == null || values.isEmpty()) {
                continue;
            }
            params.put(key, Joiner.on(",").join(values));
        }
        return params;
    }

    public static <T> T convert2Obj(@Nonnull Map<String, String[]> map, @Nonnull TypeReference<T> typeReference) {
        return convert2Object(convert2Map(map), typeReference);
    }
}
