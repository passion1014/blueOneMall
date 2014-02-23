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
		String url = "/category/registCategory.do";
		
//		mockMvc.perform(put(url).with(convertPar))
//		.andExpect(status().isOk())
//		.andExpect(model().attributeExists("msg"))
//		.andDo(print());
	}
}
