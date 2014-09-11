<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
<c:set var="test" value="goodsList" scope="request"/> 

<script language="javascript">
<!--

	$(document).ready(function() {
		$('#prdCtgL').change(function() {
			$.getJSON('/admin/categoryListByParent/' + $('#prdCtgL').val(), function(result) {
	//			alert('중분류 size=' + result.length + '    ctgCode=' + result[0].ctgCode);
				var options = '';
				if (result != null && result.length > 0) {
					options += '<option value="">전체보기</option>';
					
					for (var i = 0; i < result.length; i++) {
						options += '<option value="' + result[i].ctgCode + '">' + result[i].ctgName + '</option>';
					}
				} else {
					options = "<option value=''>없음</option>";
				}
				$("select#prdCtgM").html(options);
			});
		});
		
		$('#prdCtgM').change(function() {
			$.getJSON('/admin/categoryListByParent/' + $('#prdCtgM').val(), function(result) {
				var options = '';
				if (result != null && result.length > 0) {
					options += '<option value="">전체보기</option>';
					
					for (var i = 0; i < result.length; i++) {
						options += '<option value="' + result[i].ctgCode + '">' + result[i].ctgName + '</option>';
					}
				} else {
					options = "<option value=''>없음</option>";
				}
				$("select#prdCtgS").html(options);
			});
		});
		
	});
	
	function list_Submit(key){
	
		if (key == "modify"){
			msg = "선택하신 상품을 일괄 수정 하시겠습니까?" ;
			document.getElementById("cfrm").action = "modifyProductsInf.do" ;
		}else if (key == "del"){
			msg = "선택하신 상품을 삭제 하시겠습니까? \n\n삭제 후에는 복구가 불가능 합니다." ;
			document.getElementById("cfrm").action = "deleteProductsInf.do" ;
		}
		
		
		var chk_num = document.cfrm.elements.length;
		
		for(var i = 0; i < chk_num; i++){
			var checkbox_obj = eval("document.cfrm.elements["+i+"]");
			if(checkbox_obj.checked == true)	break;
		}
	
		if(i == chk_num) {
			alert("먼저 처리하고자 하는 정보를 선택하여 주십시오");
			return false;
		} else {
			if(confirm(msg)){
	
				
				document.getElementById("cfrm").submit() ;
				return false;
			}
		}

	}
	
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
</script>

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="goods"/>
	</c:import>
	

	<div id="Contents">
		<h1>제품관리 &gt; 상품관리 &gt; <strong>상품목록</strong></h1>

		<form name="sfrm" method="get" action="searchProductList.do" onsubmit="return chkForm(this);">
		<input id="slot" name="slot" value="product" type="hidden">
		<input id="type" name="type" value="goods_list" type="hidden">
		<table>
			<tbody><tr>
				<td class="left">
					<div style="margin-bottom:5px;">
						대분류&nbsp;:&nbsp;	 
						<select id="prdCtgL" name="prdCtgL">
							<option value="">전체보기</option>	
							<c:forEach items="${ctgLList}" var="largeTypeObj">
								<option value="<c:out value="${largeTypeObj.ctgCode}"></c:out>" <c:if test="${searchProdInfo.prdCtgL eq largeTypeObj.ctgCode}">selected</c:if>><c:out value="${largeTypeObj.ctgName}"></c:out></option>
							</c:forEach>							
						</select>&nbsp;
						중분류&nbsp;:&nbsp;	
						<select id="prdCtgM" name="prdCtgM">
							<option value="">전체보기</option>	
							<c:forEach items="${ctgList2}" var="middleTypeObj">
								<option value="<c:out value="${middleTypeObj.ctgCode}"></c:out>" <c:if test="${searchProdInfo.prdCtgM eq middleTypeObj.ctgCode}">selected</c:if>><c:out value="${middleTypeObj.ctgName}"></c:out></option>
							</c:forEach>							
						</select>&nbsp;		
						소분류&nbsp;:&nbsp;	
						<select id="prdCtgS" name="prdCtgS">
							<option value="">전체보기</option>
							<c:forEach items="${ctgList3}" var="smallTypeObj">
								<option value="<c:out value="${smallTypeObj.ctgCode}"></c:out>" <c:if test="${searchProdInfo.prdCtgS eq smallTypeObj.ctgCode}">selected</c:if>><c:out value="${smallTypeObj.ctgName}"></c:out></option>
							</c:forEach>	
						</select> &nbsp;
					</div>
					<div>
						<b>진열상태</b>
						<select name="prdDp">
							<option value="">전체</option>
							<option value="y" <c:if test="${searchProdInfo.prdDp eq 'y'}">selected</c:if>>진열</option>
							<option value="n" <c:if test="${searchProdInfo.prdDp eq 'n'}">selected</c:if>>대기</option>
						</select> &nbsp;
							<input type="checkbox" name="prdSpe1" value="y" <c:if test="${searchProdInfo.prdSpe1 eq 'y'}">checked</c:if>> 베스트 &nbsp;
							<input type="checkbox" name="prdSpe2" value="y" <c:if test="${searchProdInfo.prdSpe2 eq 'y'}">checked</c:if>> 행사품목 &nbsp; &nbsp;&nbsp;
						<select name="schType" id="schType">
							<option value="2" <c:if test="${searchProdInfo.schType == 2}">selected</c:if>>상품명</option>
							<option value="1" <c:if test="${searchProdInfo.schType == 1}">selected</c:if>>검색단어</option>
						</select> &nbsp;
						<input name="searchWord" id="searchWord" value="${searchProdInfo.searchWord}" type="text"> &nbsp;
						<input value="검색" class="Small_Button Gray" type="submit">
						<input value="초기화" class="Small_Button Gray" title="초기하기" onclick="#" type="button">
						<input type="button" value="엑셀로 만들기" class="Small_Button Gray" onClick="location.href='/admin/productListToExel.do'">&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</tbody></table>
		</form>
	
		<form id="cfrm" name="cfrm" method="POST" action="deleteProductsInf.do" >
		<input id="Mode" name="Mode" value="" type="hidden">
		<table>
			<colgroup>
				<col width="3%">
				<col width="5%">
				<col width="14%">
				<col width="10%">
				<col width="*">
				<col width="9%">
				<col width="9%">
			</colgroup>
			<tbody>
				<tr>
					<th>
     					<input id="muti_chk" name="multi_chk" onClick="allCheckbox('document.cfrm.unit_chk','multi_chk');" type="checkbox">
					</th>
					<th>Code</th>
					<th>이미지</th>
					<th>상태</th>
					<th>상품정보</th>
					<th>가격</th>
					<th>관리</th>
				</tr>
				<c:choose>
				<c:when test="${list.size() != 0}">
				<c:forEach items="${list}" var="produts">
				<tr>
				
     					<td class="center">
     					<input type="checkbox" id="unit_chk" name="unit_chk" value="${produts.prdCd}">

     					</td> 

						<td style="text-align:center;">${produts.prdCd}</td>
						<td style="text-align:center;">
							<img src="${produts.attFilePath}" width="119">
						</td>
						<td style="text-align:center;">
							<c:if test="${produts.prdDp eq 'y'}">진열</c:if>
							<c:if test="${produts.prdDp eq 'n'}">대기</c:if>
						</td>
						<td>
							상품분류:
							<c:if test="${produts.ctgLargeName eq ''}">
								없음
							</c:if>
							${produts.ctgLargeName}>
							<c:if test="${produts.ctgMiddleCode eq ''}">
								없음
							</c:if>
							${produts.ctgMiddleName}>
							
							<c:if test="${produts.ctgSmallCode eq ''}">
								없음
							</c:if>
							${produts.ctgSmallName}<br />
							
							특별분류:
									
										<c:if test="${produts.prdSpe1 eq 'y'}">
											<c:out value="베스트"></c:out>
										</c:if>
										
										<c:if test="${produts.prdSpe2 eq 'y'}">
											<c:out value="행사품목"></c:out>
										</c:if><br />
									
							상품명 : ${produts.prdNm}<br />
							재고 : ${produts.prdStock}
						</td>
						<td class="right">
							<strike><script>SetPriceInput('${produts.prdPrice}');</script></strike> 원<br />
							<b><script>SetPriceInput('${produts.prdSellPrc}');</script></b> 원						
						</td>
						<td class="center">
						<input value="보기" class="Small_Button Gray" style="margin-bottom:5px;" onclick="openWin('./adminProductView.do?prdCd=${produts.prdCd}','goods_viewer',1028,900,'scrollbars=yes');" type="button"> <br>
						<input value="관리" class="Small_Button Gray" style="margin-bottom:5px;" onclick="javascript:location.href='productManagement.do?pCd=${produts.prdCd}'" type="button"><br>
						<input value="삭제" class="Small_Button Gray" onclick="confirm_process('','삭제하시겠습니까? \n\n삭제후에는 복원이 불가능합니다.','deleteProductInf.do?prdCd=${produts.prdCd}')" type="button">
					</td>
				</tr>
					
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" height="200"> 등록된 상품이 없습니다.</td>
				</tr>					
		    </c:otherwise>
		</c:choose>
	
	
			
		
			</tbody>
		</table>
		
		<div style="padding:15px 0px 10px 0px;">
			<span class="f_left">
				<select name="prdDp">
					<option value="">변경없음</option>
					<option value="y">진열</option>
					<option value="n">대기</option>
				</select> &nbsp;
				<input name="prdSpe1" value="y" type="checkbox"> 베스트 &nbsp;
				<input name="prdSpe2" value="y" type="checkbox"> 행사품목 &nbsp;
				<input name="prdStock" value="0" type="checkbox"> 품절항목&nbsp;
				<input type="button" value="선택수정" class="Small_Button Green" onClick="list_Submit('modify')" /> 
				<input type="button" value="선택삭제" class="Small_Button Gray" onClick="list_Submit('del')"/>
				
			</span>
			
		</div>
		</form>
		<!-- page -->
		<div id="Paser">
			<a class="palign1" href="productList.do?page=1"><img src='/resources/img/common/btn_first.gif' alt='첫 페이지로 이동' /></a>
			
			<c:set var="prePage" value="${page-1}"/>
			<c:if test="${prePage < 1 }"><c:set var="prePage" value="1"/></c:if>
			<a class="palign2" href="productList.do?page=${prePage}"><img src='/resources/img/common/btn_prev.gif' alt='이전 페이지로 이동' /></a>
			
			<c:forEach var="i" begin="1" end="${endNum}">
				<a href="productList.do?page=${i}" <c:if test="${i == page}">class="on"</c:if>>${i}</a>
			</c:forEach>
			
			<c:set var="nextPage" value="${page+1}"/>
			<c:if test="${nextPage > endNum }"><c:set var="nextPage" value="${endNum}"/></c:if>
			<a class="palign1" href="productList.do?page=${nextPage}"><img src='/resources/img/common/btn_next.gif' alt='다음 페이지로 이동' /></a>
			
			<a class="palign2" href="productList.do?page=${endNum}"><img src='/resources/img/common/btn_end.gif' alt='마지막 페이지로 이동' /></a>
		</div>
	</div>
	
</div>
</body>

<c:import url="../inc/footer.jsp" />

