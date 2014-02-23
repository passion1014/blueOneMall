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
	 * �Ʒ� Ŭ����/�Լ��� �׽�Ʈ �Ѵ�.
	 * adminManageService.registAdmin
	 */
	@Test
	public void testInsertAdmin() throws Exception {
		
		// �Է��� ������ ������ �����Ѵ�.
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
		
		// ��ȸ Ű
		adminInfo.setId("id2");

		// ������ ����
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
