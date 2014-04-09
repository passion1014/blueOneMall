<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>

<c:import  url="../inc/top.jsp" />

<script type="text/javascript">
	$(document).ready(function() {
		$('#prdCtgL').change(function() {
			$.getJSON('/admin/categoryListByParent/' + $('#prdCtgL').val(), function(result) {
//				alert('중분류 size=' + result.length + '    ctgCode=' + result[0].ctgCode);
				var options = '';
				if (result != null && result.length > 0) {
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
</script>


<c:set var="test" value="goodsList" scope="request"/> 
<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="goods"/>
	</c:import>
	

	<div id="Contents">
	<h1>제품관리 &gt; 상품관리 &gt; 상품목록 &gt; <strong>선택상품관리</strong></h1>
	
	<form name="frm" method="post" enctype="multipart/form-data" action="productRegisterProc.do">
	<input type="hidden" name="Mode" value="add_goods">
	<input type="hidden" id="prdCd"      name="prdCd"  value="${prdInfo.prdCd}">
	<input type="hidden" id="fromDate"     name="fromDate" value="1900-01-01">
	<input type="hidden" id="toDate"       name="toDate" value="9999-12-31">
	<input type="hidden" id="prdTransInf"       name="prdTransInf" value="${prdInfo.prdTransInf}">
	<input type="hidden" id="modifyUserId" name="modifyUserId" value="${adminSession.id}">
	
	
	
	<div style="padding:10px 5px 2px 5px;"><b>상품 기본정보</b></div>
	<table>
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="35%">
		</colgroup>
		
		<tr>
			<th>상태설정</th>
			<td style="text-align:left;">
				<b>진열상태</b> :
				<select name="prdDp">
					<option value="y" <c:if test="${prdInfo.prdDp eq 'y'}">select</c:if>>진열</option>
					<option value="n" <c:if test="${prdInfo.prdDp eq 'n'}">select</c:if>>대기</option>
				</select>
				&nbsp;&nbsp;<b>정렬</b> :
				<select id="prdOrd" name="prdOrd">
				<c:forEach var="i" begin="1" end="50" step="1">
					<c:if test="${i!=prdInfo.prdOrd}"> 
						<option value="<c:out value="${i}"></c:out>" <c:if test="${i==prdInfo.prdOrd}">selected</c:if>><c:out value="${i}"></c:out></option>
					</c:if>
				</c:forEach>							
				</select>
			</td>
			<th>특수설정</th>
			<td style="text-align:left;">
				<input type="checkbox" id="prdSpe1" name="prdSpe1" value="y" <c:if test="${prdInfo.prdSpe1 eq 'y'}">checked</c:if>> 베스트 &nbsp;
				<input type="checkbox" id="prdSpe2" name="prdSpe2" value="y" <c:if test="${prdInfo.prdSpe2 eq 'y'}">checked</c:if>> 행사품목 &nbsp;
			</td>
		</tr>
		
		<tr>
			<th>상품분류</th>
			<td colspan="3" style="text-align:left;">
				<select id="prdCtgL" name="prdCtgL">
					<c:forEach items="${ctgLList}" var="largeTypeObj">
						<option value="<c:out value="${largeTypeObj.ctgCode}"></c:out>" <c:if test="${largeTypeObj.ctgCode==prdInfo.prdCtgL}">selected</c:if>><c:out value="${largeTypeObj.ctgName}"></c:out></option>
					</c:forEach>							
				</select>&nbsp;
				
				<select id="prdCtgM" name="prdCtgM">
<<<<<<< HEAD
					<option value="">:::: 중분류를 선택하여주십시오 ::::</option>	
<<<<<<< HEAD
					<c:forEach items="${ctgMList2}" var="middleTypeObj">
=======
					<c:forEach items="${ctgList2}" var="middleTypeObj">
						<option value="<c:out value="${middleTypeObj.ctgCode}"></c:out>" <c:if test="${middleTypeObj.ctgCode==prdInfo.prdCtgM}">selected</c:if>><c:out value="${middleTypeObj.ctgName}"></c:out></option>
=======
				
					<c:forEach items="${ctgMList}" var="middleTypeObj">
>>>>>>> c831da999bf6fcdd21bff54c37717370fd517bab
						<option value="<c:out value="${middleTypeObj.ctgCode}"></c:out>" <c:if test="${middleTypeObj.ctgCode==prdInfo.prdCtgM}">  selected </c:if>><c:out value="${middleTypeObj.ctgName}"></c:out></option>
>>>>>>> 0326ec79abf11b2e6070643269c34fd4e924cd32
					</c:forEach>							
				</select>&nbsp;		

				<select id="prdCtgS" name="prdCtgS">
<<<<<<< HEAD
					<option value="">:::: 소분류를 선택하여 주십시오 ::::</option>
					<c:forEach items="${ctgList3}" var="smallTypeObj">
						<option value="<c:out value="${smallTypeObj.ctgCode}"></c:out>" <c:if test="${smallTypeObj.ctgCode==prdInfo.prdCtgS}">selected</c:if>><c:out value="${smallTypeObj.ctgName}"></c:out></option>
=======
					<c:forEach items="${ctgSList}" var="smallTypeObj">
						<option value="<c:out value="${smallTypeObj.ctgCode}"></c:out>" <c:if test="${smallTypeObj.ctgCode==prdInfo.prdCtgS}">  selected </c:if>><c:out value="${smallTypeObj.ctgName}"></c:out></option>
>>>>>>> 0326ec79abf11b2e6070643269c34fd4e924cd32
					</c:forEach>	
				</select> &nbsp;				
			</td>
		</tr>

		<tr>
			<th>상품명</th>
			<td colspan="3" class="left">
				<input type="text" id="prdNm" name="prdNm" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
	</table>

	<div style="padding:10px 5px 2px 5px;"><b>상품 추가정보</b></div>
	<table>
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="*">
		</colgroup>
		
		<tr>
			<th>소비자가</th>
			<td style="text-align:left;">
				<input type="text" id="prdPrice" name="prdPrice" value="${prdInfo.prdPrice}" style="width:70%;text-align:right;padding-right:10px;"> 원
			</td>
			<th>판매가</th>
			<td style="text-align:left;">
				<input type="text" id="prdSellPrc" name="prdSellPrc"  value="${prdInfo.prdSellPrc}" style="width:70%;text-align:right;padding-right:10px;""> 원
			</td>		
		</tr>
		
		<tr>
			<th>모델명</th>
			<td colspan="3" class="left">
				<input type="text" id="prdModel" name="prdModel" style="width:80%;" value="${prdInfo.prdNm}" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
		<tr>
			<th>모델번호</th>
			<td colspan="3" class="left">
				<input type="text" id="prdModelNo" name="prdModelNo" value="${prdInfo.prdModelNo}" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td colspan="3" class="left">
				<input type="text" id="prdBrand" name="prdBrand" value="${prdInfo.prdBrand}" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
	 
		<tr>
			<th>리스트 이미지</th>
			<td colspan="3" class="left">
				<input type="file" id="proListImg" name="proListImg" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지1</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg1" name="proImg1" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지2</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg2" name="proImg2" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지3</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg3" name="proImg3" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지4</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg4" name="proImg4" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		
		<tr>
			<th>목록내용</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="prdListCmt" id="prdListCmt"  class="Text Kor" style="width:97%;height:150px;">
				<c:out value="${prdInfo.prdListCmt}"></c:out>
				</textarea>
			
			</td>
		</tr>
		<tr>
			<th>상세내용</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="prdConts" id="prdConts" class="Text Kor" style="width:97%;height:300px;">
				<c:out value="${prdInfo.prdConts}"></c:out>
				</textarea>
			</td>
		</tr>
		
		<tr>
			<th>검색어</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="schWord" id="schWord"  class="Text Kor" style="width:97%;height:80px;">
				<c:out value="${prdInfo.schWord}"></c:out>
				</textarea>
				<div style="margin-top:5px;">※ , 로 구분하여 입력하여 주십시오</div>
			</td>
		</tr>
	
		<tr>
			<th>배송/반품/교환정보</th>
			<td colspan="3" style="text-align:left;">
				<div><input type="button" value="배송정보선택"></div>
			</td>
		</tr>

		
		<tr>
			<th>관리 참고사항</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="admMeno" id="admMeno" class="Text Kor" style="width:97%;height:250px;">
				<c:out value="${prdInfo.admMeno}"></c:out>
				
				</textarea>
			</td>
		</tr>

	</table>

	<div class="Btn_area">
		<input type="submit" value="수정하기" class="Button Gray"> &nbsp; 
		<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
	</div>

	</form>

	</div>
	

</div>
</body>
<script language="JavaScript" type="text/JavaScript">

<c:import url="../inc/footer.jsp" />