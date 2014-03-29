package com.blueone.mall.board;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestBoardServcie extends BlueoneTestCase {
	
	@Test
	public void testBoardList() throws Exception {
		mockMvc.perform(post("/board/list.do")
				.param("brdTyp", "1")
				.param("srchBrdTyp", "1"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
