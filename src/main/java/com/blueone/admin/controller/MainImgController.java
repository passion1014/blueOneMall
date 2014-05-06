package com.blueone.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.admin.domain.AdminInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.DateUtil;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.Utility;
import com.blueone.product.domain.ProductInfo;
import com.blueone.admin.service.IAdImgService;

@SessionAttributes("adminSession")
@Controller
public class MainImgController {
	
	
	@Autowired IAdImgService adImgService;

	//메인 슬라이드 이미지 관리화면
	@RequestMapping(value="/admin/adminDesign.do")
	public String adminMdImg(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo ,BindingResult result, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		adImgInfo = adImgService.getAdImgDtl(adImgInfo);
		
		model.addAttribute("adImgInfo", adImgInfo);
		
		return "admin/admin/adminDesign";
	}
	
	@RequestMapping(value="/admin/adminBanner.do")
	public String adminBnImg(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo ,BindingResult result, Model model, HttpSession session){
		
		AdminInfo adminSession = (AdminInfo)session.getAttribute("adminSession");
		// 세션체크
		if (adminSession == null) {
			return "redirect:adminLogin.do";
		}
		
		adImgInfo = adImgService.getAdImgDtl(adImgInfo);
		
		model.addAttribute("adImgInfo", adImgInfo);
		
		return "admin/admin/adminBanner";
	}
	
	//메인화면에 메인이미지 정보변경처리
	@RequestMapping(value = "/admin/adminDesignProc.do", method = RequestMethod.POST)
	public String adminMdImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException {
		
		
		MultipartFile main1Up = adImgInfo.getMain1Up();		
		if(main1Up != null && !main1Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main1Up,false);
			adImgInfo.setMdImg1(contImg.getAttFilePath());
		}
		
