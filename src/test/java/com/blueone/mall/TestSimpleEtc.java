package com.blueone.mall;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

public class TestSimpleEtc extends BlueoneTestCase {

	@Test
	public void testStringCnvt2Int() {
		String str1 = "01";
		String str2 = "02";
		String str3 = "13";
		
		System.out.println("compare=" + "13".compareTo("01"));
		System.out.println("compare=" + "03".compareTo("01"));
		System.out.println("compare=" + "02".compareTo("01"));
		System.out.println("compare=" + "02".compareTo("11"));
		
		System.out.println("cnvt=" + NumberUtils.toInt("01"));
		System.out.println("cnvt=" + NumberUtils.toInt("03"));
		System.out.println("cnvt=" + NumberUtils.toInt("12"));
		
		System.out.println("cnvt=" + NumberUtils.toInt(null, -1));
	}
	
	@Test
	public void bindingTest() {
		
	}
}
