package com.blueone.mall.product;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.blueone.mall.BlueoneTestCase;

public class TestProductManageService extends BlueoneTestCase {

	@Test
	public void testRegistCategoryList() throws Exception {
		
		// 상품등록
		for (int idx=1; idx <= 20; idx++) {
			mockMvc.perform(get("/product/registProductInfo.do")
					.param("prdCd", "prdCd"+idx)
					.param("prdNm", "Product Nm"+idx)
					.param("prdDesc", "desc"+idx)
					.param("prdDescHtml", "desc html"+idx)
					.param("prdCtgL", "")
					.param("prdCtgM", "")
					.param("prdCtgS", "")
					.param("fromDate", "")
					.param("toDate", "")
					.param("modifyUserId", "admin"))
			.andExpect(status().isOk())
			.andDo(print());
		}
	}
	
	@Test
	public void testRegistCategory() throws Exception {
		
		// 상품등록
		mockMvc.perform(get("/product/registProductInfo.do")
				.param("prdCd", "prdCd_A")
				.param("prdNm", "Product Nm")
				.param("prdDesc", "desc")
				.param("prdDescHtml", "desc html")
				.param("prdCtgL", "")
				.param("prdCtgM", "")
				.param("prdCtgS", "")
				.param("fromDate", "")
				.param("toDate", "")
				.param("modifyUserId", "admin"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testGetCategoryList() throws Exception {
		
		// 상품조회
		mockMvc.perform(get("/product/searchProductList.do")
				.param("prdNm", "Product Nm")
				.param("prdCtgL", "")
				.param("prdCtgM", "")
				.param("prdCtgS", ""))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testGetProductView() throws Exception {
		
		// 상품조회 prdCd=P5571
		mockMvc.perform(get("/product/productView.do")
				.param("prdCd", "P5571")
				.param("currentPage", "1"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testWriteQnA() throws Exception {
		// 상품조회 prdCd=P5571
		mockMvc.perform(get("/product/writeQnA.do")
				.param("prdCd", "P5571")
				.param("content", "상품이 좋습니다!"))
		.andExpect(status().isOk())
		.andDo(print());
	}

}
