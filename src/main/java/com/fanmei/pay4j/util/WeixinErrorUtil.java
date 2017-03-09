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

import com.fanmei.pay4j.weixin.domain.XmlResult;
import com.google.common.base.Strings;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口调用错误获取
 */
public final class WeixinErrorUtil {
    private final static Map<String, String> errorCacheMap;

    static {
        errorCacheMap = new ConcurrentHashMap<String, String>();
        ErrorTextHandler textHandler = new ErrorTextHandler();
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(textHandler);
            xmlReader.parse(new InputSource(XmlResult.class
                    .getResourceAsStream("error.xml")));
            errorCacheMap.putAll(textHandler.fetchErrorMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
        }
    }

    private static class ErrorTextHandler extends DefaultHandler {

        private Map<String, String> map = null;
        private NameValuePair pair = null;
        private boolean codeElement = false;
        private boolean textElement = false;

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("error")) {
                if (null == map) {
                    map = new HashMap<String, String>();
                }
                pair = new NameValuePair();
            } else if (qName.equalsIgnoreCase("code")) {
                codeElement = true;
            } else if (qName.equalsIgnoreCase("text")) {
                textElement = true;

            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if (qName.equalsIgnoreCase("error")) {
                map.put(pair.getName(), pair.getValue());
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            if (codeElement) {
                pair.setName(new String(ch, start, length));
                codeElement = false;
            } else if (textElement) {
                pair.setValue(new String(ch, start, length));
                textElement = false;
            }
        }

        public Map<String, String> fetchErrorMap() {
            return map;
        }

    }

    private static class NameValuePair {
        String name;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static String getText(String code) throws RuntimeException {
        if (Strings.isNullOrEmpty(code)) {
            return null;
        }
        return errorCacheMap.get(code);
    }

    public static void main(String[] args) {
        System.out.println(getText("40001"));
        System.out.println(getText("40001"));
        System.out.println(getText("0"));
    }
}
