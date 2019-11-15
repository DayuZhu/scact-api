package com.sc.act.api.util;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName JsonHelper
 * @Description json转换工具类
 * @author dayu
 */
public class JsonHelper {
	private static final Logger LOG = LoggerFactory.getLogger(JsonHelper.class);

	public static ObjectMapper InstanceMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	/**
	 * 将Object转成JSON字符串 类名在 @class 属性中
	 */
	public static String write2Json(Object obj) {
		if (obj == null) {
			return null;
		}

		try {
			ObjectMapper mapper = InstanceMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LOG.error("将Object转成JSON字符串错误：", e);
			throw new RuntimeException("将Object转成JSON字符串错误", e);
		}
	}// method

	public static <T> T readJson(String json, Class<T> valueType) {
		if (json == null) {
			return null;
		}

		try {
			ObjectMapper mapper = InstanceMapper();
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			LOG.error("JSON转换OBJ错误:", e);
			throw new RuntimeException("JSON转换OBJ错误", e);
		}

	}// method

	/**
	 * 读取JSON字符串
	 */
	public static Object readJson(String source) {
		return readJson(source, Object.class);
	}// method

	/**
	 * 对象转JSON字符串
	 */
	public static String gsonToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}// method

	/**
	 * JSON字符串转 List
	 * 
	 * @param <T>
	 */
	public static <T> T gsonFromJsonList(String json, T T) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<T>>() {
		}.getType();
		return gson.fromJson(json, type);
	}// method

	/**
	 * JSON字符串转对像
	 * 
	 * @param <T>
	 */
	public static <T> T gsonFromJson(String json, Class<T> classofT) {
		Gson gson = new Gson();
		return gson.fromJson(json, classofT);
	}// method

}// class
