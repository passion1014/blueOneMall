<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />
<c:set var="test" value="goodsList" scope="request"/> 
<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="goods"/>
	</c:import>
	

	<div id="Contents">
	<h1>제품관리 &gt; 상품관리 &gt; <strong>상품등록</strong></h1>
	
	<form name="frm" method="post" enctype="multipart/form-data" action="./_action/product.do">
	<input type="hidden" name="Mode" value="add_goods">

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
				<select name="display_yn">
					<option value="y">진열</option>
					<option value="n">대기</option>
				</select>
				&nbsp;&nbsp;<b>정렬</b> :
				<select id="ctgOrder" name="ctgOrder">
				<c:forEach var="i" begin="1" end="50" step="1">
					<option value="<c:out value="${i}"></c:out>"><c:out value="${i}"></c:out></option>
				</c:forEach>							
				</select>
			</td>
			<th>특수설정</th>
			<td style="text-align:left;">
				<input type="checkbox" name="sp_type_1" value="y"> 베스트 &nbsp;
				<input type="checkbox" name="sp_type_2" value="y"> 행사품목 &nbsp;
			</td>
		</tr>
		
		<tr>
			<th>상품분류</th>
			<td colspan="3" style="text-align:left;">
				<select id="largeType" name="largeType">
					<option value="">:::: 대분류를 선택하여주십시오 ::::</option>	
					<c:forEach items="${ctgList1}" var="largeTypeObj">
						<option value="<c:out value="${largeTypeObj.ctgCode}"></c:out>"><c:out value="${largeTypeObj.ctgName}"></c:out></option>
					</c:forEach>							
				</select>&nbsp;
				
				<select id="middleType" name="middleType">
					<option value="">:::: 중분류를 선택하여주십시오 ::::</option>	
					<c:forEach items="${ctgList2}" var="middleTypeObj">
						<option value="<c:out value="${middleTypeObj.ctgCode}"></c:out>"><c:out value="${middleTypeObj.ctgName}"></c:out></option>
					</c:forEach>							
				</select>&nbsp;		

				<select name="smallType" id="smallType">
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
				<input type="text" id="goods_name" name="goods_name" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
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
				<input type="text" id="goods_name" name="goods_name" style="width:70%;text-align:right;padding-right:10px;"> 원
			</td>
			<th>판매가</th>
			<td style="text-align:left;">
				<input type="text" id="goods_name" name="goods_name" style="width:70%;text-align:right;padding-right:10px;""> 원
			</td>		
		</tr>
		
		<tr>
			<th>모델명</th>
			<td colspan="3" class="left">
				<input type="text" id="goods_name" name="goods_name" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
		<tr>
			<th>모델번호</th>
			<td colspan="3" class="left">
				<input type="text" id="goods_name" name="goods_name" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td colspan="3" class="left">
				<input type="text" id="goods_name" name="goods_name" style="width:80%;" required hname=" 상품명을 입력하여 주십시오">
			</td>
		</tr>
		
		<tr>
			<th>리스트 이미지</th>
			<td colspan="3" class="left">
				<input type="file" id="list_img" name="list_img" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지1</th>
			<td colspan="3" class="left">
				<input type="file" id="goods_img1" name="goods_img1" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지2</th>
			<td colspan="3" class="left">
				<input type="file" id="goods_img2" name="goods_img2" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>상품이미지3</th>
			<td colspan="3" class="left">
				<input type="file" id="goods_img3" name="goods_img3" style="width:80%;"> [112px X 176px]
			</td>
		</tr>
		<tr>
			<th>목록내용</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="list_cmt" id="list_cmt" class="Text" style="width:97%;height:150px;"></textarea>
			</td>
		</tr>
		<tr>
			<th>상세내용</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="contents" id="contents" class="Text" style="width:97%;height:300px;"></textarea>
			</td>
		</tr>

		<tr>
			<th>검색어</th>
			<td colspan="3" style="text-align:left;">
				<textarea name="search_word" id="search_word" class="Text Kor" style="width:97%;height:80px;"></textarea>
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
				<textarea name="admin_memo" id="admin_memo" class="Text Kor" style="width:97%;height:250px;"></textarea>
			</td>
		</tr>

	</table>

	<div class="Btn_area">
		<input type="submit" value="등록하기" class="Button Gray"> &nbsp; 
		<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
	</div>

	</form>

	</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />