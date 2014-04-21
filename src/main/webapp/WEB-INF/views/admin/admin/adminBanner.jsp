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
		
	<div style="text-align:center;color:#fff;background:#2d80a3;;width:300px;height:110px;margin:150px auto;padding-top:50px;border-bottom:2px solid #00395a;">
		<span style="font-weight:bold">현대몰</span> 배너메인입니다.<br><br>원하시는 관리메뉴를 선택해 주세요
	</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />
</body>
</html>