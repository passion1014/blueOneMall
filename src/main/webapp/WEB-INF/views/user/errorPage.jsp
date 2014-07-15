<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/topSub.jsp" />

<body>
	<div class="header">
			<p class="top_errorimg">
				<img src="<c:url value='/resources/img/main/common/blueone_logo2.png'/>" alt="blueone logo"/>
			</p>
	</div>
<!--  header 끝   -->

	<div class="container">
		<div class="error_area">
			<p class="error_img"><img src="<c:url value='/resources/img/common/error.png'/>" alt="error image"/></p>
			<p class="error_text">로그 세션이 만료되었습니다.<br /><br />다시 로그인 해주세요.</p>
			<a href="http://gift.e-hyundai.com/static/mall/html/1067115796.html" class="error_link">현대몰</a>
		</div>
		
	</div>
	<c:import url="../inc/footer.jsp" />
</body>
</html>