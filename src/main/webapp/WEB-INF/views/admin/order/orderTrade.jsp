<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
 
<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="order"/>
	</c:import>
 <div id="Contents">
	<h1>주문관리 &gt;거래내역조회&gt; 
	<c:choose>
		<c:when test="${sh eq 'month'}"> 
			<strong>월별거래내역</strong>
		</c:when>
		<c:when test="${sh eq 'product'}"> 
			<strong>상품별거래내역</strong>
		</c:when>
		
	</c:choose>
	</h1>
	<form id="sfrm" name="sfrm" method="get" action="./admin.member.php">
		<input type="hidden" id="slot" name="slot" value="member">
		<input type="hidden" id="type" name="type" value="member_list">
		<table>
			<tr>
				<td class="left">
					<div style="margin-top:5px;">
						<b>주문날짜검색</b> <input type="checkbox" id="schChkDate" name="schChkDate" value="Y" onClick="dateDisable();" /> &nbsp;&nbsp;
							<input type="text" name="schReqSDate" id="schReqSDate" readonly value="" class="Text Kor" style="width:65px;" /> 일 부터
							<input type="text" name="schReqEDate" id="schReqEDate" readonly value="" class="Text Kor" style="width:65px;" />일 까지 &nbsp;&nbsp;
					</div>
		
					<div style="margin-top:5px;">
						
						<select id="keyfield" name="keyfield">
							<option value="orderNo">주문번호</option>
							<option value="cust">주문자</option>
						</select>
						<input type="text" id="keyword" name="keyword" class="Text" value="">
						<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
						<input type="button" value="초기화" class="Small_Button Gray" onClick="location.href='./admin.member.php?slot=member&type=member_list'">&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
		</form>    
	    
	
	<c:choose>
		<c:when test="${sh eq 'month'}"> 
			<h1>월별거래내역조회 페이지 입니다.</h1>
		</c:when>
		<c:when test="${sh eq 'product'}"> 
			<h1>상품별거래내역조회 페이지 입니다.</h1>
		</c:when>
		
	</c:choose>

	<div align="center" style="padding-top:10px;">
	
	</div>
	
	
</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />