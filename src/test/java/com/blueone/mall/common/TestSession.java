package com.blueone.mall.common;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.blueone.customer.domain.MemberInfo;
import com.blueone.mall.BlueoneTestCase;

public class TestSession extends BlueoneTestCase {

	@Test
	public void testSession() throws Exception {

		// 세션선언
		MockHttpSession session = new MockHttpSession();
		
		// 세션정보 셋팅
		mockMvc.perform(get("/sessionTest.do").session(session)
				.param("comment", "comment..."))
		.andExpect(status().isOk())
		.andDo(print());
		
		// 입력된 세션정보 확인
		printSessionInfo(session, "memberInfo");
	}
	
	private void printSessionInfo(MockHttpSession session, String attributeName) {
		
		if (session.getAttribute(attributeName) != null) {
			MemberInfo memberInfo = (MemberInfo)session.getAttribute(attributeName);
			System.out.println("Login Name is " + memberInfo.getUserNm());
			
		} else {
			System.out.println(attributeName + " is NULL");
			
		}
	}

}
