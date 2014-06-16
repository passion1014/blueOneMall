<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.Calendar"%>
<%
Calendar calendar = Calendar.getInstance();
String year = Integer.toString(calendar.get(Calendar.YEAR)); //년도를 구한다
int y = Integer.parseInt(year);
%>
<c:import  url="../inc/topSub.jsp" />    

<script language="javascript">
<!--
function chk_mem_form(f){

	if(!jQuery("#agre_check1").is(":checked")){
		alert("이용약관에 동의하여 주십시오!");
		return false;
	}

	if(!jQuery("#agre_check2").is(":checked")){
		alert("개인정보 취급방침에 동의하여 주십시오!");
		return false;
	}

	/* if(f.telNo2.value == ""){
		alert("전화번호를 입력하여 주십시오!");
		f.telNo2.focus();
		return false;
	}

	if(f.telNo3.value == ""){
		alert("전화번호를 입력하여 주십시오!");
		f.telNo3.focus();
		return false;
	}

	if(f.hpNo2.value == ""){
		alert("핸드폰 번호를 입력하여 주십시오!");
		f.hpNo2.focus();
		return false;
	}

	if(f.hpNo3.value == ""){
		alert("핸드폰 번호를 입력하여 주십시오!");
		f.hpNo3.focus();
		return false;
	}

	if(f.eMail1.value == ""){
		alert("이메일을 입력하여 주십시오!");
		f.eMail1.focus();
		return false;
	}

	if(f.eMail2.value == ""){
		alert("이메일을 입력하여 주십시오!");
		f.eMail2.focus();
		return false;
	}

	if(f.custZip1.value == ""){
		alert("우편번호를 찾아 주십시오!");
		f.custZip1.focus();
		return false;
	}

	if(f.custZip2.value == ""){
		alert("우편번호를 찾아 주십시오!");
		f.custZip2.focus();
		return false;
	} */

	return true ;

}
//-->
</script>

