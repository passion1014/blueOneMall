package com.blueone.admin.domain;

import java.io.FileInputStream;

import org.springframework.web.multipart.MultipartFile;

public class AdImgInfo {
	
	private MultipartFile main1Up;
	private MultipartFile main2Up;
	private MultipartFile main3Up;
	private MultipartFile main4Up;
	private MultipartFile main5Up;
	private MultipartFile main6Up;

	private MultipartFile ban1Up;
	private MultipartFile ban2Up;
	private MultipartFile ban3Up;
	private MultipartFile ban4Up;
	private MultipartFile ban5Up;
	private MultipartFile ban6Up;
	private MultipartFile ban7Up;
	
	private String attFilePath;
	
	private String mdUrl1;
	private String mdUrl2;
	private String mdUrl3;
	private String mdUrl4;
	private String mdUrl5;
	private String mdUrl6;
	
	private String mdImg1;
	private String mdImg2;
	private String mdImg3;
	private String mdImg4;
	private String mdImg5;
	private String mdImg6;
	
	private String mdText1;
	private String mdText2;
	private String mdText3;
	private String mdText4;
	private String mdText5;
	private String mdText6;
	
	private String bnUrl1;
	private String bnUrl2;
	private String bnUrl3;
	private String bnUrl4;
	private String bnUrl5;
	private String bnUrl6;
	private String bnUrl7;
	
	private String bnImg1;
	private String bnImg2;
	private String bnImg3;
	private String bnImg4;
	private String bnImg5;
	private String bnImg6;
	private String bnImg7;
	
	private String fieldName;
	private String fileName;
		
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getBan1Up() {
		return ban1Up;
	}
	public void setBan1Up(MultipartFile ban1Up) {
		this.ban1Up = ban1Up;
	}
	public MultipartFile getBan2Up() {
		return ban2Up;
	}
	public void setBan2Up(MultipartFile ban2Up) {
		this.ban2Up = ban2Up;
	}
	public MultipartFile getBan3Up() {
		return ban3Up;
	}
	public void setBan3Up(MultipartFile ban3Up) {
		this.ban3Up = ban3Up;
	}
	public MultipartFile getBan4Up() {
		return ban4Up;
	}
	public void setBan4Up(MultipartFile ban4Up) {
		this.ban4Up = ban4Up;
	}
	public MultipartFile getBan5Up() {
		return ban5Up;
	}
	public void setBan5Up(MultipartFile ban5Up) {
		this.ban5Up = ban5Up;
	}
	public MultipartFile getBan6Up() {
		return ban6Up;
	}
	public void setBan6Up(MultipartFile ban6Up) {
		this.ban6Up = ban6Up;
	}
	public MultipartFile getBan7Up() {
		return ban7Up;
	}
	public void setBan7Up(MultipartFile ban7Up) {
		this.ban7Up = ban7Up;
	}
	public String getBnUrl1() {
		return bnUrl1;
	}
	public void setBnUrl1(String bnUrl1) {
		this.bnUrl1 = bnUrl1;
	}
	public String getBnUrl2() {
		return bnUrl2;
	}
	public void setBnUrl2(String bnUrl2) {
		this.bnUrl2 = bnUrl2;
	}
	public String getBnUrl3() {
		return bnUrl3;
	}
	public void setBnUrl3(String bnUrl3) {
		this.bnUrl3 = bnUrl3;
	}
	public String getBnUrl4() {
		return bnUrl4;
	}
	public void setBnUrl4(String bnUrl4) {
		this.bnUrl4 = bnUrl4;
	}
	public String getBnUrl5() {
		return bnUrl5;
	}
	public void setBnUrl5(String bnUrl5) {
		this.bnUrl5 = bnUrl5;
	}
	public String getBnUrl6() {
		return bnUrl6;
	}
	public void setBnUrl6(String bnUrl6) {
		this.bnUrl6 = bnUrl6;
	}
	public String getBnUrl7() {
		return bnUrl7;
	}
	public void setBnUrl7(String bnUrl7) {
		this.bnUrl7 = bnUrl7;
	}
	public String getBnImg1() {
		return bnImg1;
	}
	public void setBnImg1(String bnImg1) {
		this.bnImg1 = bnImg1;
	}
	public String getBnImg2() {
		return bnImg2;
	}
	public void setBnImg2(String bnImg2) {
		this.bnImg2 = bnImg2;
	}
	public String getBnImg3() {
		return bnImg3;
	}
	public void setBnImg3(String bnImg3) {
		this.bnImg3 = bnImg3;
	}
	public String getBnImg4() {
		return bnImg4;
	}
	public void setBnImg4(String bnImg4) {
		this.bnImg4 = bnImg4;
	}
	public String getBnImg5() {
		return bnImg5;
	}
	public void setBnImg5(String bnImg5) {
		this.bnImg5 = bnImg5;
	}
	public String getBnImg6() {
		return bnImg6;
	}
	public void setBnImg6(String bnImg6) {
		this.bnImg6 = bnImg6;
	}
	public String getBnImg7() {
		return bnImg7;
	}
	public void setBnImg7(String bnImg7) {
		this.bnImg7 = bnImg7;
	}
	public String getMdImg1() {
		return mdImg1;
	}
	public void setMdImg1(String mdImg1) {
		this.mdImg1 = mdImg1;
	}
	public String getMdImg2() {
		return mdImg2;
	}
	public void setMdImg2(String mdImg2) {
		this.mdImg2 = mdImg2;
	}
	public String getMdImg3() {
		return mdImg3;
	}
	public void setMdImg3(String mdImg3) {
		this.mdImg3 = mdImg3;
	}
	public String getMdImg4() {
		return mdImg4;
	}
	public void setMdImg4(String mdImg4) {
		this.mdImg4 = mdImg4;
	}
	public String getMdImg5() {
		return mdImg5;
	}
	public void setMdImg5(String mdImg5) {
		this.mdImg5 = mdImg5;
	}
	
