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
 * Junit과 Spring-Test-MVC를 사용하여 테스트케이스를 만드는 예제이다.
 * 2014.02.24
 * @author daniel
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TestSpringTestMVC extends BlueoneTestCase {
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

//	@Resource(name="messageSourceAccessor")
//	private MessageSourceAccessor messageSourceAccessor;
	
	/**
	 * SPRING-TEST-MVC를 사용하는 예제이다.
	 */
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

//		웹에서 사용하기
//		<spring:message code='test' arguments='메시지테스트' var="testMessage"/>
//		<spring:message code='test1' var="testMessage1"/>


		System.out.println("==================[" + resourceHost + "]==================");
		System.out.println(resourceHost);
	}
}
