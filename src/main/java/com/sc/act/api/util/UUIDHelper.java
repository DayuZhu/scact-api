package com.sc.act.api.util;

import java.util.UUID;

public class UUIDHelper {

	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
