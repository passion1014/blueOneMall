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
				.param("ctgPCode", "Y")
				.param("ctgCodeType", "id5")
				.param("ctgCode", "password1")
				.param("ctgName", "Àü¼ºÈ£")
				.param("ctgDesc", "010-1234-1111")
//				.param("fromDate", "mobile")
//				.param("toDate", "email")
//				.param("regDate", "grade")
//				.param("lastDate", "1")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
