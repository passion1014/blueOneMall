package com.blueone.common.util;
/*
 * 작 성 자 : 이관우
 * 작성일자 : 2003년 8월 5일
 * 내    용 : Date객체를 유용하게 사용, 관리, 수정등을 하는 클래스
 * 			  new을 이용하여 생성금지
 * 			  - 주요기능
 * 				1. Date객체를 특정시간설정
 * 				2. Date객체에서 시간 수정(더하기, 빼기)  
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author 이관우
 * @version 1.0
 * 
 * Date형 객체를 처리하는 Util클래스
 */
public class DateUtil {
	
	
	/**
	 * 현재 시스템의 시간을 패턴 형식에 맞게 String형으로 변환하는 메소드
	 * 기호 설  명  						표 시    
	 *  G  	기원  텍스트  					AD  
	 *  y  	년  년  						1996;	96  
	 *  M  	월  월  						July; Jul; 07  
	 *  w  	해에 있어서의 주  수치  			27  
	 *  W  	달에 있어서의 주  수치  			2  
	 *  D  	해에 있어서의 날  수치  			189  
	 *  d  	달에 있어서의 날  수치  			10  
	 *  F  	달에 있어서의 요일  수치  		2  
	 *  E  	요일  텍스트  					Tuesday; Tue  
	 *  a  	오전/오후  텍스트  				PM  
	 *  H  	하루에 있어서의 때 (0 ~ 23)수치  0  
	 *  k  	하루에 있어서의 때 (1 ~ 24)수치  24  
	 *  K  	오전/오후때 (0 ~ 11)  수치  	0  
	 *  h  	오전/오후때 (1 ~ 12)  수치  	12  
	 *  m  	분  수치  						30  
	 *  s  	초  수치  						55  
	 *  S 	밀리 세컨드  수치  				978  
	 *  z  	타임 존  일반적인 타임 존  		Pacific Standard Time; PST; GMT-08:00  
	 *  Z  	타임 존  RFC 822 타임 존  		-0800
	 * 
	 * 예) 	"yyyy.MM.dd G 'at' HH:mm:ss z"    ->>  1996.07. 10 AD at 15:08:56 PDT
	 * 		"EEE, MMM d, ''yy"                ->>  Wed, July 10, '96
	 * 		"h:mm a"                          ->>  12:08 PM
	 * 		"hh 'o''clock' a, zzzz"           ->>  12 o'clock PM, Pacific Daylight Time
	 * 		"K:mm a, z"                       ->>  0:00 PM, PST
	 * 		"yyyyy.MMMMM.dd GGG hh:mm aaa"    ->>  1996. July. 10 AD 12:08 PM
	 * @param pattern(예 : yyyy.MM.dd)
	 * @return	현재시간(예 : 2003.08.07)
	 */
	public static String getDate(String pattern) {
		if (pattern == null) {
			return null;
		}
		
		try {
			return getDate(new Date(), pattern);
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}//End getDate
		
	
	
	
	/**
	 * 지정된 시간과 패턴형식에 맞게 String형으로 변환하는 메소드 
	 * @param date 시간
	 * @param pattern
	 * @return
	 */
	public static String getDate(Date date, String pattern) {
		if (date == null || pattern == null || pattern.trim().equals("")) {
			return null;
		}
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);			
			return formatter.format(date);
		}
		catch(IllegalArgumentException ex_1) {
			System.out.println("지정된 패턴이 올바르지 않습니다.(패턴:" + pattern + ")");
			ex_1.printStackTrace();
			return null;
		}
		catch(NullPointerException ex_2) {
			System.out.println("지정된 패턴 또는 시간이 NULL입니다. (Date:" + date + ", pattern:" + pattern);
			ex_2.printStackTrace();
			return null;
		}
	}//End getDate
	
	
	
	
	/**
	 * 지정된 달력의 시간과 패턴형식에 맞게 String형으로 변환하는 메소드
	 * @param calender
	 * @param pattern
	 * @return
	 */
	public static String getDate(Calendar calender, String pattern) {
		if (calender == null || pattern == null || pattern.trim().equals("")) {
			return null;
		}
		
		try {
			return getDate(calender.getTime(), pattern);
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}//End getDate
	
	
	
	
	/**
	 * 지정된 달력의 시간과 패턴형식에 맞게 String형으로 변환하는 메소드
	 * @param g_calender
	 * @param pattern
	 * @return
	 */
	public static String getDate(GregorianCalendar g_calender, String pattern) {
		if (g_calender == null || pattern == null || pattern.trim().equals("")) {
			return null;
		}
		
		try {
			return getDate(g_calender.getTime(), pattern);
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}//End getDate
	
	
	
	
	/**
	 * 지정된 String형의 시간을 특정 패턴형식에 맞게 만들어주는 메소드
	 * @param date
	 * @param date_parse date의 파서형식(예:20030506  --> yyyyMMdd)
	 * @param pattern
	 * @return
	 */
	public static String getDate(String date, String date_parse, String pattern) {
		if (date == null || date_parse == null || pattern == null || date.trim().equals("") || date_parse.trim().equals("") || pattern.trim().equals("")) {
			return null;
		}
		
		
		try {
			return getDate(getDate(date, date_parse), pattern);
		}
		catch(Exception ex_1) {
			return null;
		}
	}//End getDate
	
	
	
	
	/**
	 * 지정된 String형의 시간을 Date객체를 변환하는 메소드
	 * @param date
	 * @param date_parse date의 parser형태(예: 20030506	--> yyyyMMdd)
	 * @return
	 */
	public static Date getDate(String date, String date_parse) {
		if (date == null || date_parse == null || date.trim().equals("") || date_parse.trim().equals("")) {
			return null;
		}
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(date_parse);
			return formatter.parse(date);
		}
		catch(ParseException ex_1) {
			System.out.println("지정된 캐릭터 라인의 선두를 해석할 수 없습니다.(" + date + ")");
			ex_1.printStackTrace();
			return null;
		}
		catch(NullPointerException ex_2) {
			System.out.println("지정된 Parse 또는 시간이 NULL입니다. (Date:" + date + ", Parse:" + date_parse);
			ex_2.printStackTrace();
			return null;
		}
	}//End getDate
	
	
	
	
	/**
	 * 지정된 Date객체에서 달력의 규칙에 근거해, 지정된 (부호 첨부의) 시간량을, 지정된 시간 필드에 가세하는 메소드
	 * 예) 1년전 --> getDate(new Date(), Calender.YEAR, -1)
	 *     월 : Calendar.MONTH 
	 *     일 : Calendar.DATE
	 *     시 : Calendar.HOUR
	 *     분 : Calendar.MINUTE
	 *     초 : Calendar.SECOND 
	 *   밀리 : Calendar.MILLISECOND
	 * @param date
	 * @param field Calender의 맴버필드 이용
	 * @param amount 가세할 량(음수, 양ㅅ)
	 * @return
	 */
	public static Date getDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		
		try {
			Calendar calendar = getCalendar(date);
			calendar.add(field, amount);
			return calendar.getTime();
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}//End getDate
	
	
	
	
	/**
	 * 지정된 String형의 시간을 Calendar객체를 변환하는 메소드
	 * @param date
	 * @param date_parse
	 * @return
	 */
	public static Calendar getCalendar(String date, String date_parse) {
		if (date == null || date_parse == null || date.trim().equals("") || date_parse.trim().equals("")) {
			return null;
		}
		
		try {
			return getCalendar(getDate(date, date_parse));
		}
		catch(Exception ex_1) {
			return null;
		}
	}//End getCalendar
	
	
	
	
	/**
	 * Date객체를 Calendar객체로 변환하는 메소드 
	 * @param date	 
	 * @return
	 */
	public static Calendar getCalendar(Date date) {
		if (date == null) {
			return null;
		}
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			return calendar;
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}//End date
	
	
	
	
	/**
	 * 지정된 String형의 시간을 GregorianCalendar객체로 변환하는 메소드
	 * @param date
	 * @param date_parse
	 * @return
	 */
	public static GregorianCalendar getGregorianCalendar(String date, String date_parse) {
		if (date == null || date_parse == null || date.trim().equals("") || date_parse.trim().equals("")) {
			return null;
		}
		
		try {
			Calendar calendar = getCalendar(date, date_parse);
			return new GregorianCalendar (calendar.get(Calendar.YEAR), 
						calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 
							calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), 
								calendar.get(Calendar.SECOND));
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}//End getGregorianCalendar
	
	
	
	
	/**
	 * 넘겨진 시간이 오늘 날짜인지 비교하여 상황에 맞게 넘겨주는 메소드
	 * @param date 시간
	 * @param date_parse 파서
	 * @return
	 */
	public static String getComparisonDate(String date, String date_parse) {
		if (date == null || date_parse == null || date.trim().equals("") || date_parse.trim().equals("")) {
			return null;
		}
		try {
			if (date.length() > 8) {
				String date_1 = date.substring(0, 8);
				String date_2 = getDate("yyyyMMdd");
				if(date_1.equals(date_2)) {
					return getDate(date, date_parse, "HH:mm:ss");
				}
				else {
					return getDate(date, date_parse, "yyyy/MM/dd");
				}
			}
			return null;
		}
		catch(Exception ex_1) {
			ex_1.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 현재부터 인자로 받은 '가세할 량' 만큼 영업일이 지난후의 Date 객체를 돌려주는 메소드 
	 *   -> 영업일 : 월,화,수,목,금
	 * @param amount 가세할 량
	 * @return
	 */
	public static Date getBusinessDate(int amount) {
		int dayOfWeek = -1;
		int businessDay = 0;
		int checkBusinessDay = amount >= 0 ? amount : (amount * -1);
		Calendar currCalendar = Calendar.getInstance();
		
		while (true) {
			dayOfWeek = currCalendar.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek >= 2 && dayOfWeek <= 6) {
				businessDay++;
			}
			
			if (businessDay >= checkBusinessDay) {
				break;
			}
			
			if (amount > 0) {
				// 현재일 이후
				currCalendar.add(Calendar.DATE, 1);
			} else {
				// 현재일 이전
				currCalendar.add(Calendar.DATE, -1);
			}
			
		}//End while
		
		return currCalendar.getTime();
	}




	

}//End DateUtil
