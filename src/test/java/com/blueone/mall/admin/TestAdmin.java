package com.blueone.mall.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.service.IAdminManageService;
import com.blueone.controller.AdminController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		})

public class TestAdmin {

	@Autowired
	AdminController adminController;
	
	@Autowired
	IAdminManageService adminManageService;
	
	/**
	 * 아래 클래스/함수를 테스트 한다.
	 * adminManageService.registAdmin
	 */
	@Test
	public void testInsertAdmin() {
		
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
		
		adminManageService.registAdminInf(adminInfo);
		
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
		
		adminManageService.editAdminInf(adminInfo);
		
	}
}
