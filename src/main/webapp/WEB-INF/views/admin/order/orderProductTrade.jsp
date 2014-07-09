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
		dateFormat: "yy-mm-dd",
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
	<h1>주문관리 &gt;거래내역조회&gt;<strong>상품별거래내역</strong>
		
	</h1>
	<form id="sfrm" name="sfrm" method="get" action="orderSearchProdcutList.do">
		<input type="hidden" id="slot" name="slot" value="member">
		<input type="hidden" id="type" name="type" value="member_list">
		<table>
			<tr>
				<td class="left">
					<div style="margin-top:5px;">
						<b>주문날짜검색</b> <input type="checkbox" id="schChkDate" name="schChkDate" value="Y" onClick="dateDisable();" checked/> &nbsp;&nbsp;
							<input type="text" id="srchStdDt" name="srchStdDt" value="${orderSrchInfo.srchStdDt}" class="Text Kor" style="width:65px;" /> 일 부터
							<input type="text" id="srchEdDt" name="srchEdDt" value="${orderSrchInfo.srchEdDt}" class="Text Kor" style="width:65px;" />일 까지 &nbsp;&nbsp;
					</div>
		
					<div style="margin-top:5px;">
						
						<select id="keyfield" name="keyfield">
							<option value="1" <c:if test="${orderSrchInfo.keyfield == 1}">selected</c:if>>주문번호</option>
							<!-- <option value="2" <c:if test="${orderSrchInfo.keyfield == 2}">selected</c:if>>주문자</option> -->
						</select>
						<input type="text" id="keyword" name="keyword" class="Text" value="${orderSrchInfo.keyword}">
						<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
						<input type="button" value="초기화" class="Small_Button Gray" onClick="">&nbsp;&nbsp;
					 <!-- 	<input type="button" value="엑셀로 만들기" class="Small_Button Gray" onClick="location.href='/admin/orderListToExel.do?orderStatCd=${orderStatCd}'">&nbsp;&nbsp; -->
					 
					 </div>
				</td>
			</tr>
		</table>
		</form>    
	    

	<table>
		<colgroup>
			<col width="10%" />
			<col width="10%" />
			<col width="*" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<tr>
			<th>주문일</th>
			<th>주문번호</th>
			<th>상품명</th>
			<th>옵션</th>
			<th>상품코드</th>
			<th>가격</th>
			<th>판매수량</th>
			<th>결제금액</th>
			
		</tr>
		<c:choose>
			<c:when test="${odList.size() != 0}">
				<c:forEach items="${odList}" var="odList">
					<tr>
						<td class="center">
							${odList.orderDate.substring(0,10)}
						</td>
						<td class="center">
							${odList.orderNo}
						</td>
						<td>
							 ${odList.ordPrd.prdNm}
						</td>
						<td>
							 <c:if test="${odList.ordPrd.prdOpColor ne ''}">${odList.ordPrd.prdOpColor.substring(3)}</c:if>
						</td>
						<td>
							 ${odList.ordPrd.prdCd}
						</td>
						<td>
							 <script>SetPriceInput('${odList.ordPrd.sellPrice}');</script> 원
						</td>
						<td>
							 ${odList.ordPrd.buyCnt}개 
						</td>
						<td>
							 <script>SetPriceInput('${odList.paymentInfo.payPrice}');</script> 원
						</td>
						
					</tr>
						
				</c:forEach>
			</c:when>
			<c:otherwise><tr><th colspan="8" height="100">주문이 없습니다.</th></tr></c:otherwise>
		</c:choose>
	
	</table>
	<div align="center" style="padding-top:10px;">
		<c:forEach var="i" begin="1" end="${endNum}">
			<c:if test="${orderSrchInfo == null}"><input type="button" value="${i}" onClick="javascript:location.href='${ordUrl}?page=${i}'"></c:if>			
			<c:if test="${orderSrchInfo != null}"><input type="button" value="${i}"
			onClick="javascript:location.href='/admin/orderSearchList.do?srchStdDt=${orderSrchInfo.srchStdDt}&srchEdDt=${orderSrchInfo.srchEdDt}&keyfield=${orderSrchInfo.keyfield}&keyword=${orderSrchInfo.keyword}&page=${i}'"
			/></c:if>			
		</c:forEach>
	</div>
	
	
</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />