package com.blueone.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.PageDivision;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.product.service.IUserProductService;

@Controller
public class UserProductController {

	@Autowired
	private IUserProductService iUserProductService;
	@Autowired
	IAttachFileManageService attFileManageService;

	@Autowired
	private ICategoryManageService categoryManageService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private IProductManageService productManageService;

	
	@RequestMapping(value="/product/productList.do")
	public String productList(@ModelAttribute("productInfo") ProductInfo productInfo, @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,String page,HttpSession session){
		// CustomerInfo customerSesstion =(CustomerInfo)session.getAttribute("customerSession");
		CustomerInfo cust = (CustomerInfo) session.getAttribute("customerSession");
		// 세션체크
		if (cust == null) {
			return "user/errorPage";
		}
		
		PageDivision pd = new PageDivision();

		if (StringUtils.isEmpty(page))
			pd.pageNum("1");
		else
			pd.pageNum(page);
		String orderBy = productInfo.getOrderBy();
		if (StringUtils.isEmpty(orderBy))
			orderBy = "low";

		// ----------------------------------------------------------
		// 변수선언
		// ----------------------------------------------------------
		List<CategoryInfo> middleCode = new ArrayList<CategoryInfo>(); // 중분류조회

		List<CategoryInfo> lnbList = new ArrayList<CategoryInfo>(); // 중분류리스트
		List<CategoryInfo> lnbSList = new ArrayList<CategoryInfo>(); // 소분류리스트

		List<ProductInfo> prdLList = new ArrayList<ProductInfo>();
		List<ProductInfo> prdMList = new ArrayList<ProductInfo>();
		List<ProductInfo> prdSList = new ArrayList<ProductInfo>();

		List<ProductInfo> prdList = new ArrayList<ProductInfo>();

		SearchProdInfo searchProdInfo = new SearchProdInfo();

		String chkMiddleCode = null;
		String prdCtgS = null;

		// ---------------------------------------------------------
		// 변수 세팅
		// 최초 넘어오는 변수 값 세팅
		// ---------------------------------------------------------
		chkMiddleCode = categoryInfo.getCtgMiddleCode();

		if (chkMiddleCode == null || chkMiddleCode == "") {
			chkMiddleCode = null;
		}

		prdCtgS = productInfo.getPrdCtgS();

		if (prdCtgS == null || prdCtgS == "") {
			prdCtgS = null;
		}

		// ----------------------------------------------------------
		// 카테고리 조회
		// ----------------------------------------------------------
		List<CategoryInfo> categoryList = categoryManageService.getCategoryInfList(categoryInfo);
		List<ProductInfo> productList = null;

		// product 조회
		if (orderBy.equals("low")) {
			productList = productManageService.oderByLowSellPriceList();
		}
		if (orderBy.equals("high")) {
			productList = productManageService.oderByHighSellPriceList();
		}
		if (orderBy.equals("name")) {
			productList = productManageService.oderByNamePriceList();
		}
		if (orderBy.equals("brd")) {
			productList = productManageService.oderByBrdPriceList();
		}

		// List<ProductInfo> productList =
		// productManageService.getProductInfList(searchProdInfo);

		
		// ----------------------------------------------------------
		// LNB 정보 조회 및 중간네비게이션용
		// ----------------------------------------------------------
		CategoryInfo largeInf = new CategoryInfo();

		if (categoryInfo.getCtgCode() == null
				|| categoryInfo.getCtgCode() == "") {
			largeInf = categoryManageService
					.getCategoryInfDetail2(categoryInfo);
		} else {
			largeInf = categoryManageService.getCategoryInfDetail(categoryInfo);
		}

		// 중분류 LNB
		for (CategoryInfo each : categoryList) {
			if (largeInf.getCtgCode().equals(each.getCtgPCode())) {
				lnbList.add(each);
			}
		}

		// 소분류 LNB
		if (chkMiddleCode != null) {
			for (CategoryInfo each : categoryList) {
				if (categoryInfo.getCtgMiddleCode().equals(each.getCtgPCode())) {
					lnbSList.add(each);
				}
			}
		}

		// 대분류가 없을 수 없다.
		if (largeInf.getCtgCode() != null) {

			if (chkMiddleCode != null) {

				if (prdCtgS != null) {

					// 소분류와 같은 제품
					for (ProductInfo each : productList) {
						if (prdCtgS.equals(each.getPrdCtgS())) {
							prdList.add(each);
						}
					}
					pd.setPrdList(prdList);

				} else {

					// 중분류와 같은 제품
					for (ProductInfo each : productList) {
						if (chkMiddleCode.equals(each.getPrdCtgM())) {
							prdList.add(each);
						}
					}
					pd.setPrdList(prdList);

				}

			} else {
				// 대분류와 같은 제품
				for (ProductInfo each : productList) {
					if (largeInf.getCtgCode().equals(each.getPrdCtgL())) {
						prdList.add(each);
					}
				}
				pd.setPrdList(prdList);

			}

		}

		List<ProductInfo> resultList = pd.getPrdList(4);

		for (ProductInfo each : resultList) {
			AttachFileInfo att = new AttachFileInfo();
			att.setAttCdKey(each.getPrdCd());
			att = attFileManageService.getAttFileInfListImg(att);

			if (att == null) {
				each.setAttFilePath("");
			} else {

				each.setAttFilePath(att.getAttFilePath());
			}

		}
		model.addAttribute("total", prdList.size());
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("largeInf", largeInf);
		model.addAttribute("lnbList", lnbList);
		model.addAttribute("lnbSList", lnbSList);
		model.addAttribute("middleCode", middleCode);
		model.addAttribute("lMenuDetail", categoryInfo);
		model.addAttribute("chkMiddleCode", chkMiddleCode);
		model.addAttribute("prdCtgS", prdCtgS);
		model.addAttribute("prdList", resultList);
		model.addAttribute("endNum", pd.getEndPageNum());
		return "product/productList";

	}

