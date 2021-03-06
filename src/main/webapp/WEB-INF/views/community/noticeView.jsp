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
					<h4>공지사항</h4>
					<p class="sub_tit">쇼핑몰의 새로운 소식이나 이벤트를 확인하실 수 있습니다.</p>
					<div class="notice_area">
						<ul class="no_answer">
							<li>
								<table class="noanswer_tbl" summary="공지사항목록">
									<caption>공지사항목록표</caption>
									<colgroup>
										<col width="15%"/>
										<col width="*"/>
										<col width="10%"/>
									</colgroup>
									<tbody>
										<tr>
											<th>제목</th>
											<td>${brdView.title}</td>
											<td style="text-align:right;padding-right:5px;">${brdView.insDt.substring(0,10)}</td>
										</tr>
										<tr>
											<td colspan="3" class="h_tdarea" style="padding:5px 10px 5px 10px;">
											<input type="hidden" name="content"/>	${content}
												<script type="text/javascript">
													document.open();
													var text=unescape('${brdView.content}');
													document.write(text);
													document.close();
													
												</script> 
											</td>
										</tr>
									</tbody>
								</table>	
							</li>
						</ul>
						<div class="evnt_btn">
							<input type="button" value="목록으로" onClick="location.href='notice.do';">
						</div>
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
		</div>
	</div>
<!--  container 끝   -->	

</div>
</body>
</body>

<c:import url="../inc/footer.jsp" />