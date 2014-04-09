package com.blueone.common.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blueone.board.domain.BoardAttachFileInfo;
import com.blueone.common.domain.AttachFileInfo;
import com.blueone.common.service.IFileMngService;

@Controller
@RequestMapping("/file")
public class FileMngController {

	@Autowired IFileMngService fileMngService;
	
	/**
	 * 다음오픈에디터에서 사용하는 파일업로드 컨트롤러
	 */
	@RequestMapping("/fileUpload.do")
	public String fileUpload(@ModelAttribute("attachFileInfo") AttachFileInfo attachFileInfo) {
		
		attachFileInfo = fileMngService.fileUpload(attachFileInfo);
		
		// redirect로 값을 넘길경우 Post방식으로 넘어가지 않는다.
		// 그래서 Get방식으로 셋팅.
		StringBuilder viewSb = new StringBuilder("redirect:/resources/editor/pages/trex/image_upload.jsp");
		viewSb.append("?imageurl=" + attachFileInfo.getSaveFileName());		// 이미지경로
		viewSb.append("&filename=" + attachFileInfo.getRealFileName());		// 원래파일명
		viewSb.append("&filesize=" + attachFileInfo.getFileSize());			// 파일크기
		viewSb.append("&originalurl=" + attachFileInfo.getRealFileName());	// 원래파일명
		viewSb.append("&thumburl=" + attachFileInfo.getRealFileName());
		
		return viewSb.toString();
	}
}
