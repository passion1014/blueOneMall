package com.blueone.category.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

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

import com.blueone.admin.controller.AdminController;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.category.domain.CategoryInfo;
import com.blueone.category.domain.MenuInfo;
import com.blueone.category.service.ICategoryManageService;
import com.blueone.common.util.PageDivision;

@Controller
@SessionAttributes("adminSession")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	ICategoryManageService categoryManageService;
	
	/*
	public  MenuInfo getMenu() {
		
		// 1. 대분류전체조회
		List<CategoryInfo> lFullList = new ArrayList<CategoryInfo>();
		
		// 2. 중분류전체조회
		List<CategoryInfo> mFullList = new ArrayList<CategoryInfo>();
		
		// 3. 소분류전체조회
		List<CategoryInfo> sFullList = new ArrayList<CategoryInfo>();
		
		
		//대 중 소 분류 선별
		for (CategoryInfo large : lFullList) {
			String largeCd = large.getCtgCode();
			
			List<CategoryInfo> mList = new ArrayList<CategoryInfo>();
			for (CategoryInfo mid : mFullList) {
				if (largeCd.equals(mid.getCtgPCode())) {
					mList.add(mid);
				}
			}
			
			large.setChildList(mList);
		}
		
		
	}
	*/
	/**
	 * 관리자 대분류 리스트
	 */
	@RequestMapping(value="/admin/largeTypeList.do", method= RequestMethod.GET)
	public String largeTypeList(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,HttpSession session, String page){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		
		if(adminSession == null){
		return "redirect:adminLogin.do";
		}
		
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
		list = getCategoryListByTypeCd(categoryInfo, "01");
		List<CategoryInfo> list1 = categoryManageService.getCategoryInfList5(categoryInfo);
		PageDivision pd = new PageDivision();
		

		if(StringUtils.isEmpty(page)) pd.pageNum("1");
		else pd.pageNum(page);
		pd.setCtList(list);
		
		model.addAttribute("list1", list1);
		model.addAttribute("list", pd.getCtList(2));
		
		model.addAttribute("endNum",pd.getEndPageNum());
	
		return "admin/product/largeTypeList";
			
	}
	
	
	/**
	 * 관리자 대분류 등록폼
	 */
	@RequestMapping(value="/admin/largeTypeRegister.do", method= RequestMethod.GET)
	public String largeTypeRegister(@ModelAttribute("adminInfo") AdminInfo adminInfo, BindingResult result, Model model){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminSession", adminInfo);
		
		int code= (int)(Math.random()*10000)+1;
		String ctgCode= "L"+code;
		
		model.addAttribute("ctgCode", ctgCode);
		
		return "admin/product/largeTypeRegister";
		
	}
	
		
	/**
	 * 관리자 대분류 등록처리
	 */
	@RequestMapping(value = "/admin/largeTypeRegisterProc.do", method = RequestMethod.POST)
	public String largeTypeRegisterProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
		
		categoryManageService.registCategoryInf(categoryInfo);
		
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		return "redirect:largeTypeRegister.do";
		
	}
	
	
	/**
	 * 관리자 대분류 수정폼
	 */		
	@RequestMapping(value="/admin/largeTypeEdit.do", method=RequestMethod.POST)
	public String largeTypeModify(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		categoryInfo = categoryManageService.getCategoryInfDetail(categoryInfo);
		model.addAttribute("largeTypeObj", categoryInfo);
		
		return "admin/product/largeTypeEdit";
	}
	
	
	
	/**
	 * 관리자 대분류 수정처리
	 */
	@RequestMapping(value = "/admin/editCategoryInfProc.do", method = RequestMethod.POST)
	public String editCategoryInfoProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.editCategoryInf(categoryInfo);
		
		return "redirect:largeTypeList.do";
	}
	
	
	/**
	 * 관리자 대분류 삭제
	 */
	@RequestMapping(value = "/admin/deleteCategoryInf.do", method = RequestMethod.GET)
	public String deleteCategoryInfo(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.deleteCategoryInf(categoryInfo);
		
		return "redirect:largeTypeList.do";
	}
	
	
	/**
	 * 관리자 중분류 리스트
	 */
	@RequestMapping(value="/admin/middleTypeList.do", method= RequestMethod.GET)
	public String middleTypeList(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,HttpSession session, String page){
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		if(adminSession == null){
			return "redirect:adminLogin.do";
		}
		
		List<CategoryInfo> list = categoryManageService.getCategoryInfList3(categoryInfo);
	    
		PageDivision pd = new PageDivision();
		

		if(StringUtils.isEmpty(page)) pd.pageNum("1");
		else pd.pageNum(page);
		pd.setCtList(list);
		
		
		model.addAttribute("list", pd.getCtList(2));
		
		model.addAttribute("endNum",pd.getEndPageNum());

		return "admin/product/middleTypeList";
			
	}

	
	/**
	 * 관리자 중분류 등록폼
	 */
	@RequestMapping(value="/admin/middleTypeRegister.do", method= RequestMethod.GET)
	public String middleTypeRegister(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
				
		int code= (int)(Math.random()*10000)+1;
		String ctgCode= "M"+code;
		
		List<CategoryInfo> rstList = getCategoryListByTypeCd(categoryInfo, "01");
		
		model.addAttribute("ctgMidCode", ctgCode);
		model.addAttribute("ctgList", rstList);
		return "admin/product/middleTypeRegister";
		
	}
	
	/**
	 * 관리자 중분류 등록처리
	 */
	@RequestMapping(value = "/admin/middleTypeRegisterProc.do", method = RequestMethod.POST)
	public String middleTypeRegisterProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,String ctgname ,RedirectAttributes redirectAttributes ) {
		
		
		categoryManageService.registCategoryInf(categoryInfo);
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		return "redirect:middleTypeRegister.do";
		
	}
	
	
	/**
	 * 관리자 중분류 수정폼
	 */		
	@RequestMapping(value="/admin/middleTypeEdit.do", method=RequestMethod.POST)
	public String middleTypeModify(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		categoryInfo = categoryManageService.getCategoryInfDetail(categoryInfo);
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
		model.addAttribute("middleTypeObj", categoryInfo);
		model.addAttribute("list", list);
		return "admin/product/middleTypeEdit";
	}
	
	
                                                                        
	/**
	 * 관리자 중분류 수정처리
	 */
	@RequestMapping(value = "/admin/editMiddleCategoryInfProc.do", method = RequestMethod.POST)
	public String editMiddleCategoryInfoProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.editCategoryInf(categoryInfo);
		
		return "redirect:middleTypeList.do";
	}
	
	
	/**
	 * 관리자 중분류 삭제
	 */
	@RequestMapping(value = "/admin/deleteMiddleCategoryInf.do", method = RequestMethod.GET)
	public String deleteMiddleCategoryInfo(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.deleteCategoryInf(categoryInfo);
		
		return "redirect:middleTypeList.do";
	}
	
