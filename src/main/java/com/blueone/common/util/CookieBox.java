package com.blueone.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;

public class CookieBox {
	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	
	public CookieBox(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0 ; i < cookies.length ; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}
	
	public static Cookie createCookie(String name, String value) throws IOException {
		return new Cookie(name, URLEncoder.encode(value, "euc-kr"));
	}
	
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException {
<<<<<<< HEAD
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "euc-kr"));
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		
=======
//		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "euc-kr"));
		Cookie cookie = new Cookie(name, value);
		
		cookie.setMaxAge(maxAge);
		//response.addCookie(cookie);
>>>>>>> 1427c12e37e2e7be0f8667c933ced478c82c2472
		return cookie;
	}


	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "euc-kr"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "euc-kr"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		
		return cookie;
	}
	
	public Cookie getCookie(String name) {
		return (Cookie)cookieMap.get(name); 
	}
	
	public String getValue(String name) throws IOException {
		Cookie cookie = (Cookie)cookieMap.get(name);
		if (cookie == null) return null;
		return cookie.getValue();
	}
	
	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}
	
	
}