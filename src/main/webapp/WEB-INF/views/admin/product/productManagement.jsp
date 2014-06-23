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
					options +=  "<option value=''>선택해주세요</option>";
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
					options +=  "<option value=''>선택해주세요</option>";
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
	function fnAddClick() {
		var f = tx_editor_form;
		
		f.action = 'manageProductInfProc.do';
		
		Editor.save(); // 다음 에디터
	}
	
	$(document).ready(function() {
		Editor.modify({
	     	"content":'${prdInfo.content}'
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
	
	<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="manageProductInfProc.do">
	<input type="hidden" name="Mode" value="add_goods">
	<input type="hidden" id="prdCd"        name="prdCd"  value="${prdInfo.prdCd}">
	<input type="hidden" id="fromDate"     name="fromDate" value="1900-01-01">
	<input type="hidden" id="toDate"       name="toDate" value="9999-12-31">
	<input type="hidden" id="prdTransInf"  name="prdTransInf" value="${prdInfo.prdTransInf}">
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
					<option value="y" <c:if test="${prdInfo.prdDp eq 'y'}">selected</c:if>>진열</option>
					<option value="n" <c:if test="${prdInfo.prdDp eq 'n'}">selected</c:if>>대기</option>
				</select>
				&nbsp;&nbsp;<b>정렬</b> :
				<select id="prdOrd" name="prdOrd">
				<c:forEach var="i" begin="1" end="50" step="1">
						<option value="<c:out value="${i}"></c:out>" <c:if test="${i==prdInfo.prdOrd}"> selected </c:if> ><c:out value="${i}"></c:out></option>
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


					<option value="">선택해주세요</option>	

					<c:forEach items="${ctgMList}" var="middleTypeObj">

						<option value="<c:out value="${middleTypeObj.ctgCode}"></c:out>" <c:if test="${middleTypeObj.ctgCode==prdInfo.prdCtgM}">  selected </c:if>><c:out value="${middleTypeObj.ctgName}"></c:out></option>

					</c:forEach>							
				</select>&nbsp;		

				<select id="prdCtgS" name="prdCtgS">
					<option value="">선택해주세요</option>
					<c:forEach items="${ctgSList}" var="smallTypeObj">
						<option value="<c:out value="${smallTypeObj.ctgCode}"></c:out>" <c:if test="${smallTypeObj.ctgCode==prdInfo.prdCtgS}">  selected </c:if>><c:out value="${smallTypeObj.ctgName}"></c:out></option>

					</c:forEach>	
				</select> &nbsp;				
			</td>
		</tr>

		<tr>
			<th>상품명</th>
			<td colspan="3" class="left">
				<input type="text" id="prdNm" name="prdNm" style="width:80%;" value="${prdInfo.prdNm}" required hname=" 상품명을 입력하여 주십시오">
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
				<input type="text" id="prdModel" name="prdModel" style="width:80%;" value="${prdInfo.prdModel}" required hname=" 상품명을 입력하여 주십시오">
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
			<th>재고량</th>
			<td colspan="3" class="left">
				<input type="text" id="prdStock" name="prdStock" value="${prdInfo.prdStock}" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
	
		<tr>
			<th>리스트 이미지</th>
			<td colspan="3" class="left">
			
				<c:set var="listImageViewVal" value="N"/>
				
				<c:forEach items="${imgList}" var="prdImg">
					<c:if test="${'01' eq prdImg.attImgType}">
						<c:set var="listImageViewVal" value="Y"/>
						<img src="${prdImg.attFilePath}" width="119">
						<input type="button" value="삭제" onClick="confirm_process('','해당 사진을 삭제하시겠습니까?','manageProductImgDelProc.do?idx=${prdImg.idx},${prdInfo.prdCd}');"  class="Button Gray">
					</c:if>
				</c:forEach>
				
				<c:if test="${'N' eq listImageViewVal}">
					<input type="file" id="proListImgUp" name="proListImgUp" style="width:80%;"> [118px X 118px]
				</c:if>
		
			</td>
		</tr>
	
		<tr>
			<th>상품이미지1</th>
			<td colspan="3" class="left">
			
				<c:set var="listImageViewVal" value="N"/>
				
				<c:forEach items="${imgList}" var="prdImg">
					<c:if test="${'02' eq prdImg.attImgType && 1 eq prdImg.attImgSeq}">
						<c:set var="listImageViewVal" value="Y"/>
						<img src="${prdImg.attFilePath}" width="379">
						<input type="button" value="삭제" onClick="confirm_process('','해당 사진을 삭제하시겠습니까?','manageProductImgDelProc.do?idx=${prdImg.idx},${prdInfo.prdCd}');"  class="Button Gray">
					</c:if>
				</c:forEach>
				
				<c:if test="${'N' eq listImageViewVal}">
					<input type="file" id="proImg1Up" name="proImg1Up" style="width:80%;"> [378px X 378px]
				</c:if>
		
			</td>
		</tr>
	
		<tr>
			<th>상품이미지2</th>
			<td colspan="3" class="left">
			
				<c:set var="listImageViewVal" value="N"/>
				
				<c:forEach items="${imgList}" var="prdImg">
					<c:if test="${'02' eq prdImg.attImgType && 2 eq prdImg.attImgSeq}">
						<c:set var="listImageViewVal" value="Y"/>
						<img src="${prdImg.attFilePath}">
						<input type="button" value="삭제" onClick="confirm_process('','해당 사진을 삭제하시겠습니까?','manageProductImgDelProc.do?idx=${prdImg.idx},${prdInfo.prdCd}');"  class="Button Gray">
					</c:if>
				</c:forEach>
				
				<c:if test="${'N' eq listImageViewVal}">
					<input type="file" id="proImg2Up" name="proImg2Up" style="width:80%;"> [378px X 378px]
				</c:if>
		
			</td>
		</tr>
			<tr>
			<th>상품이미지3</th>
			<td colspan="3" class="left">
			
				<c:set var="listImageViewVal" value="N"/>
				
				<c:forEach items="${imgList}" var="prdImg">
					<c:if test="${'02' eq prdImg.attImgType && 3 eq prdImg.attImgSeq}">
						<c:set var="listImageViewVal" value="Y"/>
						<img src="${prdImg.attFilePath}">
						<input type="button" value="삭제" onClick="confirm_process('','해당 사진을 삭제하시겠습니까?','manageProductImgDelProc.do?idx=${prdImg.idx},${prdInfo.prdCd}');"  class="Button Gray">
					</c:if>
				</c:forEach>
				
				<c:if test="${'N' eq listImageViewVal}">
					<input type="file" id="proImg3Up" name="proImg3Up" style="width:80%;"> [378px X 378px]
				</c:if>
		
			</td>
		</tr>
	
			<tr>
			<th>상품이미지4</th>
			<td colspan="3" class="left">
			
				<c:set var="listImageViewVal" value="N"/>
				
				<c:forEach items="${imgList}" var="prdImg">
					<c:if test="${'02' eq prdImg.attImgType && 4 eq prdImg.attImgSeq}">
						<c:set var="listImageViewVal" value="Y"/>
						<img src="${prdImg.attFilePath}">
						<input type="button" value="삭제" onClick="confirm_process('','해당 사진을 삭제하시겠습니까?','manageProductImgDelProc.do?idx=${prdImg.idx},${prdInfo.prdCd}');"  class="Button Gray">
					</c:if>
				</c:forEach>
				
				<c:if test="${'N' eq listImageViewVal}">
					<input type="file" id="proImg4Up" name="proImg4Up" style="width:80%;"> [378px X 378px]
				</c:if>
		
			</td>
		</tr>
	
	


		<tr>
			<th>목록내용</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="prdListCmt" id="prdListCmt" class="Text Kor" style="width:97%;height:150px;">${prdInfo.prdListCmt}</textarea>
			
			</td>
		</tr>
		<tr>
			<th>상세내용</th>
			<td colspan="3" style="text-align:left;">
				<jsp:include page="/resources/editor/editor.jsp" />
			</td>
		</tr>
		<th>옵션</th>
      		<td  colspan="3" style="text-align:left;">
      		<ul id="optionField_0" style="display:block;">
      		<select id="optionKey_0" name="optionKey">
      			<option value="01" <c:if test="${prdInfo.optionKey[0] eq '01'}">  selected </c:if>>색상</option>
      			<option value="02" <c:if test="${prdInfo.optionKey[0] eq '02'}">  selected </c:if>>크기</option>
      		</select>
      			<input type="text" id="optionValue_0" name="optionValue" value="${prdInfo.optionValue[0]}">
      			<input type="button" value="추가" onClick="chgOption('add','2')">
      			<input type="hidden" id="optionIdx_0" name="optionIdx" value="${prdInfo.optionIdx[0]}" >
      			
      		</ul>
      		
      		<c:forEach var="opKey" items="${prdInfo.optionKey}" begin="1" end="49" varStatus="i">
      			<c:if test="${null ne prdInfo.optionValue[i.index]}">
      				<ul id="optionField_${i.index}" style="display:block;">
      			</c:if>
      			
      			<c:if test="${null eq prdInfo.optionValue[i.index]}">
      				<ul id="optionField_${i.index}" style="display:none;">
      			</c:if>
      		
      			<select id="optionKey_${i.index}" name="optionKey">
      				<option value="01" <c:if test="${opKey eq '01'}">  selected </c:if>>색상</option>
      				<option value="02" <c:if test="${opKey eq '02'}">  selected </c:if>>크기</option>
      			</select> 
      			<input type="text" id="optionValue_${i.index}" name="optionValue" value="${prdInfo.optionValue[i.index]}">
      			<input type="button" value="추가" onClick="chgOption('add','${i.index+1}','${prdInfo.optionIdx[i.index+1]}')">
      			<input type="button" value="삭제" onClick="chgOption('del','${i.index}','${prdInfo.optionIdx[i.index]}')">
      			<input type="hidden" id="optionIdx_${i.index}" name="optionIdx" value="${prdInfo.optionIdx[i.index]}" >
      			
      		</ul>
      	</c:forEach>
      	
		<tr>
			<th>검색어</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="schWord" id="schWord"  class="Text Kor" style="width:97%;height:80px;">${prdInfo.schWord}
				</textarea>
				<div style="margin-top:5px;">※ , 로 구분하여 입력하여 주십시오</div>
			</td>
		</tr>
	
		<tr>
			<th>배송/반품/교환정보</th>
			<td colspan="3" style="text-align:left;">
				<div><input type="button" value="배송정보변경" class="Small_Button Gray" onClick="openWin('./transferInfoPopup.do','transferInfoPopup',600,300,'scrollbars=no');">${transDetail.tTitle}</div>
				<div>
					<span id="transferInfoValue">${prdInfo.prdTransContents}</span>
				</div>
			</td>
		</tr>

		<tr>
			<th>관리 참고사항</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="admMemo" id="admMemo" class="Text Kor" style="width:97%;height:250px;">${prdInfo.admMemo}</textarea>
			</td>
		</tr>

	</table>

	<div class="Btn_area">
		<input type="button" value="수정하기" class="Button Gray" onClick="fnAddClick();" /> &nbsp; 
		<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
	</div>

	</form>

	</div><br /><br /><br /><br /><br /><br />
	

</div>
</body>

<c:import url="../inc/footer.jsp" />

<script language="JavaScript" type="text/JavaScript">
function chgOption(op,n,Idx){
	   var targetField = "optionField_" + n ;
	   if(op == "add"){
	      document.getElementById(targetField).style.display = "block" ;
	   }else{
	      document.getElementById(targetField).style.display = "none" ;
	      location.href='deletePrdOptionInf.do?idx='+Idx+','+${prdInfo.prdCd};
	   }
	}
</script>
