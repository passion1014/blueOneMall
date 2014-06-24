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
			<form action="#" method="post" name="noticeView">
				<div class="customer_section">
					<h4>Event</h4>
					<p class="sub_tit">쇼핑몰의 새로운 소식이나 이벤트를 확인하실 수 있습니다.</p>
					<div class="notice_area">
						<ul class="no_answer">
							<li>
								<table class="noanswer_tbl" summary="이벤트목록">
									<caption>이벤트목록표</caption>
									<colgroup>
										<col width="25%"/>
										<col width="25%"/>
										<col width="25%"/>
										<col width="25%"/>
									</colgroup>
									<tbody>
										<tr>
											<th>제목</th>
											<td>${brdView.title}</td>
											<th>날짜</th>
											<td>${brdView.insDt.substring(0,10)}</td>
										</tr>
										<tr>
											<td colspan="4" class="h_tdarea">
											${brdView.content}
											</td>
										</tr>
										
									</tbody>
								</table>
							</li>
						</ul>
						<%-- <div class="paging2">
							<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>"  alt="처음으로"></a>
							<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/>"  alt="이전"></a>
							<a href="#" class="on">1</a>
							<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>"  alt="다음"></a>
							<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>"  alt="끝으로"></a>
						</div> --%>
					</div>
				</div>
			</form>
			<c:forEach items="${qnaList}" var="qna">
			 <table>
				<tr>
					<td class="texalign"><a href="#">${qna.content}</a></td>
					<td class="bgcolor">${qna.insUser}</td>
					<td>${qna.insDt}</td>
				</tr>
			 </table>
			</c:forEach>
			<form action="/community/writeEvent.do" method="post" name="qnaform">
				<input type="hidden" name="brdSeq" value="${brdView.brdSeq}" />
					${customerSession.custNm} <input type="text" name="content" style="height:80px;"> <input type="submit" value="댓글등록">
					
			</form>
		</div>
	</div>
<!--  container 끝   -->	

</div>
</body>
</body>

<c:import url="../inc/footer.jsp" />