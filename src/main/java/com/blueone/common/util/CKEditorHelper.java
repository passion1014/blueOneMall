package com.blueone.common.util;

import java.util.ArrayList;
import java.util.List;

import com.ckeditor.CKEditorConfig;

public class CKEditorHelper {
//	private final static String imgUploadUrl = Configuration.getInstance().getProperty("editor.config.filebrowserImageUploadUrl");
//	private final static String fullUrlImgUploadUrl = Configuration.getInstance().getProperty("editor.config.fullUrl.filebrowserImageUploadUrl");
	private final static String imgUploadUrl = "/editor/uploadImage.do";
	private final static String fullUrlImgUploadUrl = "editor.config.fullUrl.filebrowserImageUploadUrl";
	
	/**
	 * CKEditor의 기본 설정을 돌려주는 메소드
	 * @return
	 */
	public static CKEditorConfig getDefaultConfig() {
		return getDefaultConfig(760, 420, null, imgUploadUrl);
	}
	
	public static CKEditorConfig getDefaultConfig(int width, int height) {
		return getDefaultConfig(width, height, null, imgUploadUrl);
	}
	
	public static CKEditorConfig getMailConfig(int width, int height) {
		return getDefaultConfig(width, height, null, fullUrlImgUploadUrl);
	}
	
	public static CKEditorConfig getDefaultConfig(int width, int height, String imgBrowseUrl,  String imgUploadUrl) {
		CKEditorConfig config = new CKEditorConfig();
		List<List<String>> list = new ArrayList<List<String>>();
		
		list.add(Utility.toArrayList(new String[]{
				"Source","-","DocProps","Preview"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Font","FontSize"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Bold","Italic","Strike"
		}));
		list.add(Utility.toArrayList(new String[]{
				"TextColor","BGColor"
		}));

		list.add(Utility.toArrayList(new String[]{
				"Image","Table","HorizontalRule"
		}));


		list.add(Utility.toArrayList(new String[]{
				"NumberedList","BulletedList","-","Outdent","Indent","-","JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"
		}));
		
		config.addConfigValue("toolbar", list);
		config.addConfigValue("width", width);
		config.addConfigValue("height", height);
		if (imgBrowseUrl != null) config.addConfigValue("filebrowserImageBrowseUrl", imgBrowseUrl);
		if (imgUploadUrl != null) config.addConfigValue("filebrowserImageUploadUrl", imgUploadUrl);
		return config;
	}
	
	
	/**
	 * CKEditor의 기본 설정을 돌려주는 메소드
	 * @return
	 */
	public static CKEditorConfig getConfig() {
		CKEditorConfig config = new CKEditorConfig();
		List<List<String>> list = new ArrayList<List<String>>();
		
		list.add(Utility.toArrayList(new String[]{
				"Source","-","DocProps","Preview","Print","-","Templates"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Cut","Copy","Paste","PasteText","PasteFromWord","-","Undo","Redo"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Find","Replace","-","SelectAll","-","Scayt"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Image","Flash","Table","HorizontalRule","Smiley","SpecialChar","Iframe"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Font","FontSize"
		}));
		list.add(Utility.toArrayList(new String[]{
				"TextColor","BGColor"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Bold","Italic","Strike","-","RemoveFormat"
		}));
		list.add(Utility.toArrayList(new String[]{
				"NumberedList","BulletedList","-","Outdent","Indent","-","Blockquote","-","JustifyLeft","JustifyCenter","JustifyRight","JustifyBlock"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Link","Unlink"
		}));
		list.add(Utility.toArrayList(new String[]{
				"Maximize","-","About"
		}));
		
		config.addConfigValue("toolbar", list);
		config.addConfigValue("width","500");
		//config.addConfigValue("filebrowserImageBrowseUrl", "/img");
		//config.addConfigValue("filebrowserImageUploadUrl", "/img");
		return config;
	} 
}
