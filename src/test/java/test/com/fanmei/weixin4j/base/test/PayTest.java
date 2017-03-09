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
package test.com.fanmei.weixin4j.base.test;

import com.alibaba.fastjson.JSON;
import com.fanmei.pay4j.api.WeixinPayProxy;
import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.exception.WeixinException;
import com.fanmei.pay4j.http.DefaultRequestExecutor;
import com.fanmei.pay4j.http.WeixinSSLRequestExecutor;
import com.fanmei.pay4j.http.response.FanmeiResponse;
import com.fanmei.pay4j.sign.Signature;
import com.fanmei.pay4j.weixin.config.WeixinConfig;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.domain.mch.Order;
import com.fanmei.pay4j.weixin.domain.mch.RefundRecord;
import com.fanmei.pay4j.weixin.domain.mch.RefundRequestResult;
import com.fanmei.pay4j.weixin.domain.mch.WeixinStatementDetail;
import com.fanmei.pay4j.weixin.model.WeixinPayAccount;
import com.fanmei.pay4j.weixin.param.OrderParam;
import com.fanmei.pay4j.weixin.param.RefundCreateParam;
import com.fanmei.pay4j.weixin.param.RefundQueryParam;
import com.fanmei.pay4j.weixin.sign.WeixinPaymentSignature;
import com.fanmei.pay4j.weixin.type.BillType;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * 支付测试（商户平台）
 */
public class PayTest {

    private final String head = "交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,企业红包金额,微信退款单号,商户退款单号,退款金额,企业红包退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率";

    private final String total = "总交易单数,总交易额,总退款金额,总企业红包退款金额,手续费总金额";
    protected final static WeixinPayAccount ACCOUNT;
    protected final static Signature SIGNATURE;
    protected final static WeixinPayProxy PAY;

    public static final String APP_ID = "";

    public static final String API_SIGN_KEY = "";

    public static final String PARTNER_ID = "";

    static {
        ACCOUNT = new WeixinPayAccount(APP_ID, API_SIGN_KEY, PARTNER_ID);
        SIGNATURE = new WeixinPaymentSignature(ACCOUNT.getPaySignKey());
        WeixinConfig weixinConfig = new WeixinConfig(ACCOUNT, "config/apiclient_cert.p12");
        PAY = new WeixinPayProxy(weixinConfig,new DefaultRequestExecutor(),new WeixinSSLRequestExecutor(weixinConfig));
    }

    @Test
    public void queryOrder() throws WeixinException {
        Order order = PAY.queryOrder(OrderParam.createWithOutTradeNo("2016070138348075825"));
        System.err.println(order);
        String sign = order.getSign();
        order.setSign(null);
        String valiSign = SIGNATURE.sign(order, false);
        System.err.println(String.format("sign=%s,valiSign=%s", sign, valiSign));
        Assert.assertEquals(valiSign, sign);
    }

    @Test
    public void queryRefund() throws WeixinException {
        RefundRecord record = PAY.queryRefund(RefundQueryParam.createWithOutTradeNo("2016070138348075825"));
        System.err.println(record);
        // 这里的验证签名需要把details循环拼接
        String sign = record.getSign();
        record.setSign(null);
        System.out.println("record=" + JSON.toJSONString(record));
        String valiSign = SIGNATURE.sign(record, false);
        System.err.println(String.format("sign=%s,valiSign=%s", sign, valiSign));
        Assert.assertEquals(valiSign, sign);
    }


    @Test
    public void downBill() throws WeixinException {
        String fileName = String.format("weixinStatement_%s.txt", 20160919);
        File file = new File(String.format("%s%s%s", "/Users/rongtian/download", File.separator, fileName));
        if (file.exists()) {
            return;
        }

        FanmeiResponse response = PAY.fetchBill(String.valueOf(20160919), BillType.ALL);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), Constants.UTF_8));
            reader = new BufferedReader(new InputStreamReader(
                    response.getBody(), Constants.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ignore) {

            }
        }
    }

    @Test
    public void readBill() throws Exception {
        String fileName = String.format("weixinStatement_%s.txt", 20160919);
        File file = new File(String.format("%s%s%s", "/Users/rongtian/download", File.separator, fileName));

        int pageSize = 500;
        long pos = 0;
        RandomAccessFile reader = null;
        try {
            reader = new RandomAccessFile(file, "r");
            while (true) {
                List<String> list = Lists.newArrayList();
                reader.seek(pos);
                for (int index = 0; index < pageSize; index++) {
                    String line = reader.readLine();
                    if (Strings.isNullOrEmpty(line)) {
                        break;
                    }
                    list.add(new String(line.getBytes("8859_1"), Constants.UTF_8));
                }

                if (list != null && !list.isEmpty()) {
                    for (String line : list) {
                        if (line.contains(head)) {
                            continue;
                        }
                        if (line.contains("return_code")) {
                            break;
                        }
                        if (line.contains(total)) {
                            break;
                        }
                        List<String> records = Splitter.on(",").splitToList(line);
                        WeixinStatementDetail detail = WeixinStatementDetail.of(records);
                        System.out.println(detail.fetchUniqueId());
                    }
                }

                pos = reader.getFilePointer();
                if (list.size() < pageSize) {
                    break;
                }
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }

    }

    @Test
    public void refund() throws WeixinException, IOException {
        RefundRequestResult result = PAY.applyRefund(RefundCreateParam.witchRechargeFunds("72057594040603643",
                "test"+1, 1, 1, "aa"));
        System.err.println(result);
        String sign = result.getSign();
        result.setSign(null);
        String valiSign = SIGNATURE.sign(result);
        System.err
                .println(String.format("sign=%s,valiSign=%s", sign, valiSign));
        Assert.assertEquals(valiSign, sign);
    }

    @Test
    public void closeOrder() throws WeixinException {
        CommonResult result = PAY.closeOrder("D111");
        System.err.println(result);
        String sign = result.getSign();
        result.setSign(null);
        String valiSign = SIGNATURE.sign(result);
        System.err
                .println(String.format("sign=%s,valiSign=%s", sign, valiSign));
        Assert.assertEquals(valiSign, sign);
    }

    @Test
    public void shortUrl() throws WeixinException {
        String url = "weixin://wxpay/bizpayurl?xxxxxx";
        String shortUrl = PAY.getPayShortUrl(url);
        System.err.println(shortUrl);
    }

}
