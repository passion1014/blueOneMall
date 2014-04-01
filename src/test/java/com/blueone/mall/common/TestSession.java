package com.blueone.mall.common;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.customer.domain.CustomerInfo;
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
	
	@Test
	public void testAdminSession() throws Exception {

		// 세션선언
		MockHttpSession session = new MockHttpSession();
		
		// 세션정보 셋팅
		mockMvc.perform(post("/admin/adminLoginProc.do").session(session)
				.param("adminId", "id5")
				.param("adminPw", "password"))
		.andExpect(status().isOk())
		.andDo(print());
		
		// 입력된 세션정보 확인
		printAdminSessionInfo(session, "adminSession");
	}
	
	

	@Test
	public void testSession2() throws Exception {
		// 세션선언
		MockHttpSession session = new MockHttpSession();
		mockMvc.perform(get("/cust/setSessionTest.do").session(session)
				.param("status", "status")
				.param("id", "id")
				.param("password", "password")
				.param("pasword", "password")
		)
		.andDo(print());

		mockMvc.perform(get("/cust/getSessionTest2.do").session(session)
//				.param("author", "mOer")
		)
		.andDo(print());

	}
	
	private void printSessionInfo(MockHttpSession session, String attributeName) {
		
		if (session.getAttribute(attributeName) != null) {
			CustomerInfo memberInfo = (CustomerInfo)session.getAttribute(attributeName);
			System.out.println("Login Name is " + memberInfo.getCustNm());
			
		} else {
			System.out.println(attributeName + " is NULL");
			
		}
	}

	private void printAdminSessionInfo(MockHttpSession session, String attributeName) {
		
		if (session.getAttribute(attributeName) != null) {
			AdminInfo memberInfo = (AdminInfo)session.getAttribute(attributeName);
			System.out.println("Login admin id = " + memberInfo.getId());
			
		} else {
			System.out.println(attributeName + " is NULL");
			
		}
	}
}