	@RequestMapping(value = "/include/header.do")
	public String header() {

		return "/inc/header";
	}

	/*
	 * @RequestMapping(value="/product/productView.do") public String
	 * productView(@ModelAttribute("productInfo") ProductInfo
	 * productInfo,@ModelAttribute("categoryInfo") CategoryInfo categoryInfo
	 * ,BindingResult result, Model model){
	 * 
	 * 
	 * categoryInfo = new CategoryInfo(); categoryInfo =
	 * categoryManageService.getCategoryInfDetail(categoryInfo);
	 * model.addAttribute("categoryInfo", categoryInfo); List<CategoryInfo>
	 * fullList = categoryManageService.getCategoryInfList(categoryInfo); //대분류
	 * 조회 List<CategoryInfo> lFullList = new ArrayList<CategoryInfo>(); //중분류 조회
	 * List<CategoryInfo> mFullList = new ArrayList<CategoryInfo>();
	 * 
	 * String ctgLargeCode = categoryInfo.getCtgLargeCode();
	 * 
	 * for(CategoryInfo large : fullList){
	 * if("01".equals(large.getCtgCodeType())){ lFullList.add(large); }
	 * if("02".equals(large.getCtgCodeType())){ mFullList.add(large); }
	 * 
	 * }
	 * 
	 * for(int idx=0; idx < mFullList.size(); idx++) { CategoryInfo each =
	 * mFullList.get(idx); if (!ctgLargeCode.equals(each.getCtgPCode())) {
	 * mFullList.remove(idx); idx--; } }
	 * 
	 * 
	 * /*categoryInfo =
	 * categoryManageService.getCategoryInfDetail(categoryInfo);
	 * List<CategoryInfo> list =
	 * categoryManageService.getCategoryInfList4(categoryInfo);
	 * 
	 * List<CategoryInfo> rstList1 = getCategoryListByTypeCd(categoryInfo,
	 * "01");//대분류 list List<CategoryInfo> rstList2 =
	 * getCategoryListByTypeCd(categoryInfo, "02");//중분류 list
	 * 
	 * String ctgLargeCode = categoryInfo.getCtgLargeCode();
	 * 
	 * for(int idx=0; idx < rstList2.size(); idx++) { CategoryInfo each =
	 * rstList2.get(idx); if (!ctgLargeCode.equals(each.getCtgPCode())) {
	 * rstList2.remove(idx); idx--; } }
	 * 
	 * 
	 * model.addAttribute("ctgList1", rstList1); model.addAttribute("ctgList2",
	 * rstList2); model.addAttribute("categoryInfo", categoryInfo);
	 * model.addAttribute("list", list);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * model.addAttribute("lFullList", lFullList);
	 * model.addAttribute("mFullList", mFullList);
	 * 
	 * 
	 * 
	 * return "product/productView"; }
	 */

}