<body>
<div class="wrap">
	<!--  header 시작 -->
	<c:import url="../inc/header.jsp"/>
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/userLnb.jsp"/>
		
		<div class="sub_content">

			<form id="frm" name="frm" method="post" action="/user/userRegisterProc.do" onSubmit="return chk_mem_form(this);">
			<!--form id="frm" name="frm" method="post" onSubmit="return chk_mem_form(this);"-->
			<input type="hidden" id="custId" name="custId" value="${customer.custId}"> 
			<input type="hidden" id="custNm" name="custNm" value="${customer.custNm}">
			<div class="membership_section">
				<h4>회원가입</h4>
				<ul class="lctit_section">
					<li class="on">
						<span class="loc_boxtit">STEP 1</span>
						<span class="loc_boxstit">약관동의 및 실명인증<br/>회원가입</span>
					</li>
					<li class="rightline">
						<span class="loc_aboxtit">STEP 2</span>
						<span class="loc_boxstit">회원가입 완료</span>
					</li>
				</ul>
				
				<h5>약관동의</h5>
				<div class="text_area">
					<c:forEach items="${agreementInfo}" var="agreementInfo">
						<c:if test="${agreementInfo.agrType == 1}">
							<p class="sub_tit1">> 회원약관</p>
							<textarea style="width:98%;height:250px;padding:5px;">${agreementInfo.agrContents}</textarea>
							<span class="agree_box"> <input type="checkbox" id="agre_check1" name="agree_checked1" value="y" required hname="이용약관에 동의하여 주십시오" /> <label for="agre_check1">&nbsp;이용약관에 동의합니다</label> </span>
						</c:if>
						<c:if test="${agreementInfo.agrType == 2}">
							<p class="sub_tit1 clearfix">> 개인정보 수집항목, 목적 및 이용안내</p>
							<textarea style="width:98%;height:250px;padding:5px;">${agreementInfo.agrContents}</textarea>
							<span class="agree_box"> <input type="checkbox" id="agre_check2" name="agree checked2" value="y" required hname="개인보호 취급방침에 동의하여 주십시오" /> <label for="agre_check2">&nbsp;개인정보 취급방침에 동의합니다</label> </span>
						</c:if>
					</c:forEach>
				</div>

				<h5>기본정보 입력</h5>
				<table class="membership_tbl" summary="회원가입양식">
					<caption>회원가입목록</caption>
					<colgroup>
						<col width="15%"/>
						<col width="35%"/>
						<col width="15%"/>
						<col width="*"/>
					</colgroup>
					<tbody>
					<tr>
						<th>이름</th>
						<td>${customer.custNm}<!-- <input type="text" id="custNm" name="custNm" title="아이디입력창"/> --></td>
						<th>아이디</th>
						<td>${customer.custId}</td>
					</tr>

					<tr>
						<th><span class="bullet_color">*</span>전화번호</th>
						<td>
							<span class="in_number">
								<select  name="telNo1" id="telNo1">
									<option value="02" selected>02</option>
									<option value="031">031</option>
									<option value="032">032</option>
									<option value="033">033</option>
									<option value="041">041</option>
									<option value="042">042</option>
									<option value="043">043</option>
									<option value="051">051</option>
									<option value="052">052</option>
									<option value="053">053</option>
									<option value="054">054</option>
									<option value="061">061</option>
									<option value="062">062</option>
									<option value="063">063</option>
									<option value="064">064</option>
								</select> - 
								<input type="text" title="전화번호" name="telNo2" id="telNo2" maxlength="4" size="4" onkeyup="passTab('telNo2','telNo3',4)" required hname="전화번호를 입력하여 주십시오" /> -
								<input type="text" title="전화번호" name="telNo3" id="telNo3" maxlength="4" size="4" onkeyup="passTab('telNo3','hpNo2',4)" required hname="전화번호를 입력하여 주십시오" />
							</span>
						</td>

						<th><span class="bullet_color">*</span>휴대전화</th>
						<td>
							<span class="in_number">
								<select  name="hpNo1" id="hpNo1">
									<option value="010" selected>010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
								</select> -
								<input type="text" name="hpNo2" id="hpNo2" title="휴대전화번호" maxlength="4" size="4" onkeyup="passTab('hpNo2','hpNo3',4)" required hname="휴대전화번호를 입력하여 주십시오" /> -
								<input type="text" name="hpNo3" id="hpNo3" title="휴대전화번호" maxlength="4" size="4" onkeyup="passTab('hpNo3','eMail1',4)" required hname="휴대전화번호를 입력하여 주십시오" />
							</span>
						</td>
					</tr>
					
					<tr>
						<th><span class="bullet_color">*</span>이메일</th>
						<td colspan="3">
							<span class="in_email">
								<input type="text" title="email text"  name="eMail1" id="eMail1" required hname="이메일을 입력하여 주십시오" />&nbsp;@&nbsp;
								<select name="mailDomain" id="mailDomain" onChange="document.getElementById('eMail2').value=this.value;">
									<option value="">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="nate.com">nate.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="hotmail.com">hotmail.com</option>
								</select>
								<input type="text" title="email text"  name="eMail2" id="eMail2" required hname="이메일을 입력하여 주십시오"/>
							</span>
						</td>
					</tr>
					
					<tr>
						<th>주소</th>
						<td colspan="3" class="in_address">
							<input type="text" title="address text" style="width:60px;" id="custZip1" name="custZip1" value="" readonly required hname="우편번호를 입력하여 주십시오" />-
							<input type="text" title="address text" style="width:60px;" id="custZip2" name="custZip2" value="" readonly required hname="우편번호를 입력하여 주십시오" />
							<input type="button" value="우편번호 찾기" style="width:100px;height:21px;" onClick="openWin('/user/searchZipCode.do?type=userRegi','searchZipuserRegiForm',650,450,'scrollbars=yes');" /><br/>
							<input type="text" title="address text" style="width:50%;" id="custAdd"       name="custAdd"       value="" readonly/>
							<input type="text" title="address text" style="width:40%;" id="custAddDetail" name="custAddDetail" value=""/>
						</td>
					</tr>
					<tr>
						<th class="bottomline">SMS수신여부</th>
						<td class="bottomline">
							<span class="in_radio">
								<input type="radio" id="custSmSRcv" name="custSmSRcv" value="y"  /> <label for="agree">수신동의</label>
								<input type="radio" id="custSmSRcv" name="custSmSRcv" value="n" checked /> <label for="refusal">수신거부</label>
							</span>
						</td>
						<th class="bottomline">e-mail수신여부</th>
						<td class="bottomline">
							<span class="in_radio">
								<input type="radio" id="custMailRcv" name="custMailRcv" value="y" /> <label for="e_agree">수신동의</label>
								<input type="radio" id="custMailRcv" name="custMailRcv" value="n" checked /> <label for="e_refusal">수신거부</label>
							</span>
						</td>
					</tr>
				</tbody>
				</table>
				
				<div class="complet_box">
					<span class="btn_complete"><input type="submit" value="확인" title="확인" /></span>
					<span class="btn_cancle"><input type="reset" value="취소" title="취소"/></span>
				</div>
			</div>
			</form>

		</div>
	</div>
	<!--  container 끝   -->	

	<c:import url="../inc/footer.jsp"/>
</body>
</html>