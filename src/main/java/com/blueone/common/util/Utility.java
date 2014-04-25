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
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import javax.xml.parsers.DocumentBuilderFactory ; 
import javax.xml.parsers.DocumentBuilder ; 
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document ; 
import org.w3c.dom.NodeList ; 
import org.w3c.dom.Node ; 
import org.w3c.dom.Element ; 

import org.xml.sax.SAXException;



import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;

import com.blueone.common.domain.SearchAddress;

/**
 * 怨듯넻 Utility Class
 *
 * @version $Revision: 1.11 $
 * @author $Author: kwanwool $
 */

public final class Utility
{

	private static final char		DOUBLE_SPACE			= 'd';
	
	private static HashMap<String, String> tags = new HashMap<String, String>();

	// �닿굅 洹몃깷 怨듬갚 �꾨땲怨��꾧컖臾몄옄 怨듬갚�� �섏쨷���꾩뒪�ㅼ퐫�쒕줈 諛붽씀��寃껋씠 諛붾엺吏곹븷 ��
	// �뱀떆�쇰룄 吏�슦怨�洹몃깷 �ㅽ럹�댁뒪 �ｌ쑝硫��덈맖 (by sik)

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
	 * String ��quotation��遺숈씤��
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
	 * 二쇱뼱吏�臾몄옄�댁쓣 int�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��湲곗〈媛믪쓣 由ы꽩�쒕떎.
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
	 * 二쇱뼱吏�臾몄옄�댁쓣 int�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��0��由ы꽩�쒕떎.
	 */
	public static int parseInt(String s)
	{
		return parseInt(s, 0);
	}

	/**
	 * 二쇱뼱吏�臾몄옄�댁쓣 long�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��湲곗〈媛믪쓣 由ы꽩�쒕떎.
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
	 * 二쇱뼱吏�臾몄옄�댁쓣 long�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��0��由ы꽩�쒕떎.
	 */
	public static long parseLong(String s)
	{
		return parseLong(s, 0);
	}

	/**
	 * 二쇱뼱吏�臾몄옄�댁쓣 double�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��湲곗〈媛믪쓣 由ы꽩�쒕떎.
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
	 * 二쇱뼱吏�臾몄옄�댁쓣 double�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��0��由ы꽩�쒕떎.
	 */
	public static double parseDouble(String s)
	{
		return parseDouble(s, 0);
	}

	/**
	 * 二쇱뼱吏�臾몄옄�댁쓣 flat�뺤쑝濡�蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��湲곗〈媛믪쓣 由ы꽩�쒕떎.
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
	 * 二쇱뼱吏�臾몄옄�댁쓣 float 蹂�솚�쒕떎. �ㅻ쪟諛쒖깮��0��由ы꽩�쒕떎.
	 */
	public static float parseFloat(String s)
	{
		return parseFloat(s, 0);
	}

	/**
	 * 二쇱뼱吏�臾몄옄�댁씠 NULL�먮뒗 怨듬갚�몄� 由ы꽩�쒕떎.
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
	 * �몄옄濡�諛쏆� 臾몄옄��諛곗뿴��怨듬갚�먮뒗 NULL�몄� �뚮젮二쇰뒗 硫붿냼��
	 * @param values 臾몄옄�대같��
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
		// 怨듬갚�댁쇅��泥ルЦ��寃�깋
		int start = -1;
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] != ' ' && chars[i] != DOUBLE_SPACE & chars[i] != '\t')
			{
				start = i;
				break;
			}
		}
		// 怨듬갚�댁쇅��留덉�留됰Ц��寃�깋
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
	 * �대떦 �꾩썡��泥ル쾲吏��좎쓽 �붿씪��援ы븳��
	 *
	 * @param yy �꾨룄, mm ��
	 * @return int(1:�쇱슂��~ 4(�� ~ 7:�좎슂��
	 */
	public static int getFirstDayWeek(int yy, int mm)
	{
		Calendar now = Calendar.getInstance();
		now.set(yy, mm - 1, 1);
		int dte = now.get(Calendar.DAY_OF_WEEK);

		return dte;
	}

