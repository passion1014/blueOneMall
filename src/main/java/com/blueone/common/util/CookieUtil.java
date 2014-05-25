package com.blueone.common.util;

public class CookieUtil {
	public static String changeProdOption(String str, String optName, String optValue) {

		if (str == null) return str;
		
		int idx = str.lastIndexOf(optName);
		if (idx != -1) {
			int startIdx = idx+optName.length()+1;
			int endIdx = str.indexOf(",", idx);
			
			if (endIdx == -1) endIdx = str.length();

			// 결과값 만들기
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(str.substring(0, startIdx)).append(optValue).append(str.substring(endIdx));
			return sBuilder.toString();
		}
		
		return str;
	}
}
