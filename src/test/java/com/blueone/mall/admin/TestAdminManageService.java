package com.blueone.mall.admin;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.*;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.common.util.TestUtil;
import com.blueone.mall.BlueoneTestCase;
import com.google.gson.Gson;


public class TestAdminManageService extends BlueoneTestCase {

	
	public void testCheckDuplicate() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(post("/admin/checkAdminId.do")
				.param("id", "id100"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	/**
	 * adminManageService.registAdmin
	 */
	@Test
	public void testInsertAdmin() throws Exception {
		
		// Spring MVC Test!
		mockMvc.perform(post("/admin/registAdminInf.do")
//				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.param("status", "Y")
				.param("id", "id5")
				.param("password", "password1")
				.param("name", "�댁꽦��")
				.param("phone", "010-1234-1111")
				.param("mobile", "mobile")
				.param("email", "email")
				.param("grade", "grade")
				.param("hit", "1")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void testEditAdminInf() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(post("/admin/editAdminInf.do")
//				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.param("status", "Y")
				.param("id", "id5")
				.param("password", "password")
				.param("name", "newName")
				.param("phone", "phone")
				.param("mobile", "mobile")
				.param("email", "email")
				.param("grade", "grade")
				.param("hit", "1")
				.param("comment", "comment"))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	public void testGetAdminDetailInf() throws Exception {
		// Spring MVC Test!
		mockMvc.perform(get("/admin/getAdminDetailInf.do")
//				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.param("id", "id5"))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void testGetAdminInfoListAll() throws Exception {
		mockMvc.perform(get("/admin/getAdminList.do"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testGetAdminInfoListByName() throws Exception {
		mockMvc.perform(get("/admin/getAdminDetailInf.do")
				.param("id", "id2"))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
