package com.blueone.mall;


import org.junit.Test;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.*;

/**
 * Junit과 Spring-Test-MVC를 사용하여 테스트케이스를 만드는 예제이다.
 * 2014.02.24
 * @author daniel
 */
public class TestSpringTestMVC extends BlueoneTestCase {

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
}
