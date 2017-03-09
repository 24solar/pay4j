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
package com.fanmei.pay4j.api;

import com.fanmei.pay4j.exception.WeixinException;
import com.fanmei.pay4j.http.RequestExecutor;
import com.fanmei.pay4j.http.WeixinSSLRequestExecutor;
import com.fanmei.pay4j.http.response.FanmeiResponse;
import com.fanmei.pay4j.weixin.config.WeixinConfig;
import com.fanmei.pay4j.weixin.domain.CommonResult;
import com.fanmei.pay4j.weixin.domain.mch.Order;
import com.fanmei.pay4j.weixin.domain.mch.PrePay;
import com.fanmei.pay4j.weixin.domain.mch.RefundRecord;
import com.fanmei.pay4j.weixin.domain.mch.RefundRequestResult;
import com.fanmei.pay4j.weixin.param.*;
import com.fanmei.pay4j.weixin.service.PayService;
import com.fanmei.pay4j.weixin.type.BillType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * 微信支付接口实现
 *
 * @see <a href="http://pay.weixin.qq.com/wiki/doc/api/index.html">商户平台支付API</a>
 *
 * @author rongtian
 * @author shice
 */
public class WeixinPayProxy {

    /**
     * 微信支付:js支付、扫码支付等接口
     */
    private final PayService payService;

    public WeixinPayProxy(@Nonnull WeixinConfig weixinConfig, @Nonnull RequestExecutor requestExecutor, @Nonnull WeixinSSLRequestExecutor weixinSSLRequestExecutor) {
        this.payService = new PayService(weixinConfig, requestExecutor, weixinSSLRequestExecutor);
    }

    /**
     * 统一下单接口</br>
     * 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、JSAPI
     * 、APP等不同场景生成交易串调起支付。
     *
     * @param preOrderCreateParam 包含订单信息的对象
     * @return 预支付对象
     * @see PayService
     * @see PreOrderCreateParam
     * @see PrePay
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">统一下单接口
     * </a>
     */
    public PrePay createPrePay(PreOrderCreateParam preOrderCreateParam) throws WeixinException {
        return payService.createPrePay(preOrderCreateParam);
    }

    /**
     * 订单查询
     * <p>
     * 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；</br> 调用支付接口后，返回系统错误或未知交易状态情况；</br>
     * 调用被扫支付API，返回USERPAYING的状态；</br> 调用关单或撤销接口API之前，需确认支付状态；
     * </P>
     *
     * @param orderParam 商户系统内部的订单号, transaction_id、out_trade_no 二 选一,如果同时存在优先级:
     *                   transaction_id> out_trade_no
     * @return 订单详情
     * @throws WeixinException
     * @see Order
     * @see PayService
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2">
     * 订单查询API</a>
     * @since V3
     */
    public Order queryOrder(OrderParam orderParam) throws WeixinException {
        return payService.queryOrder(orderParam);
    }

    /**
     * 申请退款(请求需要双向证书)</br>
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
     * @return 退款申请结果
     * @throws WeixinException
     * @see RefundRequestResult
     * @see PayService
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">
     * 申请退款API</a>
     * @since V3
     */
    public RefundRequestResult applyRefund(RefundCreateParam refundCreateParam) throws WeixinException {
        return payService.applyRefund(refundCreateParam);
    }

    /**
     * 退款查询
     * <p>
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
     * </p>
     *
     * @param queryParam 单号 refund_id、out_refund_no、 out_trade_no 、 transaction_id
     *                   四个参数必填一个,优先级为:
     *                   refund_id>out_refund_no>transaction_id>out_trade_no
     * @return 退款记录
     * @throws WeixinException
     * @see PayService
     * @see RefundRecord
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5">
     * 退款查询API</a>
     * @since V3
     */
    public RefundRecord queryRefund(RefundQueryParam queryParam) throws WeixinException {
        return payService.queryRefund(queryParam);
    }

