package com.blueone.common.util;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadUtility {
	
	/**
	 * 파일을 다운로드한다.
	 *
	 * @param request
	 * @param response
	 * @param path 파일이 있는 서버상의 경로
	 * @param fileName 파일명
	 */
	public static void doFileDownload(HttpServletRequest request, HttpServletResponse response, int uploadTyp, String saveFileName, String realFileName)
	{
		String fullName = "";
		try
		{
			String basePath = "";
			
			switch (uploadTyp) {
				case FileUploadUtility.UPLOAD_TYP_BOARD :
					basePath = FileUploadUtility.FILE_UPLOAD_DIR;
					fullName = basePath + "/board/";
					break;
				case FileUploadUtility.UPLOAD_TYP_BOARD_IMAGE :
					basePath = FileUploadUtility.WEBROOT_DIR;
					fullName = basePath + "/userfiles/board/";
					break;
				case FileUploadUtility.UPLOAD_TYP_EDUCATE :
					basePath = FileUploadUtility.FILE_UPLOAD_DIR;
					fullName = basePath + "/educate/";
					break;
				case FileUploadUtility.UPLOAD_TYP_EDUCATE_IMAGE :
					basePath = FileUploadUtility.WEBROOT_DIR;
					fullName = basePath + "/userfiles/educate/";
					break;
				case FileUploadUtility.UPLOAD_TYP_SAMPLE :
					basePath = FileUploadUtility.WEBROOT_DIR;
					fullName = basePath + "/sample/";
					break;
				default : 
			}

			if (!fullName.endsWith("/") && fullName.endsWith("\\"))
			{
				fullName += "/";
			}

			fullName += saveFileName;

			RandomAccessFile m_file = new RandomAccessFile(fullName, "r");

			response.reset();

			// mime type의 설정
			String iegbn = request.getHeader("user-agent");
			if (iegbn == null)
			{
				iegbn = "";
			}
			response.setContentType("application;");

			if (iegbn.indexOf("MSIE 5.5") != -1)
			{
				response.setHeader("Content-Type", "doesn/matter; charset=euc-kr");
			}
			else
			{
				response.setHeader("Content-Type", "application/octet-stream; charset=euc-kr");
			}

			// response.setHeader("Content-Disposition", "attachment; filename=" + Utility.urlEncode(fileName) + ";");
			// 다운로드시 한글이 깨지는 문제가 발생하여 Encoding하는 부분 변경
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(realFileName.getBytes("EUC-KR"), "8859_1") + ";");

			ServletOutputStream os = response.getOutputStream();
			int binaryRead;

			// fullpath에 있는 파일을 Byte로 읽어서 OutputStream에 Write한다
			while ((binaryRead = m_file.read()) != -1)
			{
				os.write(binaryRead);
			}
			os.close();
		}
		catch (FileNotFoundException fe)
		{
			fe.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}//End class
