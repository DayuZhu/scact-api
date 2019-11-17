package com.sc.act.api.commons.web.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.act.api.commons.web.constant.Constant;

/**
 * @ClassName MD5Util
 * @Description MD5工具类
 */
public class Md5Util {
    private static final Logger LOG = LoggerFactory.getLogger(Md5Util.class);

    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     *
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5编码
     *
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String getMD5String(String origin) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("MD5Util中加密的原串 {}", origin);
        }
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance(Constant.MD5);
            resultString = byteArrayToHexString(md.digest(resultString.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            LOG.error("MD5异常：", e);
        }
        return resultString;
    }

}// class