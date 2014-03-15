package com.blueone.mall;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.*;

/**
 * Junit�� Spring-Test-MVC�� ����Ͽ� �׽�Ʈ���̽��� ����� �����̴�.
 * 2014.02.24
 * @author daniel
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TestSpringTestMVC extends BlueoneTestCase {
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

	@Test
	public void testHomeController() throws Exception {

		mockMvc.perform(get("/").param("author", "mOer").param("body", "some contents...").param("title", "some title"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("msg"))
		.andExpect(model().attribute("msg", "[Blue One From DB]"))
		.andDo(print());
		
	}
	
	@Test
	public void testPropertyPlaceHolder() throws Exception {
//		String resourceHost = messageSourceAccessor.getMessage("DataDrivenEnumerationValueImpl_friendyName", "");
		String resourceHost = messageSourceAccessor.getMessage("basenames", "1");

		System.out.println("==================[" + resourceHost + "]==================");
		System.out.println(resourceHost);
	}
}
