<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="img"/>
	</c:import>
		
	<div id="Contents">
	<h1>메인관리 &gt; 배너 관리 &gt; <strong>배너관리</strong></h1>
	
	<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="adminBnImgProc.do">
	<input type="hidden" id="modifyUserId" name="modifyUserId" value="${adminSession.id}">
	
	
	

	<div style="padding:10px 5px 2px 5px;"><b>배너이미지 등록</b></div>
	<table>
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="*">
		</colgroup>
		
		
		
		<tr>
			<th>배너이미지1</th>
			<td colspan="3" class="left">
				<input type="file" id="ban1Up" name="ban1Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지1-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl1" name="bnUrl1" style="width:80%;"> 
			</td>
		</tr>
		
		<tr>
			<th>배너이미지2</th>
			<td colspan="3" class="left">
				<input type="file" id="ban2Up" name="ban2Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지2-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl2" name="bnUrl2" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>배너이미지3</th>
			<td colspan="3" class="left">
				<input type="file" id="ban3Up" name="ban3Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지3-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl3" name="bnUrl3" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>배너이미지4</th>
			<td colspan="3" class="left">
				<input type="file" id="ban4Up" name="ban4Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지4-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl4" name="bnUrl4" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>배너이미지5</th>
			<td colspan="3" class="left">
				<input type="file" id="ban5Up" name="ban5Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지5-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl5" name="bnUrl5" style="width:80%;"> 
			</td>
		</tr>
		<tr>
			<th>배너이미지6</th>
			<td colspan="3" class="left">
				<input type="file" id="ban6Up" name="ban6Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지6-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl6" name="bnUrl6" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>배너이미지7</th>
			<td colspan="3" class="left">
				<input type="file" id="ban7Up" name="ban7Up" style="width:80%;">
			</td>
		</tr>
		<tr>	
			<th>배너이미지7-URL</th>
			<td colspan="2" class="left">
				<input type="text" id="bnUrl7" name="bnUrl7" style="width:80%;"> 
			</td>
		</tr>
		
		
		
	
		
	</table>

	<div class="Btn_area">
		<input type="submit" value="등록" 	class="Button Gray"  / > &nbsp; 
		<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
	</div>

	</form>

	</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />
</body>
</html>