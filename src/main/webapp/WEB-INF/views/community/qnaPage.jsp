<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<c:import url="../inc/header.jsp" />
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/communityLnb.jsp" />
		<div class="sub_content">
			<form action="qnaSeach.do" method="post">
				<div class="customer_section">
					<h4>1:1 문의하기</h4>
					<p class="sub_tit">쇼핑몰 이용중 의문사항이나 불편한 점을 문의해주세요.</p>
					<div class="inquiry_area">
						<ul>
							<li class="inquiry_tit">
								<span class="textbox">전일 17시 이후 ~ 당일 17시 까지 문의해 주신 경우 당일 18시 이내 <br/>
								당일 17시 이후 문의해 주신 경우 익을 9시 이후에 신속하게 답변처리해 드리겠습니다.</span>
								<span class="btn">
								<input type="button" value="상담하기" onclick="location.href='qnaWrite.do'"/>
								</span>
							</li>
							<li>
								<ol class="datebox">
									<li class="datebox_day">
										<span class="tit">등록일</span>
										<span>
											<input type="text" title="날짜박스" value="2014-02-13"/>
											<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"  alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
											<input type="text" title="날짜박스" value="2014-02-14"/>
											<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"   alt="날짜체크박스"/></a>
											
											<button class="btn">조회하기</button>
										</span>
									</li>
									<li class="datebox_search">
										<span class="tit">검색</span>
										<span>
											<select name="schType">
													<!--<option value="a">전체</option>-->
													<option value="t">제목</option>
													<!--<option value="c">내용</option>-->
												</select>
												<input type="text" id="srchKeyword" name="srchKeyword" title="searchbox" />
											<button class="btn">조회하기</button>
										</span>
									</li>
								</ol>
							</li>
							<li>
								<span class="tbl_tit">검색결과</span><span class="st_color">0건</span>
								<table class="inquiry_tbl" summary="문의하기목록표">
									<caption>문의하기목록</caption>
									<colgroup>
										<col width="20%"/>
										<col width="40%"/>
										<col width="20%"/>
										<col width="20%"/>
									</colgroup>
									<thead>
										<tr>
											<th>제목</th>
											<th>문의일자</th>
											<th>처리현황</th>
										</tr>
									</thead>
									
									<tbody>
										<c:forEach items="${noticeList}" var="qna">
											<tr>
												<td style="text-align:left; padding-left:15px "><a href="/community/qnaView.do?brdSeq=${qna.brdSeq}">${qna.title}</a></td>
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

	<c:import url="../inc/footer.jsp" />
</div>
</body>
</html>