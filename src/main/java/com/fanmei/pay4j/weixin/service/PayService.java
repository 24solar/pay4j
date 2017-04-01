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
package com.fanmei.pay4j.weixin.service;

import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.exception.WeixinException;
import com.fanmei.pay4j.http.RequestExecutor;
import com.fanmei.pay4j.http.WeixinSSLRequestExecutor;
import com.fanmei.pay4j.http.response.FanmeiResponse;
import com.fanmei.pay4j.util.RandomUtil;
import com.fanmei.pay4j.weixin.config.WeixinConfig;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.domain.mch.Order;
import com.fanmei.pay4j.weixin.domain.mch.PrePay;
import com.fanmei.pay4j.weixin.domain.mch.RefundRecord;
import com.fanmei.pay4j.weixin.domain.mch.RefundRequestResult;
import com.fanmei.pay4j.weixin.param.*;
import com.fanmei.pay4j.xml.ListSuffixResultDeserializer;
import com.fanmei.pay4j.xml.XmlStream;

import javax.annotation.Nonnull;
import java.util.Map;

import static com.fanmei.pay4j.common.Constants.*;

/**
 * 支付API
 * @author rongtian
 * @author shice
 */
public class PayService extends AbstractService {

    public PayService(WeixinConfig weixinConfig, RequestExecutor requestExecutor, WeixinSSLRequestExecutor weixinSSLRequestExecutor) {
        super(weixinConfig, requestExecutor, weixinSSLRequestExecutor);
    }

    /**
     * 统一下单接口<br>
     * 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、JSAPI
     * 、APP等不同场景生成交易串调起支付。
     *
     * @param preOrderCreateParam 包含订单信息的对象
     * @return 预支付对象
     * @see PreOrderCreateParam
     * @see PrePay
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">统一下单接口
     * </a>
     */
    public PrePay createPrePay(PreOrderCreateParam preOrderCreateParam) throws WeixinException {
        declareMerchant(preOrderCreateParam);
        preOrderCreateParam.setSign(weixinSignature.sign(preOrderCreateParam));
        String xml = XmlStream.toXML(preOrderCreateParam);
        FanmeiResponse response = weixinExecutor.post(ORDER_CREATE_URI, xml);
        return response.getAsObject(PrePay.class);
    }

    /**
     * 订单查询
     * <p>
     * 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；<br> 调用支付接口后，返回系统错误或未知交易状态情况；<br>
     * 调用被扫支付API，返回USERPAYING的状态；<br> 调用关单或撤销接口API之前，需确认支付状态；
     * </P>
     *
     * @param queryParam 商户系统内部的订单号, transaction_id、out_trade_no 二 选一,如果同时存在优先级:
     *                   transaction_id &gt; out_trade_no
     * @return 订单信息
     * @throws WeixinException WeixinException
     * @see Order
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">
     * 订单查询API</a>
     * @since V3
     */
    public Order queryOrder(@Nonnull OrderParam queryParam) throws WeixinException {
        declareMerchant(queryParam);
        queryParam.setSign(weixinSignature.sign(queryParam));
        String param = XmlStream.toXML(queryParam);
        FanmeiResponse response = weixinExecutor.post(ORDER_QUERY_URI, param);
        return ListSuffixResultDeserializer.deserialize(response.getContent(),
                Order.class);
    }

    /**
     * 申请退款(请求需要双向证书)
     * <p>
     * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，
     * 按照退款规则将支付款按原路退到买家帐号上。
     * </p>
     * <p style="color:red">
     * 1.交易时间超过半年的订单无法提交退款；
     * 2.微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交
     * ，要采用原来的退款单号。总退款金额不能超过用户实际支付金额。
     * </p>
     *
     * @param refundCreateParam RefundCreateParam
     * @return 退款申请结果
     * @throws WeixinException WeixinException
     * @see RefundRequestResult
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">
     * 申请退款API</a>
     * @since V3
     */
    public RefundRequestResult applyRefund(@Nonnull RefundCreateParam refundCreateParam) throws WeixinException {
        declareMerchant(refundCreateParam);
        refundCreateParam.setSign(weixinSignature.sign(refundCreateParam));
        String param = XmlStream.toXML(refundCreateParam);
        FanmeiResponse response = weixinSslRequestExecutor.post(REFUND_APPLY_URI, param);
        return response.getAsObject(RefundRequestResult.class);
    }