	/**
	 * �대떦 �꾩썡��留덉�留됱씪��援ы븳��
	 *
	 * @param yy �꾨룄, mm ��
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
	 * 臾몄옄�댁쓣 Date濡��뚯떛�쒕떎.
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
	 * 臾몄옄�댄삎�쒖쓽 �レ옄瑜��낅젰諛쏆븘 �쇱젙��format���섑븯����긽 臾몄옄�댁쓣 format�쇰줈 蹂�꼍�쒗궓��
	 *
	 * @param number �レ옄�뺥깭瑜�媛�쭊 ��긽 臾몄옄��
	 * @param format �대떦 臾몄옄�댁쓣 蹂�꼍�쒗궎湲��꾪븳 format
	 * @return 蹂�꼍�섏뼱吏�Number媛앹껜
	 */
	public static Number parseNumber(String number, String format)
	{
		DecimalFormat formatter = new DecimalFormat(format);
		return formatter.parse(number, new ParsePosition(0));
	}

	/**
	 * 臾몄옄�댄삎�쒖쓽 �レ옄瑜��낅젰諛쏆븘 �쇱젙��format���섑븯����긽 臾몄옄�댁쓣 int�뺥깭��format�쇰줈 蹂�꼍�쒗궓��
	 *
	 * @param number �レ옄�뺥깭瑜�媛�쭊 ��긽 臾몄옄��
	 * @param format �대떦 臾몄옄�댁쓣 蹂�꼍�쒗궎湲��꾪븳 format
	 * @return 蹂�꼍�섏뼱吏�int�뺥깭��媛�
	 */
	public static int parseInt(String number, String format)
	{
		return parseNumber(number, format).intValue();
	}

	/**
	 * 臾몄옄�댄삎�쒖쓽 �レ옄瑜��낅젰諛쏆븘 �쇱젙��format���섑븯����긽 臾몄옄�댁쓣 long�뺥깭��format�쇰줈 蹂�꼍�쒗궓��
	 *
	 * @param number �レ옄�뺥깭瑜�媛�쭊 ��긽 臾몄옄��
	 * @param format �대떦 臾몄옄�댁쓣 蹂�꼍�쒗궎湲��꾪븳 format
	 * @return 蹂�꼍�섏뼱吏�long�뺥깭��媛�
	 */
	public static long parseLong(String number, String format)
	{
		return parseNumber(number, format).longValue();
	}

	/**
	 * 臾몄옄�댄삎�쒖쓽 �レ옄瑜��낅젰諛쏆븘 �쇱젙��format���섑븯����긽 臾몄옄�댁쓣 float�뺥깭��format�쇰줈 蹂�꼍�쒗궓��
	 *
	 * @param number �レ옄�뺥깭瑜�媛�쭊 ��긽 臾몄옄��
	 * @param format �대떦 臾몄옄�댁쓣 蹂�꼍�쒗궎湲��꾪븳 format
	 * @return 蹂�꼍�섏뼱吏�float�뺥깭��媛�
	 */
	public static float parseFloat(String number, String format)
	{
		return parseNumber(number, format).floatValue();
	}

	/**
	 * 臾몄옄�댄삎�쒖쓽 �レ옄瑜��낅젰諛쏆븘 �쇱젙��format���섑븯����긽 臾몄옄�댁쓣 double�뺥깭��format�쇰줈 蹂�꼍�쒗궓��
	 *
	 * @param number �レ옄�뺥깭瑜�媛�쭊 ��긽 臾몄옄��
	 * @param format �대떦 臾몄옄�댁쓣 蹂�꼍�쒗궎湲��꾪븳 format
	 * @return 蹂�꼍�섏뼱吏�double�뺥깭��媛�
	 */
	public static double parseDouble(String number, String format)
	{
		return parseNumber(number, format).doubleValue();
	}

