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
			<form method="GET" id="eventFrc" name="eventFrc" action="/community/eventSeach.do">
				<div class="customer_section">
					<h4>공지사항</h4>
					<p class="sub_tit">쇼핑몰의 새로운 소식이나 이벤트를 확인하실 수 있습니다.</p>
					<div class="notice_area">
						<ul>
							<li>
								<ol class="datebox">
									<li class="datebox_search">
										<span class="tit">검색</span>
										<span>
											<select name="schType">
													<option value="a">전체</option>
													<option value="t">제목</option>
													<option value="c">내용</option>
												</select>
												<input type="text" id="srchKeyword" name="srchKeyword" title="searchbox" />
											<button class="btn">조회하기</button>
										</span>
									</li>
								</ol>
							</li>
							<li>
								<span class="tbl_tit">검색결과</span><span class="st_color">${noticeList.size()}건</span>
								<table class="notice_tbl" summary="문의하기목록표">
									<caption>문의하기목록</caption>
									<colgroup>
										<col width="8%"/>
										<col width="*"/>
										<col width="12%"/>										
										<col width="8%"/>
									</colgroup>
									<thead>
										<tr>
											<th>No</th>
											<th>Title</th>
											<th>Date</th>
											<th>Hit</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${noticeList}" var="qna">
											<tr>
												<td>${qna.brdSeq}</td>
												<td style="text-align:left; padding-left:15px "><a href="/community/noticeView.do?brdSeq=${qna.brdSeq}">${qna.title}</a></td>
												<td>${qna.insDt.substring(0,10)}</td>												
												<td>${qna.hit}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</li>
						</ul>
						<div class="paging2">
							${pageHtml}
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	
</div>
</body>

<c:import url="../inc/footer.jsp" />