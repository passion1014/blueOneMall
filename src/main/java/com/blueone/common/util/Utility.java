package com.blueone.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;

/**
 * 공통 Utility Class
 *
 * @version $Revision: 1.11 $
 * @author $Author: kwanwool $
 */

public final class Utility
{

	private static final char		DOUBLE_SPACE			= '　';
	
	private static HashMap<String, String> tags = new HashMap<String, String>();

	// 이거 그냥 공백 아니고 전각문자 공백임. 나중에 아스키코드로 바꾸는 것이 바람직할 듯
	// 혹시라도 지우고 그냥 스페이스 넣으면 안됨 (by sik)

	private static final String	DEFAULT_DATE_FORMAT		= "yyyy-MM-dd";

	private static final String	DEFAULT_MONTH_FORMAT	= "yyyy-MM-dd";

	private static final String	DEFAULT_NUMBER_FORMAT	= "#,##0";

	/**
	 * 
	 * @param inputYYMM
	 * @param value
	 * @return
	 */
	public static String getMonth(String inputYYMM, int value)
	{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = getCalendar();

		String yy = inputYYMM.substring(0, 4);
		String mm = inputYYMM.substring(4, 6);

		cal.set(Integer.parseInt(yy), Integer.parseInt(mm) - 1 + value, 1);
		Date d = cal.getTime();

		return sdf.format(d);
	}

	/**
	 * String 에 quotation을 붙인다.
	 *
	 * @param str
	 */
	public static String getQuot(String str)
	{

		if (str == null)
		{
			str = "";
		}
		str = str.trim();
		str = "'" + str + "'";

		return str;
	}

	/**
	 * 주어진 문자열을 int형으로 변환한다. 오류발생시 기존값을 리턴한다.
	 */
	public static int parseInt(String s, int defualtValue)
	{
		try
		{
			return parseNumber(s, DEFAULT_NUMBER_FORMAT).intValue();
		}
		catch (Exception e)
		{
			return defualtValue;
		}
	}

	/**
	 * 주어진 문자열을 int형으로 변환한다. 오류발생시 0을 리턴한다.
	 */
	public static int parseInt(String s)
	{
		return parseInt(s, 0);
	}

	/**
	 * 주어진 문자열을 long형으로 변환한다. 오류발생시 기존값을 리턴한다.
	 */
	public static long parseLong(String s, long defualtValue)
	{
		try
		{
			return parseNumber(s, DEFAULT_NUMBER_FORMAT).longValue();
		}
		catch (Exception e)
		{
			return defualtValue;
		}
	}

	/**
	 * 주어진 문자열을 long형으로 변환한다. 오류발생시 0을 리턴한다.
	 */
	public static long parseLong(String s)
	{
		return parseLong(s, 0);
	}

	/**
	 * 주어진 문자열을 double형으로 변환한다. 오류발생시 기존값을 리턴한다.
	 */
	public static double parseDouble(String s, double defualtValue)
	{
		try
		{
			return parseNumber(s, DEFAULT_NUMBER_FORMAT).doubleValue();
		}
		catch (Exception e)
		{
			return defualtValue;
		}
	}

	/**
	 * 주어진 문자열을 double형으로 변환한다. 오류발생시 0을 리턴한다.
	 */
	public static double parseDouble(String s)
	{
		return parseDouble(s, 0);
	}

	/**
	 * 주어진 문자열을 flat형으로 변환한다. 오류발생시 기존값을 리턴한다.
	 */
	public static float parseFloat(String s, float defualtValue)
	{
		try
		{
			return parseNumber(s, DEFAULT_NUMBER_FORMAT).floatValue();
		}
		catch (Exception e)
		{
			return defualtValue;
		}
	}

	/**
	 * 주어진 문자열을 float 변환한다. 오류발생시 0을 리턴한다.
	 */
	public static float parseFloat(String s)
	{
		return parseFloat(s, 0);
	}

	/**
	 * 주어진 문자열이 NULL또는 공백인지 리턴한다.
	 */
	public static boolean isEmpty(String s)
	{
		if (s == null)
			return true;
		if (s.trim().length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 인자로 받은 문자열 배열이 공백또는 NULL인지 돌려주는 메소드 
	 * @param values 문자열배열
	 * @return
	 */
	public static boolean isEmpty(String[] values)
	{
		if (values == null)
			return true;
		
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null || values[i].trim().length() > 0)
				return false;
		}
		return true;
	}

	public static String makeZip(String fileName, String zipName)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(new File(zipName));
			ZipOutputStream zos = new ZipOutputStream(fos);
			FileInputStream fis;

			byte[] buffer = new byte[1024];

			ZipEntry ze;
			int c;
			String fName = "";