	/**
	 * �꾩옱 �좎쭨瑜�yyyy��MM��dd��HH��mm遺꾩쓽 �뺥깭濡�媛믪쓣 �살뼱�몃떎.
	 *
	 * @return yyyy��MM��dd��HH��mm遺꾩쓽 �뺥깭濡�諛붾� �꾩옱 �쒓컙媛�
	 */
	public static String getTime()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH��mm遺�");
		return sdf.format(d);
	}

	/**
	 * �꾩옱�쇱옄瑜��쒖� �щ㎎�쇰줈 由ы꽩�쒕떎.
	 *
	 * @return �꾩옱�쇱옄
	 */
	public static String getCurrentDate()
	{
		return getCurrentDate(DEFAULT_DATE_FORMAT);
	}

	/**
	 * �꾩옱�쇱옄瑜�二쇱뼱吏��щ㎎�쇰줈 由ы꽩�쒕떎.
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
	 * �꾩옱�꾩썡��yyyyMM�щ㎎�쇰줈 由ы꽩�쒕떎.
	 *
	 * @return
	 */
	public static String getYearMonth()
	{
		String f = "yyyyMM";
		return getCurrentDate(f);
	}

	/**
	 * �꾩옱�꾨룄瑜�由ы꽩�쒕떎.
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
	 * �꾩옱�붿쓣 由ы꽩�쒕떎.
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
	 * �ㅻ뒛��湲곗��쇰줈 �댁젣���좎쭨瑜��뚯븘�몃떎
	 *
	 * @return 湲곕낯 �좎쭨 �뺥깭濡�蹂�꼍�섎맂 臾몄옄�닿컪
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
	 * �꾩옱 �살뼱���좎쭨��留덉�留��ъ쓣 �뚯븘�몃떎.
	 *
	 * @return yyyy/mm�뺥깭濡�蹂�꼍�섎맂 臾몄옄�닿컪
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
	 * GMT湲곗��쒓컙以묒쓽 �쒓뎅�쒖��쒕� 諛섑솚�쒕떎.
	 *
	 * return GMT+09:00�뺥깭����븳誘쇨뎅�쒖���
	 */
	public static Calendar getCalendar()
	{
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(new Date());

		return calendar;
	}

	/**
	 * 臾몄옄�댁쓣 Date��엯�쇰줈 蹂�솚�쒕떎.
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
	 * ���좎쭨 �ъ씠��紐뉗씪���덈뒗吏�怨꾩궛�쒕떎.
	 *
	 * @param start �쒖옉�쇱옄
	 * @param end �앹씪
	 * @return
	 */
	public static int getDaysBetween(String start, String end)
	{
		Date d1 = parseDate2(start);
		Date d2 = parseDate2(end);
		return (int) (Math.abs(d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * �쇰컲臾몄옄�댄삎�쒕� HTML���뺥깭濡�諛붽씔�� 湲곕낯�곸쑝濡�&lt; &gt; �깆쓽 臾몄옄���ㅼ젣 HTML�뺥깭濡�蹂�꼍���쒓렇瑜�留앷��⑤━��
	 * ��긽���좎닔 �덉쑝誘�줈 媛곴컖��tag���곹뼢��誘몄튂吏�븡寃뚮걫 臾몄옄瑜�replace�쒗궎�꾨줉 �쒕떎.
	 *
	 * @param fromText HTML�뺥깭濡�蹂�꼍�쒗궗 臾몄옄��
	 * @return HTML�щ㎎�쇰줈 蹂�꼍�섏뼱吏�臾몄옄��
	 */
	public static String toHTML(String fromText)
	{
		return toHTML(fromText, false);
	}

	/**
	 * �쇰컲臾몄옄�댄삎�쒕� HTML���뺥깭濡�諛붽씔�� 湲곕낯�곸쑝濡�&lt; &gt; �깆쓽 臾몄옄���ㅼ젣 HTML�뺥깭濡�蹂�꼍���쒓렇瑜�留앷��⑤━��
	 * ��긽���좎닔 �덉쑝誘�줈 媛곴컖��tag���곹뼢��誘몄튂吏�븡寃뚮걫 臾몄옄瑜�replace�쒗궎�꾨줉 �쒕떎.
	 *
	 * @param fromText HTML�뺥깭濡�蹂�꼍�쒗궗 臾몄옄��
	 * @param replyMode HTML mode or Reply mode瑜�吏�젙�섎뒗 媛�
	 * @return HTML�щ㎎�쇰줈 蹂�꼍�섏뼱吏�臾몄옄��
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
	 * �몄옄濡�諛쏆� 臾몄옄�댁쓣 HTML濡�蹂�솚�섏뿬 �뚮젮二쇰뒗 硫붿냼��
	 * @param str 臾몄옄��
	 * @return
	 */
	public static String toPebsHtml(String str) {
		if (str == null) {
			return str;
		}

		int workPoint = -1;      // �묒뾽�섎뒗 �ъ씤��
		int lastPoint = 0;       // �묒뾽��留덉�留��ъ씤��
		String tempStr = "";	 // �묒뾽以묒씤 臾몄옄��
		String tempTag = "";	 // �묒뾽以묒씤 �쒓렇紐�

		StringBuffer rStrBuf = new StringBuffer();

		workPoint = str.indexOf('<', workPoint);

		while (workPoint != -1) {

			// HTML濡�蹂�솚�댁빞���묒뾽���덉쑝硫�泥섎━�쒕떎.
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

		// �⑥� 臾몄옄�댁쓣 �대뒗��
		tempStr = str.substring(lastPoint, str.length());
		rStrBuf.append(toHTML(tempStr));

		return rStrBuf.toString();
	}

	/**
	 * �몄옄濡�諛쏆� 臾몄옄�댁뿉���몄옄濡�諛쏆� �쒖옉 �몃뜳�ㅻ����쒓렇紐낆묶 李얠븘 �뚮젮以�떎.
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
	 * �몄옄濡�諛쏆� 臾몄옄�댁씠 �쒓렇紐낆씠 留욌뒗吏��뚮젮二쇰뒗 硫붿냼��
	 * @param str
	 * @return
	 */
	private static boolean isTag(String str) {
		if (str.length() < 1) {
			return false;
		}
		
		// �쒓렇吏묓빀��珥덇린�뷀븳��
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
	 * double 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * double 媛믪쓣 �뚯닔�먯씠���먮━�섎� �뺥빐 蹂�솚�쒕떎.
	 *
	 * @param value double
	 * @param decimalCount int �뚯닔�먯씠���먮━��
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
	 * double 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * float 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * float 媛믪쓣 �뚯닔�먯씠���먮━�섎� �뺥빐 蹂�솚�쒕떎.
	 *
	 * @param value float
	 * @param decimalCount int �뚯닔�먯씠���먮━��
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
	 * float 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * int 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * int 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * data瑜�湲곕낯 �щ㎎�쇰줈 蹂�솚�쒕떎..
	 *
	 * @param data String, mask String
	 * @return String
	 */
	public static String format(java.util.Date date)
	{
		return format(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * data瑜�吏�젙���щ㎎�쇰줈 蹂�솚�쒕떎..
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
	 * long 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * long 媛믪쓣 湲곕낯 �レ옄 �щ㎎�쇰줈 蹂�솚�쒕떎.
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
	 * data瑜�吏�젙��mask濡��ㅼ젙�쒕떎.
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
				case '^': // 湲�옄瑜�嫄대꼫�대떎 added by sik
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
	 * ���좎쭨瑜�yyyyMMdd �꾩떇��String �쇰줈 由ы꽩�쒕떎.
	 */
	public static String getShortDateString()
	{

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * ���좎쭨瑜��낅젰���뺥깭��String �쇰줈 由ы꽩�쒕떎.
	 */
	public static String getSystemDate(String pattern)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, java.util.Locale.KOREA);
		return formatter.format(new Date());
	}

	/**
	 * ���쒓컙��HHmmss �뺤떇��String �쇰줈 由ы꽩�쒕떎.
	 */
	public static String getShortTimeString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HHmmss", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * 7�먮━�좎쭨瑜�8�먮━濡�諛붽씔��
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
	 * 8�먮━�좎쭨瑜�7�먮━濡�諛붽씔��
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
	 * 5�먮━�꾩썡��6�먮━�꾩썡濡�諛붽씔��
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
	 * 6�먮━�꾩썡��5�먮━�꾩썡濡�諛붽씔��
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

		// 怨듬갚�댁쇅��留덉�留됰Ц��寃�깋
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
	 * 臾몄옄�댁쓣 byte湲몄씠濡��섎씪�몃떎.
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
	 * 二쇰� 踰덊샇 泥댄겕
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
	 * �ъ뾽�먮쾲���좏슚 泥댄겕 function
	 */
	public static boolean isTaxNo(String vencod)
	{
		// �좏슚��泥댄겕
		if (vencod == null || vencod.length() != 10) {
			return false;
		}
		
		int sum = 0;
		int getlist[] = new int[10];
		String chkvalue[] = new String[] { "1", "3", "7", "1", "3", "7", "1", "3", "5" };

		for (int i = 0; i < 10; i++)
		{
			getlist[i] = parseInt(vencod.substring(i, i + 1), -1);
			
			// �レ옄媛��꾨땶寃쎌슦...
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
	 * �몄옄濡�諛쏆� 硫붿씪二쇱냼媛��좏슚�쒖� 泥댄겕�섏뿬 �뚮젮二쇰뒗 硫붿냼��
	 * @param email 硫붿씪二쇱냼
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
	 * �좎쭨 �뺥빀��泥댄겕
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
	 * �뚮씪誘명꽣濡��섏뼱��臾몄옄�댁씠 �レ옄濡쒕쭔 �대쨪���덈뒗吏�泥댄겕�섎뒗 function
	 * @param str : 臾몄옄��
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
	 * �뚮씪誘명꽣濡��섏뼱���쇱옄瑜�flag�⑥쐞濡�number留뚰겮 �뷀븯��function
	 *
	 * @param current : �쇱옄(yyyyMMdd)
	 * @param number : �뷀븷 媛꾧꺽 (鍮쇨퀬 �띠쑝硫�-濡��낅젰)
	 * @param flag : 湲곗� �⑥쐞 (������二�
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
	 * �꾩옱 �쒓컙��湲곗��쇰줈 �щ젰��洹쒖튃��湲곕컲��吏�젙���쒓컙�됱씠 吏�젙���щ젰 �꾨뱶��媛�궛 �먮뒗 媛먯궛�섎뒗 硫붿냼��
	 *
	 * @param field - �щ젰 �꾨뱶
	 * @param amount - �꾨뱶��異붽��섎뒗 �쇱옄 �먮뒗 �쒓컖����
	 * @return
	 */
	public static Date getDateAdd(int field, int amount)
	{
		return getDateAdd(new Date(), field, amount);
	}
	
	/**
	 * �꾩옱 �쒓컙��湲곗��쇰줈 �щ젰��洹쒖튃��湲곕컲��吏�젙���쒓컙�됱씠 吏�젙���щ젰 �꾨뱶��媛�궛 �먮뒗 媛먯궛�섎뒗 硫붿냼��
	 *
	 * @param field - �щ젰 �꾨뱶
	 * @param amount - �꾨뱶��異붽��섎뒗 �쇱옄 �먮뒗 �쒓컖����
	 * @return
	 */
	public static Date getCalendarAdd(Calendar cal, int field, int amount)
	{
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * �꾩옱 �쒓컙��湲곗��쇰줈 �щ젰��洹쒖튃��湲곕컲��吏�젙���쒓컙�됱씠 吏�젙���щ젰 �꾨뱶��媛�궛 �먮뒗 媛먯궛�섎뒗 硫붿냼��
	 *
	 * @param field - �щ젰 �꾨뱶
	 * @param amount - �꾨뱶��異붽��섎뒗 �쇱옄 �먮뒗 �쒓컖����
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
	 * �꾩옱 �쇱옄瑜��뚮씪誘명꽣濡��섏뼱��flag�⑥쐞濡�number留뚰겮 �뷀븯��function
	 *
	 * @param number : �뷀븷 媛꾧꺽 (鍮쇨퀬 �띠쑝硫�-濡��낅젰)
	 * @param flag : 湲곗� �⑥쐞 (������二�
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
	 * �뚮씪誘명꽣濡��섏뼱���쇱옄瑜�Calendar type�쇰줈 return�섎뒗 function
	 *
	 * @param current : �쇱옄(yyyyMMdd)
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
	 * �⑤갑���뷀샇��泥섎━(String)
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
	 * @param s �먮� 臾몄옄��
	 * @param size �먮� 湲몄씠
	 * @return �섎┛ 臾몄옄��
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
			{ // 1諛붿씠��臾몄옄�쇰㈃...
				cnt++; // 湲몄씠 1 利앷�
			}
			else
			{
				cnt += 2; // 湲몄씠 2 利앷�
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
	 * �몄옄濡�諛쏆� �뚯씪�ъ씠利덈� 蹂댁뿬二쇰뒗 �뚯씪�ъ씠利덈줈 蹂�솚�섏뿬 �뚮젮二쇰뒗 硫붿냼��
	 * @param fileSize �뚯씪�ъ씠利�
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
	 * ByteBuffer �먯꽌 size 湲몄씠留뚰땲 String �쇰줈 �뚮젮以�떎.
	 * 
	 * @param byteBuffer
	 * @param size
	 *            �ъ씠利�
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
     * �몄옄濡�諛쏆� 臾몄옄�댁쓣 �몄옄濡�諛쏆� �ъ씠利덉뿉 留욊쾶 泥섎━(�쇱そ�뺣젹)�섏뿬 �뚮젮二쇰뒗 硫붿냼��
     *   -> 泥섎━�섎뒗 �⑥쐞��byte�닿퀬, 
     *      臾몄옄�댁씠 �묒쓣寃쎌슦 臾몄옄�댁쓣 �쇱そ���뺣젹�쒗궎怨��섎㉧吏�뒗 �몄옄濡�諛쏆� 諛붿씠�몃줈 梨꾩썙���뚮젮以�떎.
     * @param str 臾몄옄��
     * @param size �ъ씠利�
     * @param b 湲곕낯 Byte
     * @return
     */
    public static String alignLeft(String str, int size, byte b) {
    	byte[] strByte = str.getBytes();
    	byte[] returnByte = new byte[size];
    	
    	if (strByte.length >= size) {
    		// �몄옄濡�諛쏆� 臾몄옄�댁씠 異⑸텇��寃쎌슦
    		System.arraycopy(strByte, 0, returnByte, 0, size);
    	} else {
    		// �몄옄濡�諛쏆� 臾몄옄�댁씠 遺�”��寃쎌슦
    		System.arraycopy(strByte, 0, returnByte, 0, strByte.length);
    		
    		for (int i = strByte.length; i < size; i++)
    			returnByte[i] = b;
    	}
    	
    	return new String(returnByte);
    }
    
    /**
     * �몄옄濡�諛쏆� 臾몄옄�댁쓣 �몄옄濡�諛쏆� �ъ씠利덉뿉 留욊쾶 泥섎━(�ㅻⅨ履쎌젙���섏뿬 �뚮젮二쇰뒗 硫붿냼��
     *   -> 泥섎━�섎뒗 �⑥쐞��byte�닿퀬, 
     *      臾몄옄�댁씠 �묒쓣寃쎌슦 臾몄옄�댁쓣 �ㅻⅨ履쎌뿉 �뺣젹�쒗궎怨��섎㉧吏�뒗 �몄옄濡�諛쏆� 諛붿씠�몃줈 梨꾩썙���뚮젮以�떎.
     * @param str
     * @param size
     * @param b
     * @return 
     */
    public static String alignRight(String str, int size, byte b) {
    	byte[] strByte = str.getBytes();
    	byte[] returnByte = new byte[size];
    	
    	if (strByte.length >= size) {
    		// �몄옄濡�諛쏆� 臾몄옄�댁씠 異⑸텇��寃쎌슦
    		System.arraycopy(strByte, 0, returnByte, 0, size);
    	} else {
    		// �몄옄濡�諛쏆� 臾몄옄�댁씠 遺�”��寃쎌슦
    		for (int i = 0; i < (size - strByte.length); i++)
    			returnByte[i] = b;
    		
    		System.arraycopy(strByte, 0, returnByte, (size - strByte.length), strByte.length);
    	}
    	
    	return new String(returnByte);
    }
    
	
	/**
	 * �몄옄濡�諛쏆� 泥댄겕媛믪씠 怨듬갚 �먮뒗 NULL �대㈃ 諛섑솚媛믪쓣 �뚮젮二쇰뒗 硫붿냼��
	 * @param chkValue 泥댄겕媛�
	 * @param rtnValue 諛섑솚媛�
	 * @return
	 */
	public static String toNvl(String chkValue, String rtnValue) {
		if (!isEmpty(chkValue)) {
			return chkValue;
		}
		
		return rtnValue;
	}
	
	
	/**
	 * 吏�젙��湲몄씠 length�먯꽌 臾몄옄�댁쓣 梨꾩슦怨��⑥� 怨듦컙���쇱そ遺�꽣 臾몄옄濡�梨꾩썙���뚮젮二쇰뒗 硫붿냼��
	 * @param str 臾몄옄��
	 * @param ch 臾몄옄
	 * @param length 湲몄씠
	 * @return
	 */
	public static String lpad(String str, int length, char ch) {
		// �좏슚��泥댄겕
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
	 * �몄옄濡�諛쏆� 臾몄옄��諛곗뿴��ArrayList濡�蹂�솚�섏뿬 �뚮젮二쇰뒗 硫붿냼��
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
	 * �몄옄濡�諛쏆� �꾪솕踰덊샇����以묎컙/�룹옄由щ� �뚮젮二쇰뒗 硫붿냼��
	 * @param telNo �꾩껜�꾪솕踰덊샇
	 * @param partNo 1:�뺤옄由�援�쾲), 2:以묎컙�먮━, 3:�룹옍由�
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
	 * �몄옄濡�諛쏆� �대��꾪솕踰덊샇����以묎컙/�룹옄由щ� �뚮젮二쇰뒗 硫붿냼��
	 * @param hpNo �꾩껜�꾪솕踰덊샇
	 * @param partNo 1:�뺤옄由�援�쾲), 2:以묎컙�먮━, 3:�룹옍由�
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
	
	public static List<SearchAddress>  searchAdd(String q) throws ParserConfigurationException, SAXException, IOException{
		List<SearchAddress> result = new ArrayList<SearchAddress>();
		
		 NodeList nodeDOC;
		 String query="http://udml.co.kr/api_hwi/zipcode/get_address_xml.php?query=";
		
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder=dbf.newDocumentBuilder(); 
		 
		
		
		try{
			
			query+=URLEncoder.encode(q,"UTF-8");

			 
			}catch(Exception e){
				  e.printStackTrace();
				 }finally{
				  
		}
		 Document doc=docBuilder.parse(query);
		 
		 nodeDOC=doc.getElementsByTagName("item");
		 
		  for (int temp = 0; temp < nodeDOC.getLength(); temp++) {
			  
			  Node nNode = nodeDOC.item(temp);
			  SearchAddress re = new SearchAddress();
			  if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
			   Element eElement = (Element) nNode;
			   String zip = eElement.getElementsByTagName("postcd").item(0).getFirstChild().getNodeValue();
			   zip= zip.substring(0, 3)+"-"+zip.substring(3);
				
			  re.setZipCode(zip);
			  re.setAddress( eElement.getElementsByTagName("address").item(0).getFirstChild().getNodeValue());
			  result.add(re);
			  
			  }


		  }
		return result;
	}

}