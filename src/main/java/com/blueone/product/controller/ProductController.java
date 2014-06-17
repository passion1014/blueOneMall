package com.blueone.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.admin.domain.SchWordInfo;
import com.blueone.admin.service.ISearchWordService;
import com.blueone.board.controller.BoardController;
import com.blueone.board.domain.BoardInfo;
import com.blueone.board.domain.BoardSrchInfo;
import com.blueone.board.service.IBoardService;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.domain.BaseInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.PageDivision;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.CustomerInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.domain.SearchProdInfo;
import com.blueone.product.domain.TransferInfo;
import com.blueone.product.service.IProductManageService;
import com.blueone.product.service.ITransferService;

@Controller
@SessionAttributes("adminSession")
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	IProductManageService productManageService;
	@Autowired
	ISearchWordService schWordManageService;
	@Autowired
	ICategoryManageService categoryManageService;
	@Autowired
	IAttachFileManageService attFileManageService;
	@Autowired
	ITransferService transferService;
	@Autowired
	IBoardService boardService;

	BoardController boardController = new BoardController();

	/*
	 * 관리자 물품 리스트
	 */
	@RequestMapping(value = "/admin/productList.do", method = RequestMethod.GET)
	public String productList(@ModelAttribute("ProductInfo") SearchProdInfo srchProdInfo,
			BindingResult result, Model model, HttpSession session, String page) {

		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		List<ProductInfo> list = productManageService.getProductInfList1(srchProdInfo);

		PageDivision pd = new PageDivision();

		if (StringUtils.isEmpty(page))
			pd.pageNum("1");
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setPrdList(list);

		List<ProductInfo> resultList = pd.getPrdList();

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
		model.addAttribute("list", resultList);

		model.addAttribute("endNum", pd.getEndPageNum());

		// -----------------------------------------------------------------
		// 2. 상품등록을 위한 카테고리(대분류) 리스트를 넘긴다.
		// -----------------------------------------------------------------
		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> Llist = categoryManageService
				.getCategoryInfList(categoryInfo);

		// 대분류로만 필터링한다.
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();


		for (CategoryInfo each : Llist) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);

			}

		}

				// 결과값 셋팅
		model.addAttribute("ctgLList", rstList);
		
		return "admin/product/productList";
	}

	/*
	 * 상품등록 폼 구성
	 */
	@RequestMapping(value = "/admin/productRegister.do")
	public String productRegister(
			@ModelAttribute("ProductInfo") ProductInfo productInfo,
			@ModelAttribute("transferInfo") TransferInfo transferInfo,
			BindingResult result, Model model, HttpSession session) {

		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		// -----------------------------------------------------------------
		// 2. 상품등록을 위한 카테고리(대분류) 리스트를 넘긴다.
		// -----------------------------------------------------------------
		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> list = categoryManageService
				.getCategoryInfList(categoryInfo);

		// 대분류로만 필터링한다.
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();


		for (CategoryInfo each : list) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);

			}

		}

		// 결과값 셋팅
		model.addAttribute("ctgLList", rstList);

		return "admin/product/ProductRegister";
	}

	/**
	 * 상품등록처리
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "/admin/productRegisterProc.do", method = RequestMethod.POST)
	public String productRegisterProc(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model, String ctgname,
			RedirectAttributes redirectAttributes)
			throws FileNotFoundException, IOException {

		// 상품 코드 채번
		int code = (int) (Math.random() * 10000) + 1;
		productInfo.setPrdCd("P" + code);

		productInfo.setPrdConts(productInfo.getContent());

		MultipartFile proListImgUp = productInfo.getProListImgUp();
		if (proListImgUp != null && !proListImgUp.isEmpty()) {
			// 상품 리스트 이미지 등록
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(7, productInfo.getProListImgUp(),false);
			contImg.setAttCdType("01");// 등록유형 : 상품
			contImg.setAttCdKey(productInfo.getPrdCd()); //
			contImg.setAttImgType("01");// 목록
			contImg.setAttImgSeq(1);
			attFileManageService.registProductImgInfo(contImg);
		}

		MultipartFile proImg1Ip = productInfo.getProImg1Up();
		if (proImg1Ip != null && !proImg1Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg1 = new AttachFileInfo();
			FileUploadUtility utilList1 = new FileUploadUtility();
			contImg1 = utilList1.doFileUpload(7, productInfo.getProImg1Up(),false);
			contImg1.setAttCdType("01");// 등록유형 : 상품
			contImg1.setAttCdKey(productInfo.getPrdCd()); //
			contImg1.setAttImgType("02");// 뷰
			contImg1.setAttImgSeq(1);

			attFileManageService.registProductImgInfo(contImg1);
		}

		MultipartFile proImg2Ip = productInfo.getProImg2Up();
		if (proImg2Ip != null && !proImg2Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg2 = new AttachFileInfo();
			FileUploadUtility utilList2 = new FileUploadUtility();
			contImg2 = FileUploadUtility.doFileUpload(7,productInfo.getProImg2Up(), false);
			contImg2.setAttCdType("01");// 등록유형 : 상품
			contImg2.setAttCdKey(productInfo.getPrdCd()); //
			contImg2.setAttImgType("02");// 뷰
			contImg2.setAttImgSeq(2);

			attFileManageService.registProductImgInfo(contImg2);
		}

		MultipartFile proImg3Ip = productInfo.getProImg3Up();
		if (proImg3Ip != null && !proImg3Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg3 = new AttachFileInfo();
			FileUploadUtility utilList3 = new FileUploadUtility();
			contImg3 = utilList3.doFileUpload(7, productInfo.getProImg3Up(),false);
			contImg3.setAttCdType("01");// 등록유형 : 상품
			contImg3.setAttCdKey(productInfo.getPrdCd()); //
			contImg3.setAttImgType("02");// 뷰
			contImg3.setAttImgSeq(3);

			attFileManageService.registProductImgInfo(contImg3);
		}

		MultipartFile proImg4Ip = productInfo.getProImg4Up();
		if (proImg4Ip != null && !proImg4Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg4 = new AttachFileInfo();
			FileUploadUtility utilList4 = new FileUploadUtility();
			contImg4 = utilList4.doFileUpload(7, productInfo.getProImg4Up(),false);
			contImg4.setAttCdType("01");// 등록유형 : 상품
			contImg4.setAttCdKey(productInfo.getPrdCd()); //
			contImg4.setAttImgType("02");// 뷰
			contImg4.setAttImgSeq(4);
			attFileManageService.registProductImgInfo(contImg4);
		}

		// 상픔등록
		productManageService.registProductInfo(productInfo);

		redirectAttributes.addFlashAttribute("reloadVar", "yes");

		return "redirect:productList.do";

	}

	@RequestMapping(value = "/registProductInfo.do")
	public String registAdminInfo(
			@ModelAttribute("ProductInfo") ProductInfo productInfo,
			BindingResult result, Model model) {

		productManageService.registProductInfo(productInfo);

		return "product/result";
	}

	
	
	@RequestMapping(value = "/admin/searchProductList.do", method = RequestMethod.POST)
	public String searchAdminProductList(@ModelAttribute("searchProdInfo") ProductInfo searchProdInfo,BindingResult result,String page, Model model, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		// -----------------------------------------------------------------
		// 2. 상품등록을 위한 카테고리(대분류) 리스트를 넘긴다.
		// -----------------------------------------------------------------
		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> list = categoryManageService
				.getCategoryInfList(categoryInfo);

		// 대분류로만 필터링한다.
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();

		// 중분류만 필터링
		List<CategoryInfo> rstList2 = new ArrayList<CategoryInfo>();

		for (CategoryInfo each : list) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);

			}

		}

		// 결과값 셋팅
		model.addAttribute("ctgLList", rstList);
		
		switch(searchProdInfo.getSchType()){
		
			case 1 :
				searchProdInfo.setSchWord(searchProdInfo.getSearchWord());
				break;
			case 2 :
				searchProdInfo.setPrdNm(searchProdInfo.getSearchWord());
				break;
		
		}
		List<ProductInfo> prolist = productManageService.getAdminProductSearchList(searchProdInfo);
		PageDivision pd = new PageDivision();

		if (StringUtils.isEmpty(page))
			pd.pageNum("1");
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setPrdList(prolist);

		List<ProductInfo> resultList = pd.getPrdList();

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
		model.addAttribute("list", resultList);

		//model.addAttribute("endNum", pd.getEndPageNum());

		return "admin/product/productList";
	}
	/**
	 * 상품 관리 수정폼
	 */
	@RequestMapping(value = "/admin/productManagement.do", method = RequestMethod.GET)
	public String productManage(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model, String pCd, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		productInfo.setPrdCd(pCd);

		productInfo = productManageService.getProductInfDetail(productInfo);

		productInfo.setContent(productInfo.getPrdConts());

		List<ProductInfo> prdOpList = new ArrayList<ProductInfo>();
		prdOpList = productManageService.getProductOptionInfDetail(productInfo);
		int i = 0;

		int[] opIdx = new int[50];
		String[] opKey = new String[50];
		String[] opValue = new String[50];
		
		for (ProductInfo each : prdOpList) {
			opKey[i] = each.getPropType();
			opValue[i] = each.getPropName();
			opIdx[i] = each.getPropIdx();
			i++;
		}
		
		productInfo.setOptionKey(opKey);
		productInfo.setOptionValue(opValue);
		productInfo.setOptionIdx(opIdx);
		
		// 2. 상품수정을 위한 카테고리(대분류) 리스트를 넘긴다.
		// -----------------------------------------------------------------
		CategoryInfo categoryInfo = new CategoryInfo();

		// 카테고리항목 다 불러오기
		List<CategoryInfo> list = categoryManageService
				.getCategoryInfList(categoryInfo);

		// 대분류로만 필터링한다.
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		// 중분류만 필터링
		List<CategoryInfo> rstList2 = new ArrayList<CategoryInfo>();
		// 소분류만 필터링
		List<CategoryInfo> rstList3 = new ArrayList<CategoryInfo>();

		// 필터링 하는중
		for (CategoryInfo each : list) {
			if ("01".equals(each.getCtgCodeType())) {
				rstList.add(each);

			}
			if ("02".equals(each.getCtgCodeType())) {
				rstList2.add(each);

			}
			if ("03".equals(each.getCtgCodeType())) {
				rstList3.add(each);
			}

		}

		// 해당 대분류코드빼고 다른 중분류 코드 제거
		String ctgLargeCode = productInfo.getPrdCtgL();

		for (int idx = 0; idx < rstList2.size(); idx++) {
			CategoryInfo each = rstList2.get(idx);
			if (!ctgLargeCode.equals(each.getCtgPCode())) {
				rstList2.remove(idx);
				idx--;
			}

		}

		// 해당 중분류코드빼고 다른 소분류 코드 제거
		String ctgMiddleCode = productInfo.getPrdCtgM();

		for (int idx = 0; idx < rstList3.size(); idx++) {
			CategoryInfo each = rstList3.get(idx);
			if (!ctgMiddleCode.equals(each.getCtgPCode())) {
				rstList3.remove(idx);
				idx--;
			}
		}

		AttachFileInfo attFile = new AttachFileInfo();
		attFile.setAttCdKey(productInfo.getPrdCd());
		List<AttachFileInfo> imgList = attFileManageService.getAttFileInfList(attFile);

		model.addAttribute("imgList", imgList);

		model.addAttribute("prdInfo", productInfo);

		// 결과값 셋팅
		model.addAttribute("ctgLList", rstList);
		model.addAttribute("ctgMList", rstList2);
		model.addAttribute("ctgSList", rstList3);

		return "admin/product/productManagement";
	}

	/**
	 * 상품 수정처리
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "/admin/manageProductInfProc.do", method = RequestMethod.POST)
	public String manageProductInfProc(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model) throws FileNotFoundException,
			IOException {
		productInfo.setPrdConts(productInfo.getContent());
		productManageService.manageProductInf(productInfo);

		
		MultipartFile proListImgUp = productInfo.getProListImgUp();
		if (proListImgUp != null && !proListImgUp.isEmpty()) {
			// 상품 리스트 이미지 등록
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(7, productInfo.getProListImgUp(),false);
			contImg.setAttCdType("01");// 등록유형 : 상품
			contImg.setAttCdKey(productInfo.getPrdCd()); //
			contImg.setAttImgType("01");// 목록
			contImg.setAttImgSeq(1);
			attFileManageService.registProductImgInfo(contImg);
		}

		MultipartFile proImg1Ip = productInfo.getProImg1Up();
		if (proImg1Ip != null && !proImg1Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg1 = new AttachFileInfo();
			FileUploadUtility utilList1 = new FileUploadUtility();
			contImg1 = utilList1.doFileUpload(7, productInfo.getProImg1Up(),false);
			contImg1.setAttCdType("01");// 등록유형 : 상품
			contImg1.setAttCdKey(productInfo.getPrdCd()); //
			contImg1.setAttImgType("02");// 뷰
			contImg1.setAttImgSeq(1);

			attFileManageService.registProductImgInfo(contImg1);
		}

		MultipartFile proImg2Ip = productInfo.getProImg2Up();
		if (proImg2Ip != null && !proImg2Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg2 = new AttachFileInfo();
			FileUploadUtility utilList2 = new FileUploadUtility();
			contImg2 = FileUploadUtility.doFileUpload(7,productInfo.getProImg2Up(), false);
			contImg2.setAttCdType("01");// 등록유형 : 상품
			contImg2.setAttCdKey(productInfo.getPrdCd()); //
			contImg2.setAttImgType("02");// 뷰
			contImg2.setAttImgSeq(2);

			attFileManageService.registProductImgInfo(contImg2);
		}

		MultipartFile proImg3Ip = productInfo.getProImg3Up();
		if (proImg3Ip != null && !proImg3Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg3 = new AttachFileInfo();
			FileUploadUtility utilList3 = new FileUploadUtility();
			contImg3 = utilList3.doFileUpload(7, productInfo.getProImg3Up(),false);
			contImg3.setAttCdType("01");// 등록유형 : 상품
			contImg3.setAttCdKey(productInfo.getPrdCd()); //
			contImg3.setAttImgType("02");// 뷰
			contImg3.setAttImgSeq(3);

			attFileManageService.registProductImgInfo(contImg3);
		}

		MultipartFile proImg4Ip = productInfo.getProImg4Up();
		if (proImg4Ip != null && !proImg4Ip.isEmpty()) {
			// 상품 이미지 등록
			AttachFileInfo contImg4 = new AttachFileInfo();
			FileUploadUtility utilList4 = new FileUploadUtility();
			contImg4 = utilList4.doFileUpload(7, productInfo.getProImg4Up(),false);
			contImg4.setAttCdType("01");// 등록유형 : 상품
			contImg4.setAttCdKey(productInfo.getPrdCd()); //
			contImg4.setAttImgType("02");// 뷰
			contImg4.setAttImgSeq(4);
			attFileManageService.registProductImgInfo(contImg4);
		}

		return "redirect:productManagement.do?pCd=" + productInfo.getPrdCd();
	}

	/**
	 * 상품 옵션 삭제
	 */
	@RequestMapping(value = "/admin/deletePrdOptionInf.do", method = RequestMethod.GET)
	public String deletePrdOptionInfo(@ModelAttribute("productInfo") ProductInfo productInfo,BindingResult result, Model model, String idx, HttpSession session) {
		
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		StringTokenizer st = new StringTokenizer(idx, ",");

		String index = st.nextToken();
		int i = Integer.parseInt(index);
		productInfo.setPropIdx(i);
		String prdCd = st.nextToken();

		productManageService.deleteProductOptionInf(productInfo);

		String redi = "redirect:productManagement.do?pCd="
				+ prdCd;
		return redi;
	}

	/**
	 * 상품 삭제
	 */
	@RequestMapping(value = "/admin/deleteProductInf.do", method = RequestMethod.GET)
	public String deleteProductInfo(@ModelAttribute("productInfo") ProductInfo productInfo,BindingResult result, Model model, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		productManageService.deleteProductInf(productInfo);

		return "redirect:productList.do";
	}

	/**
	 * 상품 다중 삭제
	 */
	@RequestMapping(value = "/admin/deleteProductsInf.do", method = RequestMethod.POST)
	public String deleteProductsInfo(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		StringTokenizer st = new StringTokenizer(productInfo.getUnit_chk(), ",");

		while (st.hasMoreTokens()) { // 반활할 토큰이 있는가? true/false;
			productInfo.setPrdCd(st.nextToken());
			productManageService.deleteProductInf(productInfo);

		}

		return "redirect:productList.do";
	}

	/**
	 * 상품 다중 수정
	 */
	@RequestMapping(value = "/admin/modifyProductsInf.do", method = RequestMethod.POST)
	public String modifyProductsInfo(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		StringTokenizer st = new StringTokenizer(productInfo.getUnit_chk(), ",");

		while (st.hasMoreTokens()) { // 반활할 토큰이 있는가? true/false;
			productInfo.setPrdCd(st.nextToken());
			productManageService.manageProductInf(productInfo);

		}

		return "redirect:productList.do";
	}

	/**
	 * 이미지 찾기 폼
	 */
	@RequestMapping(value = "/admin/searchProductImg.do", method = RequestMethod.POST)
	public String searchProductImg(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model) {

		return "redirect:productList.do";
	}

	/**
	 * 이미지 찾기 처리
	 */
	@RequestMapping(value = "/admin/searchProductImgProc.do", method = RequestMethod.POST)
	public String searchProductImgProc(
			@ModelAttribute("productInfo") ProductInfo productInfo,
			BindingResult result, Model model) {

		return "redirect:productList.do";
	}

	/**
	 * 상품 관리-이미지삭제
	 */
	@RequestMapping(value = "/admin/manageProductImgDelProc.do", method = RequestMethod.GET)
	public String deleteImgProc(
			@ModelAttribute("attFileInfo") AttachFileInfo attFileInfo,
			BindingResult result, Model model, String idx, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		StringTokenizer st = new StringTokenizer(idx, ",");

		String index = st.nextToken();
		int i = Integer.parseInt(index);
		attFileInfo.setIdx(i);
		String prdCd = st.nextToken();

		attFileManageService.deleteAttachFileInf(attFileInfo);

		String redi = "redirect:productManagement.do?pCd=" + prdCd;
		return redi;
	}

	/**
	 * 배송-배송목록
	 */
	@RequestMapping(value = "/admin/transferList.do", method = RequestMethod.GET)
	public String transferList(@ModelAttribute("transferInfo") TransferInfo transferInfo,BindingResult result, Model model, HttpSession session, String page) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		PageDivision pd = new PageDivision();

		List<TransferInfo> transferList = new ArrayList<TransferInfo>();

		transferList = transferService.transferList(transferInfo);

		if (StringUtils.isEmpty(page))
			pd.pageNum("1");
		else
			pd.pageNum(page);
		pd.setItemNum(10);
		pd.setTrList(transferList);

		model.addAttribute("transferList",pd.getTrList());
		model.addAttribute("endNum", pd.getEndPageNum());
		return "admin/product/transferList";
	}

	/**
	 * 배송-배송등록
	 */
	@RequestMapping(value = "/admin/transferRegister.do", method = RequestMethod.GET)
	public String transferRegister(@ModelAttribute("transferInfo") TransferInfo transferInfo,BindingResult result, Model model, HttpSession session) {

	
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		return "admin/product/transferRegister";
	}

	@RequestMapping(value = "/admin/transferRegisterProc.do", method = RequestMethod.POST)
	public String transferRegisterProc(@ModelAttribute("transferInfo") TransferInfo transferInfo,BindingResult result, Model model, HttpSession session) {

		transferInfo.settContents(transferInfo.getContent());
		transferService.transferInsert(transferInfo);

		return "admin/product/transferRegister";
	}

	/**
	 * 배송-배송수정
	 */
	@RequestMapping(value = "/admin/transferEdit.do", method = RequestMethod.GET)
	public String transferEdit(@ModelAttribute("transferInfo") TransferInfo transferInfo,BindingResult result, Model model, HttpSession session) {
	
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		transferInfo = transferService.transferDetail(transferInfo);
		transferInfo.setContent(transferInfo.gettContents());
		model.addAttribute("transferInfo", transferInfo);

		return "admin/product/transferEdit";
	}

	/**
	 * 배송-배송수정처리
	 */
	@RequestMapping(value = "/admin/transferEditProc.do", method = RequestMethod.POST)
	public String transferEditProc(
			@ModelAttribute("transferInfo") TransferInfo transferInfo,
			BindingResult result, Model model, HttpSession session) {
		transferInfo.settContents(transferInfo.getContent());
		transferService.transferEdit(transferInfo);

		return "redirect:transferList.do";
	}

	/**
	 * 배송-배송삭제
	 */
	@RequestMapping(value = "/admin/transferDelete.do", method = RequestMethod.GET)
	public String transferDelete(
			@ModelAttribute("transferInfo") TransferInfo transferInfo,
			BindingResult result, Model model, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		transferService.transDelete(transferInfo);

		return "redirect:transferList.do";
	}

	/**
	 * 제품등록페이지의 배송관련팝업창
	 */
	@RequestMapping(value = "/admin/transferInfoPopup.do", method = RequestMethod.GET)
	public String transferInfoPopup(
			@ModelAttribute("transferInfo") TransferInfo transferInfo,
			BindingResult result, Model model, HttpSession session) {
		// -----------------------------------------------------------------
				// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
				// -----------------------------------------------------------------
				AdminInfo adminSession = (AdminInfo) session
						.getAttribute("adminSession");
				if (adminSession == null) {
					return "redirect:adminLogin.do";
				}
		List<TransferInfo> transferList = new ArrayList<TransferInfo>();

		transferList = transferService.transferList(transferInfo);

		model.addAttribute("transferList", transferList);

		return "admin/product/transferInfoPopup";
	}

	/**
	 * 제품등록페이지의 배송관련팝업창처리
	 */
	@RequestMapping(value = "/admin/transferInfoPopupProc.do", method = RequestMethod.GET)
	public String transferInfoPopupProc(
			@ModelAttribute("transferInfo") TransferInfo transferInfo,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		// -----------------------------------------------------------------
				// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
				// -----------------------------------------------------------------
				AdminInfo adminSession = (AdminInfo) session
						.getAttribute("adminSession");
				if (adminSession == null) {
					return "redirect:adminLogin.do";
				}
		TransferInfo transDetail = null;

		transDetail = transferService.transferDetail(transferInfo);

		redirectAttributes.addFlashAttribute("transDetail", transDetail);

		return "redirect:productRegister.do";
	}

	/*
	 * private List<CategoryInfo> getCategoryListByTypeCd(CategoryInfo
	 * categoryInfo, String ctgCodeType) { List<CategoryInfo> list =
	 * categoryManageService.getCategoryInfList(categoryInfo);
	 * List<CategoryInfo> rstList = new ArrayList<CategoryInfo>(); for
	 * (CategoryInfo each : list) { if
	 * (ctgCodeType.equals(each.getCtgCodeType())) { rstList.add(each); } }
	 * 
	 * return rstList; }
	 */

	@RequestMapping(value = "/admin/adminProductView.do", method = RequestMethod.GET)
	public String adminProductView(@ModelAttribute("productInfo") ProductInfo productInfo,@ModelAttribute("categoryInfo") CategoryInfo categoryInfo,BindingResult result, Model model, HttpSession session) {
		// -----------------------------------------------------------------
		// 1. 세션정보를 확인해서 세션정보가 없을 경우 로그인 페이지로 이동한다.
		// -----------------------------------------------------------------
		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}


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
	

		

		// 상품QnA 페이지
		int currentPage = productInfo.getCurrentPage();

		// 상품이미지보내기
		productInfo = productManageService.getProductInfDetail(productInfo);
		AttachFileInfo attFile = new AttachFileInfo();
		attFile.setAttCdKey(productInfo.getPrdCd());
		List<AttachFileInfo> imgList = attFileManageService
				.getAttFileInfList(attFile);
		model.addAttribute("imgList", imgList);

		// 상품정보보내기
		productInfo = productManageService.getProductInfDetail(productInfo);

		// 상품옵션보내기
		List<ProductInfo> prdOpList = new ArrayList<ProductInfo>();
		prdOpList = productManageService.getProductOptionInfDetail(productInfo);
		int i = 0;

		int[] opIdx = new int[50];
		String[] opKey = new String[50];
		String[] opValue = new String[50];
		
		for (ProductInfo each : prdOpList) {
			
			opKey[i] = each.getPropType();
			if(each.getPropType().equals("01")) productInfo.setPrdColor("y");
			if(each.getPropType().equals("02")) productInfo.setPrdSize("y");
			
			opValue[i] = each.getPropName();
			opIdx[i] = each.getPropIdx();
			i++;
		}
		productInfo.setOptionKey(opKey);
		productInfo.setOptionValue(opValue);

		model.addAttribute("pro", productInfo);

		// ----------------------------------------------------
		// 상품QnA 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(10);
		boardSrchInfo.setBrdCodeType("01");
		boardSrchInfo.setBrdCodeKey(productInfo.getPrdCd());

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService
				.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService
				.getBrdTypTotalCount(boardSrchInfo));

		model.addAttribute("qnaList", boardList);
		model.addAttribute("pageHtml",	getPageHtml(productInfo.getPrdCd(), boardSrchInfo));
		model.addAttribute("lnbList", lnbList);
		model.addAttribute("lnbSList", lnbSList);
		model.addAttribute("middleCode", middleCode);
		model.addAttribute("lMenuDetail", categoryInfo);
		model.addAttribute("chkMiddleCode", chkMiddleCode);
		return "admin/product/adminProductView";
	}

	@RequestMapping(value = "/product/productView.do", method = RequestMethod.GET)
	public String productView(@ModelAttribute("productInfo") ProductInfo productInfo,@ModelAttribute("categoryInfo") CategoryInfo categoryInfo,BindingResult result, Model model, HttpSession session) {
		// CustomerInfo customerSesstion
		// =(CustomerInfo)session.getAttribute("customerSession");
		CustomerInfo cust = (CustomerInfo) session
				.getAttribute("customerSession");
		// 세션체크
		if (cust == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", cust.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
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
		
		// ----------------------------------------------------------
		// LNB 정보 조회 및 중간네비게이션용
		// ----------------------------------------------------------
		List<CategoryInfo> categoryList = categoryManageService.getCategoryInfList(categoryInfo);
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

		

		// 상품QnA 페이지
		int currentPage = productInfo.getCurrentPage();

		// 상품이미지보내기
		productInfo = productManageService.getProductInfDetail(productInfo);
		AttachFileInfo attFile = new AttachFileInfo();
		attFile.setAttCdKey(productInfo.getPrdCd());
		List<AttachFileInfo> imgList = attFileManageService
				.getAttFileInfList(attFile);
		model.addAttribute("imgList", imgList);

		// 상품정보보내기
		productInfo = productManageService.getProductInfDetail(productInfo);

		// 상품옵션보내기
		List<ProductInfo> prdOpList = new ArrayList<ProductInfo>();
		prdOpList = productManageService.getProductOptionInfDetail(productInfo);
		int i = 0;

		int[] opIdx = new int[50];
		String[] opKey = new String[50];
		String[] opValue = new String[50];
		
		for (ProductInfo each : prdOpList) {
			
			opKey[i] = each.getPropType();
			if(each.getPropType().equals("01")) productInfo.setPrdColor("y");
			if(each.getPropType().equals("02")) productInfo.setPrdSize("y");
			
			opValue[i] = each.getPropName();
			opIdx[i] = each.getPropIdx();
			i++;
		}
		productInfo.setOptionKey(opKey);
		productInfo.setOptionValue(opValue);

		model.addAttribute("pro", productInfo);

		// ----------------------------------------------------
		// 상품QnA 가져오기
		// ----------------------------------------------------
		BoardSrchInfo boardSrchInfo = new BoardSrchInfo();
		boardSrchInfo.setSrchBrdTyp(10);
		boardSrchInfo.setBrdCodeType("01");
		boardSrchInfo.setBrdCodeKey(productInfo.getPrdCd());

		// 페이지정보 셋팅
		if (currentPage != 0)
			boardSrchInfo.setCurrentPage(currentPage);

		List<BoardInfo> boardList = boardService
				.getBrdTypBoardList(boardSrchInfo);
		boardSrchInfo.setTotalCount(boardService
				.getBrdTypTotalCount(boardSrchInfo));

		model.addAttribute("qnaList", boardList);
		model.addAttribute("pageHtml",	getPageHtml(productInfo.getPrdCd(), boardSrchInfo));
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("largeInf", largeInf);
		model.addAttribute("lnbList", lnbList);
		model.addAttribute("lnbSList", lnbSList);
		model.addAttribute("middleCode", middleCode);
		model.addAttribute("lMenuDetail", categoryInfo);
		model.addAttribute("chkMiddleCode", chkMiddleCode);
		return "product/productView";
	}

	@RequestMapping(value = "/product/writeQnA.do")
	public String writeQNAByProdCd(String content, String prdCd,
			HttpSession session) {
		CustomerInfo cust = (CustomerInfo) session
				.getAttribute("customerSession");
		if (cust == null) { // 세션체크
		// return "user/errorPage";
			cust = new CustomerInfo();
			cust.setCustId("Guest!");
		}
		
		int brdTyp = 10;// 게시판유형 = 10 (QNA게시판)

		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setBrdTyp(brdTyp);
		boardInfo.setInsUser(cust.getCustId());
		boardInfo.setContent(content);
		boardInfo.setSrchBrdTyp(brdTyp);
		boardInfo.setBrdCodeKey(prdCd);
		boardInfo.setBrdCodeType("01"); // 01=상품QNA

		if (boardService.insertBoard(boardInfo)) {
			// 글이 등록됐음
			System.out.println("ok");
		} else {
			// 글 등록이 안됐음.
			System.out.println("fail");
		}

		String viewName = "redirect:productView.do?prdCd=" + prdCd;
		return viewName;
	}

	/*
	 * @RequestMapping(value="/product/productList.do") public String
	 * productList(@ModelAttribute("productInfo") ProductInfo productInfo,
	 * @ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult
	 * result, Model model){
	 * 
	 * // ---------------------------------------------------------- // 변수선언 //
	 * ----------------------------------------------------------
	 * List<CategoryInfo> middleCode = new ArrayList<CategoryInfo>(); //중분류조회
	 * 
	 * List<CategoryInfo> lnbList = new ArrayList<CategoryInfo>();
	 * List<CategoryInfo> lnbSList = new ArrayList<CategoryInfo>(); //소분류리스트
	 * 
	 * List<ProductInfo> prdLList = new ArrayList<ProductInfo>();
	 * List<ProductInfo> prdMList = new ArrayList<ProductInfo>();
	 * List<ProductInfo> prdSList = new ArrayList<ProductInfo>(); SearchProdInfo
	 * searchProdInfo = new SearchProdInfo();
	 * 
	 * String chkMiddleCode=null; String prdCtgS = null;
	 * 
	 * // ---------------------------------------------------------- // 카테고리 조회
	 * // ----------------------------------------------------------
	 * List<CategoryInfo> categoryList =
	 * categoryManageService.getCategoryInfList(categoryInfo); // product 조회
	 * List<ProductInfo> productList =
	 * productManageService.getProductInfList(searchProdInfo);
	 * 
	 * // ---------------------------------------------------------- // 대분류 정보
	 * 조회 // 사용처는 LNB 대분류 정보용 //
	 * ---------------------------------------------------------- CategoryInfo
	 * largeInf = new CategoryInfo(); if(categoryInfo.getCtgCode() == null &&
	 * chkMiddleCode == null && prdCtgS == null){ largeInf =
	 * categoryManageService.getCategoryInfDetail2(categoryInfo);
	 * 
	 * for(ProductInfo each: productList){
	 * if(largeInf.getCtgCode().equals(each.getPrdCtgL())){ prdLList.add(each);
	 * } }
	 * 
	 * }else { largeInf =
	 * categoryManageService.getCategoryInfDetail(categoryInfo);
	 * 
	 * for(ProductInfo each: productList){
	 * if(largeInf.getCtgCode().equals(each.getPrdCtgL())){ prdLList.add(each);
	 * } }
	 * 
	 * }
	 * 
	 * //중분류리스트 for (CategoryInfo each : categoryList) { if
	 * (largeInf.getCtgCode().equals(each.getCtgPCode())) { lnbList.add(each); }
	 * }
	 * 
	 * 
	 * 
	 * chkMiddleCode = categoryInfo.getCtgMiddleCode() ; prdCtgS =
	 * productInfo.getPrdCtgS();
	 * 
	 * 
	 * //상품 중분류리스트 if(chkMiddleCode != null){ for (CategoryInfo each :
	 * categoryList) { if
	 * (categoryInfo.getCtgMiddleCode().equals(each.getCtgPCode())) {
	 * lnbSList.add(each); } } for(ProductInfo each: productList){
	 * if(chkMiddleCode.equals(each.getPrdCtgM())){ prdMList.add(each); } } }
	 * 
	 * 
	 * 
	 * //상품 소분류리스트 if(prdCtgS != null){ for(ProductInfo each : productList){
	 * if(prdCtgS.equals(each.getPrdCtgS())){ prdSList.add(each); } } }
	 * 
	 * model.addAttribute("categoryList",categoryList);
	 * model.addAttribute("largeInf",largeInf);
	 * model.addAttribute("lnbList",lnbList);
	 * model.addAttribute("lnbSList",lnbSList);
	 * model.addAttribute("middleCode",middleCode);
	 * model.addAttribute("lMenuDetail",categoryInfo);
	 * model.addAttribute("chkMiddleCode",chkMiddleCode);
	 * model.addAttribute("prdCtgS",prdCtgS);
	 * model.addAttribute("prdLList",prdLList);
	 * model.addAttribute("prdMList",prdMList);
	 * model.addAttribute("prdSList",prdSList); return "product/productList";
	 * 
	 * }
	 */

	/**
	 * user-product search
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/product/searchProduct.do")
	public String searchProduct(@ModelAttribute("productInfo") SearchProdInfo searchProdInfo,BindingResult result, Model model, HttpSession session, String page) throws UnsupportedEncodingException {
		// CustomerInfo customerSesstion
		// =(CustomerInfo)session.getAttribute("customerSession");
		CustomerInfo cust = (CustomerInfo) session
				.getAttribute("customerSession");
		// 세션체크
		if (cust == null) {
			return "user/errorPage";
		}
		model.addAttribute("CUST_NAME", cust.getCustNm());
		String customerPoint = (String)session.getAttribute("customerPoint");
		model.addAttribute("CUST_POINT", customerPoint);
		String word = searchProdInfo.getSchWord();
		PageDivision pd = new PageDivision();
		if (StringUtils.isEmpty(page))
			pd.pageNum("1");
		else
			pd.pageNum(page);
		
		pd.setItemNum(6);
		
		if (word != null && !word.isEmpty()) {
			SchWordInfo schWordInfo = new SchWordInfo();
			//if(StringUtils.isEmpty(page)&&Integer.parseInt(page)>1) word = URLDecoder.decode(word, "UTF-8");
			schWordInfo.setUserSchword(word);
			 List<SchWordInfo> schWdList=schWordManageService.getSchWordDtlList(schWordInfo);
			if(schWdList!=null){
				for(SchWordInfo each:schWdList){
					schWordManageService.updateHit(each);
				}
			}
			List<ProductInfo> prdList = productManageService.getProductInfList(searchProdInfo);
			pd.setPrdList(prdList);

			
			List<ProductInfo> resultList = pd.getPrdList();
			model.addAttribute("prdList", resultList);
			model.addAttribute("endNum", pd.getEndPageNum());
			model.addAttribute("preSchWord",word);

			return "product/productSearch";
		} else {
			model.addAttribute("endNum", "1");
			return "product/productSearch";
		}

	}

	/**
	 * 리스트의 하단 페이지를 돌려주는 메소드
	 * 
	 * @param boardSrchModel
	 * @return
	 */
	private String getPageHtml(String prdCd, BaseInfo baseModel) {
		StringBuffer pageHtml = new StringBuffer();
		int startPage = 0;
		int lastPage = 0;
		int prevPage = (baseModel.getCurrentPage() - 1);
		int nextPage = (baseModel.getCurrentPage() + 1);

		int pagesPerPage = baseModel.getPagesPerPage();
		if (pagesPerPage == 0) {
			pagesPerPage = 10;
		}

		// expression page variables
		startPage = ((baseModel.getCurrentPage() - 1) / baseModel
				.getPagesPerPage()) * baseModel.getPagesPerPage() + 1;
		lastPage = startPage + pagesPerPage - 1;

		if (lastPage > (baseModel.getTotalCount() / baseModel.getRowsPerPage())) {
			if ((baseModel.getTotalCount() % baseModel.getRowsPerPage()) == 0) {
				lastPage = (baseModel.getTotalCount() / baseModel
						.getRowsPerPage());
			} else {
				lastPage = (baseModel.getTotalCount() / baseModel
						.getRowsPerPage()) + 1;
			}
		}

		if (prevPage < 1)
			prevPage = 1;
		if (nextPage > lastPage)
			nextPage = lastPage;

		// create page html code
		// pageHtml.append("<div id='paging'>");
		// <a href="#" class="palign1"><img
		// src="<c:url value='/resources/img/common/btn_first.gif'/>"
		// alt="처음으로"></a>
		pageHtml.append("<a class='palign1'><img src='/resources/img/common/btn_first.gif' onclick='fnGotoPage(1);' alt='첫 페이지로 이동' /></a>");
		pageHtml.append("<a class='palign2'><img src='/resources/img/common/btn_prev.gif' onclick='fnGotoPage("
				+ prevPage + ");' alt='이전 페이지로 이동' /></a>");

		for (int i = startPage; i <= lastPage; i++) {
			if (i == baseModel.getCurrentPage()) {
				pageHtml.append("<a href='#' class='on'>" + i + "</a>");
			} else {
				pageHtml.append("<a href='javascript:fnGotoPage(" + i + ");'>"
						+ i + "</a>");
			}

		}

		pageHtml.append("<a class='palign1'><img src='/resources/img/common/btn_next.gif' onclick='fnGotoPage("
				+ nextPage + ");' alt='다음 페이지로 이동' /></a>");
		pageHtml.append("<a class='palign2'><img src='/resources/img/common/btn_end.gif' onclick='fnGotoPage("
				+ lastPage + ");' alt='마지막 페이지로 이동' /></a>");
		// pageHtml.append("</div>");

		return pageHtml.toString();
	}


	/*
	 * 검색어 리스트
	 */
	@RequestMapping(value = "/admin/searchWordList.do", method = RequestMethod.GET)
	public String searchWordList(@ModelAttribute("ProductInfo") SearchProdInfo srchProdInfo,
			BindingResult result, Model model, HttpSession session, String page) {

		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		SchWordInfo all = new SchWordInfo();
		List<SchWordInfo> list = schWordManageService.getSchWordDtlList(all);


		model.addAttribute("list", list);

		
		return "admin/product/searchWordList";
	}

	/*
	 * 검색어 수정 폼
	 */
	@RequestMapping(value = "/admin/searchWordEdit.do", method = RequestMethod.GET)
	public String searchWordList(@ModelAttribute("SchWordInfo") SchWordInfo schWord,BindingResult result, Model model, HttpSession session, String page) {

		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		SchWordInfo resSchWd = schWordManageService.getSchWordDtl(schWord);


		model.addAttribute("resSchWd", resSchWd);

		
		return "admin/product/searchWordEdit";
	}

	/*
	 * 검색어 수정 처리
	 */
	@RequestMapping(value = "/admin/searchWordEditProc.do", method = RequestMethod.POST)
	public String searchWordEditProc(@ModelAttribute("SchWordInfo") SchWordInfo schWord,BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

		AdminInfo adminSession = (AdminInfo) session
				.getAttribute("adminSession");
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}

		schWordManageService.updateSchWord(schWord);


		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		
		return "redirect:searchWordEdit.do?swRank="+schWord.getSwRank();
	}
}
