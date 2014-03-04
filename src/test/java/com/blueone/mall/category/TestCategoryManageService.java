package com.blueone.mall.category;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.*;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestCategoryManageService extends BlueoneTestCase {

	@Test
	public void testEditCategory() throws Exception {
		// LV101
		mockMvc.perform(post("/category/editCategoryInf.do")
				.param("ctgPCode", "")
				.param("ctgCodeType", "01")
				.param("ctgCode", "LV101")
				.param("ctgName", "MAIN_CATEGORY_11111")
				.param("ctgDesc", "THIS IS MAIN CATEGORY!~")
				.param("comment", "comment..."))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testGetCategoryList() throws Exception {
		// LV101
		mockMvc.perform(get("/category/getCategoryList.do")
				.param("ctgPCode", "")
				.param("ctgCodeType", "01")
				.param("ctgCode", "LV101")
				.param("ctgName", "MAIN_CATEGORY_1")
				.param("ctgDesc", "THIS IS MAIN CATEGORY!")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * "/category/registCategory.do"
	 * @throws Exception
	 */
	@Test
	public void testRegistCategory() throws Exception {
		
		// LV101
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "")
				.param("ctgCodeType", "01")
				.param("ctgCode", "LV101")
				.param("ctgName", "MAIN_CATEGORY_1")
				.param("ctgDesc", "THIS IS MAIN CATEGORY!")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());

		// LV102
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "")
				.param("ctgCodeType", "01")
				.param("ctgCode", "LV102")
				.param("ctgName", "Main_CATEGORY_2")
				.param("ctgDesc", "THIS IS MAIN CATEGORY!")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
		
		// LV201
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "LV101")
				.param("ctgCodeType", "02")
				.param("ctgCode", "LV201")
				.param("ctgName", "name")
				.param("ctgDesc", "desc")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());

		// LV202
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "LV101")
				.param("ctgCodeType", "02")
				.param("ctgCode", "LV202")
				.param("ctgName", "name")
				.param("ctgDesc", "desc")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());

		// LV203
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "LV102")
				.param("ctgCodeType", "02")
				.param("ctgCode", "LV203")
				.param("ctgName", "name")
				.param("ctgDesc", "desc")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
		
		// LV204
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "LV102")
				.param("ctgCodeType", "02")
				.param("ctgCode", "LV204")
				.param("ctgName", "name")
				.param("ctgDesc", "desc")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
		
		// LV205
		mockMvc.perform(post("/category/registCategoryInf.do")
				.param("ctgPCode", "LV102")
				.param("ctgCodeType", "02")
				.param("ctgCode", "LV205")
				.param("ctgName", "name")
				.param("ctgDesc", "desc")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