	public MultipartFile getMain1Up() {
		return main1Up;
	}
	public void setMain1Up(MultipartFile main1Up) {
		this.main1Up = main1Up;
	}
	public MultipartFile getMain2Up() {
		return main2Up;
	}
	public void setMain2Up(MultipartFile main2Up) {
		this.main2Up = main2Up;
	}
	public MultipartFile getMain3Up() {
		return main3Up;
	}
	public void setMain3Up(MultipartFile main3Up) {
		this.main3Up = main3Up;
	}
	public MultipartFile getMain4Up() {
		return main4Up;
	}
	public void setMain4Up(MultipartFile main4Up) {
		this.main4Up = main4Up;
	}
	public MultipartFile getMain5Up() {
		return main5Up;
	}
	public void setMain5Up(MultipartFile main5Up) {
		this.main5Up = main5Up;
	}
	public String getMdUrl1() {
		return mdUrl1;
	}
	public void setMdUrl1(String mdUrl1) {
		this.mdUrl1 = mdUrl1;
	}
	public String getMdUrl2() {
		return mdUrl2;
	}
	public void setMdUrl2(String mdUrl2) {
		this.mdUrl2 = mdUrl2;
	}
	public String getMdUrl3() {
		return mdUrl3;
	}
	public void setMdUrl3(String mdUrl3) {
		this.mdUrl3 = mdUrl3;
	}
	public String getMdUrl4() {
		return mdUrl4;
	}
	public void setMdUrl4(String mdUrl4) {
		this.mdUrl4 = mdUrl4;
	}
	public String getMdUrl5() {
		return mdUrl5;
	}
	public void setMdUrl5(String mdUrl5) {
		this.mdUrl5 = mdUrl5;
	}
	public String getAttFilePath() {
		return attFilePath;
	}
	public void setAttFilePath(String attFilePath) {
		this.attFilePath = attFilePath;
	}
	public MultipartFile getMain6Up() {
		return main6Up;
	}
	public void setMain6Up(MultipartFile main6Up) {
		this.main6Up = main6Up;
	}
	public String getMdUrl6() {
		return mdUrl6;
	}
	public void setMdUrl6(String mdUrl6) {
		this.mdUrl6 = mdUrl6;
	}
	public String getMdImg6() {
		return mdImg6;
	}
	public void setMdImg6(String mdImg6) {
		this.mdImg6 = mdImg6;
	}
	public String getMdText1() {
		return mdText1;
	}
	public void setMdText1(String mdText1) {
		this.mdText1 = mdText1;
	}
	public String getMdText2() {
		return mdText2;
	}
	public void setMdText2(String mdText2) {
		this.mdText2 = mdText2;
	}
	public String getMdText3() {
		return mdText3;
	}
	public void setMdText3(String mdText3) {
		this.mdText3 = mdText3;
	}
	public String getMdText4() {
		return mdText4;
	}
	public void setMdText4(String mdText4) {
		this.mdText4 = mdText4;
	}
	public String getMdText5() {
		return mdText5;
	}
	public void setMdText5(String mdText5) {
		this.mdText5 = mdText5;
	}
	public String getMdText6() {
		return mdText6;
	}
	public void setMdText6(String mdText6) {
		this.mdText6 = mdText6;
	}
	
	
	
	
	
	

	
	

}
