package com.blueone.mall.customer;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestCustomer extends BlueoneTestCase {

	@Test
	public void testGetCustomerInfo() throws Exception {
		mockMvc.perform(get("/cust/getCustomerInfo.do")
				.param("custId", "custId"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