    /**
     * 冲正订单(需要证书)<br> 当支付返回失败,或收银系统超时需要取消交易,可以调用该接口<br> 接口逻辑:支
     * 付失败的关单,支付成功的撤销支付<br> <span style="color:red">7天以内的单可撤销,其他正常支付的单
     * 如需实现相同功能请调用退款接口</span><br> <span style="color:red">
     * 调用扣款接口后请勿立即调用撤销,需要等待5秒以上。先调用查单接口,如果没有确切的返回,再调用撤销</span> <br>
     *
     * @param orderParam 商户系统内部的订单号, transaction_id 、 out_trade_no 二选一,如果同时存在优先级:
     *                   transaction_id &gt; out_trade_no
     * @return 撤销结果
     * @throws WeixinException WeixinException
     * @since V3
     */
    public CommonResult reverseOrder(@Nonnull OrderParam orderParam) throws WeixinException {
        declareMerchant(orderParam);
        orderParam.setSign(weixinSignature.sign(orderParam));
        String param = XmlStream.toXML(orderParam);
        FanmeiResponse response = weixinSslRequestExecutor.post(ORDER_REVERSE_URI, param);
        return response.getAsObject(CommonResult.class);
    }

    /**
     * native支付URL转短链接：用于扫码原生支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，减小二维码数据量
     * ，提升扫描速度和精确度。
     *
     * @param urlParam 具有native标识的支付URL
     * @return 转换后的短链接
     * @throws WeixinException WeixinException
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_9">
     * 转换短链接API</a>
     */
    public String getShortUrl(@Nonnull ShortUrlParam urlParam) throws WeixinException {
        declareMerchant(urlParam);
        urlParam.setSign(weixinSignature.sign(urlParam));
        String param = XmlStream.toXML(urlParam);
        FanmeiResponse response = weixinExecutor.post(LONGURL_CONVERT_URI, param);
        Map<String, String> map = XmlStream.xml2map(response.getContent());
        return map.get("short_url");
    }

    /**
     * 关闭订单
     * <p>
     * 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续
     * ，请调用关单接口,如果关单失败,返回已完 成支付请按正常支付处理。如果出现银行掉单,调用关单成功后,微信后台会主动发起退款。
     * </p>
     *
     * @param outTradeNo 商户系统内部的订单号
     * @return 处理结果
     * @throws WeixinException WeixinException
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3">
     * 关闭订单</a>
     */
    public CommonResult closeOrder(@Nonnull String outTradeNo) throws WeixinException {
        OrderParam orderParam = OrderParam.createWithOutTradeNo(outTradeNo);
        declareMerchant(orderParam);
        orderParam.setSign(weixinSignature.sign(orderParam));
        String param = XmlStream.toXML(orderParam);
        FanmeiResponse response = weixinExecutor.post(ORDER_CLOSE_URI, param);
        return response.getAsObject(CommonResult.class);
    }

    /**
     * 下载对账单<br>
     * 1.微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账 单中,跟原支付单订单号一致,bill_type 为
     * REVOKED;<br>
     * 2.微信在次日 9 点启动生成前一天的对账单,建议商户 9 点半后再获取;<br>
     * 3.对账单中涉及金额的字段单位为“元”。<br>
     *
     * @param billParam BillParam
     * @return FanmeiResponse
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">
     * 下载对账单</a>
     */
    public FanmeiResponse fetchBill(@Nonnull BillParam billParam)
            throws WeixinException {
        declareMerchant(billParam);
        billParam.setSign(weixinSignature.sign(billParam));
        String param = XmlStream.toXML(billParam);
        FanmeiResponse response = weixinExecutor.post(DOWNLOADBILL_URI, param);
        return response;
    }

    /**
     * 退款查询
     * <p>
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     * </p>
     *
     * @param queryParam 单号 refund_id、out_refund_no、out_trade_no、transaction_id
     *                   四个参数必填一个,优先级为:
     *                   refund_id &gt; out_refund_no &gt; transaction_id &gt; out_trade_no
     * @return 退款记录
     * @throws WeixinException WeixinException
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">
     * 退款查询API</a>
     */
    public RefundRecord queryRefund(RefundQueryParam queryParam) throws WeixinException {
        declareMerchant(queryParam);
        queryParam.setSign(weixinSignature.sign(queryParam));
        String param = XmlStream.toXML(queryParam);
        FanmeiResponse response = weixinExecutor.post(REFUND_QUERY_URI, param);
        return ListSuffixResultDeserializer.deserialize(response.getContent(),
                RefundRecord.class);
    }

    /*
     * 对回调对象进行签名验证
     */
    public <T extends CommonResult> boolean isValidSign(@Nonnull T result) {
        return weixinSignature.isValidSign(result, result.getSign());
    }

    /*
     * 生成客户端支付参数
     */
    public OrderPayParam genOrderPayParam(@Nonnull String prePayId) throws WeixinException {
        OrderPayParam orderPayParam = OrderPayParam.of(prePayId);
        orderPayParam.setPartnerId(weixinAccount.getMchId());
        orderPayParam.setAppId(weixinAccount.getId());
        orderPayParam.setPkg(Constants.PACKAGE);
        orderPayParam.setNonceStr(RandomUtil.generateString(16));
        orderPayParam.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
        try {
            orderPayParam.setSign(weixinSignature.sign(orderPayParam));
        } catch (Exception e) {
            throw WeixinException.of(e);
        }
        return orderPayParam;
    }
}