//	@RequestMapping(value="/admin/middleTypeRegister.do", method= RequestMethod.GET)
//	public ModelAndView middleTypeRegister2(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
//		
//		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
//				
//		int code= (int)(Math.random()*10000)+1;
//		String ctgCode= "M"+code;
//		
//		// 카테고리타입이 01(대분류)인 것들만 수집
//		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
//		for (CategoryInfo each : list) {
//			if ("01".equals(each.getCtgCodeType())) {
//				rstList.add(each);
//			}
//		}
//
//		// 리턴객체 셋팅
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("ctgCode", ctgCode);
//		mav.addObject("ctgList", rstList);
//		mav.setViewName("admin/product/middleTypeRegister");
//
//		return mav;
//		
//	}

	
	/**
	 * 관리자 소분류 리스트
	 */
	@RequestMapping(value="/admin/smallTypeList.do", method= RequestMethod.GET)
	public String smallTypeList(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,HttpSession session, String page){
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		if(adminSession == null){
		return "redirect:adminLogin.do";
		}
		List<CategoryInfo> list = categoryManageService.getCategoryInfList4(categoryInfo);
	    
		PageDivision pd = new PageDivision();
		

		if(StringUtils.isEmpty(page)) pd.pageNum("1");
		else pd.pageNum(page);
		pd.setCtList(list);
		
		
		model.addAttribute("list", pd.getCtList(2));
		
		model.addAttribute("endNum",pd.getEndPageNum());
		
		return "admin/product/smallTypeList";
			
	}

	
	/**
	 * 관리자 소분류 등록폼
	 */
	@RequestMapping(value="/admin/smallTypeRegister.do", method= RequestMethod.GET)
	public String smallTypeRegister(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		int code= (int)(Math.random()*10000)+1;
		String ctgCode= "S"+code;
		List<CategoryInfo> rstList1 = getCategoryListByTypeCd(categoryInfo, "01");//대분류 lsit
		List<CategoryInfo> rstList2 = getCategoryListByTypeCd(categoryInfo, "02");//중분류 list
		
		model.addAttribute("ctgCode", ctgCode);
		model.addAttribute("ctgList1", rstList1);
		model.addAttribute("ctgList2", rstList2);
		model.addAttribute("categoryInfo",categoryInfo);
		
		
		return "admin/product/smallTypeRegister";
		
	}
	
	/**
	 * 관리자 소분류 등록처리
	 */
	@RequestMapping(value = "/admin/smallTypeRegisterProc.do", method = RequestMethod.POST)
	public String smallTypeRegisterProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model,String ctgname,RedirectAttributes redirectAttributes) {
		
		
		categoryManageService.registCategoryInf(categoryInfo);
		redirectAttributes.addFlashAttribute("reloadVar", "yes");
		model.addAttribute("reloadVar", "yes");
		return "redirect:smallTypeRegister.do";
		
	}
	
	
	/**
	 * 관리자 소분류 수정폼
	 */		
	@RequestMapping(value="/admin/smallTypeEdit.do", method=RequestMethod.GET)
	public String smallTypeEdit(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model){
		
		categoryInfo = categoryManageService.getCategoryInfDetail(categoryInfo);
		List<CategoryInfo> list = categoryManageService.getCategoryInfList4(categoryInfo);
		
		List<CategoryInfo> rstList1 = getCategoryListByTypeCd(categoryInfo, "01");//대분류 list
		List<CategoryInfo> rstList2 = getCategoryListByTypeCd(categoryInfo, "02");//중분류 list
		
		String ctgLargeCode = categoryInfo.getCtgLargeCode();
		
		for(int idx=0; idx < rstList2.size(); idx++) {
			CategoryInfo each = rstList2.get(idx);
			if (!ctgLargeCode.equals(each.getCtgPCode())) {
				rstList2.remove(idx);
				idx--;
			}
		}

		
		model.addAttribute("ctgList1", rstList1);
		model.addAttribute("ctgList2", rstList2);
		model.addAttribute("categoryInfo", categoryInfo);
		model.addAttribute("list", list);
		
		return "admin/product/smallTypeEdit";
	}
	
	
                                                                        
	/**
	 * 관리자 소분류 수정처리
	 */
	@RequestMapping(value = "/admin/editsmallCategoryInfProc.do", method = RequestMethod.POST)
	public String editsmallCategoryInfoProc(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		// ------------------------------------------------------
		// 1. 화면으로부터 받은 입력값을 체크한다.
		// ------------------------------------------------------
		
		// ------------------------------------------------------
		// 2. CategoryInfo 값을 BOM_CATEGORY_TB에 업데이트 한다.
		// ------------------------------------------------------
		categoryManageService.editCategoryInf(categoryInfo);

		
		return "redirect:smallTypeList.do";
	}
	
	
	/**
	 * 관리자 소분류 삭제
	 */
	@RequestMapping(value = "/admin/deletesmallCategoryInf.do", method = RequestMethod.GET)
	public String deletesmallCategoryInfo(@ModelAttribute("categoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.deleteCategoryInf(categoryInfo);
		
		return "redirect:smallTypeList.do";
	}
	
	
	/**
	 * 카테고리 등록
	 */
	@RequestMapping(value = "/registCategoryInf.do", method = RequestMethod.POST)
	public String registCategoryInfo(@ModelAttribute("CategoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.registCategoryInf(categoryInfo);
		
		return "category/result";
	}

	/**
	 * 카테고리 수정
	 */
	@RequestMapping(value = "/editCategoryInf.do", method = RequestMethod.POST)
	public String editCategoryInfo(@ModelAttribute("CategoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.editCategoryInf(categoryInfo);
		
		return "category/result";
	}

	
	/**
	 * 카테고리목록 조회
	 */
	@RequestMapping(value = "/getCategoryList.do", method = RequestMethod.GET)
	public String getListCategoryInfo(@ModelAttribute("CategoryInfo") CategoryInfo categoryInfo, BindingResult result, Model model) {
		categoryManageService.getCategoryInfList(categoryInfo);
		
		return "category/result";
	}
	
	
	/**
	 * 화면에서 콤보박스를 구성하기 위하여 호출하는 서비스
	 * JSON 형식의 데이터로 반환한다.
	 * @return
	 */
	@RequestMapping(value="/admin/categoryListByParent/{ctgPCode}", method= RequestMethod.GET)
	@ResponseBody
	public List<CategoryInfo> getCategoryList4Combo(@PathVariable String ctgPCode) {

		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setCtgPCode(ctgPCode);

//		List<CategoryInfo> list = categoryManageService.getCategoryInfList3(categoryInfo);
		List<CategoryInfo> list = categoryManageService.getCategoryInfListByPCode(categoryInfo);
		
	
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		for (CategoryInfo each : list) {
			if (ctgPCode.equals(each.getCtgPCode())) {
				rstList.add(each);
			}
		}
		
		
		return rstList;
	}
	
	
	
	
	
	
	private List<CategoryInfo> getCategoryListByTypeCd(CategoryInfo categoryInfo, String ctgCodeType) {
		List<CategoryInfo> list = categoryManageService.getCategoryInfList(categoryInfo);
		List<CategoryInfo> rstList = new ArrayList<CategoryInfo>();
		for (CategoryInfo each : list) {
			if (ctgCodeType.equals(each.getCtgCodeType())) {
				rstList.add(each);
			}
		}
		
		return rstList;
	}
	
	

	
	

}
