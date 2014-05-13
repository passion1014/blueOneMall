package com.blueone.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Set;
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
	
	public List<Cookie> getCookieListByPath(HttpServletRequest request) {
		List<Cookie> rstList = new ArrayList<Cookie>();
		
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if ("/product".equals(c.getPath())) {
				rstList.add(c);
			}
		}
		
		return rstList;
	}
	
	public static Cookie createCookie(String name, String value) throws IOException {
		return new Cookie(name,value);
	}
	
	public static Cookie createCookie(String name, String value, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		
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
	
	public List<String> getKey(){
		
		Iterator mk =  cookieMap.keySet().iterator();
		List<String> key = new ArrayList<String>();
		
		while ( mk.hasNext()) {
			key.add((String)mk.next());
		}
		
		return key;
		
	}
	
	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}
	
	public boolean delete(String name) {
		cookieMap.remove(name);
		return cookieMap.remove(name) != null;
	}
	
	
}