    /**
     * 获取对账单<br>
     * 1.微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账 单中,跟原支付单订单号一致,bill_type 为
     * REVOKED;<br>
     * 2.微信在次日 9 点启动生成前一天的对账单,建议商户 9 点半后再获取;<br>
     * 3.对账单中涉及金额的字段单位为“元”。<br>
     *
     * @param billDate 下载对账单的日期
     * @param billType 下载对账单的类型 ALL,返回当日所有订单信息, 默认值 SUCCESS_UPPER,返回当日成功支付的订单
     *                 REFUND,返回当日退款订单
     * @see PayService
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">
     * 下载对账单API</a>
     */
    public FanmeiResponse fetchBill(@Nonnull Date billDate, @Nullable BillType billType)
            throws WeixinException {
        return payService.fetchBill(BillParam.of(billDate, billType));
    }

    /**
     * 获取对账单<br>
     *
     * @param billDate 下载对账单的日期(格式:20140603)
     * @param billType 下载对账单的类型 ALL,返回当日所有订单信息, 默认值 SUCCESS_UPPER,返回当日成功支付的订单
     *                 REFUND,返回当日退款订单
     * @see PayService
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">
     * 下载对账单API</a>
     */
    public FanmeiResponse fetchBill(@Nonnull String billDate, @Nullable BillType billType)
            throws WeixinException {
        return payService.fetchBill(BillParam.of(billDate, billType));
    }

    /**
     * 冲正订单(需要证书)</br> 当支付返回失败,或收银系统超时需要取消交易,可以调用该接口</br> 接口逻辑:支
     * 付失败的关单,支付成功的撤销支付</br> <font color="red">7天以内的单可撤销,其他正常支付的单
     * 如需实现相同功能请调用退款接口</font></br> <font
     * color="red">调用扣款接口后请勿立即调用撤销,需要等待5秒以上。先调用查单接口,如果没有确切的返回,再调用撤销</font> </br>
     *
     * @param orderParam 商户系统内部的订单号, transaction_id 、 out_trade_no 二选一,如果同时存在优先级:
     *                   transaction_id> out_trade_no
     * @return 撤销结果
     * @see PayService
     */
    public CommonResult reverseOrder(OrderParam orderParam) throws WeixinException {
        return payService.reverseOrder(orderParam);
    }

    /**
     * 关闭订单
     * <p>
     * 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续
     * ，请调用关单接口,如果关单失败,返回已完 成支付请按正常支付处理。如果出现银行掉单,调用关单成功后,微信后台会主动发起退款。
     * </p>
     *
     * @param outTradeNo 商户系统内部的订单号
     * @return 执行结果
     * @throws WeixinException
     * @see PayService
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3">
     * 关闭订单API</a>
     * @since V3
     */
    public CommonResult closeOrder(String outTradeNo) throws WeixinException {
        return payService.closeOrder(outTradeNo);
    }

    /**
     * native支付URL转短链接:用于扫码原生支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，减小二维码数据量
     * ，提升扫描速度和精确度。
     *
     * @param url 具有native标识的支付URL
     * @return 转换后的短链接
     * @throws WeixinException
     * @see PayService
     * @see <a href=
     * "http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_9">
     * 转换短链接API</a>
     */
    public String getPayShortUrl(@Nonnull String url) throws WeixinException {
        ShortUrlParam urlParam = ShortUrlParam.create(url);
        return payService.getShortUrl(urlParam);
    }

    /**
     * 对回调对象进行签名验证
     *
     * @param result
     * @param <T>
     * @return
     */
    public <T extends CommonResult> boolean isValidSign(@Nonnull T result) {
        return payService.isValidSign(result);
    }

    /**
     * 生成客户端支付参数
     */
    public OrderPayParam genOrderPayParam(@Nonnull String prePayId) throws WeixinException {
        return payService.genOrderPayParam(prePayId);
    }

}
