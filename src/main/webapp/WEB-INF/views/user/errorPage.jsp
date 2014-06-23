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
			<p class="error_text">로그인이 잘못되었습니다. 다시 접근해주세요</p>
			<a href="http://gift.e-hyundai.com/static/default/html/index2.html" class="error_link">현대몰</a>
		</div>
		
	</div>
	<c:import url="../inc/footer.jsp" />
</body>
</html>