			try
			{
				if (fileName.lastIndexOf("\\") != -1)
				{
					fName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
				}
				else if (fileName.lastIndexOf("/") != -1)
				{
					fName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
				}
				else
				{
					fName = fileName;
				}

				fis = new FileInputStream(new File(fileName));
				ze = new ZipEntry(fName);
				zos.putNextEntry(ze);

				while ((c = fis.read(buffer, 0, 1024)) != -1)
				{
					zos.write(buffer, 0, c);
				}

				fis.close();

			}
			catch (FileNotFoundException fnfe)
			{
				System.out.println("makeZip() fnfe =>" + fnfe.toString());
				return null;
			}
			catch (IOException ioe)
			{
				System.out.println("makeZip() ioe =>" + ioe.toString());
				return null;
			}
			catch (NullPointerException npe)
			{
				System.out.println("makeZip() npe =>" + npe.toString());
				return null;
			}
			catch (Exception se)
			{
				System.out.println("makeZip() se =>" + se.toString());
				return null;
			}
			zos.close();
		}
		catch (Exception e)
		{
			System.out.println("makeZip() e => " + e.toString());
			return null;
		}

		return zipName;
	}

	/**
	 * TRIM
	 *
	 * @param
	 * @return
	 */
	public static String trim(String s)
	{

		if (s == null)
			return "";

		char[] chars = s.toCharArray();
		// 공백이외의 첫문자 검색
		int start = -1;
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] != ' ' && chars[i] != DOUBLE_SPACE & chars[i] != '\t')
			{
				start = i;
				break;
			}
		}
		// 공백이외의 마지막문자 검색
		int end = -1;
		for (int i = chars.length - 1; i >= 0; i--)
		{
			if (chars[i] != ' ' && chars[i] != DOUBLE_SPACE & chars[i] != '\t')
			{
				end = i;
				break;
			}
		}
		if (start == -1)
			return "";
		else
			return new String(chars, start, (end - start + 1));
	}

	/**
	 * 해당 년월의 첫번째 날의 요일을 구한다.
	 *
	 * @param yy 년도, mm 월
	 * @return int(1:일요일 ~ 4(수) ~ 7:토요일
	 */
	public static int getFirstDayWeek(int yy, int mm)
	{
		Calendar now = Calendar.getInstance();
		now.set(yy, mm - 1, 1);
		int dte = now.get(Calendar.DAY_OF_WEEK);

		return dte;
	}

	/**
	 * 해당 년월의 마지막일을 구한다.
	 *
	 * @param yy 년도, mm 월
	 * @return String
	 */
	public static int getLastDayMonth(int yy, int mm)
	{
		Calendar now = Calendar.getInstance();
		now.set(yy, mm - 1, 1);
		int dte = now.getActualMaximum(Calendar.DAY_OF_MONTH);
		return dte;
	}

	public static String delChar(String s, char c)
	{
		if (s == null)
		{
			return "";
		}
		StringBuffer sb = new StringBuffer(s.length());
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] != c)
			{
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}
	public static String delComma(String s)
	{
		return delChar(s, ',');
	}
	
	public static String delDash(String s)
	{
		return delChar(s, '-');
	}
	
	public static String delSpace(String s)
	{
		return delChar(s, ' ');
	}

	/**
	 * 문자열을 Date로 파싱한다.
	 *
	 * @param s
	 * @return
	 */
	public static java.util.Date parseDate(String s)
	{
		return parseDate(s, DEFAULT_DATE_FORMAT);
	}

	public static java.util.Date parseDate(String s, String format)
	{
		java.util.Date d = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			d = sdf.parse(s, new ParsePosition(0));
		}
		catch (Exception e)
		{
			throw new RuntimeException("Date format not valid.");
		}
		return d;
	}

	/**
	 * 문자열형태의 숫자를 입력받아 일정한 format에 의하여 대상 문자열을 format으로 변경시킨다.
	 *
	 * @param number 숫자형태를 가진 대상 문자열
	 * @param format 해당 문자열을 변경시키기 위한 format
	 * @return 변경되어진 Number객체
	 */
	public static Number parseNumber(String number, String format)
	{
		DecimalFormat formatter = new DecimalFormat(format);
		return formatter.parse(number, new ParsePosition(0));
	}

	/**
	 * 문자열형태의 숫자를 입력받아 일정한 format에 의하여 대상 문자열을 int형태의 format으로 변경시킨다.
	 *
	 * @param number 숫자형태를 가진 대상 문자열
	 * @param format 해당 문자열을 변경시키기 위한 format
	 * @return 변경되어진 int형태의 값
	 */
	public static int parseInt(String number, String format)
	{
		return parseNumber(number, format).intValue();
	}

	/**
	 * 문자열형태의 숫자를 입력받아 일정한 format에 의하여 대상 문자열을 long형태의 format으로 변경시킨다.
	 *
	 * @param number 숫자형태를 가진 대상 문자열
	 * @param format 해당 문자열을 변경시키기 위한 format
	 * @return 변경되어진 long형태의 값
	 */
	public static long parseLong(String number, String format)
	{
		return parseNumber(number, format).longValue();
	}

	/**
	 * 문자열형태의 숫자를 입력받아 일정한 format에 의하여 대상 문자열을 float형태의 format으로 변경시킨다.
	 *
	 * @param number 숫자형태를 가진 대상 문자열
	 * @param format 해당 문자열을 변경시키기 위한 format
	 * @return 변경되어진 float형태의 값
	 */
	public static float parseFloat(String number, String format)
	{
		return parseNumber(number, format).floatValue();
	}

	/**
	 * 문자열형태의 숫자를 입력받아 일정한 format에 의하여 대상 문자열을 double형태의 format으로 변경시킨다.
	 *
	 * @param number 숫자형태를 가진 대상 문자열
	 * @param format 해당 문자열을 변경시키기 위한 format
	 * @return 변경되어진 double형태의 값
	 */
	public static double parseDouble(String number, String format)
	{
		return parseNumber(number, format).doubleValue();
	}

	/**
	 * 현재 날짜를 yyyy년 MM월 dd일 HH시 mm분의 형태로 값을 얻어낸다.
	 *
	 * @return yyyy년 MM월 dd일 HH시 mm분의 형태로 바뀐 현재 시간값
	 */
	public static String getTime()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		return sdf.format(d);
	}

	/**
	 * 현재일자를 표준 포맷으로 리턴한다.
	 *
	 * @return 현재일자
	 */
	public static String getCurrentDate()
	{
		return getCurrentDate(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 현재일자를 주어진 포맷으로 리턴한다.
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format)
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * 현재년월을 yyyyMM포맷으로 리턴한다.
	 *
	 * @return
	 */
	public static String getYearMonth()
	{
		String f = "yyyyMM";
		return getCurrentDate(f);
	}

	/**
	 * 현재년도를 리턴한다.
	 *
	 * @return
	 */
	public static String getYear()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(d);
	}

	/**
	 * 현재월을 리턴한다.
	 *
	 * @return
	 */
	public static String getMonth()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(d);
	}

	/**
	 * 오늘을 기준으로 어제의 날짜를 알아낸다
	 *
	 * @return 기본 날짜 형태로 변경되된 문자열값
	 */
	public static String getYesterday(String format)
	{
		Calendar cal = getCalendar();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	public static String getYesterday()
	{
		return getYesterday(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 현재 얻어낸 날짜의 마지막 달을 알아낸다.
	 *
	 * @return yyyy/mm형태로 변경되된 문자열값
	 */
	public static String getLastMonth(String format)
	{
		Calendar cal = getCalendar();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	public static String getLastMonth()
	{
		return getLastMonth(DEFAULT_MONTH_FORMAT);
	}

	/**
	 * GMT기준시간중의 한국표준시를 반환한다.
	 *
	 * return GMT+09:00형태의 대한민국표준시
	 */
	public static Calendar getCalendar()
	{
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(new Date());

		return calendar;
	}

	/**
	 * 문자열을 Date타입으로 변환한다.
	 *
	 * @param s
	 * @return
	 */
	public static Date parseDate2(String s)
	{
		String yy, mm, dd;
		if (s.length() == 10)
		{
			yy = s.substring(0, 4);
			mm = s.substring(5, 7);
			dd = s.substring(8, 10);
		}
		else if (s.length() == 8)
		{
			if (s.indexOf("-") == -1)
			{
				yy = s.substring(0, 4);
				mm = s.substring(4, 6);
				dd = s.substring(6, 8);
			}
			else
			{
				yy = s.substring(0, 2);
				mm = s.substring(3, 5);
				dd = s.substring(6, 8);
			}
		}
		else if (s.length() == 6)
		{
			yy = s.substring(0, 2);
			mm = s.substring(3, 5);
			dd = s.substring(5, 7);
		}
		else
		{
			throw new RuntimeException("invalid year" + s);
		}
		int y, m, d;
		y = Utility.parseInt(yy);
		if (y <= 0)
		{
			throw new RuntimeException("invalid year" + s);
		}
		if (y < 50)
			y += 2000;
		else if (y >= 50 && y < 100)
			y += 1900;

		m = Utility.parseInt(mm);
		if (m < 0 || m > 12)
		{
			throw new RuntimeException("invalid month" + s);
		}

		d = Utility.parseInt(dd);
		if (d < 0 || d > 31)
		{
			throw new RuntimeException("invalid day" + s);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(y, m, d);
		return cal.getTime();
	}

	/**
	 * 두 날짜 사이에 몇일이 있는지 계산한다.
	 *
	 * @param start 시작일자
	 * @param end 끝일
	 * @return
	 */
	public static int getDaysBetween(String start, String end)
	{
		Date d1 = parseDate2(start);
		Date d2 = parseDate2(end);
		return (int) (Math.abs(d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 일반문자열형태를 HTML의 형태로 바꾼다. 기본적으로 &lt; &gt; 등의 문자는 실제 HTML형태로 변경시 태그를 망가뜨리는
	 * 대상이 될수 있으므로 각각의 tag에 영향을 미치지않게끔 문자를 replace시키도록 한다.
	 *
	 * @param fromText HTML형태로 변경시킬 문자열
	 * @return HTML포맷으로 변경되어진 문자열
	 */
	public static String toHTML(String fromText)
	{
		return toHTML(fromText, false);
	}

	/**
	 * 일반문자열형태를 HTML의 형태로 바꾼다. 기본적으로 &lt; &gt; 등의 문자는 실제 HTML형태로 변경시 태그를 망가뜨리는
	 * 대상이 될수 있으므로 각각의 tag에 영향을 미치지않게끔 문자를 replace시키도록 한다.
	 *
	 * @param fromText HTML형태로 변경시킬 문자열
	 * @param replyMode HTML mode or Reply mode를 지정하는 값
	 * @return HTML포맷으로 변경되어진 문자열
	 */
	private static String toHTML(String fromText, boolean replyMode)
	{
		if (fromText == null)
			return "";

		StringBuffer sb = new StringBuffer(100);
		char[] buf = fromText.toCharArray();
		if (replyMode)
			sb.append("> ");
		for (int i = 0; i < buf.length; i++)
		{
			switch (buf[i])
			{
			/*
			 * case ' ': sb.append("&nbsp;"); break;
			 */
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&#39;");
				break;
			case '\t':
				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
				break;
			case '\n':
				sb.append("<br>\n");
				break;
			default:
				sb.append(buf[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 인자로 받은 문자열을 HTML로 변환하여 돌려주는 메소드
	 * @param str 문자열
	 * @return
	 */
	public static String toPebsHtml(String str) {
		if (str == null) {
			return str;
		}

		int workPoint = -1;      // 작업하는 포인트
		int lastPoint = 0;       // 작업의 마지막 포인트
		String tempStr = "";	 // 작업중인 문자열
		String tempTag = "";	 // 작업중인 태그명

		StringBuffer rStrBuf = new StringBuffer();

		workPoint = str.indexOf('<', workPoint);

		while (workPoint != -1) {

			// HTML로 변환해야할 작업이 있으면 처리한다.
			if (workPoint > lastPoint) {
				tempStr = str.substring(lastPoint, workPoint);
				rStrBuf.append(toHTML(tempStr));
				lastPoint = workPoint;
			}

			tempTag = getTagName(str, workPoint);

			if (isTag(tempTag)) {
				workPoint = str.indexOf('>', workPoint);

				rStrBuf.append(str.substring(lastPoint, workPoint + 1));
				lastPoint = workPoint + 1;
			}

			workPoint = str.indexOf('<', workPoint + 1);

		}//End while

		// 남은 문자열을 담는다.
		tempStr = str.substring(lastPoint, str.length());
		rStrBuf.append(toHTML(tempStr));

		return rStrBuf.toString();
	}

	/**
	 * 인자로 받은 문자열에서 인자로 받은 시작 인덱스부터 태그명칭 찾아 돌려준다.
	 * @param str
	 * @param findIndex
	 * @return
	 */
	private static String getTagName(String str, int findIndex) {
		if (str == null) {
			return "";
		}

		char ch;
		int length = str.length();
		String returnValue = "";

		for (int i = findIndex + 1; i < length; i++) {
			ch = str.charAt(i);

			if (ch == ' ' || ch == '\t' || ch == '>') {
				returnValue = str.substring(findIndex + 1, i);
				break;
			}
			
			if(i == length - 1) {
				returnValue = str.substring(findIndex + 1);
			}
		}

		return returnValue;
	}

	/**
	 * 인자로 받은 문자열이 태그명이 맞는지 돌려주는 메소드
	 * @param str
	 * @return
	 */
	private static boolean isTag(String str) {
		if (str.length() < 1) {
			return false;
		}
		
		// 태그집합을 초기화한다.
		if (tags.size() < 1) {
			Tag[] tempTags = HTML.getAllTags();
			for (int i = 0; i < tempTags.length; i++) {
				tags.put(tempTags[i].toString(), "");
			}
			
			tags.put("iframe", "");
		}
		
		str = str.charAt(0) == '/' ? str.replace("/", "") : str;		
		return tags.containsKey(str.toLowerCase());
	}

	/**
	 * 
	 * @param buffer
	 * @param oldWord
	 * @param newWord
	 * @return
	 */
	public static String replace(String buffer, String oldWord, String newWord)
	{
		int start = 0, end = 0, index = 0;

		while (start <= buffer.length())
		{

			start = buffer.indexOf(oldWord, index);
			end = start + oldWord.length();
			index = start + newWord.length();

			if (start == -1)
				break;

			buffer = buffer.substring(0, start) + newWord + buffer.substring(end);
		}
		return buffer;
	}

	/**
	 * double 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value double
	 * @return String
	 */
	public static String format(double value)
	{
		java.text.DecimalFormat df = new DecimalFormat(DEFAULT_NUMBER_FORMAT);
		return df.format(value);
	}

	/**
	 * double 값을 소수점이하 자리수를 정해 변환한다.
	 *
	 * @param value double
	 * @param decimalCount int 소수점이하 자리수
	 * @return String
	 */
	public static String format(double value, int decimalCount)
	{
		StringBuffer sb = new StringBuffer(DEFAULT_NUMBER_FORMAT);
		if (decimalCount > 0)
		{
			sb.append('.');
			for (int i = 0; i < decimalCount; i++)
			{
				sb.append('0');
			}
		}
		java.text.DecimalFormat df = new DecimalFormat(sb.toString());
		return df.format(value);
	}

	/**
	 * double 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value double
	 * @param numberFormat String
	 * @return String
	 */
	public static String format(double value, String numberFormat)
	{
		java.text.DecimalFormat df = new DecimalFormat(numberFormat);
		return df.format(value);
	}

	/**
	 * float 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value float
	 * @return String
	 */
	public static String format(float value)
	{
		java.text.DecimalFormat df = new DecimalFormat(DEFAULT_NUMBER_FORMAT);
		return df.format(value);
	}

	/**
	 * float 값을 소수점이하 자리수를 정해 변환한다.
	 *
	 * @param value float
	 * @param decimalCount int 소수점이하 자리수
	 * @return String
	 */
	public static String format(float value, int decimalCount)
	{
		StringBuffer sb = new StringBuffer(DEFAULT_NUMBER_FORMAT);
		if (decimalCount > 0)
		{
			sb.append('.');
			for (int i = 0; i < decimalCount; i++)
			{
				sb.append('0');
			}
		}
		java.text.DecimalFormat df = new DecimalFormat(sb.toString());
		return df.format(value);
	}

	/**
	 * float 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value float
	 * @param numberFormat String
	 * @return String
	 */
	public static String format(float value, String numberFormat)
	{
		java.text.DecimalFormat df = new DecimalFormat(numberFormat);
		return df.format(value);
	}

	/**
	 * int 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value int
	 * @return String
	 */
	public static String format(int value)
	{
		java.text.DecimalFormat df = new DecimalFormat(DEFAULT_NUMBER_FORMAT);
		return df.format(value);
	}

	/**
	 * int 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value int
	 * @param numberFormat String
	 * @return String
	 */
	public static String format(int value, String numberFormat)
	{
		java.text.DecimalFormat df = new DecimalFormat(numberFormat);
		return df.format(value);
	}

	/**
	 * data를 기본 포맷으로 변환한다..
	 *
	 * @param data String, mask String
	 * @return String
	 */
	public static String format(java.util.Date date)
	{
		return format(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * data를 지정된 포맷으로 변환한다..
	 *
	 * @param data String, mask String
	 * @return String
	 */
	public static String format(java.util.Date date, String dateFormat)
	{
		if (date == null)
			return "";
		java.text.SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * long 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value long
	 * @return String
	 */
	public static String format(long value)
	{
		java.text.DecimalFormat df = new DecimalFormat(DEFAULT_NUMBER_FORMAT);
		return df.format(value);
	}

	/**
	 * long 값을 기본 숫자 포맷으로 변환한다.
	 *
	 * @param value long
	 * @param numberFormat String
	 * @return String
	 */
	public static String format(long value, String numberFormat)
	{
		java.text.DecimalFormat df = new DecimalFormat(numberFormat);
		return df.format(value);
	}

	/**
	 * data를 지정된 mask로 설정한다.
	 *
	 * @param data String, mask String
	 * @return String
	 */
	public static String format(String data, String mask)
	{
		String temp = "";
		if (mask.equals(""))
		{
			return data;
		}
		StringBuffer sb = new StringBuffer();
		try
		{
			int index = 0;
			for (int i = 0; i < mask.length(); i++)
			{
				switch (mask.charAt(i))
				{
				case '9':
				case '#':
				case 'x':
				case 'X':
					if (index < data.length())
					{
						sb.append(data.substring(index, index + 1));
						index++;
					}
					break;
				case '^': // 글자를 건너뛴다 added by sik
					index++;
					break;
				default:
					sb.append(mask.substring(i, i + 1));
				}
			}
			return sb.toString();
		}
		catch (Throwable e)
		{
			e.printStackTrace(System.out);
		}
		return temp;
	}

	/**
	 * 현 날짜를 yyyyMMdd 현식의 String 으로 리턴한다.
	 */
	public static String getShortDateString()
	{

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * 현 날짜를 입력한 형태의 String 으로 리턴한다.
	 */
	public static String getSystemDate(String pattern)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, java.util.Locale.KOREA);
		return formatter.format(new Date());
	}

	/**
	 * 현 시간을 HHmmss 형식의 String 으로 리턴한다.
	 */
	public static String getShortTimeString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HHmmss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * 7자리날짜를 8자리로 바꾼다.
	 *
	 * @param data String
	 * @return String
	 */
	public static String getParseYYYYMMDD(String yyymmdd)
	{
		String str = null;
		if (yyymmdd == null || yyymmdd.trim().length() == 0)
		{
			return "";
		}

		if (yyymmdd.trim().length() == 7)
		{
			str = yyymmdd.substring(0, 1);
			if (str.equals("1"))
				return "20" + yyymmdd.substring(1);
			if (str.equals("0"))
				return "19" + yyymmdd.substring(1);
		}
		return yyymmdd;
	}

	/**
	 * 8자리날짜를 7자리로 바꾼다.
	 *
	 * @param data String
	 * @return String
	 */
	public static String getParseYYYMMDD(String yyyymmdd)
	{
		String str = null;
		if (yyyymmdd == null || yyyymmdd.trim().length() == 0)
		{
			return "";
		}

		if (yyyymmdd.trim().length() == 8)
		{
			str = yyyymmdd.substring(0, 2);
			if (str.equals("20"))
				return "1" + yyyymmdd.substring(2);
			if (str.equals("19"))
				return "0" + yyyymmdd.substring(2);
		}
		return yyyymmdd;
	}

	/**
	 * 5자리년월을 6자리년월로 바꾼다.
	 *
	 * @param data String
	 * @return String
	 */
	public static String getParseYYYYMM(String yyymm)
	{
		String str = null;
		if (yyymm == null || yyymm.trim().length() == 0)
		{
			return "";
		}

		if (yyymm.trim().length() == 5)
		{
			str = yyymm.substring(0, 1);
			if (str.equals("1"))
				return "20" + yyymm.substring(1);
			if (str.equals("0"))
				return "19" + yyymm.substring(1);
		}
		return yyymm;
	}

	/**
	 * 6자리년월을 5자리년월로 바꾼다.
	 *
	 * @param data String
	 * @return String
	 */
	public static String getParseYYYMM(String yyyymm)
	{
		String str = null;
		if (yyyymm == null || yyyymm.trim().length() == 0)
		{
			return "";
		}

		if (yyyymm.trim().length() == 5)
		{
			str = yyyymm.substring(0, 1);
			if (str.equals("1"))
				return "20" + yyyymm.substring(1);
			if (str.equals("0"))
				return "19" + yyyymm.substring(1);
		}
		return yyyymm;
	}

	/**
	 * RTRIM
	 *
	 * @param
	 * @return
	 */
	public static String rtrim(String s)
	{

		if (s == null)
			return "";

		char[] chars = s.toCharArray();

		// 공백이외의 마지막문자 검색
		int end = -1;

		for (int i = chars.length - 1; i >= 0; i--)
		{

			if (chars[i] != ' ' && chars[i] != DOUBLE_SPACE & chars[i] != '\t')
			{
				end = i;
				break;
			}
		}

		return new String(chars, 0, (end + 1));
	}

	public static void setString(PreparedStatement ps, int columnIndex, String value) throws SQLException
	{
		if (value == null)
		{
			ps.setString(columnIndex, value);
		}
		else
		{
			try
			{
				ps.setBytes(columnIndex, value.trim().getBytes("933"));
			}
			catch (Exception e)
			{
				ps.setString(columnIndex, value);
			}
		}
	}

	public static void setStringTW(PreparedStatement ps, int columnIndex, String value) throws SQLException
	{
		if (value == null)
		{
			ps.setString(columnIndex, value);
		}
		else
		{
			try
			{
				ps.setBytes(columnIndex, value.trim().getBytes("1208"));
			}
			catch (Exception e)
			{
				ps.setString(columnIndex, value);
			}
		}
	}

	/**
	 * 문자열을 byte길이로 잘라낸다.
	 *
	 * @param s
	 * @param byteLength
	 * @return
	 */
	public static String cutString(String s, int byteLength)
	{
		while (s.getBytes().length > byteLength)
		{
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	/**
	 * 주민 번호 체크
	 *
	 * @param v
	 * @return
	 */
	public static boolean isRegNo(String v)
	{

		int IDtot = 0;
		String IDAdd = "234567892345";
		
		try {

			for (int i = 0; i < 12; i++)
			{
				IDtot = IDtot + parseInt(v.substring(i, (i + 1))) * parseInt(IDAdd.substring(i, (i + 1)));
			}// end for
			IDtot = 11 - (IDtot % 11);
			if (IDtot == 10)
			{
				IDtot = 0;
			}
			else if (IDtot == 11)
			{
				IDtot = 1;
			}// end if
			if (parseInt(v.substring(12, 13)) != IDtot)
			{
				return false;
			}// end if
			
		} catch (Exception ex) {
			return false;
		}
		return true;
	}// end function

	/**
	 * 사업자번호 유효 체크 function
	 */
	public static boolean isTaxNo(String vencod)
	{
		// 유효성 체크
		if (vencod == null || vencod.length() != 10) {
			return false;
		}
		
		int sum = 0;
		int getlist[] = new int[10];
		String chkvalue[] = new String[] { "1", "3", "7", "1", "3", "7", "1", "3", "5" };

		for (int i = 0; i < 10; i++)
		{
			getlist[i] = parseInt(vencod.substring(i, i + 1), -1);
			
			// 숫자가 아닌경우...
			if (getlist[i] == -1) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++)
		{
			sum += getlist[i] * parseInt(chkvalue[i]);
		}
		sum = sum + ((getlist[8] * 5) / 10);
		int sidliy = sum % 10;
		int sidchk = 0;
		if (sidliy != 0)
		{
			sidchk = 10 - sidliy;
		}
		else
		{
			sidchk = 0;
		}
		if (sidchk != getlist[9])
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 인자로 받은 메일주소가 유효한지 체크하여 돌려주는 메소드
	 * @param email 메일주소
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null) return false;
		boolean b = Pattern.matches(
				"[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",
				email.trim());
		return b;
	}

	/**
	 * 날짜 정합성 체크
	 *
	 * @param data
	 * @return
	 */
	public static boolean isValidDate(String data)
	{

		String str;
		int yyyy;
		int y_year;
		int m_month;
		int mm_month;
		int d_day;
		str = data;
		try
		{
			char[] tmp = delChar(data, '-').toCharArray();
			for (int i = 0; i < tmp.length; i++)
			{
				if (!Character.isDigit(tmp[i]))
					return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
		yyyy = parseInt(str.substring(0, 4), 10);
		String[] end = new String[] { "31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" };
		if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0)
		{
			end[1] = "29";
		}
		y_year = parseInt(str.substring(0, 4), 10);
		m_month = parseInt(str.substring(4, 6), 10);
		mm_month = m_month - 1;
		d_day = parseInt(str.substring(6), 10);
		if (str.length() == 8)
		{
			if (1900 < y_year && y_year <= 2100)
			{
				if (0 < m_month && m_month <= 12)
				{
					if (0 < d_day && d_day <= parseInt(end[mm_month]))
						return true;
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * 파라미터로 넘어온 문자열이 숫자로만 이뤄져 있는지 체크하는 function
	 * @param str : 문자열
	 * @return boolean
	 */
	public static boolean isNumber(String str){
		char c;
		
		if(str == null || "".equals(str)) return false;
		 
		for(int i = 0 ; i < str.length() ; i++){
			c = str.charAt(i);
			if(c < 48 || c > 59) return false;
		}
		return true;
	}
		 
	/**
	 * 파라미터로 넘어온 일자를 flag단위로 number만큼 더하는 function
	 *
	 * @param current : 일자(yyyyMMdd)
	 * @param number : 더할 간격 (빼고 싶으면 -로 입력)
	 * @param flag : 기준 단위 (년,월,일,주)
	 * @return
	 */
	public static String getDateTerm(String current, int number, char flag)
	{

		Calendar today = Calendar.getInstance();

		if (current.length() == 8)
		{
			today.set(Integer.parseInt(current.substring(0, 4)), Integer.parseInt(current.substring(4, 6)) - 1, Integer
					.parseInt(current.substring(6, 8)));
		}
		else
		{
			return "";
		}

		switch (flag)
		{

		case 'Y':
			today.add(Calendar.YEAR, number);
			break;
		case 'M':
			today.add(Calendar.MONTH, number);
			break;
		case 'W':
			today.add(Calendar.WEEK_OF_MONTH, number);
			break;
		case 'D':
			today.add(Calendar.DAY_OF_MONTH, number);
			break;
		}

		String strYear = Integer.toString(today.get(Calendar.YEAR));
		String strMonth = Integer.toString(today.get(Calendar.MONTH) + 1);
		strMonth = (strMonth.length() == 1) ? strMonth = "0" + strMonth : strMonth;
		String strDay = Integer.toString(today.get(Calendar.DAY_OF_MONTH));
		strDay = (strDay.length() == 1) ? strDay = "0" + strDay : strDay;
		return strYear + strMonth + strDay;
	}
	
	/**
	 * 현재 시간을 기준으로 달력의 규칙에 기반해 지정된 시간량이 지정된 달력 필드에 가산 또는 감산하는 메소드 
	 *
	 * @param field - 달력 필드
	 * @param amount - 필드에 추가되는 일자 또는 시각의 양
	 * @return
	 */
	public static Date getDateAdd(int field, int amount)
	{
		return getDateAdd(new Date(), field, amount);
	}
	
	/**
	 * 현재 시간을 기준으로 달력의 규칙에 기반해 지정된 시간량이 지정된 달력 필드에 가산 또는 감산하는 메소드 
	 *
	 * @param field - 달력 필드
	 * @param amount - 필드에 추가되는 일자 또는 시각의 양
	 * @return
	 */
	public static Date getCalendarAdd(Calendar cal, int field, int amount)
	{
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * 현재 시간을 기준으로 달력의 규칙에 기반해 지정된 시간량이 지정된 달력 필드에 가산 또는 감산하는 메소드 
	 *
	 * @param field - 달력 필드
	 * @param amount - 필드에 추가되는 일자 또는 시각의 양
	 * @return
	 */
	public static Date getDateAdd(Date date, int field, int amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(field, amount);
		return cal.getTime();
	}
	

	/**
	 * 현재 일자를 파라미터로 넘어온 flag단위로 number만큼 더하는 function
	 *
	 * @param number : 더할 간격 (빼고 싶으면 -로 입력)
	 * @param flag : 기준 단위 (년,월,일,주)
	 * @return
	 */
	public static String getDateTerm(int number, char flag)
	{

		Calendar today = Calendar.getInstance();

		switch (flag)
		{

		case 'Y':
			today.add(Calendar.YEAR, number);
			break;
		case 'M':
			today.add(Calendar.MONTH, number);
			break;
		case 'W':
			today.add(Calendar.WEEK_OF_MONTH, number);
			break;
		case 'D':
			today.add(Calendar.DAY_OF_MONTH, number);
			break;
		}

		String strYear = Integer.toString(today.get(Calendar.YEAR));
		String strMonth = Integer.toString(today.get(Calendar.MONTH) + 1);
		strMonth = (strMonth.length() == 1) ? strMonth = "0" + strMonth : strMonth;
		String strDay = Integer.toString(today.get(Calendar.DAY_OF_MONTH));
		strDay = (strDay.length() == 1) ? strDay = "0" + strDay : strDay;
		return strYear + strMonth + strDay;
	}

	/**
	 * 파라미터로 넘어온 일자를 Calendar type으로 return하는 function
	 *
	 * @param current : 일자(yyyyMMdd)
	 * @return
	 */
	public static Calendar getCalendar(String current)
	{

		Calendar today = Calendar.getInstance();

		if (current.length() == 8)
		{
			today.set(Integer.parseInt(current.substring(0, 4)), Integer.parseInt(current.substring(4, 6)) - 1, Integer
					.parseInt(current.substring(6, 8)));
		}
		return today;
	}

	/**
	 * Turns array of bytes into string representing each byte as a two digit
	 * unsigned hex number.
	 *
	 * @param hash Array of bytes to convert to hex-string
	 * @return Generated hex string
	 */
	private static String toHex(byte hash[])
	{
		StringBuffer buf = new StringBuffer(hash.length * 2);
		for (int i = 0; i < hash.length; i++)
		{
			int intVal = hash[i] & 0xff;
			if (intVal < 0x10)
			{
				// append a zero before a one digit hex
				// number to make it two digits.
				buf.append("0");
			}
			buf.append(Integer.toHexString(intVal));
		}
		return buf.toString();
	}

	/**
	 * Gets the MD5 hash of the given byte array.
	 *
	 * @param b byte array for which an MD5 hash is desired.
	 * @return 32-character hex representation the data's MD5 hash.
	 */
	public static String getMD5String(byte[] b)
	{
		String javaPackageMD5 = "";
		try
		{
			byte[] javaDigest = MessageDigest.getInstance("MD5").digest(b);
			javaPackageMD5 = toHex(javaDigest);
		}
		catch (Exception ex)
		{
		}
		return javaPackageMD5;
	}

	/**
	 * 단방향 암호화 처리(String)
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str)
	{
		byte[] byteString = str.getBytes();
		return getMD5String(byteString);
	}

	/**
	 * return url encoded string
	 *
	 * @param s
	 * @return
	 */
	public static String urlEncode(String s)
	{
		try
		{
			return replace(URLEncoder.encode(s, "EUC-KR"), "+", "%20");
		}
		catch (Exception e)
		{
			return s;
		}
	}

	/**
	 * @param s 자를 문자열
	 * @param size 자를 길이
	 * @return 잘린 문자열
	 */
	public static String head(String s, int size)
	{
		if (s == null || size <= 0)
			return "";
		String value = null;
		s = detranslate(s);

		int len = s.length();
		int cnt = 0, index = 0;

		while (index < len && cnt < size)
		{
			if (s.charAt(index++) < 256)
			{ // 1바이트 문자라면...
				cnt++; // 길이 1 증가
			}
			else
			{
				cnt += 2; // 길이 2 증가
			}
		}// while

		if (index < len && size >= cnt)
		{
			s = s.substring(0, index) + "...";
		}
		else if (index < len && size < cnt)
		{
			s = s.substring(0, index - 1) + "...";
		}

		value = s;

		return value;
	}

	public static String detranslate(String s)
	{
		if (s == null)
			return null;
		s = replace(s, "&amp;", "&");
		s = replace(s, "&lt;", "<");
		s = replace(s, "&gt;", ">");
		s = replace(s, "&quot;", "\"");
		s = replace(s, "&#039;", "'");
		return s;
	}

	private static String	encodeKey	= "PrimeElectronicBookkeepingSystem";

	private static String	suffleKey1	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	private static String	suffleKey2	= "ZAnBglCost35GHIJ678wDpqrEWdefhijk2QSKL90XYabFNOPuvMVTmx4Ryz1Uc";

	public static String encodeString(String s)
	{
		// SUFFLE
		char[] chars = s.toCharArray();
		int index;
		for (int i = 0; i < chars.length; i++)
		{
			if ((index = suffleKey1.indexOf(chars[i])) != -1)
			{
				chars[i] = suffleKey2.charAt(index);
			}
		}

		byte[] key = encodeKey.getBytes();
		byte[] bytes = (new String(chars)).getBytes();
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) (bytes[i] ^ key[i % key.length]);
		}
		return toHex(bytes).toUpperCase();
	}

	private static byte[] fromHexa(String s)
	{
		byte[] buffer = new byte[s.length() / 2];
		for (int i = 0; i < buffer.length; i++)
		{
			buffer[i] = (byte) (Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
		}
		return buffer;
	}

	public static String decodeString(String s)
	{
		byte[] bytes = fromHexa(s);
		byte[] key = encodeKey.getBytes();

		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) (bytes[i] ^ key[i % key.length]);
		}

		s = new String(bytes);

		// SUFFLE
		char[] chars = s.toCharArray();
		int index;
		for (int i = 0; i < chars.length; i++)
		{
			if ((index = suffleKey2.indexOf(chars[i])) != -1)
			{
				chars[i] = suffleKey1.charAt(index);
			}
		}
		return new String(chars);

	}
	
	/**
	 * 인자로 받은 파일사이즈를 보여주는 파일사이즈로 변환하여 돌려주는 메소드
	 * @param fileSize 파일사이즈
	 * @return
	 */
	public static String getViewFileSize(long fileSize) {
		String returnValue = "";
	    if( fileSize <  1000) {
	    	returnValue = Utility.format( fileSize, "#,##0") + "B";;
	    } else {
	    	fileSize = fileSize / 1024;
		    if( fileSize < 10) {
		    	returnValue = Utility.format( fileSize, "#0.0") + "KB";
		    } else if( fileSize < 1000) {
		    	returnValue = Utility.format( fileSize, "#,##0") + "KB";
		    } else {
		    	fileSize = fileSize / 1024;
			    if( fileSize < 10) {
			    	returnValue = Utility.format( fileSize, "#0.0") + "MB";
			    } else if( fileSize < 1000) {
			    	returnValue = Utility.format( fileSize, "#,##0") + "MB";
			    } else {
			    	fileSize = fileSize / 1024;
			        returnValue = Utility.format( fileSize, "#,##0.0") + "GB";
			    }
		    }
	    }
	    
	    return returnValue;
	}    
	    
    public static void putString(ByteBuffer byteBuffer, String str, int size) {
	    putStringLeft(byteBuffer, str, size);
	}
	
	public static void putStringLeft(ByteBuffer byteBuffer, String str, int size) {
		byteBuffer.put(alignLeft(str, size, (byte)' ').getBytes());
	}
	
	public static void putNumber(ByteBuffer byteBuffer, Number n, int size) {
		byteBuffer.put(alignRight(String.valueOf(n), size, (byte)'0').getBytes());
	}
	
	/**
	 * ByteBuffer 에서 size 길이만틈 String 으로 돌려준다.
	 * 
	 * @param byteBuffer
	 * @param size
	 *            사이즈
	 * 
	 * @return String
	 */
	public static String getString(ByteBuffer byteBuffer, int size) {
		if (size == 0) {
			return "";
		}

		byte[] temp = new byte[size];
	
		byteBuffer.get(temp);

		return new String(temp);
	}
    
	  /**
     * 인자로 받은 문자열을 인자로 받은 사이즈에 맞게 처리(왼쪽정렬)하여 돌려주는 메소드 
     *   -> 처리하는 단위는 byte이고, 
     *      문자열이 작을경우 문자열을 왼쪽에 정렬시키고 나머지는 인자로 받은 바이트로 채워서 돌려준다.
     * @param str 문자열
     * @param size 사이즈
     * @param b 기본 Byte
     * @return
     */
    public static String alignLeft(String str, int size, byte b) {
    	byte[] strByte = str.getBytes();
    	byte[] returnByte = new byte[size];
    	
    	if (strByte.length >= size) {
    		// 인자로 받은 문자열이 충분할 경우
    		System.arraycopy(strByte, 0, returnByte, 0, size);
    	} else {
    		// 인자로 받은 문자열이 부족할 경우
    		System.arraycopy(strByte, 0, returnByte, 0, strByte.length);
    		
    		for (int i = strByte.length; i < size; i++)
    			returnByte[i] = b;
    	}
    	
    	return new String(returnByte);
    }
    
    /**
     * 인자로 받은 문자열을 인자로 받은 사이즈에 맞게 처리(오른쪽정렬)하여 돌려주는 메소드 
     *   -> 처리하는 단위는 byte이고, 
     *      문자열이 작을경우 문자열을 오른쪽에 정렬시키고 나머지는 인자로 받은 바이트로 채워서 돌려준다.
     * @param str
     * @param size
     * @param b
     * @return 
     */
    public static String alignRight(String str, int size, byte b) {
    	byte[] strByte = str.getBytes();
    	byte[] returnByte = new byte[size];
    	
    	if (strByte.length >= size) {
    		// 인자로 받은 문자열이 충분할 경우
    		System.arraycopy(strByte, 0, returnByte, 0, size);
    	} else {
    		// 인자로 받은 문자열이 부족할 경우
    		for (int i = 0; i < (size - strByte.length); i++)
    			returnByte[i] = b;
    		
    		System.arraycopy(strByte, 0, returnByte, (size - strByte.length), strByte.length);
    	}
    	
    	return new String(returnByte);
    }
    
	
	/**
	 * 인자로 받은 체크값이 공백 또는 NULL 이면 반환값을 돌려주는 메소드
	 * @param chkValue 체크값
	 * @param rtnValue 반환값
	 * @return
	 */
	public static String toNvl(String chkValue, String rtnValue) {
		if (!isEmpty(chkValue)) {
			return chkValue;
		}
		
		return rtnValue;
	}
	
	
	/**
	 * 지정된 길이 length에서 문자열을 채우고 남은 공간은 왼쪽부터 문자로 채워서 돌려주는 메소드
	 * @param str 문자열
	 * @param ch 문자
	 * @param length 길이
	 * @return
	 */
	public static String lpad(String str, int length, char ch) {
		// 유효성 체크
		int strLength = -1;
		if (str == null || (strLength = str.length()) >= length) {
			return str;
		}
		
		String returnValue = "";
		for (int i = 0; i < length; i++) {
			if (i < strLength) {
				returnValue = returnValue + str.charAt(i);
			} else {
				returnValue = ch + returnValue;
			}
		}
		
		return returnValue;
	}
	
	
	/**
	 * 인자로 받은 문자열 배열을 ArrayList로 변환하여 돌려주는 메소드
	 * @param arrayStr
	 * @return
	 */
	public static ArrayList<String> toArrayList(String[] arrayStr) {
		ArrayList<String> rtnList = new ArrayList<String>();
		
		if (arrayStr != null && arrayStr.length > 0) {
			for (int i = 0; i < arrayStr.length; i++) {
				rtnList.add(arrayStr[i]);
			}
		}
		
		return rtnList;
	}
	
	/**
	 * 인자로 받은 전화번호의 앞/중간/뒷자리를 돌려주는 메소드
	 * @param telNo 전체전화번호
	 * @param partNo 1:압자리(국번), 2:중간자리, 3:뒷잔리
	 * @return
	 */
	public static String getTelPartNo(String telNo, int partNo) {
		String telNo1 = "";
		String telNo2 = "";
		String telNo3 = "";
		
		if (telNo != null) {
			int length = telNo.length();
			if (length >= 9) {
				if (telNo.indexOf("02") == 0) {
					telNo1 = telNo.substring(0, 2);
					telNo2 = telNo.substring(2, length-4);
				} else {
					telNo1 = telNo.substring(0, 3);
					telNo2 = telNo.substring(3, length-4);
				}
				telNo3 = telNo.substring(length-4);
			} else if (length > 4) {
				telNo2 = telNo.substring(0, length-4);
				telNo3 = telNo.substring(length-4);
			}
		}
		
		if (partNo == 1) return telNo1;
		else if (partNo == 2) return telNo2;
		else if (partNo == 3) return telNo3;
		else return null;
	}
	
	/**
	 * 인자로 받은 휴대전화번호의 앞/중간/뒷자리를 돌려주는 메소드
	 * @param hpNo 전체전화번호
	 * @param partNo 1:압자리(국번), 2:중간자리, 3:뒷잔리
	 * @return
	 */
	public static String getHpPartNo(String hpNo, int partNo) {
		String hpNo1 = "";
		String hpNo2 = "";
		String hpNo3 = "";
		
		if (hpNo != null) {
			int length = hpNo.length();
			if (length >= 10) {
				hpNo1 = hpNo.substring(0, 3);
				hpNo2 = hpNo.substring(3, length-4);
				hpNo3 = hpNo.substring(length-4);
			}
		}
		
		if (partNo == 1) return hpNo1;
		else if (partNo == 2) return hpNo2;
		else if (partNo == 3) return hpNo3;
		else return null;
	}
	

	public static void main(String[] args)
	{
		GregorianCalendar gc = new GregorianCalendar();
		System.out.println("==>" + gc.get(Calendar.DATE));
	}

}