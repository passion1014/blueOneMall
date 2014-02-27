package com.blueone.mall.category;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.*;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestCategoryManageService extends BlueoneTestCase {

	/**
	 * "/category/registCategory.do"
	 * @throws Exception
	 */
	@Test
	public void testRegistCategory() throws Exception {
		
		// Spring MVC Test!
		mockMvc.perform(post("/category/registCategoryInf.do")
//				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.param("ctgPCode", "MC01")
				.param("ctgCodeType", "02")
				.param("ctgCode", "B02")
				.param("ctgName", "SUB_CATEGORY_2")
				.param("ctgDesc", "THIS IS SUB CATEGORY!")
//				.param("fromDate", "mobile")
//				.param("toDate", "email")
//				.param("regDate", "grade")
//				.param("lastDate", "1")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
