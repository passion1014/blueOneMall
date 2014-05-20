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
					<p class="sub_tit">FAQ 페이지입니다.</p>
					<div class="notice_area">
						<ul>
							<li>
								<ol class="datebox">
									<li class="datebox_day">
										
									</li>
									<li class="datebox_search">
										<span class="tit">검색</span>
										<span>
											<select>
												<option>전체</option>
												<option>질문</option>
												<option>답변</option>
											</select>
											<input type="text" title="searchbox" />
											<button class="btn">조회하기</button>
										</span>
									</li>
								</ol>
							</li>
							<li>
								<span class="tbl_tit">검색결과</span><span class="st_color">0건</span>
								<table class="notice_tbl" summary="문의하기목록표">
									<caption>문의하기목록</caption>
									<colgroup>
										<col width="20%"/>
										<col width="40%"/>
										<col width="20%"/>
										<col width="20%"/>
									</colgroup>
									<thead>
										<tr>
											<!-- <th>상담유형</th> -->
											<th>제목</th>
											<th>문의일자</th>
											<!-- <th>처리현황</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${faqList}" var="qna">
										<tr>
											<th><a href="faqView.do?faqIdx=${qna.faqIdx}">${qna.faqQes}</a></th>
											<td>${qna.faqRegDate.substring(0,10)}</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</li>
						</ul>
						<div class="paging2">
							<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>"  alt="처음으로"></a>
							<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>"  alt="이전"></a>
							<a href="#" class="on">1</a>
							<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>"  alt="다음"></a>
							<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>"  alt="끝으로"></a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<import url="../inc/footer.jsp" />
</div>
</body>
</html>