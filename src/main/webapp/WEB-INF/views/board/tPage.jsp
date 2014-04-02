<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<base target="_self" />
<title>▒ 교육일정 달력 ▒</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/regist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<form name="oneForm" action="list.do" method="get">
<div style="border: 1px solid rgb(204, 204, 204); width: 210px;">
	<div style="padding: 3px; color: rgb(0, 0, 0); line-height: 120%; font-family: 돋움; font-size: 12px; filter: alpha(opacity=100); background-color: rgb(239, 239, 239);">
		<div style="top: 9px; right: 9px; position: absolute; cursor: pointer;" onclick="CloseCalTarget ('DivCalendarByStudymap_eduStrYmd');">X</div>
		<div style="width: 100%;" id="eduStrYmd_todayCalendarContents">
			<div style="width: 100%;">
				<table style="margin: 0px auto; padding: 0px; border: 0px currentColor; width: 100%; background-color: rgb(206, 206, 206);" border="0" cellSpacing="0" cellPadding="0">
				<div style='margin: 0px auto; padding: 5px 0px 5px 3px; text-align: left; color: rgb(0, 0, 0); font-family: "돋움"; font-size: 11px; font-weight: bold;' id="eduStrYmd_todayCalendarSelecter">
					<span style="cursor: pointer;" onclick="goCreateCalendarMethod('eduStrYmd',2012,1, -1);">&lt;&lt;</span>
					<strong style="margin: 0px 5px;">2012년 2월</strong>
					<span style="cursor: pointer;" onclick="goCreateCalendarMethod('eduStrYmd',2012,1, 1);">&gt;&gt;</span>
				</div>
				<thead>
				<tr>
					<th style="padding: 2px 1px; text-align: center; color: rgb(0, 0, 0); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-left:#e5e5e5 solid 1px; border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">월</th>
					<th style="padding: 2px 1px; text-align: center; color: rgb(0, 0, 0); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">화</th>
					<th style="padding: 2px 1px; text-align: center; color: rgb(0, 0, 0); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">수</th>
					<th style="padding: 2px 1px; text-align: center; color: rgb(0, 0, 0); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">목</th>
					<th style="padding: 2px 1px; text-align: center; color: rgb(0, 0, 0); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">금</th>
					<th style="padding: 2px 1px; text-align: center; color: rgb(70, 140, 200); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">토</th>
					<th style="padding: 2px 1px; text-align: center; color: rgb(226, 111, 111); font-family: 돋움; font-size: 11px; font-weight: normal; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-top:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" scope="col">일</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td width="27" style="padding: 2px 1px; background-color: rgb(255, 255, 255); border-left:#e5e5e5 solid 1px;  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
					<td width="27" style="padding: 2px 1px; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
					<td width="27" style="padding: 2px 1px; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
					<td width="27" style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background:url(../images/common/nochoice2.gif);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','1')">1</td>
					<td width="27" style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background:url(../images/common/nochoice2.gif);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','2')">2</td>
					<td width="27" style="padding: 2px 1px; text-align: center; color: rgb(70, 140, 200); font-size: 12px; cursor: pointer; background:url(../images/common/nochoice2.gif);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','3')">3</td>
					<td width="27" style="padding: 2px 1px; text-align: center; color: rgb(226, 111, 111); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','4')">4</td>
				</tr>
				<tr>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-left:#e5e5e5 solid 1px; border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','6')">5</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','7')">6</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','8')">7</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','9')">8</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','10')">9</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(70, 140, 200); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','11')">10</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(226, 111, 111); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255);  border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','5')">11</td>
				</tr>

				<tr>
					<td style="padding: 2px 1px; text-align: center; color: rgb(0, 153, 0); font-size: 12px; font-weight: bold; text-decoration: underline; cursor: pointer; background-color: rgb(255, 255, 255); border-left:#e5e5e5 solid 1px; border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','13')">12</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','14')">13</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','15')">14</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','16')">15</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','17')">16</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(70, 140, 200); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','18')">17</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(226, 111, 111); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','5')">18</td>
				</tr>
				<tr>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-left:#e5e5e5 solid 1px; border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','20')">19</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','21')">20</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','22')">21</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','23')">22</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','24')">23</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(70, 140, 200); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','25')">24</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(226, 111, 111); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','5')">25</td>
				</tr>
				<tr>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-left:#e5e5e5 solid 1px; border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','27')">26</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','28')">27</td>
					<td style="padding: 2px 1px; text-align: center; color: rgb(51, 51, 51); font-size: 12px; cursor: pointer; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;" onmouseover="this.style.backgroundColor='#EEFFEE';" onmouseout="this.style.backgroundColor='#ffffff';" onclick="CalendarDaySelecter('eduStrYmd','2012','2','29')">28</td>
					<td style="padding: 2px 1px; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
					<td style="padding: 2px 1px; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
					<td style="padding: 2px 1px; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
					<td style="padding: 2px 1px; background-color: rgb(255, 255, 255); border-bottom:#e5e5e5 solid 1px; border-right:#e5e5e5 solid 1px;">&nbsp;</td>
				</tr>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>