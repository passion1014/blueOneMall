package com.blueone.common.util;

import java.io.InputStream;
import java.util.Properties;


public class Configuration {
	
	// 인스턴스
	private static Configuration instance = null;
	
	/** 속성값 객체 */
	private static Properties props = null;

	/** 속성 파일명 */
	private final static String	CONF_FILE_NAME = "blueone.properties";
	
	
	private Configuration() {
		super();
		initialize();
	}
	
	/**
	 * 
	 * @return
	 */
	public static synchronized Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		
		return instance;
	}
	
	
	/**
	 * 주어진 key에 대한 속성값을 읽는다,
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(String key)
	{

		if (props == null)
		{
			throw new RuntimeException("configration not initialized");
		}
		String value = props.getProperty(key);
		if (value == null)
		{
			throw new RuntimeException("configration property not found : " + key);
		}
		return value;
	}
	
	/**
	 * 초기화한다.
	 */
	private synchronized void initialize()
	{
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			props = new Properties();
			props.load(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
