package com.blueone.mall.board;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestBoardServcie extends BlueoneTestCase {
	
	@Test
	public void testEditCategory() throws Exception {
		// LV101
		mockMvc.perform(post("/board/list.do")
				.param("ctgPCode", "")
				.param("ctgCodeType", "01")
				.param("ctgCode", "LV101")
				.param("ctgName", "MAIN_CATEGORY_11111")
				.param("ctgDesc", "THIS IS MAIN CATEGORY!~")
				.param("comment", "comment..."))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
