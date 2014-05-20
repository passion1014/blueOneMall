<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<c:import  url="../inc/topSub.jsp" />

<body>
<div class="wrap">

	<c:import  url="../inc/header.jsp" />

	<div class="container">
		
		<c:import url="../inc/communityLnb.jsp" />
		
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
											</select>
											<input type="text" title="searchbox" />
											<button class="btn">조회하기</button>
										</span>
									</li>
								</ol>
							</li>
							<li>
								<span class="tbl_tit">검색결과</span><span class="st_color">0건</span>
								<div>
									<div class="accordion">
										<div class="accord-header question left">
											<span class="btn_question">질문</span>
										</div>
										<div class="accord-content answer left">답변</div>
									</div>
								</div>
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