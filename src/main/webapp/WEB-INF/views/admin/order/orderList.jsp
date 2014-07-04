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
	<h1>주문관리 &gt; 주문서리스트 &gt;
	<c:choose>
		<c:when test="${sh eq 'all'}"> 
			<strong>전체리스트</strong>
			<c:set var="ordUrl" value="orderList.do"/>
		</c:when>
		<c:when test="${sh eq 'ordering'}"> 
			<strong>신청중</strong>
			<c:set var="ordUrl" value="orderingList.do"/>
		</c:when>
		<c:when test="${sh eq 'orderComplete'}"> 
			<strong>주문완료</strong>
			<c:set var="ordUrl" value="orderCompleteList.do"/>
		</c:when>
		<c:when test="${sh eq 'Transfering'}"> 
			<strong>배송중</strong>
			<c:set var="ordUrl" value="orderTransferingList.do"/>
		</c:when>
		<c:when test="${sh eq 'TransferReady'}"> 
			<strong>배송준비중</strong>
			<c:set var="ordUrl" value="orderTransferReadyList.do"/>
		</c:when>
		<c:when test="${sh eq 'cancel'}"> 
			<strong>취소신청</strong>
			<c:set var="ordUrl" value="orderCancelList.do"/>
		</c:when>
		<c:when test="${sh eq 'cancelComplete'}"> 
			<strong>취소완료</strong>
			<c:set var="ordUrl" value="orderCancelCompleteList.do"/>
		</c:when>
		<c:when test="${sh eq 'return'}"> 
			<strong>반품신청</strong>
			<c:set var="ordUrl" value="orderTakeBackList.do"/>
		</c:when>
		<c:when test="${sh eq 'retrunComplete'}"> 
			<strong>반품완료</strong>
			<c:set var="ordUrl" value="orderTakeBackCompleteList.do"/>
		</c:when>
		<c:when test="${sh eq 'search'}"> 
			<strong>검색결과</strong>
			<c:set var="ordUrl" value="orderSearchList.do"/>
		</c:when>
				
	</c:choose>
	</h1>
	<form id="sfrm" name="sfrm" method="get" action="orderSearchList.do">
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
						<!-- select id="search_status" name="search_status" onChange="sfrm.submit();">
							<option value="01">:: 주문중 ::</option>
							<option value="02">:: 결제완료 ::</option>
							<option value="03">:: 배송중 ::</option>
							<option value="04">:: 배송완료 ::</option>
							<option value="05">:: 고객확인 ::</option>
						</select> -->
						
						<select id="keyfield" name="keyfield">
							<option value="1" <c:if test="${orderSrchInfo.keyfield == 1}">selected</c:if>>주문번호</option>
							<option value="2" <c:if test="${orderSrchInfo.keyfield == 2}">selected</c:if>>주문자</option>
						</select>
						<input type="text" id="keyword" name="keyword" class="Text" value="${orderSrchInfo.keyword}">
						<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
						<input type="button" value="초기화" class="Small_Button Gray" onClick="">&nbsp;&nbsp;
						<input type="button" value="엑셀로 만들기" class="Small_Button Gray" onClick="location.href='/admin/orderListToExel.do'">&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
		</form>    
	    
	
	<table>
		<colgroup>
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="*" />
			<col width="12%" />
		</colgroup>
		<tr>
			<th>주문일</th>
			<th>주문번호</th>
			<th>상태</th>
			<th>주문자</th>
			<th>관리</th>
			
		</tr>
		<c:choose>
			<c:when test="${odList.size() != 0}">
				<c:forEach items="${odList}" var="odList">
					<tr>
						<td>
							${odList.orderDate.substring(0,10)}
						</td>
						<td>
							${odList.orderNo}
						</td>
						<td class="center">
							<c:if test="${odList.orderStatCd eq '01'}">신청대기</c:if>
							<c:if test="${odList.orderStatCd eq '02'}">주문완료</c:if>
							<c:if test="${odList.orderStatCd eq '07'}">취소신청</c:if>
							<c:if test="${odList.orderStatCd eq '08'}">취소완료</c:if>
							<c:if test="${odList.orderStatCd eq '03'}">배송준비</c:if>
							<c:if test="${odList.orderStatCd eq '04'}">배송중</c:if>
							<c:if test="${odList.orderStatCd eq '05'}">배송완료</c:if>
							<c:if test="${odList.orderStatCd eq '09'}">반품신청</c:if>
							<c:if test="${odList.orderStatCd eq '10'}">반품신청완료</c:if>
							
						</td>
						<td>
							${odList.customerInfo.custId} / ${odList.customerInfo.custNm}  / ${odList.ordPrd.prdNm} / <script>SetPriceInput('${odList.paymentInfo.payPrice}');</script> 원
						</td>
						
						<td><input type="button" value="관리"class="Small_Button Gray"onClick="location.href='orderManagement.do?orderNo=${odList.orderNo}&custId=${odList.customerInfo.custId}'"></td>
					</tr>
						
				</c:forEach>
			</c:when>
			<c:otherwise><tr><td colspan="5" height="100">주문이 없습니다.</td></tr></c:otherwise>
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

