package com.blueone.mall.customer;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestSSO extends BlueoneTestCase {
	
	@Test
	public void testSSO() throws Exception {
		mockMvc.perform(get("/sso/login.do")
				.param("MEM_NO", "B856E909E78FDFA2132E230C3A557FC3")
				.param("SHOP_NO", "F18A132281C854DE34799E99601BB6FE")
				.param("MEM_NM", "88E47E665935EEFE")
				.param("SHOPEVENT_NO", "5E1F456A9D36B942B97F35E597E81E5A")
				.param("ENTR_NO", "C21E2B1234BAD6A1DD6A02B59467204C")
				)
		.andExpect(status().isOk())
		.andDo(print());
	}
}


// /sso/login.do?MEM_NO=B856E909E78FDFA2132E230C3A557FC3&SHOP_NO