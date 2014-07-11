<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
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

//-->
<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="order"/>
	</c:import>
 <div id="Contents">
	<h1>주문관리 &gt;거래내역조회&gt;<strong>현대몰 정산 내역</strong></h1>
	
	    

	<table>
		<colgroup>
			<col width="5%" />
			<col width="5%" />
			<col width="3%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="*" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
			<col width="5%" />
		</colgroup>
		<tr>
			<th>주문번호</th>
			<th>상품번호</th>
			<th>주문구분</th>
			<th>주문일자</th>
			<th>행사번호</th>
			<th>고객번호</th>
			<th>과세여부</th>
			<th>판매금액</th>
			<th>기본금</th>
			<th>기타결제</th>
			<th>배송비</th>
			<th>상품명</th>
			<th>단품가격</th>
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
							${odList.orderGb}
						</td>
						<td class="center">
							${odList.orderDm}
						</td>
						<td class="center">
							${odList.memNo}
						</td>
						<td>
							 ${odList.taxGb}
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
			<c:otherwise><tr><th colspan="8" height="100">주문이 없습니다.</th></tr></c:otherwise>
		</c:choose>
	
	</table>
	<div align="center" style="padding-top:10px;">
		<c:forEach var="i" begin="1" end="${endNum}">
			<input type="button" value="${i}" onClick="javascript:location.href='/admin/orderHMList.do?page=${i}'">	
		</c:forEach>
	</div>
	
	
</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />