<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
<c:import  url="../inc/topSub.jsp" />

<body>
<div class="wrap">

	<c:import  url="../inc/header.jsp" />

	<div class="container">
		
		<c:import url="../inc/communityLnb.jsp" />
		
		<div class="sub_content">
			<form action="faqSearch.do" method="post" name="faqFm">
				<div class="customer_section">
					<h4>FAQ</h4>
					<p class="sub_tit">FAQ 페이지입니다.</p>
					<div class="notice_area">
						<ul>
							<li>
								<ol class="datebox">
									<li class="datebox_search">
										<span class="tit">검색</span>
										<span>
											<select name="schType">
												<option value="t">전체</option>
												<option value="q">질문</option>
												<option value="a">답변</option>
											</select>
											<input type="text" id="schWord" name="schWord" title="searchbox" />
											<button class="btn">조회하기</button>
										</span>
									</li>
								</ol>
							</li>
							<li>
								<span class="tbl_tit">검색결과</span><span class="st_color">${faqList.size()}건</span>
								<div>
									<span class="top_linebrink"></span>
									<div class="accordion">
										<c:forEach items="${faqList}" var="qna" varStatus="i">
											<div class="accord-header question left">
												<span class="btn_question">질문</span><span>${qna.faqQes}</span>
											</div>
											
											<div class="accord-content answer left">
												<script type="text/javascript">
													document.open();
													var text=unescape('${qna.faqAns}');
													document.write(text);
													document.close();
													
												</script>
											</div>
										</c:forEach>
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
<script type="text/javascript">
$(document).ready(function(){
	// accordion
	$(".accordion .accord-header").click(function() {
		if($(this).next("div").is(":visible")){
			
			$(this).next("div").slideUp("normal");
			
		} else {
			$(".accordion .accord-content").slideUp("normal");
			$(this).next("div").slideToggle("normal");
			
		}
	});
});
</script>
<!--  container 끝   -->	

</div>
</body>

<c:import url="../inc/footer.jsp" />