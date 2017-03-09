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
package com.fanmei.pay4j.http;

import com.fanmei.pay4j.exception.WeixinException;
import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.weixin.config.WeixinConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * Created by rongtian on 16/7/4.
 */
public class WeixinSSLRequestExecutor extends DefaultRequestExecutor {

    public WeixinSSLRequestExecutor(WeixinConfig weixinConfig)
            throws WeixinException {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(weixinConfig.getCertificateFile());
        try {
            String password = weixinConfig.getAccount().getCertificateKey();
            KeyStore keyStore = KeyStore.getInstance(Constants.PKCS12);
            keyStore.load(inputStream, password.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory
                    .getInstance(Constants.SunX509);
            kmf.init(keyStore, password.toCharArray());
            SSLContext sslContext = SSLContext.getInstance(Constants.TLS);
            sslContext.init(kmf.getKeyManagers(), null,
                    new java.security.SecureRandom());

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            throw WeixinException.of("Key load error", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
