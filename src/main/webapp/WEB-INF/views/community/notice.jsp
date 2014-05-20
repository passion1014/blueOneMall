<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:import  url="../inc/topSub.jsp" />
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>###### 현대프로모션몰 ######</title>
</head>
<body>
	<div class="wrap">
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/communityLnb.jsp" />
		<div class="sub_content">
			<form action="#" method="post">
				<div class="customer_section">
					<h4>공지사항</h4>
					<p class="sub_tit">쇼핑몰의 새로운 소식이나 이벤트를 확인하실 수 있습니다.</p>
					<div class="notice_area">
						<ul>
							<li>
								<ol class="datebox">
									<li class="datebox_day">
										<span class="tit">등록일</span>
										<span>
											<input type="text" title="날짜박스" value="2014-02-13"/>
											<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"  alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
											<input type="text" title="날짜박스" value="2014-02-14"/>
											<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"  alt="날짜체크박스"/></a>
											<span class="titext">당일</span>
											<button>1개월</button>
											<button>3개월</button>
											<button>6개월</button>
										</span>
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
											<th>상담유형</th>
											<th>제목</th>
											<th>문의일자</th>
											<th>처리현황</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th><a href="/community/noticeView.do">[구분]</a></th>
											<td>쿠폰사용 방법에 대해 알려드립니다.</td>
											<td>2014.02.14</td>
											<td>11</td>
										</tr>
										<tr>
											<th>[구분]</th>
											<td>쿠폰사용 방법에 대해 알려드립니다.</td>
											<td>2014.02.14</td>
											<td>11</td>
										</tr>
										<tr>
											<th>[구분]</th>
											<td>쿠폰사용 방법에 대해 알려드립니다.</td>
											<td>2014.02.14</td>
											<td>11</td>
										</tr>
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