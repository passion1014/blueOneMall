<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.Calendar"%>
<%
Calendar calendar = Calendar.getInstance();
String year = Integer.toString(calendar.get(Calendar.YEAR)); //년도를 구한다
int y = Integer.parseInt(year);
%>
    
<c:import  url="../inc/topSub.jsp" />    

<body>
<div class="wrap">
	<!--  header 시작 -->
	<c:import url="../inc/header.jsp"/>
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/userLnb.jsp"/>
		
		<div class="sub_content">
			<form name="frm" action="userRegisterProc.do" method="post">
			<input type="hidden" id="custId" name="custId" value="${customer.custId}"> 
			<input type="hidden" id="custId" name="custNm" value="${customer.custNm}">
			
				<div class="membership_section">
					<h4>회원가입</h4>
					<ul class="lctit_section">
						<li class="on">
							<span class="loc_boxtit">STEP 1</span>
							<span class="loc_boxstit">약관동의 및 실명인증<br/>
							회원가입
							</span>
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
									<textarea>${agreementInfo.agrContents}</textarea>
									<span class="agree_box"> <input type="checkbox"
										id="agre_check1" name="agree_checked1" /> <label
										for="agre_check1">이용약관에 동의합니다</label>
									</span>
							</c:if>
							<c:if test="${agreementInfo.agrType == 2}">
								<p class="sub_tit1 clearfix">> 개인정보 수집항목, 목적 및 이용안내</p>
								
									<textarea>${agreementInfo.agrContents}</textarea>
							
								<span class="agree_box"> <input type="checkbox"
									id="agre_check2" name="agree checked2" /> <label
									for="agre_check2">이용약관에 동의합니다</label>
								</span>
								
							</c:if>
						</c:forEach>
					</div>
					<h5>기본정보 입력</h5>
					<table class="membership_tbl" summary="회원가입양식">
						<caption>회원가입목록</caption>
						<colgroup>
							<col width="15%"/>
							<col width="38%"/>
							<col width="15%"/>
							<col width="32%"/>
						</colgroup>
						<tbody>
							<tr>
								<th>이름</th>
								<td>${customer.custNm}<!-- <input type="text" id="custNm" name="custNm" title="아이디입력창"/> --></td>
								<th><span class="bullet_color">*</span>아이디</th>
								<td>${customer.custId}</td>
							</tr>
							<th class="topline">생년월일</th>
									<td class="topline">
										<span class="in_text">
											<select id="birthY" name="birthY">
												<c:forEach var="i" begin="1930" end="<%=y%>">
												<option>${i}</option>
												</c:forEach>
											</select>
											<label for="year">년</label>
											
											<select id="birthM" name="birthM">
												<c:forEach var="i" begin="1" end="12">
												<option>${i}</option>
												</c:forEach>
											</select>
											<label for="month">월</label>
											
											<select id="birthD" name="birthD">
												<c:forEach var="i" begin="1" end="31">
												<option>${i}</option>
												</c:forEach>
											</select>
											<label for="day">일</label>
										</span>
										
									</td>
								</tr>
								<tr>
									<th>성별</th>
									<td colspan="3">
										<span class="in_radio">
											<input type="radio" id="custSx" name="custSx" value="f" <c:if test="${customer.custSx eq 'f'}">checked</c:if>/>
											<label for="woman">여성</label>
											<input type="radio" id="custSx" name="custSx" value="m" <c:if test="${customer.custSx eq 'm'}">checked</c:if>/>
											<label for="man">남성</label>
										</span>
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>
										<span class="in_number">
										<select  name="telNo1" id="telNo1">
											<option>02</option>
											<option>031</option>
											<option>032</option>
											<option>033</option>
											<option>041</option>
											<option>042</option>
											<option>043</option>
											<option>051</option>
											<option>052</option>
											<option>053</option>
											<option>054</option>
											<option>061</option>
											<option>062</option>
											<option>063</option>
											<option>064</option>
										</select>
										-<input type="text" title="전화번호" name="telNo2" id="telNo2" maxlength="4" />-
										<input type="text" title="전화번호" name="telNo3" id="telNo3" maxlength="4"/>
									</span>
									</td>
								
									<th>휴대전화</th>
										<td>
										<span class="in_number">
										<select  name="hpNo1" id="hpNo1">
											<option>010</option>
											<option>011</option>
											<option>017</option>
											<option>016</option>
											<option>019</option>
											<option>018</option>
										</select>
										-<input type="text"  name="hpNo2" id="hpNo2"title="휴대전화번호" maxlength="4"/>-
										<input type="text"  name="hpNo3" id="hpNo3" title="휴대전화번호" maxlength="4""/>
									</span>
									</td>
								</tr>
								<tr>
									<th>이메일</th>
									<td colspan="3">
										<span class="in_email">
											<input type="text" title="email text"  name="eMail1" id="eMail1"/>&nbsp;@&nbsp;
											<input type="text" title="email text"  name="eMail2" id="eMail2"/>
											<select name="mail" id="mail">
												<option>직접입력</option>
												<option>hanmail.net</option>
												<option>nate.com</option>
												<option>naver.com</option>
												<option>gmail.com</option>
												<option>hotmail.com</option>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<th>주소</th>
									<td colspan="3" class="in_address">
										<input type="text" title="address text" style="width:120px;" id="custZip" name="custZip" />
										<button onClick="openWin('./searchZipCode.do?type=userRegi','searchZipForm',600,450,'scrollbars=no');">우편번호 찾기</button><br/>
										<input type="text" title="address text" style="width:200px;"id="custAdd" name="custAdd"/>
									</td>
								</tr>
								<tr>
									<th class="bottomline">SMS수신여부</th>
									<td class="bottomline">
										<span class="in_radio">
											<input type="radio" id="custSmSRcv" name="custSmSRcv" value="y"  />
											<label for="agree">수신동의</label>
											<input type="radio" id="custSmSRcv" name="custSmSRcv" value="n" />
											<label for="refusal">수신거부</label>
										</span>
									</td>
									<th class="bottomline">e-mail수신여부</th>
									<td class="bottomline">
										<span class="in_radio">
											<input type="radio" id="custMailRcv" name="custMailRcv" value="y" />
											<label for="e_agree">수신동의</label>
											<input type="radio" id="custMailRcv" name="custMailRcv" value="n" />
											<label for="e_refusal">수신거부</label>
										</span>
									</td>
								</tr>
						</tbody>
					</table>
					<div class="complet_box">
						<span class="btn_complete">
							<input type=submit value="확인" title="확인" />
						</span>
						<span class="btn_cancle">
							<input type="reset" value="취소" title="취소"/>
						</span>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--  container 끝   -->	

	<c:import url="../inc/footer.jsp"/>
</body>
</html>