		MultipartFile main2Up = adImgInfo.getMain2Up();		
		if(main2Up != null && !main2Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main2Up,false);
			adImgInfo.setMdImg2(contImg.getAttFilePath());
		}
		
		MultipartFile main3Up = adImgInfo.getMain3Up();
		if(main3Up != null && !main3Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main3Up,false);
			adImgInfo.setMdImg3(contImg.getAttFilePath());
		}
		
		MultipartFile main4Up = adImgInfo.getMain4Up();
		if(main4Up != null && !main4Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main4Up,false);
			adImgInfo.setMdImg4(contImg.getAttFilePath());
		}
		
		MultipartFile main5Up = adImgInfo.getMain5Up();		
		if(main5Up != null && !main5Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main5Up,false);
			adImgInfo.setMdImg5(contImg.getAttFilePath());
		}
		
		MultipartFile main6Up = adImgInfo.getMain6Up();		
		if(main6Up != null && !main6Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main6Up,false);
			adImgInfo.setMdImg6(contImg.getAttFilePath());
		}
		
		MultipartFile ban1Up = adImgInfo.getBan1Up();
		if(ban1Up != null && !ban1Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban1Up,false);
			adImgInfo.setBnImg1(contImg.getAttFilePath());
		}
		
		MultipartFile ban2Up = adImgInfo.getBan2Up();
		if(ban2Up != null && !ban2Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban2Up,false);
			adImgInfo.setBnImg2(contImg.getAttFilePath());
		}
		
		MultipartFile ban3Up = adImgInfo.getBan3Up();
		if(ban3Up != null && !ban3Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban3Up,false);
			adImgInfo.setBnImg3(contImg.getAttFilePath());
		}
		
		MultipartFile ban4Up = adImgInfo.getBan4Up();
		if(ban4Up != null && !ban4Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban4Up,false);
			adImgInfo.setBnImg4(contImg.getAttFilePath());
		}
		
		MultipartFile ban5Up = adImgInfo.getBan5Up();
		if(ban5Up != null && !ban5Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban5Up,false);
			adImgInfo.setBnImg5(contImg.getAttFilePath());
		}
		
		MultipartFile ban6Up = adImgInfo.getBan6Up();
		if(ban6Up != null && !ban6Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban6Up,false);
			adImgInfo.setBnImg6(contImg.getAttFilePath());
		}
		
		MultipartFile ban7Up = adImgInfo.getBan7Up();
		if(ban7Up != null && !ban7Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban7Up,false);
			adImgInfo.setBnImg7(contImg.getAttFilePath());
		}
		
		
		adImgService.updateAdImg(adImgInfo);
		//adImgService.insertAdImg(adImgInfo);
		
		return "redirect:adminDesign.do";
	}
	
	
	
	//메인화면에 베너이미지 정보변경처리
	@RequestMapping(value="/admin/adminBannerProc.do", method = RequestMethod.POST)
	public String adminBnImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException {
		
		
		MultipartFile main1Up = adImgInfo.getMain1Up();		
		if(main1Up != null && !main1Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main1Up,false);
			adImgInfo.setMdImg1(contImg.getAttFilePath());
		}
		
		MultipartFile main2Up = adImgInfo.getMain2Up();		
		if(main2Up != null && !main2Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main2Up,false);
			adImgInfo.setMdImg2(contImg.getAttFilePath());
		}
		
		MultipartFile main3Up = adImgInfo.getMain3Up();
		if(main3Up != null && !main3Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main3Up,false);
			adImgInfo.setMdImg3(contImg.getAttFilePath());
		}
		
		MultipartFile main4Up = adImgInfo.getMain4Up();
		if(main4Up != null && !main4Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main4Up,false);
			adImgInfo.setMdImg4(contImg.getAttFilePath());
		}
		
		MultipartFile main5Up = adImgInfo.getMain5Up();		
		if(main5Up != null && !main5Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main5Up,false);
			adImgInfo.setMdImg5(contImg.getAttFilePath());
		}
		
		MultipartFile main6Up = adImgInfo.getMain6Up();		
		if(main6Up != null && !main6Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main6Up,false);
			adImgInfo.setMdImg6(contImg.getAttFilePath());
		}
		
		MultipartFile ban1Up = adImgInfo.getBan1Up();
		if(ban1Up != null && !ban1Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban1Up,false);
			adImgInfo.setBnImg1(contImg.getAttFilePath());
		}
		
		MultipartFile ban2Up = adImgInfo.getBan2Up();
		if(ban2Up != null && !ban2Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban2Up,false);
			adImgInfo.setBnImg2(contImg.getAttFilePath());
		}
		
		MultipartFile ban3Up = adImgInfo.getBan3Up();
		if(ban3Up != null && !ban3Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban3Up,false);
			adImgInfo.setBnImg3(contImg.getAttFilePath());
		}
		
		MultipartFile ban4Up = adImgInfo.getBan4Up();
		if(ban4Up != null && !ban4Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban4Up,false);
			adImgInfo.setBnImg4(contImg.getAttFilePath());
		}
		
		MultipartFile ban5Up = adImgInfo.getBan5Up();
		if(ban5Up != null && !ban5Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban5Up,false);
			adImgInfo.setBnImg5(contImg.getAttFilePath());
		}
		
		MultipartFile ban6Up = adImgInfo.getBan6Up();
		if(ban6Up != null && !ban6Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban6Up,false);
			adImgInfo.setBnImg6(contImg.getAttFilePath());
		}
		
		MultipartFile ban7Up = adImgInfo.getBan7Up();
		if(ban7Up != null && !ban7Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban7Up,false);
			adImgInfo.setBnImg7(contImg.getAttFilePath());
		}
		
		
		adImgService.updateAdImg(adImgInfo);
		//adImgService.insertAdImg(adImgInfo);
		
		return "redirect:adminBanner.do";
	}
	

	
	
	
	
	
	
	
	
	
	
	//메인이미지 삭제처리
	@RequestMapping(value="/admin/deleteDesignImgProc.do", method = RequestMethod.GET)
	public String deleteDesignImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException {
		
		
		//
		// 이 부분에 물리적 이미지 파일 삭제 로직 구현바람
		//
		
		AdImgInfo resultValue = new AdImgInfo() ;
		
		if(adImgInfo.getFieldName().equals("mdImg1")){
			
			resultValue.setMdImg1("yes");
			
		}else if(adImgInfo.getFieldName().equals("mdImg2")){
			
			resultValue.setMdImg2("yes");
			
		}else if(adImgInfo.getFieldName().equals("mdImg3")){
			
			resultValue.setMdImg3("yes");
			
		}else if(adImgInfo.getFieldName().equals("mdImg4")){
			
			resultValue.setMdImg4("yes");
			
		}else if(adImgInfo.getFieldName().equals("mdImg5")){
			
			resultValue.setMdImg5("yes");
			
		}else if(adImgInfo.getFieldName().equals("mdImg6")){
			
			resultValue.setMdImg6("yes");
			
		}
				
		adImgService.deleteDesignImg(resultValue);
		return "redirect:adminDesign.do";
		
	}
	

	
	//베너이미지 삭제처리
	@RequestMapping(value="/admin/deleteBannerImgProc.do", method = RequestMethod.GET)
	public String deleteBannerImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException {
		
		
		//
		// 이 부분에 물리적 이미지 파일 삭제 로직 구현바람
		//
		
		AdImgInfo resultValue = new AdImgInfo() ;
		
		if(adImgInfo.getFieldName().equals("bnImg1")){
			
			resultValue.setBnImg1("yes");
			
		}else if(adImgInfo.getFieldName().equals("bnImg2")){
			
			resultValue.setBnImg2("yes");
			
		}else if(adImgInfo.getFieldName().equals("bnImg3")){
			
			resultValue.setBnImg3("yes");
			
		}else if(adImgInfo.getFieldName().equals("bnImg4")){
			
			resultValue.setBnImg4("yes");
			
		}else if(adImgInfo.getFieldName().equals("bnImg5")){
			
			resultValue.setBnImg5("yes");
			
		}else if(adImgInfo.getFieldName().equals("bnImg6")){
			
			resultValue.setBnImg6("yes");
			
		}else if(adImgInfo.getFieldName().equals("bnImg7")){
			
			resultValue.setBnImg7("yes");
			
		}
				
		adImgService.deleteDesignImg(resultValue);
		
		return "redirect:adminBanner.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

//			//메인화면 메인이미지와 배너이미지
//			@RequestMapping(value="/admin/adminBanner.do")
//			public String adminBnImg(Model model){
//				
//				int code= (int)(Math.random()*10000)+1;
//				String bnCode= "I"+code;
//				
//				model.addAttribute("bnCode", bnCode);
//				return "admin/admin/adminBanner";
//			}
//			
//			//메인화면에 배너이미지 등록처리
//			@RequestMapping(value="/admin/adminBnImgProc.do", method = RequestMethod.POST)
//			public String adminBnImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException{
//						
//				
//				MultipartFile ban1Up = adImgInfo.getBan1Up();
//				if(ban1Up != null && !ban1Up.isEmpty()) {
//					AttachFileInfo contImg = new AttachFileInfo();
//					FileUploadUtility utilList = new FileUploadUtility();
//					contImg = utilList.doFileUpload(8,ban1Up,false);
//					adImgInfo.setBnImg1(contImg.getAttSaveFileNm());
//				}
//				
//				MultipartFile ban2Up = adImgInfo.getBan2Up();
//				if(ban2Up != null && !ban2Up.isEmpty()) {
//					AttachFileInfo contImg = new AttachFileInfo();
//					FileUploadUtility utilList = new FileUploadUtility();
//					contImg = utilList.doFileUpload(8,ban2Up,false);
//					adImgInfo.setBnImg2(contImg.getAttSaveFileNm());
//				}
//				
//				MultipartFile ban3Up = adImgInfo.getBan3Up();
//				if(ban3Up != null && !ban3Up.isEmpty()) {
//					AttachFileInfo contImg = new AttachFileInfo();
//					FileUploadUtility utilList = new FileUploadUtility();
//					contImg = utilList.doFileUpload(8,ban3Up,false);
//					adImgInfo.setBnImg3(contImg.getAttSaveFileNm());
//				}
//				
//				MultipartFile ban4Up = adImgInfo.getBan4Up();
//				if(ban4Up != null && !ban4Up.isEmpty()) {
//					AttachFileInfo contImg = new AttachFileInfo();
//					FileUploadUtility utilList = new FileUploadUtility();
//					contImg = utilList.doFileUpload(8,ban4Up,false);
//					adImgInfo.setBnImg4(contImg.getAttSaveFileNm());
//				}
//				
//				MultipartFile ban5Up = adImgInfo.getBan5Up();
//				if(ban5Up != null && !ban5Up.isEmpty()) {
//					AttachFileInfo contImg = new AttachFileInfo();
//					FileUploadUtility utilList = new FileUploadUtility();
//					contImg = utilList.doFileUpload(8,ban5Up,false);
//					adImgInfo.setBnImg5(contImg.getAttSaveFileNm());
//				}
//		return "redirect:adminDesign.do";
//	}


//	//메인화면 메인이미지와 배너이미지
//	@RequestMapping(value="/admin/adminBanner.do")
//	public String adminBnImg(){
//		return "admin/admin/adminBanner";
//	}
//	
//	//메인화면에 배너이미지 등록처리
//	@RequestMapping(value="/admin/adminBnImgProc.do", method = RequestMethod.POST)
//	public String adminBnImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException{
//				
//		
//		MultipartFile ban1Up = adImgInfo.getBan1Up();
//		if(ban1Up != null && !ban1Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban1Up,false);
//			adImgInfo.setBnImg1(contImg.getAttSaveFileNm());
//		}
//		
//		MultipartFile ban2Up = adImgInfo.getBan2Up();
//		if(ban2Up != null && !ban2Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban2Up,false);
//			adImgInfo.setBnImg2(contImg.getAttSaveFileNm());
//		}
//		
//		MultipartFile ban3Up = adImgInfo.getBan3Up();
//		if(ban3Up != null && !ban3Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban3Up,false);
//			adImgInfo.setBnImg3(contImg.getAttSaveFileNm());
//		}
//		
//		MultipartFile ban4Up = adImgInfo.getBan4Up();
//		if(ban4Up != null && !ban4Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban4Up,false);
//			adImgInfo.setBnImg4(contImg.getAttSaveFileNm());
//		}
//		
//		MultipartFile ban5Up = adImgInfo.getBan5Up();
//		if(ban5Up != null && !ban5Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban5Up,false);
//			adImgInfo.setBnImg5(contImg.getAttSaveFileNm());
//		}
//
//		MultipartFile ban6Up = adImgInfo.getBan6Up();
//		if(ban6Up != null && !ban6Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban6Up,false);
//			adImgInfo.setBnImg6(contImg.getAttSaveFileNm());
//		}
//		
//		MultipartFile ban7Up = adImgInfo.getBan7Up();
//		if(ban7Up != null && !ban7Up.isEmpty()) {
//			AttachFileInfo contImg = new AttachFileInfo();
//			FileUploadUtility utilList = new FileUploadUtility();
//			contImg = utilList.doFileUpload(8,ban7Up,false);
//			adImgInfo.setBnImg7(contImg.getAttSaveFileNm());
//		}
//		
//		adImgService.insertAdImg2(adImgInfo);
//
//		return "redirect:adminBanner.do";
//	}
	
	
	private String getFileExt(String originalFilename) {
		// TODO Auto-generated method stub
		return null;
	}
			
			
			
			
			
			
			
			
			
			
	
	
	
}

