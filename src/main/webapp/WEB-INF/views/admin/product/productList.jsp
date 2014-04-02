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
		<h1>제품관리 &gt; 상품관리 &gt; <strong>상품목록</strong></h1>

	<form name="sfrm" method="GET" action="./admin.product.php" onsubmit="return chkForm(this);">
	<input id="slot" name="slot" value="product" type="hidden">
	<input id="type" name="type" value="goods_list" type="hidden">
	<table>
		<tbody><tr>
			<td class="left">
				<div style="margin-bottom:5px;">
					<select id="s_l_idx" name="s_l_idx" onchange="sfrm.submit();">
						<option value="">:::: 대분류를 선택하여 주십시오 ::::</option>
						<option value="1">Tool kits</option><option value="2">Bench Tools</option><option value="3">Machine &amp; Equipment</option><option value="4">Silver &amp; Gemstone</option><option value="5">Case &amp; Display</option><option value="6">Gemmoligical Instruments</option>					</select> &nbsp;
					
					<select id="s_m_idx" name="s_m_idx" onchange="sfrm.submit();">
						<option value="">:::: 중분류를 선택하여 주십시오 ::::</option>
											</select>&nbsp;


					<select id="s_s_idx" name="s_s_idx" onchange="sfrm.submit();">
						<option value="">:::: 소분류를 선택하여 주십시오 ::::</option>
						<option value="1">2014 신상품</option><option value="2">2014 공구상품</option><option value="3">1111</option>					</select>
				</div>
				<div>
					<b>상태</b>
					<select id="s_display_yn" name="s_display_yn" onchange="sfrm.submit();">
						<option value="">전체</option>
						<option value="y">진열</option>
						<option value="n">대기</option>
					</select> &nbsp;
					<input name="sp_type_1" id="sp_type_1" value="y" onclick="sfrm.submit();" type="checkbox"> 베스트 &nbsp;
					<input name="sp_type_2" id="sp_type_2" value="y" onclick="sfrm.submit();" type="checkbox"> 행사품목 &nbsp;&nbsp;
					<input name="sp_type_3" id="sp_type_3" value="y" onclick="sfrm.submit();" type="checkbox"> md추천 &nbsp;
					<input name="sp_type_4" id="sp_type_4" value="y" onclick="sfrm.submit();" type="checkbox"> 공동구매 &nbsp;&nbsp;

					<select name="keyfield" id="keyfield">
						<option value="t2.search_word">검색어</option>
						<option value="t1.goods_name">상품명</option>
					</select> &nbsp;
					<input name="keyword" id="keyword" value="" type="text"> &nbsp;

					<input value="검색" class="Small_Button Gray" type="submit">
					<input value="초기화" class="Small_Button Gray" title="초기하기" onclick="location.href='./admin.product.php?slot=product&amp;type=goods_list'" type="button">
				</div>
			</td>
		</tr>
	</tbody></table>
	</form>

	<form id="cfrm" name="cfrm" method="POST" action="./_action/product.do.php" target="actionForm">
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
		<tbody><tr>
			<th>
				<input id="multi_chk" name="multi_chk" onclick="allCheckbox('document.cfrm.unit_chk','multi_chk');" type="checkbox">			</th>
			<th>No</th>
			<th>이미지</th>
			<th>상태</th>
			<th>상품정보</th>
			<th>제품군</th>
			<th>관리</th>
		</tr>

						<tr>
					<td><input id="unit_chk" name="unit_chk[]" value="gd_533bce184e45d" type="checkbox"></td>
					<td>1</td>
					<td>
						&nbsp;					</td>
					<td style="line-height:160%;">
						<font color="#3333FF"><b>진열</b></font><br>					</td>
					<td style="text-align:left;line-height:160%;">
						상품분류 : Tool kits &gt; 11 <br>
						특별분류 : <br>
						상품명 : 111												
											</td>
					<td><b>1</b></td>
					<td>
						<input value="보기" class="Small_Button Gray" style="margin-bottom:5px;" onclick="openWin('./product/product.goods_viewer.php?gCode=gd_533bce184e45d','goods_viewer',1028,900,'scrollbars=yes');" type="button"> <br>
						<input value="관리" class="Small_Button Gray" style="margin-bottom:5px;" onclick="location.href='./admin.product.php?slot=product&amp;type=goods_detail&amp;step=information&amp;gCode=gd_533bce184e45d&amp;s_1_idx=&amp;s_m_idx=&amp;s_s_idx=&amp;s_display_yn=&amp;sp_type_1=&amp;sp_type_2=&amp;sp_type_3=&amp;sp_type_4=&amp;keyfield=&amp;keyword='" type="button"> <br>
						<input value="삭제" class="Small_Button Gray" onclick="confirm_process('actionForm','삭제하시겠습니까? \n\n삭제후에는 복원이 불가능합니다.','./_action/product.do.php?Mode=del_goods&amp;goods_code=gd_533bce184e45d')" type="button">
					</td>
				</tr>
					</tbody></table>

	<div style="padding:15px 0px 10px 0px;">
		<div class="f_left">
			<div style="margin-bottom:5px;">
				<select name="large_idx" id="large_idx" required="" hname="대분류를 선택하여 주십시오" onchange="change_middle_type(this.value);">
					<option value="">대분류 변경없음</option>
					<option value="1">Tool kits</option><option value="2">Bench Tools</option><option value="3">Machine &amp; Equipment</option><option value="4">Silver &amp; Gemstone</option><option value="5">Case &amp; Display</option><option value="6">Gemmoligical Instruments</option>				</select> &nbsp;
				
				<select name="middle_idx" id="middle_idx" required="" hname="세분류를 선택하여 주십시오" onchange="change_small_type(this.value);">
					<option value="">세분류 변경없음</option>	
				</select> &nbsp;
			</div>
			
			<select name="c_display_yn">
				<option value="">변경없음</option>
				<option value="ing">진열</option>
				<option value="end">대기</option>
			</select> &nbsp;
			<input name="c_sp_1" value="y" type="checkbox"> 베스트 &nbsp;
			<input name="c_sp_2" value="y" type="checkbox"> 행사품목 &nbsp;
			<input name="c_sp_3" value="y" type="checkbox"> MD추천 &nbsp;
			<input name="c_sp_4" value="y" type="checkbox"> 공동구매 &nbsp;&nbsp;
			<input value="선택수정" class="Small_Button Gray" onclick="list_Submit('modify')" type="button"> 
			<input value="선택삭제" class="Small_Button Gray" onclick="list_Submit('del')" type="button">
	</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />