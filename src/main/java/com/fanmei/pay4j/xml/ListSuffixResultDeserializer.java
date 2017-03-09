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

import com.fanmei.pay4j.util.ReflectionUtil;
import com.fanmei.pay4j.util.StringUtil;
import com.fanmei.pay4j.common.Constants;
import com.google.common.base.Strings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对 后缀为_$n 的 xml节点反序列化
 */
public class ListSuffixResultDeserializer {

    private static Pattern DEFAULT_PATTERN;

    static {
        String regex = null;
        try {
            Object value = ListSuffixResult.class.getMethod("value")
                    .getDefaultValue();
            if (value instanceof String) {
                regex = (String) value;
            } else if (value instanceof String[]) {
                regex = ((String[]) value)[0];
            }
        } catch (NoSuchMethodException e) {

        }
        if (Strings.isNullOrEmpty(regex)) {
            regex = "(_\\d)$";
        }
        DEFAULT_PATTERN = Pattern.compile(regex);
    }

    /**
     * 对包含$n节点的xml反序列化
     *
     * @param content xml内容
     * @param clazz
     * @return
     */
    public static <T> T deserialize(String content, Class<T> clazz) {
        T t = XmlStream.fromXML(content, clazz);
        Map<Field, String[]> listSuffixFields = getListSuffixFields(clazz);
        if (!listSuffixFields.isEmpty()) {
            for (Entry<Field, String[]> entry : listSuffixFields.entrySet()) {
                Field field = entry.getKey();
                Type type = field.getGenericType();
                Class<?> wrapperClazz = null;
                if (type instanceof ParameterizedType) {
                    wrapperClazz = (Class<?>) ((ParameterizedType) type)
                            .getActualTypeArguments()[0];
                } else {
                    continue;
                }
                ListWrapper<?> listWrapper = deserializeToListWrapper(content,
                        wrapperClazz, entry.getValue());
                if (listWrapper != null) {
                    try {
                        field.setAccessible(true);
                        field.set(t, listWrapper.getItems());
                    } catch (Exception e) {

                    }
                }
            }
        }
        return t;
    }

    public static <T> ListWrapper<T> deserializeToListWrapper(String content,
                                                              Class<T> clazz, String... matchPattern) {
        XMLStreamReader xr = null;
        XMLStreamWriter xw = null;
        try {
            xr = XMLInputFactory.newInstance().createXMLStreamReader(
                    new StringReader(content));
            List<Pattern> patterns = new ArrayList<Pattern>();
            for (String pattern : matchPattern) {
                patterns.add(Pattern.compile(pattern));
            }
            Matcher matcher = null;
            Map<String, Map<String, String>> outMap = new HashMap<String, Map<String, String>>();
            while (true) {
                int event = xr.next();
                if (event == XMLStreamConstants.END_DOCUMENT) {
                    break;
                } else if (event == XMLStreamConstants.START_ELEMENT) {
                    String name = xr.getLocalName();
                    for (Pattern pattern : patterns) {
                        if ((matcher = pattern.matcher(name)).find()) {
                            while (true) {
                                event = xr.next();
                                if (event == XMLStreamConstants.START_ELEMENT) {
                                    name = xr.getLocalName();
                                } else if (event == XMLStreamConstants.END_ELEMENT) {
                                    break;
                                } else if (event == XMLStreamConstants.CHARACTERS || event == XMLStreamConstants.CDATA) {
                                    String key = matcher.group();
                                    if (!pattern.pattern().equals(
                                            DEFAULT_PATTERN.pattern())) {
                                        matcher = DEFAULT_PATTERN.matcher(name);
                                        matcher.find();
                                        key = matcher.group();
                                    }
                                    Map<String, String> innerMap = null;
                                    if ((innerMap = outMap.get(key)) == null) {
                                        innerMap = new HashMap<String, String>();
                                        outMap.put(key, innerMap);
                                    }
                                    innerMap.put(name.replace(key, ""),
                                            xr.getText());
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if (!outMap.isEmpty()) {
                StringWriter sw = new StringWriter();
                xw = XMLOutputFactory.newInstance().createXMLStreamWriter(sw);
                xw.writeStartDocument(Constants.UTF_8.name(), "1.0");
                xw.writeStartElement(clazz.getCanonicalName());
                String itemName = StringUtil
                        .unCapitalize(clazz.getSimpleName());
                XmlRootElement rootElement = clazz
                        .getAnnotation(XmlRootElement.class);
                if (rootElement != null && Strings.isNullOrEmpty(rootElement.name())) {
                    try {
                        if (!rootElement.name().equals(XmlRootElement.class.getMethod("name")
                                        .getDefaultValue().toString())) {
                            itemName = rootElement.name();
                        }
                    } catch (NoSuchMethodException e) {

                    }
                }
                for (Entry<String, Map<String, String>> outE : outMap
                        .entrySet()) {
                    xw.writeStartElement(itemName);
                    for (Entry<String, String> innerE : outE
                            .getValue().entrySet()) {
                        xw.writeStartElement(innerE.getKey());
                        xw.writeCharacters(innerE.getValue());
                        xw.writeEndElement();
                    }
                    xw.writeEndElement();
                }
                xw.writeEndElement();
                xw.writeEndDocument();
                JAXBContext ctx = JAXBContext.newInstance(ListWrapper.class,
                        clazz);
                Unmarshaller u = ctx.createUnmarshaller();
                return u.unmarshal(
                        new StreamSource(new StringReader(sw.getBuffer()
                                .toString())), ListWrapper.class).getValue();
            }
            return null;
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (xw != null) {
                    xw.close();
                }
                if (xr != null) {
                    xr.close();
                }
            } catch (XMLStreamException e) {

            }
        }
    }

    public static Map<Field, String[]> getListSuffixFields(Class<?> clazz) {
        Map<Field, String[]> listsuffixFields = new HashMap<Field, String[]>();
        Set<Field> allFields = ReflectionUtil.getAllField(clazz);
        ListSuffixResult listSuffixResult = null;
        for (Field field : allFields) {
            listSuffixResult = field.getAnnotation(ListSuffixResult.class);
            if (listSuffixResult != null) {
                listsuffixFields.put(field, listSuffixResult.value());
            }
        }
        return listsuffixFields;
    }
}
