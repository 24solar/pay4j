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
package com.fanmei.pay4j.xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.NameFilter;
import com.fanmei.pay4j.common.Constants;
import com.google.common.base.Strings;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对 后缀为_$n 的 xml节点序列化
 */
public class ListSuffixResultSerializer {

    /**
     * 序列化为json
     *
     * @param object
     * @return json
     */
    public static JSONObject serializeToJSON(Object object) {
        JSONObject result = (JSONObject) JSON.toJSON(object);
        Map<Field, String[]> listSuffixFields = ListSuffixResultDeserializer
                .getListSuffixFields(object.getClass());
        if (!listSuffixFields.isEmpty()) {
            JSONField jsonField = null;
            Object value = null;
            for (Field field : listSuffixFields.keySet()) {
                jsonField = field.getAnnotation(JSONField.class);
                if (jsonField != null && Strings.isNullOrEmpty(jsonField.name())) {
                    result.remove(jsonField.name());
                } else {
                    result.remove(field.getName());
                }
                try {
                    field.setAccessible(true);
                    value = field.get(object);
                } catch (Exception e) {

                }
                if (value != null && value instanceof List) {
                    result.putAll(listSuffixConvertMap((List<?>) value));
                }
            }
        }
        return result;
    }

    /**
     * list对象转换为map的$n形式
     *
     * @param listsuffix
     * @return
     */
    public static Map<String, String> listSuffixConvertMap(List<?> listsuffix) {
        Map<String, String> listMap = new HashMap<String, String>();
        if (listsuffix != null && !listsuffix.isEmpty()) {
            for (int i = 0; i < listsuffix.size(); i++) {
                listMap.putAll(JSON.parseObject(JSON.toJSONString(
                        listsuffix.get(i), new ListSuffixEndNameFilter(i)),
                        new TypeReference<Map<String, String>>() {
                        }));
            }
        }
        return listMap;
    }

    private static class ListSuffixEndNameFilter implements NameFilter {
        private final int index;

        public ListSuffixEndNameFilter(int index) {
            this.index = index;
        }

        @Override
        public String process(Object object, String name, Object value) {
            return String.format("%s_%d", name, index);
        }
    }

    /**
     * 序列化为xml
     *
     * @param object
     * @return xml
     */
    public static String serializeToXML(Object object) {
        JSONObject obj = serializeToJSON(object);
        StringWriter sw = new StringWriter();
        XMLStreamWriter xw = null;
        try {
            xw = XMLOutputFactory.newInstance().createXMLStreamWriter(sw);
            xw.writeStartDocument(Constants.UTF_8.name(), "1.0");
            xw.writeStartElement("xml");
            for (String key : obj.keySet()) {
                if (Strings.isNullOrEmpty(obj.getString(key))) {
                    continue;
                }
                xw.writeStartElement(key);
                xw.writeCData(obj.getString(key));
                xw.writeEndElement();
            }
            xw.writeEndElement();
            xw.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (xw != null) {
                try {
                    xw.close();
                } catch (XMLStreamException e) {

                }
            }
            try {
                sw.close();
            } catch (IOException e) {

            }
        }
        return sw.getBuffer().toString();
    }
}
