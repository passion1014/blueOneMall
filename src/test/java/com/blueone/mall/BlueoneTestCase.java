package com.blueone.mall;

import junit.framework.TestCase;

import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;

import com.google.gson.Gson;

public abstract class BlueoneTestCase extends TestCase {
	public MockMvc mockMvc;
	
	@Override
	protected void setUp() throws Exception {
		mockMvc = MockMvcBuilders.xmlConfigSetup(new String[] {
				"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
				"file:src/main/webapp/WEB-INF/spring/root-context.xml"}).build();
	}
	
	public String convertJsonTypeStr(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

}
