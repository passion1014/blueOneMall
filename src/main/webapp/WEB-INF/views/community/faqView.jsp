<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:import  url="../inc/topSub.jsp" />
<c:import  url="../inc/topMain.jsp" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>###### 현대프로모션몰 ######</title>
</head>
<body>
	<div class="wrap">
	<import url="../inc/header.jsp" />

<!--  header 끝   -->

	<div class="container">
		<import url="../inc/communityLnb.jsp" />
		<div class="sub_content">
			<form action="#" method="post">
				<div class="customer_section">
					<h4>FAQ</h4>
					<p class="sub_tit">FAQ상세 페이지입니다.</p>
					
					<table>
						<tr>
							<td>Q</td>
							<td>${reFaqInfo.faqQes}</td>
							<td>날짜</td>
							<td>${reFaqInfo.faqRegDate.substring(0,10)}</td>
						</tr>
						<tr>
							<td colspan="4">${reFaqInfo.faqAns}</td>
						</tr>
					</table>
					
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<import url="../inc/footer.jsp" />
</div>
</body>
</html>