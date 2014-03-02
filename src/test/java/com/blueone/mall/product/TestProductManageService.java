package com.blueone.mall.product;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestProductManageService extends BlueoneTestCase {

	@Test
	public void testGetCategoryList() throws Exception {
		mockMvc.perform(get("/product/registProductInfo.do")
				.param("prdCd", "prd_cd1")
				.param("prdNm", "Product Nm")
				.param("prdDesc", "desc")
				.param("prdDescHtml", "desc html")
				.param("prdCtgL", "LV101")
				.param("prdCtgM", "")
				.param("prdCtgS", "")
				.param("fromDate", "")
				.param("toDate", "")
//				.param("regDate", "THIS IS MAIN CATEGORY!")
//				.param("lastDate", "THIS IS MAIN CATEGORY!")
				.param("modifyUserId", "admin"))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
