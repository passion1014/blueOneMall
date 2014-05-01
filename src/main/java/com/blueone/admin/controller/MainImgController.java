package com.blueone.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blueone.admin.domain.AdImgInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.DateUtil;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.Utility;
import com.blueone.product.domain.ProductInfo;
import com.blueone.admin.service.IAdImgService;

@Controller
public class MainImgController {
	
	
	@Autowired IAdImgService adImgService;

	//메인화면에 메인이미지 등록
	@RequestMapping(value="/admin/adminDesign.do")
	public String adminMdImg(Model model){
		
		int code= (int)(Math.random()*10000)+1;
		String imgCode= "I"+code;
		
		model.addAttribute("imgCode", imgCode);
		
		return "admin/admin/adminDesign";
	}
	
	//메인화면에 메인이미지 등록처리
	@RequestMapping(value="/admin/adminDesignProc.do", method = RequestMethod.POST)
	public String adminMdImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException {
		
		
		MultipartFile main1Up = adImgInfo.getMain1Up();
		if(main1Up != null && !main1Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main1Up,false);
			adImgInfo.setMdImg1(contImg.getAttSaveFileNm());
		}
		
		MultipartFile main2Up = adImgInfo.getMain2Up();
		if(main2Up != null && !main2Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main2Up,false);
			adImgInfo.setMdImg2(contImg.getAttSaveFileNm());
		}
		
		MultipartFile main3Up = adImgInfo.getMain3Up();
		if(main3Up != null && !main3Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main3Up,false);
			adImgInfo.setMdImg3(contImg.getAttSaveFileNm());
		}
		
		MultipartFile main4Up = adImgInfo.getMain4Up();
		if(main4Up != null && !main4Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main4Up,false);
			adImgInfo.setMdImg4(contImg.getAttSaveFileNm());
		}
		
		MultipartFile main5Up = adImgInfo.getMain5Up();
		if(main5Up != null && !main5Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,main5Up,false);
			adImgInfo.setMdImg5(contImg.getAttSaveFileNm());
		}
		
		
		
		
		adImgService.insertAdImg(adImgInfo);

		return "redirect:adminDesign.do";
	}


	//메인화면 메인이미지와 배너이미지
	@RequestMapping(value="/admin/adminBanner.do")
	public String adminBnImg(){
		return "admin/admin/adminBanner";
	}
	
	//메인화면에 배너이미지 등록처리
	@RequestMapping(value="/admin/adminBnImgProc.do", method = RequestMethod.POST)
	public String adminBnImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model)throws FileNotFoundException, IOException{
				
		
		MultipartFile ban1Up = adImgInfo.getBan1Up();
		if(ban1Up != null && !ban1Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban1Up,false);
			adImgInfo.setBnImg1(contImg.getAttSaveFileNm());
		}
		
		MultipartFile ban2Up = adImgInfo.getBan2Up();
		if(ban2Up != null && !ban2Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban2Up,false);
			adImgInfo.setBnImg2(contImg.getAttSaveFileNm());
		}
		
		MultipartFile ban3Up = adImgInfo.getBan3Up();
		if(ban3Up != null && !ban3Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban3Up,false);
			adImgInfo.setBnImg3(contImg.getAttSaveFileNm());
		}
		
		MultipartFile ban4Up = adImgInfo.getBan4Up();
		if(ban4Up != null && !ban4Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban4Up,false);
			adImgInfo.setBnImg4(contImg.getAttSaveFileNm());
		}
		
		MultipartFile ban5Up = adImgInfo.getBan5Up();
		if(ban5Up != null && !ban5Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban5Up,false);
			adImgInfo.setBnImg5(contImg.getAttSaveFileNm());
		}

		MultipartFile ban6Up = adImgInfo.getBan6Up();
		if(ban6Up != null && !ban6Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban6Up,false);
			adImgInfo.setBnImg6(contImg.getAttSaveFileNm());
		}
		
		MultipartFile ban7Up = adImgInfo.getBan7Up();
		if(ban7Up != null && !ban7Up.isEmpty()) {
			AttachFileInfo contImg = new AttachFileInfo();
			FileUploadUtility utilList = new FileUploadUtility();
			contImg = utilList.doFileUpload(8,ban7Up,false);
			adImgInfo.setBnImg7(contImg.getAttSaveFileNm());
		}
		
		adImgService.insertAdImg2(adImgInfo);

		return "redirect:adminBanner.do";
	}
	
	
	
	
	
	
	private String getFileExt(String originalFilename) {
		// TODO Auto-generated method stub
		return null;
	}
			
			
			
			
			
			
			
			
			
			
	
	
	
}

