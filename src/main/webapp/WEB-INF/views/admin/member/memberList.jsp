<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../inc/top.jsp" />

<body>

<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="member" value="member"/>
	</c:import>
	
	
	
	<div style="text-align:center;color:#000;width:300px;height:110px;margin:150px auto;padding-top:50px;">
		<span style="font-weight:bold">현대몰</span> 회원관리페이지입니다.<br><br>원하시는 관리메뉴를 선택해 주세요
	</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />