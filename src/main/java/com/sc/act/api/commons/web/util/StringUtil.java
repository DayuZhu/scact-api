package com.sc.act.api.commons.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName StringUtil
 * @Description String操作类
 */
public class StringUtil {
	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * @Title stringAssembly
	 * @Description 字符串拼接
	 * @param strings
	 * @return
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

}// class
