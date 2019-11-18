package com.sc.act.api.commons.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sc.act.api.commons.web.constant.CommonConstant;

/**
 * @ClassName SignUtil
 * @Description 签名工具类
 */
public class SignUtil {

	private static final Logger LOG = LoggerFactory.getLogger(SignUtil.class);

	public static final String _SIGN_TYPE = "sign_type";
	public static final String _SIGN_INFO = "sign_info";
	public static final String _CHARSET_NAME = "UTF-8";

	public static String signMd5(Map<String, ?> objMap, String key) {
		String hexSign = "";
		Map<String, ?> filterMap = SignUtil.paraFilter(objMap);
		// 得到带签名数据
		String linkStr = SignUtil.createLinkString(filterMap);
		String md5Key = key;
		hexSign = md5Sign(linkStr, md5Key);

		return hexSign;
	}

	/**
	 * 除去数组中的空值和签名参数
	 *
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, ?> paraFilter(Map<String, ?> sArray) {
		Map<String, Object> result = new HashMap<String, Object>();
		if ((sArray == null) || (sArray.size() <= 0)) {
			return result;
		}
		for (String key : sArray.keySet()) {
			Object value = sArray.get(key);
			if ((value == null) || value.equals("") || key.equalsIgnoreCase(_SIGN_INFO)
					|| key.equalsIgnoreCase(_SIGN_TYPE)) {
				continue;
			}
			if (value instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, ?> m = (Map<String, ?>) value;
				result.put(key, paraFilter(m));
			} else if (value instanceof List) {
				continue;// 不应包含多集合数据
			} else {
				result.put(key, value);
			}
		}
		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 *
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, ?> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		StringBuffer prestr = new StringBuffer("");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object o = params.get(key);
			String value = String.valueOf(o);
			if (o instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, ?> m = (Map<String, ?>) o;
				value = "{" + createLinkString(m) + "}";
			}

			if (i == (keys.size() - 1)) {// 拼接时，不包括最后一个&字符
				prestr.append(key + "=" + value);
			} else {
				prestr.append(key + "=" + value + "&");
			}
		}
		return prestr.toString();
	}

	/**
	 * md5签名
	 *
	 * @param linkStr
	 * @param md5Key
	 * @return
	 * @throws Exception
	 */
	public static String md5Sign(String linkStr, String md5Key) {
		String templinkStr = linkStr + "&key=" + md5Key;
		String md5HexSign = Md5Util.getMD5String(templinkStr);
		if (LOG.isDebugEnabled()) {
			LOG.debug("原始签名信息 ：{} 加签签名信息 ：{}", templinkStr, md5HexSign);
		}
		return md5HexSign;
	}

	/**
	 * 检查get方法签名
	 *
	 * @param linkStr
	 * @param md5Key
	 * @return SignString
	 * @throws Exception
	 */
	public static String getSignString(String param, String token) {
		String webparams = paramsSortAsc(param, token);
		if (LOG.isDebugEnabled()) {
			LOG.info("加签原文:{}", webparams);
		}
		if (null != webparams) {
			String MD5String = Md5Util.getMD5String(webparams);
			if (LOG.isDebugEnabled()) {
				LOG.info("加签密文:{}", MD5String);
			}

			return MD5String;
		}
		return null;
	}

	/**
	 * 检查post方法签名
	 *
	 * @param linkStr
	 * @param md5Key
	 * @return SignString
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String postSignString(String json, String token) {

		Map<String, Object> paramMap = JsonUtil.readJson(json, Map.class);
		return signMd5(paramMap, token);
	}

	/**
	 * 排序获取表单参数
	 * 
	 * @param params
	 * @return String
	 * 
	 */
	private static String paramsSortAsc(String ps, String token) {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		String[] params = ps.split(CommonConstant.STRING_AND);
		int paramLen = params.length;
		if (paramLen > 0) {
			for (int i = 0; i < paramLen; i++) {
				if (StringUtils.isNotBlank(params[i])) {
					String[] mapstr = params[i].split(CommonConstant.STRING_EQUAL);
					int mapStrLen = mapstr.length;
					if (mapStrLen == 2) {
						String key = mapstr[0];
						String value = mapstr[1];
						treeMap.put(key, value);
					}
				}
			}
		}

		if (treeMap.size() > 0) {
			return StringUtil.stringAssembly(
					JsonUtil.write2Json(treeMap).replaceAll(CommonConstant.STRING_REGEXP, CommonConstant.STRING_EMPTY)
							.replaceAll(CommonConstant.STRING_COLON, CommonConstant.STRING_EQUAL)
							.replaceAll(CommonConstant.STRING_COMMA, CommonConstant.STRING_AND),
					CommonConstant.STRING_AND, CommonConstant.STRING_TOKEN_KEY, CommonConstant.STRING_EQUAL, token);
		}
		return null;
	}

}
