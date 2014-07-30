package com.blueone.mall;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String ordNo = "서울특별시";
		ordNo=URLEncoder.encode(ordNo, "UTF-8");
		System.out.println(ordNo);
		
		 
	}

}
