package com.sc.act.api.util;

public class StringHelper {

	public static String getNewString(String str1, String str2) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(str1);
		sbf.append("/");
		sbf.append(str2);
		return sbf.toString();
	}// method

	public static String arrayToString(String[] array) {
		StringBuffer toList = new StringBuffer();
		int length = array.length;
		if (array != null && length < 2) {
			toList.append(array[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(array[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();
	}
	
	
}
