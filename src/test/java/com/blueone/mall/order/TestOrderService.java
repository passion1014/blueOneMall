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
				.param("srchStdDt", "2013-03-01")
				.param("srchEdDt", "2014-04-01"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testMain() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	
	@Test
	public void testCartList() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(post("/order/cartList.do")
				.param("prdOpColor", "RED")
				.param("buyCnt", "10"))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	public void testEncoding() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(get("/order/orderComplete.do")
				.param("reciInfo.reciNm", "%C3%D6%B5%BF%BD%C4")
				.param("reciInfo.reciAdd", "135-905+%BC%AD%BF%EF%C6%AF%BA%B0%BD%C3+%B0%AD%B3%B2%B1%B8+%BE%D0%B1%B8%C1%A4%B5%BF+%B1%B8%C7%F6%B4%EB%BE%C6%C6%C4%C6%AE+%2880%7E87%B5%BF%29+11111"))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
