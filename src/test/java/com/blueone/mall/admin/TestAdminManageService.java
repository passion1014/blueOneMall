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

	/**
	 * 아래 클래스/함수를 테스트 한다.
	 * adminManageService.registAdmin
	 */
	@Test
	public void testInsertAdmin() throws Exception {
		
		// 입력할 관리자 정보를 셋팅한다.
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setStatus("Y");
		adminInfo.setId("id2");
		adminInfo.setPassword("password");
		adminInfo.setName("name");
		adminInfo.setPhone("phone");
		adminInfo.setMobile("mobile");
		adminInfo.setEmail("email");
		adminInfo.setGrade("grade");
		adminInfo.setHit(1);
		adminInfo.setComment("comment");
		
		String adminJson = convertJsonTypeStr(adminInfo);
		
		// Spring MVC Test!
		mockMvc.perform(post("/admin/registAdminInf.do").contentType(TestUtil.APPLICATION_JSON_UTF8)
//				.body(TestUtil.convertObjectToJsonBytes(adminInfo)))
				.requestAttr("AdminInfo", adminInfo))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void testEditAdminInf() {
		AdminInfo adminInfo = new AdminInfo();
		
		// 조회 키
		adminInfo.setId("id2");

		// 변경할 값들
		adminInfo.setStatus("Y");
		adminInfo.setPassword("password");
		adminInfo.setName("name");
		adminInfo.setPhone("phone");
		adminInfo.setMobile("mobile");
		adminInfo.setEmail("email");
		adminInfo.setGrade("grade");
		adminInfo.setHit(1);
		adminInfo.setComment("comment");
		
	}
}
