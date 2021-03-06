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
	function fnAddClick() {
		var f = tx_editor_form;

		if(f.prdNm.value == ""){
			alert("상품명을 입력하여 주십시오");
			f.prdNm.focus();
			return false;
		}

		if(f.prdPrice.value == ""){
			alert("소비자가를 입력하여 주십시오");
			f.prdPrice.focus();
			return false;
		}

		if(f.prdSellPrc.value == ""){
			alert("판매가를 입력하여 주십시오");
			f.prdSellPrc.focus();
			return false;
		}

		if(f.prdBrand.value == ""){
			alert("제조사를 입력하여 주십시오");
			f.prdBrand.focus();
			return false;
		}

		if(f.prdStock.value == ""){
			alert("재고량을 입력하여 주십시오");
			f.prdStock.focus();
			return false;
		}
		
		f.action = 'productRegisterProc.do';

		Editor.save(); // 다음 에디터
	}

</script>


<c:set var="test" value="goodsList" scope="request"/> 
<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="goods"/>
	</c:import>
	

	<div id="Contents">
	<h1>제품관리 &gt; 상품관리 &gt; <strong>상품등록</strong></h1>
	
	<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="http://posttestserver.com/post.php">
	<input type="hidden" name="Mode" value="add_goods">
	<input type="hidden" id="prdCd"      name="prdCd"  value="${prdCd}">
	<input type="hidden" id="fromDate"     name="fromDate" value="1900-01-01">
	<input type="hidden" id="toDate"       name="toDate" value="9999-12-31">
	<input type="hidden" id="prdTransInf"       name="prdTransInf" value="1">
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
					<option value="y">진열</option>
					<option value="n">대기</option>
				</select>
				&nbsp;&nbsp;<b>정렬</b> :
				<select id="prdOrd" name="prdOrd">
				<c:forEach var="i" begin="1" end="50" step="1">
					<option value="<c:out value="${i}"></c:out>" <c:if test="${i == 15}">selected</c:if>><c:out value="${i}"></c:out></option>
				</c:forEach>							
				</select>
			</td>
			<th>특수설정</th>
			<td style="text-align:left;">
				<input type="checkbox" name="prdSpe1" value="y"> 베스트 &nbsp;
				<input type="checkbox" name="prdSpe2" value="y"> 행사품목 &nbsp;
			</td>
		</tr>
		
		<tr>
			<th>상품분류</th>
			<td colspan="3" style="text-align:left;">
				<select id="prdCtgL" name="prdCtgL">
					<option value="">:::: 대분류를 선택하여주십시오 ::::</option>	
					<c:forEach items="${ctgLList}" var="largeTypeObj">
						<option value="<c:out value="${largeTypeObj.ctgCode}"></c:out>"><c:out value="${largeTypeObj.ctgName}"></c:out></option>
					</c:forEach>							
				</select>&nbsp;
				
				<select id="prdCtgM" name="prdCtgM">
					<option value="">:::: 중분류를 선택하여주십시오 ::::</option>	
					<c:forEach items="${ctgList2}" var="middleTypeObj">
						<option value="<c:out value="${middleTypeObj.ctgCode}"></c:out>"><c:out value="${middleTypeObj.ctgName}"></c:out></option>
					</c:forEach>							
				</select>&nbsp;		

				<select id="prdCtgS" name="prdCtgS">
					<option value="">:::: 소분류를 선택하여 주십시오 ::::</option>
					<c:forEach items="${ctgList3}" var="smallTypeObj">
						<option value="<c:out value="${smallTypeObj.ctgCode}"></c:out>"><c:out value="${smallTypeObj.ctgName}"></c:out></option>
					</c:forEach>	
				</select> &nbsp;				
			</td>
		</tr>

		<tr>
			<th>상품명</th>
			<td colspan="3" class="left">
				<input type="text" id="prdNm" name="prdNm" style="width:80%;">
			</td>
		</tr>
		
		<tr>
			<th>메인추출 상품명</th>
			<td colspan="3" class="left">
				<input type="text" id="prdMainNm" name="prdMainNm" style="width:80%;">
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
				<input type="text" id="prdPrice" name="prdPrice" value="" style="width:50%;text-align:right;padding-right:10px;"> 원
			</td>
			<th>판매가</th>
			<td style="text-align:left;">
				<input type="text" id="prdSellPrc" name="prdSellPrc"  value="" style="width:50%;text-align:right;padding-right:10px;"> 원
			</td>		
		</tr>

		<tr>
			<th>모델명</th>
			<td class="left">
				<input type="text" id="prdModel" name="prdModel" style="width:80%;" value="">
			</td>
			<th>모델번호</th>
			<td class="left">
				<input type="text" id="prdModelNo" name="prdModelNo" value="" style="width:80%;">
			</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td class="left">
				<input type="text" id="prdBrand" name="prdBrand" value="" style="width:80%;">
			</td>
			<th>재고량</th>
			<td class="left">
				<input type="text" id="prdStock" name="prdStock" value="" style="width:30%;text-align:right;padding-right:10px;">
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td class="left">
				<input type="text" id="prdHit" name="prdHit" value="0" style="width:80%;">
			</td>
			<th>판매수</th>
			<td class="left">
				<input type="text" id="prdBuyCount" name="prdBuyCount" value="0" style="width:30%;text-align:right;padding-right:10px;">
			</td>
		</tr>
		<tr>
			<th>리스트 이미지</th>
			<td colspan="3" class="left">
				<input type="file" id="proListImgUp" name="proListImgUp" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지1</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg1Up" name="proImg1Up" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지2</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg2Up" name="proImg2Up" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지3</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg3Up" name="proImg3Up" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지4</th>
			<td colspan="3" class="left">
				<input type="file" id="proImg4Up" name="proImg4Up" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		
		<tr>
			<th>목록내용</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="prdListCmt" id="prdListCmt" class="Text" style="width:97%;height:150px;"></textarea>
			</td>
		</tr>
		<tr>
			<th>상세내용</th>
			<td colspan="3" style="text-align:left;">
				
				<jsp:include page="/resources/editor/editor.jsp" />
				
			</td>
		</tr>
		<tr>
      <th>옵션</th>
      <td  colspan="3" style="text-align:left;">
      	<ul id="optionField_1" style="display:block;">
      	<select id="optionKey_1" name="optionKey">
      		<option value="01">색상</option>
      		<option value="02">크기</option>
      	</select>
      	<input type="text" id="optionValue_1" name="optionValue" value="">
      	<input type="button" value="추가" onClick="chgOption('add','2')">
      	</ul>
      	
      	<c:forEach var="i" begin="2" end="50">
      		<ul id="optionField_${i}" style="display:none;">
      	<select id="optionKey_${i}" name="optionKey">
      		<option value="01">색상</option>
      		<option value="02">크기</option>
      	</select>
      	<input type="text" id="optionValue_${i}" name="optionValue" value="">
      	<input type="button" value="추가" onClick="chgOption('add','${i+1}')">
      	<input type="button" value="삭제" onClick="chgOption('del','${i}')">
      	</ul>
      	</c:forEach>
      
      </td>
   </tr>
		<tr>
			<th>검색어</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="schWord" id="schWord" class="Text Kor" style="width:97%;height:80px;"></textarea>
				<div style="margin-top:5px;">※ , 로 구분하여 입력하여 주십시오</div>
			</td>
		</tr>
		<tr>
			<th>배송/반품/교환정보</th>
			<td colspan="3" style="text-align:left;">
				<div><input type="button" value="배송상태등록" class="Small_Button Gray" onClick="openWin('./transferInfoPopup.do','transferInfoPopup',600,300,'scrollbars=no');">${transDetail.tTitle}</div>
				<div>
					<span id="transferInfoValue"></span>
					<input type="hidden" id="prdTransInf" name="prdTransInf" value="">
				</div>
			</td>
		</tr>
		
		<tr>
			<th>관리 참고사항</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="admMemo" id="admMemo" class="Text Kor" style="width:97%;height:250px;"></textarea>
			</td>
		</tr>

	</table>

	<div class="Btn_area">
		<input type="button" value="등록하기" 	class="Button Gray" onClick="fnAddClick();" / > &nbsp; 
		<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
	</div>

	</form>

	</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />

<script language="JavaScript" type="text/JavaScript">
function chgOption(op,n){
	   var targetField = "optionField_" + n ;
	   if(op == "add"){
	      document.getElementById(targetField).style.display = "block" ;
	   }else{
	      document.getElementById(targetField).style.display = "none" ;
	   }
	}
	
	
<!--
$(document).ready(function() {
	
	$("#dialog-form").dialog({
		autoOpen: false,
		closeOnEscape: false,
		draggable: false,
		modal: true,
		resizable: false,
		title: '',
		width: 650,
		//height: 0,
		//zIndex: 0,
		create: function(event, ui) {},
		open: function(event, ui) {},
		close: function(event, ui) {}
	});

});

function openDialog() {
	$("#dialog-form").dialog("open");
}

function closeDialog() {
	$("#dialog-form").dialog("close");
}

function dialogUpdate() {
	$.ajax({
		type: "POST",
		url: "largeTypeEdit.do",
		cache: false,
		async: false,
		data: {
			ctgCode : cCode
		},
		dataType: "html",
		success: function(e) {
			$("#dialog-form").html(e);
			openDialog();
		}
	});

}


-->
</script>