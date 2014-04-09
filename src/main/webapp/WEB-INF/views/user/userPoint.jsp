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
	<c:import url="../inc/header.jsp"/>
<!--  header 끝   -->

	<div class="container">
		<c:import url="../inc/userLnb.jsp" />
		<div class="sub_content">
			<form action="#" method="post">
				<div class="mypage_section">
					<h4>적립금현황</h4>
					<p class="sub_tit">나의 적립금 내역을 확인 할 수 있습니다.</p>
					<div class="mypage_step">
						<div class="mpoint_current">
							<div class="mpoint_graph">
								<img src="<c:url value='/resources/img/sub/list_img.jpg'/>"  alt="포인트현황그래프"/>
							</div>
							<dl class="point_tbox">
								<dt>
									<span class="point_tit">구분</span>
									<span class="point_tit">적립금</span>
								</dt>
								<dd class="paddingbox">
									<span class="pbox1">적립예정액</span>
									<span class="tpoint">0P</span>
								</dd>
								<dd class="paddingbox">
									<span class="pbox2">가용적립액</span>
									<span class="tpoint">0P</span>
								</dd>
								<dd class="bt_textbox">
									<span class="pbox_text"><strong>예정&nbsp;:&nbsp;</strong>적립예정액은 적립후 15일이후 가용적립금으로 전환됩니다. </span>
									<span class="pbox_text"><strong>가용&nbsp;:&nbsp;</strong>현재 사용가능한 적립금</span>
								</dd>
							</dl>
						</div>
						<ul>
							<li class="cupon_listbox">
								<ul class="plist_area">
									<li class="on">적립완료</li>
									<li>적립예정</li>
								</ul>
							</li>
							<li class="datebox tmargin">
								<ol>
									<li class="datebox_day">
										<span class="tit">등록일</span>
										<span>
											<input type="text" title="날짜박스" value="2014-02-13"/>
											<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"  alt="날짜체크박스"/></a>&nbsp; ~ &nbsp;
											<input type="text" title="날짜박스" value="2014-02-14"/>
											<a href=""><img src="<c:url value='/resources/img/common/btn_date.gif'/>"  alt="날짜체크박스"/></a>
											<button class="titext">당일</button>
											<button>1개월</button>
											<button>3개월</button>
											<button>6개월</button>
											<button class="btn">조회하기</button>
										</span>
									</li>
									<li class="select_box">
										<span class="text1">적립유형</span>
										<select>
											<option>선택</option>
										</select>
										</span>
										<span class="text2">적립형태</span>
										<select>
											<option>선택</option>
										</select>
									</li>
								</ol>
							</li>
							<li class="cupon_tb">
								<span class="tbl_tit">검색결과</span><span class="st_color">0건</span>
								<table class="cupon_tbl" summary="쿠폰사용목록표">
									<caption>쿠폰사용표</caption>
									<colgroup>
										<col width="5%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="35%"/>
										<col width="12%"/>
										<col width="14%"/>
										<col width="14%"/>
									</colgroup>
									<thead>
										<tr>
											<th>NO</th>
											<th>적립일</th>
											<th>적립유형</th>
											<th>내용</th>
											<th>적립금</th>
											<th>적립상태</th>
											<th>유효기간</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th></th>
											<td colspan="6">최근에 적립 내역이 없습니다.</td>
										</tr>
									</tbody>
								</table>
							</li>
							<li class="paging2">
								<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_first.gif'/>"  alt="처음으로"></a>
								<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_prev.gif'/> " alt="이전"></a>
								<a href="#" class="on">1</a>
								<a href="#" class="palign1"><img src="<c:url value='/resources/img/common/btn_next.gif'/>"  alt="다음"></a>
								<a href="#" class="palign2"><img src="<c:url value='/resources/img/common/btn_end.gif'/>"  alt="끝으로"></a>
							</li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<c:import url="../inc/footer.jsp"/>
</div>
</body>
</html>