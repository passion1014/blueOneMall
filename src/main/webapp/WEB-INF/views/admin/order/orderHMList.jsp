<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
<script type="text/javascript">
<!--
function SetPriceInput(str)
{
	str=str.replace(/,/g,'');
	var retValue = "";
	for(i=1; i<=str.length; i++)
	{
		if(i > 1 && (i%3)==1) 
			  retValue = str.charAt(str.length - i) + "," + retValue;
		else 
			  retValue = str.charAt(str.length - i) + retValue;    
	}
	document.write(retValue); 
}
$(document).ready(function() {
	var dates = $("#srchStdDt,#srchEdDt").datepicker({
		changeYear: true,
		changeMonth: true,
		dateFormat: "yymmdd",
		showMonthAfterYear: true,
		onSelect: function(selectedDate) {
			var option = this.id == "srchStdDt" ? "minDate": "maxDate",
			instance = $(this).data("datepicker"),
			date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
			dates.not(this).datepicker("option", option, date);
		}
	});

});
//-->
</script>
<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="order"/>
	</c:import>
 <div id="Contents">
	<h1>주문관리 &gt;거래내역조회&gt;<strong>현대몰 정산 내역</strong></h1>
	<form id="sfrm" name="sfrm" method="get" action="orderHMList.do">
	<div style="margin-top:5px;">
		<b>주문날짜검색</b> <input type="checkbox" id="schChkDate" name="schChkDate" value="Y" onClick="dateDisable();" /> &nbsp;&nbsp;
			<input type="text" name="srchStdDt" id="srchStdDt" readonly value="${hminfo.srchStdDt}" class="Text Kor" style="width:65px;" /> 일 부터
			<input type="text" name="srchEdDt" id="srchEdDt" readonly value="${hminfo.srchEdDt}" class="Text Kor" style="width:65px;" />일 까지 &nbsp;&nbsp;
			<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
			<!--<input type="button" value="엑셀로 만들기" class="Small_Button Gray" onClick="location.href='/admin/orderListToExel.do?orderStatCd=${orderStatCd}'">&nbsp;&nbsp;-->
	</div>
	<div class="right"><input type="button" value="엑셀로 만들기" class="Small_Button Gray" onClick="location.href='/admin/orderHMListToExel.do?srchStdDt=${hminfo.srchStdDt}&srchEdDt=${hminfo.srchEdDt}'"/></div>
	</form>	
	    

	<table>
		<colgroup>
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="6%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="6%" />
			<col width="6%" />
			<col width="6%" />
			<col width="4%" />
			<col width="*" />
			<col width="6%" />
			<col width="3%" />
			<col width="4%" />
			<col width="3%" />
		</colgroup>
		<tr>
			<th>주문번호</th>
			<th>상품<br>번호</th>
			<th>주문<br>구분</th>
			<th>주문일자</th>
			<th>행사번호</th>
			<th>고객번호</th>
			<th>과세<br>여부</th>
			<th>판매<br>금액</th>
			<th>기본금</th>
			<th>기타<br>결제</th>
			<th>배<br>송<br>비</th>
			<th>상품명</th>
			<th>단품<br>가격</th>
			<th>주문수량</th>
			<th>할인금액</th>
			<th>리턴코드</th>
			
		</tr>
		<c:choose>
			<c:when test="${odList.size() != 0}">
				<c:forEach items="${odList}" var="odList">
					<tr>
						<td class="center">
							${odList.orderNo}
						</td>
						<td class="center">
							${odList.itemCd}
						</td>
						<td class="center">
							<c:if test="${odList.orderGb eq '10'}">주문</c:if>
							<c:if test="${odList.orderGb eq '20'}">취소</c:if>
							(${odList.orderGb})
						</td>
						<td class="center">
							${odList.orderDm.substring(0,8)}<br>${odList.orderDm.substring(8)}
						</td>
						<td class="center">
							${odList.shopEventNo}
						</td>
						<td class="center">
							${odList.memNo}
						</td>
						
						
						<td>
							<c:if test="${odList.taxGb eq '1'}">과세</c:if>
							<c:if test="${odList.taxGb eq '2'}">비과세</c:if>
							(${odList.taxGb})
						</td>
						<td>
							 <script>SetPriceInput('${odList.salePrice}');</script> 원
						</td>
						
						<td>
							 <script>SetPriceInput('${odList.pointAmt}');</script> 원
						</td>
						
						<td>
							<script>SetPriceInput('${odList.etcAmt}');</script> 원
						</td>
						
						<td>
							<script>SetPriceInput('${odList.deliAmt}');</script> 원
						</td>
						
						<td>
							${odList.itemNm}
						</td>
						<td>
							<script>SetPriceInput('${odList.itemPrice}');</script> 원
						</td>
						<td>
							${odList.orderQty}
						</td>
						<td>
							<script>SetPriceInput('${odList.dcPrice}');</script> 원
						</td>
						<td>
							${odList.returnCode}
						</td>
					</tr>
						
				</c:forEach>
			</c:when>
			<c:otherwise><tr><th colspan="16" height="100">주문이 없습니다.</th></tr></c:otherwise>
		</c:choose>
	
	</table>

	<!-- page -->
	<div id="Paser">
		<a class="palign1" href="orderHMList.do?page=1"><img src='/resources/img/common/btn_first.gif' alt='첫 페이지로 이동' /></a>
		
		<c:set var="prePage" value="${page-1}"/>
		<c:if test="${prePage < 1 }"><c:set var="prePage" value="1"/></c:if>
		<a class="palign2" href="orderHMList.do?page=${prePage}"><img src='/resources/img/common/btn_prev.gif' alt='이전 페이지로 이동' /></a>
		
		<c:forEach var="i" begin="1" end="${endNum}">
			<a href="orderHMList.do?page=${i}" <c:if test="${i == page}">class="on"</c:if>>${i}</a>
		</c:forEach>
		
		<c:set var="nextPage" value="${page+1}"/>
		<c:if test="${nextPage > endNum }"><c:set var="nextPage" value="${endNum}"/></c:if>
		<a class="palign1" href="orderHMList.do?page=${nextPage}"><img src='/resources/img/common/btn_next.gif' alt='다음 페이지로 이동' /></a>
		
		<a class="palign2" href="orderHMList.do?page=${endNum}"><img src='/resources/img/common/btn_end.gif' alt='마지막 페이지로 이동' /></a>
	</div>
	
</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />