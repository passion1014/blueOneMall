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

import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IAttachFileManageService;
import com.blueone.common.util.DateUtil;
import com.blueone.common.util.FileUploadUtility;
import com.blueone.common.util.Utility;
import com.blueone.product.domain.AdImgInfo;
import com.blueone.product.domain.ProductInfo;
import com.blueone.product.service.IAdImgService;

@Controller
public class MainImgController {
	
	
	@Autowired IAdImgService adImgService;

	//메인화면에 메인이미지 등록
			@RequestMapping(value="/admin/adminDesign.do")
			public String adminMdImg(){
				return "admin/admin/adminDesign";
			}
			
			//메인화면에 메인이미지 등록처리
			@RequestMapping(value="/admin/adminDesignProc.do", method = RequestMethod.POST)
			public String adminMdImgProc(@ModelAttribute("adImgInfo") AdImgInfo adImgInfo , BindingResult result, Model model,MultipartFile file)throws FileNotFoundException, IOException{
				
				
				
				String savePath = null;
				String saveFilename = null;
				String fullSaveFilename = null;
				String fileExt = null;
				AttachFileInfo fileModel = null;
				
				
				
				try {
					
					File dir = new File(savePath);
					
					// 저장할 폴더가 없을경우 생성
					if(!dir.exists()) dir.mkdirs();
					
					// 저장할 파일이 이미 있는지 체크
					fileExt = getFileExt(file.getOriginalFilename());
					saveFilename = Utility.getCurrentDate("yyyyMMddHHmmssSSS") + Math.round(Math.random() * 1000) + "." + fileExt;
					fullSaveFilename = fullSaveFilename + saveFilename;
					File saveFile = new File(savePath + saveFilename);
				
					
					file.transferTo(saveFile);
					
					adImgInfo = new AdImgInfo();
					adImgInfo.setBnImg1(fullSaveFilename);
					
					
				} catch(FileNotFoundException fe) {
					fe.printStackTrace();
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
						
				adImgService.insertAdImg(adImgInfo);

				return "redirect:adminBanner.do";
			}
			
			
			
			
			
			
			private String getFileExt(String originalFilename) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			
			
			
			
			
			
			
			
	
	
	
}

