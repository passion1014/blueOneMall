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
	<h1>메인관리 &gt; 메인디자인 관리 &gt; <strong>디자인관리</strong></h1>
	
	<form name="tx_editor_form" method="post"  action="adminDesignProc.do">
	<input type="hidden" id="modifyUserId" name="modifyUserId" value="${adminSession.id}">
	
	
	

	<div style="padding:10px 5px 2px 5px;"><b>이미지 등록</b></div>
	<table>
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="*">
		</colgroup>
		
		
		<tr>
			<th>메인이미지1</th>
			<td colspan="2" class="left">
				<input type="file" id="mdImg1" name="mdImg1" style="width:80%;"> 
			</td>
		</tr>
		<tr>	
			<th>메인이미지1URL</th>
			<td colspan="2" class="left">
				<input type="text" id="mdUrl1" name="mdUrl1" style="width:80%;"> 
			</td>
		</tr>
		<tr>
			<th>메인이미지2</th>
			<td colspan="2" class="left">
				<input type="file" id="mdImg2" name="mdImg2" style="width:80%;">
				<input type="hidden" name="mpImg2" /> 
			</td>
		</tr>
		<tr>	
			<th>메인이미지2URL</th>
			<td colspan="2" class="left">
				<input type="text" id="mdUrl2" name="mdUrl2" style="width:80%;"> 
			</td>
		</tr>
		<tr>
			<th>메인이미지3</th>
			<td colspan="2" class="left">
				<input type="file" id="mdImg3" name="mdImg3" style="width:80%;"> 
			</td>
		</tr>
		<tr>	
			<th>메인이미지3URL</th>
			<td colspan="2" class="left">
				<input type="text" id="mdUrl3" name="mdUrl3" style="width:80%;"> 
			</td>
		</tr>
		<tr>
			<th>메인이미지4</th>
			<td colspan="2" class="left">
				<input type="file" id="mdImg4" name="mdImg4" style="width:80%;"> 
			</td>
		</tr>
		<tr>	
			<th>메인이미지4URL</th>
			<td colspan="2" class="left">
				<input type="text" id="mdUrl4" name="mdUrl4" style="width:80%;"> 
			</td>
		</tr>
		<tr>
			<th>메인이미지5</th>
			<td colspan="2" class="left">
				<input type="file" id="mdImg5" name="mdImg5" style="width:80%;"> 
			</td>
		</tr>
		<tr>	
			<th>메인이미지5URL</th>
			<td colspan="2" class="left">
				<input type="text" id="mdUrl5" name="mdUrl5" style="width:80%;"> 
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