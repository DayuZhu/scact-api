package com.sc.act.api.commons.web.util;

import java.util.UUID;

public class UUIDUtil {

	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
