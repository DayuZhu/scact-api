package com.sc.act.api.commons.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtil
 * @Description String操作类
 */
public class StringUtil {
    private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

    /**
     * @param strings
     * @return
     * @Title stringAssembly
     * @Description 字符串拼接
     */
    public static String stringAssembly(String... strings) {
        StringBuffer sb = new StringBuffer();
        for (String str : strings) {
            sb.append(str);
        }
        String retStr = sb.toString();
        if (LOG.isDebugEnabled()) {
            LOG.debug("String拼接 {}", retStr);
        }
        return retStr;
    }

    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    public static boolean isTelNum(String str) {
        if (str == null) {
            return false;
        } else {
            String regEx = "^1[0-9]{10}$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }

}
