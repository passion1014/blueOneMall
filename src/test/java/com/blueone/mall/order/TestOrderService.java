package com.blueone.mall.order;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestOrderService extends BlueoneTestCase {

	@Test
	public void testGetOrderList() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(get("/order/getOrderList.do")
				.param("srchStdDt", "Y")
				.param("srchEdDt", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
	}
}
