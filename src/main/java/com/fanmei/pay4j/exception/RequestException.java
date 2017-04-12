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
package com.fanmei.pay4j.exception;

import com.fanmei.pay4j.util.WeixinErrorUtil;
import com.google.common.base.Strings;

public class RequestException extends RuntimeException {

    private static final long serialVersionUID = 7148145661883468514L;

    private String code;
    private String desc;

    protected RequestException(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    protected RequestException(String desc) {
        this.code = "-1";
        this.desc = desc;
    }

    public static RequestException of(String code, String desc) {
        return new RequestException(code, desc);
    }

    public static RequestException of(int code, String desc) {
        return new RequestException(String.valueOf(code), desc);
    }

    public static RequestException of(String desc) {
        return new RequestException(desc);
    }

    protected RequestException(Throwable e) {
        super(e);
    }

    public static RequestException of(Throwable e) {
        return new RequestException(e);
    }

    protected RequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RequestException of(String message, Throwable cause) {
        return new RequestException(message, cause);
    }

    public String getErrorCode() {
        return code;
    }

    public String getErrorDesc() {
        return desc;
    }

    public String getErrorText() {
        return WeixinErrorUtil.getText(code);
    }

    @Override
    public String getMessage() {
        if (!Strings.isNullOrEmpty(code)) {
            StringBuilder buf = new StringBuilder();
            buf.append(code).append(" >> ").append(desc);
            String text = getErrorText();
            if (!Strings.isNullOrEmpty(text)) {
                buf.append(" >> ").append(text);
            }
            return buf.toString();
        } else {
            return super.getMessage();
        }
    }
}
