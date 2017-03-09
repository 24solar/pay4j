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
package com.fanmei.pay4j.alipay.domain.sub;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by rongtian on 16/8/19.
 */
public class AccountQueryAccountLogVO {

    /**
     * 余额
     */
    @XmlElement(name = "balance")
    private String balance;
    /**
     * 收入金额
     */
    @XmlElement(name = "income")
    private String income;
    /**
     * 支出金额
     */
    @XmlElement(name = "outcome")
    private String outcome;
    /**
     * 交易付款时间 2010-09-25 17:27:30
     */
    @XmlElement(name = "trans_date")
    private String transDate;
    /**
     * 子业务类型
     */
    @XmlElement(name = "sub_trans_code_msg")
    private String subTransCodeMsg;
    /**
     * 业务类型
     */
    @XmlElement(name = "trans_code_msg")
    private String transCodeMsg;
    /**
     * 商户订单号
     */
    @XmlElement(name = "merchant_out_order_no")
    private String outOrderNo;
    /**
     * 订单号
     */
    @XmlElement(name = "trade_no")
    private String tradeNo;
    /**
     * 买家支付宝账号
     */
    @XmlElement(name = "buyer_account")
    private String buyerAccount;
    /**
     * 货币代码
     */
    @XmlElement(name = "currency")
    private String currency;
    /**
     * 充值网银流水号
     */
    @XmlElement(name = "deposit_bank_no")
    private String depositBankNo;
    /**
     * 商品标题
     */
    @XmlElement(name = "goods_title")
    private String title;
    /**
     * 财务流水号
     */
    @XmlElement(name = "iw_account_log_id")
    private String iwAccountLogId;
    /**
     * 备注
     */
    @XmlElement(name = "memo")
    private String memo;
    /**
     * 合作者身份ID
     */
    @XmlElement(name = "partner_id")
    private String partnerId;
    /**
     * 费率 0.006
     */
    @XmlElement(name = "rate")
    private String rate;
    /**
     * 卖家姓名
     */
    @XmlElement(name = "seller_fullname")
    private String sellerFullname;
    /**
     * 交易服务费
     */
    @XmlElement(name = "service_fee")
    private String serviceFee;
    /**
     * 交易服务费率
     */
    @XmlElement(name = "service_fee_ratio")
    private String serviceFeeRatio;
    /**
     * 签约产品
     */
    @XmlElement(name = "sign_product_name")
    private String signProductName;
    /**
     * 交易总金额
     */
    @XmlElement(name = "total_fee")
    private String totalFee;
    /**
     * 累计退款金额
     */
    @XmlElement(name = "trade_refund_amount")
    private String tradeRefundAmount;
    /**
     * 银行名称
     */
    @XmlElement(name = "bank_name")
    private String bankName;
    /**
     * 银行账号
     */
    @XmlElement(name = "bank_account_no")
    private String bankAccountNo;
    /**
     * 银行账户名字
     */
    @XmlElement(name = "bank_account_name")
    private String bankAccountName;
    /**
     * 卖家支付宝人民币资金账号
     */
    @XmlElement(name = "seller_account")
    private String sellerAccount;
    /**
     * 财务本方支付宝人民币资金账号
     */
    @XmlElement(name = "trans_account")
    private String transAccount;
    /**
     * 财务对方邮箱
     */
    @XmlElement(name = "other_account_email")
    private String otherAccountEmail;
    /**
     * 财务对方全称
     */
    @XmlElement(name = "other_account_fullname")
    private String otherAccountFullname;
    /**
     * 财务对方支付宝用户号
     */
    @XmlElement(name = "other_user_id")
    private String otherUserId;
    /**
     * 余额
     */
    @XmlElement(name = "ext_info")
    private String extInfo;

    @XmlTransient
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @XmlTransient
    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    @XmlTransient
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    @XmlTransient
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @XmlTransient
    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    @XmlTransient
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlTransient
    public String getDepositBankNo() {
        return depositBankNo;
    }

    public void setDepositBankNo(String depositBankNo) {
        this.depositBankNo = depositBankNo;
    }

    @XmlTransient
    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    @XmlTransient
    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    @XmlTransient
    public String getIwAccountLogId() {
        return iwAccountLogId;
    }

    public void setIwAccountLogId(String iwAccountLogId) {
        this.iwAccountLogId = iwAccountLogId;
    }

    @XmlTransient
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @XmlTransient
    public String getOtherAccountEmail() {
        return otherAccountEmail;
    }

    public void setOtherAccountEmail(String otherAccountEmail) {
        this.otherAccountEmail = otherAccountEmail;
    }

    @XmlTransient
    public String getOtherAccountFullname() {
        return otherAccountFullname;
    }

    public void setOtherAccountFullname(String otherAccountFullname) {
        this.otherAccountFullname = otherAccountFullname;
    }

    @XmlTransient
    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    @XmlTransient
    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @XmlTransient
    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    @XmlTransient
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @XmlTransient
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @XmlTransient
    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    @XmlTransient
    public String getSellerFullname() {
        return sellerFullname;
    }

    public void setSellerFullname(String sellerFullname) {
        this.sellerFullname = sellerFullname;
    }

    @XmlTransient
    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    @XmlTransient
    public String getServiceFeeRatio() {
        return serviceFeeRatio;
    }

    public void setServiceFeeRatio(String serviceFeeRatio) {
        this.serviceFeeRatio = serviceFeeRatio;
    }

    @XmlTransient
    public String getSignProductName() {
        return signProductName;
    }

    public void setSignProductName(String signProductName) {
        this.signProductName = signProductName;
    }

    @XmlTransient
    public String getSubTransCodeMsg() {
        return subTransCodeMsg;
    }

    public void setSubTransCodeMsg(String subTransCodeMsg) {
        this.subTransCodeMsg = subTransCodeMsg;
    }

    @XmlTransient
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    @XmlTransient
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @XmlTransient
    public String getTradeRefundAmount() {
        return tradeRefundAmount;
    }

    public void setTradeRefundAmount(String tradeRefundAmount) {
        this.tradeRefundAmount = tradeRefundAmount;
    }

    @XmlTransient
    public String getTransAccount() {
        return transAccount;
    }

    public void setTransAccount(String transAccount) {
        this.transAccount = transAccount;
    }

    @XmlTransient
    public String getTransCodeMsg() {
        return transCodeMsg;
    }

    public void setTransCodeMsg(String transCodeMsg) {
        this.transCodeMsg = transCodeMsg;
    }

    @XmlTransient
    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
}
