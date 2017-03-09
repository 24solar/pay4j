package com.fanmei.pay4j.alipay.sign;

import com.fanmei.pay4j.alipay.config.AlipayConfig;
import com.fanmei.pay4j.common.Constants;
import com.fanmei.pay4j.common.SignType;
import com.fanmei.pay4j.sign.AbstractSignature;
import com.fanmei.pay4j.util.DigestUtil;
import com.fanmei.pay4j.util.StringUtil;

import javax.annotation.Nonnull;
import java.nio.charset.Charset;

/**
 * 支付宝签名
 * Created by rongtian on 16/7/7.
 */
public class AlipaySignature extends AbstractSignature {

    private AlipayConfig alipayConfig;
    private final SignType signType;

    public AlipaySignature(AlipayConfig alipayConfig) {
        this.alipayConfig = alipayConfig;
        this.signType = alipayConfig.getSignType();
    }

    @Override
    public String sign(Object obj, Charset charset, boolean encode) {
        StringBuilder sb = join(obj, charset, encode);
        return sign(sb.toString(), charset);
    }

    /**
     * alipay专用
     * @param obj
     * @param charset
     * @param encode
     * @return
     */
    public String signWithQuota(Object obj, Charset charset, boolean encode) {
        StringBuilder sb = join(obj, charset, encode, true);
        return sign(sb.toString(), charset);
    }

    @Override
    public boolean isValidSign(Object obj, String sign) {
        StringBuilder str = join(obj, Constants.UTF_8, false);
        if (signType.isMd5()) {
            return sign.equalsIgnoreCase(DigestUtil.MD5(str.toString()).toUpperCase());
        } else if (signType.isRsa()) {
            return RSA.verify(str.toString(), sign, alipayConfig.getPublicKey(), Constants.UTF_8.name());
        }
        return false;
    }


    private StringBuilder join(Object obj, Charset charset, boolean encode, boolean withQuota) {
        return new StringBuilder(StringUtil.toJoinForSign(obj, charset, encode, withQuota));
    }


    private String sign(@Nonnull String str, Charset charset) {
        if (signType.isMd5()) {
            return DigestUtil.MD5(str).toUpperCase();
        } else if (signType.isRsa()) {
            return RSA.sign(str, alipayConfig.getPrivateKey(), charset.name());
        }
        return null;
    }
}
