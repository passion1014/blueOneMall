package com.blueone.mall.customer;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.blueone.customer.domain.CustomerInfo;
import com.blueone.mall.BlueoneTestCase;

public class TestSSO extends BlueoneTestCase {
	
	@Test
	public void testSSO() throws Exception {
		// 세션선언
		MockHttpSession session = new MockHttpSession();
		
		mockMvc .perform(get("/sso/login.do").session(session)
				.param("MEM_NO", "B856E909E78FDFA2132E230C3A557FC3")
				.param("SHOP_NO", "F18A132281C854DE34799E99601BB6FE")
				.param("MEM_NM", "88E47E665935EEFE")
				.param("SHOPEVENT_NO", "5E1F456A9D36B942B97F35E597E81E5A")
				.param("ENTR_NO", "C21E2B1234BAD6A1DD6A02B59467204C")
				)
		.andExpect(status().isOk())
		.andDo(print());
		
		// 입력된 세션정보 확인
		printSessionInfo(session, "customerSession");
	}
	
	
	private void printSessionInfo(MockHttpSession session, String attributeName) {
		
		if (session.getAttribute(attributeName) != null) {
			CustomerInfo memberInfo = (CustomerInfo)session.getAttribute(attributeName);
			System.out.println("Login Name is " + memberInfo.getCustNm());
			
		} else {
			System.out.println(attributeName + " is NULL");
			
		}
	}
}


// localhost:8080/sso/login.do?MEM_NO=B856E909E78FDFA2132E230C3A557FC3&SHOP_NO=F18A132281C854DE34799E99601BB6FE&MEM_NM=88E47E665935EEFE&SHOPEVENT_NO=5E1F456A9D36B942B97F35E597E81E5A&ENTR_NO=C21E2B1234BAD6A1DD6A02B59467204C
