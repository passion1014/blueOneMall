<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page import="java.util.Calendar"%>

<%
	Calendar calendar = Calendar.getInstance();
	String year = Integer.toString(calendar.get(Calendar.YEAR)); //년도를 구한다
	int y = Integer.parseInt(year);
%>

<script type="text/javascript">

	$(document).ready(function() {
		$('#mail').change(function() {
			alert();
			var add = $('#mail').val();
			alert(add);
			
			
			$("value#eMail2").html(add);
			
		});
	});
	
	
	
</script>


<c:import  url="../inc/topSub.jsp" />
<html>
<body>
	<div class="wrap">
	<!--  header 시작   -->
	<c:import url="../inc/header.jsp" />
	<!--  header 끝   -->

	<div class="container">
		
		<c:import url="../inc/memberLnb.jsp" />
		
		<div class="sub_content">
			<form action="userEditProc.do" method="post">
			<input type="hidden" id="custId" name="custId" value="id1">
				<div class="mypage_section">
					<h4>나의 정보</h4>
					<p class="sub_tit">고객의 기본정보 내역을 확인하실 수 있습니다.</p>
					<div class="mypage_infobox">
						<h5 class="basic_infotit">기본정보 입력</h5>
						<table class="myinfo_tbl" summary="정보 입력표">
							<caption>나의정보입력표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th class="topline">이름</th>
									<td class="topline"><input type="text" id="custNm" name="custNm" value="${customer.custNm}"/></td>
									<th class="topline">생년월일</th>
									<td class="topline">
										<span class="in_text">
											<select id="birthY" name="birthY">
												<c:forEach var="i" begin="1930" end="<%=y%>">
												<option <c:if test="${customer.birthY eq 'i'}">selected</c:if>>${i}</option>
												</c:forEach>
											</select>
											<label for="year">년</label>
											
											<select id="birthM" name="birthM">
												<c:forEach var="i" begin="1" end="12">
												<option <c:if test="${customer.birthM eq 'i'}">selected</c:if>>${i}</option>
												</c:forEach>
											</select>
											<label for="month">월</label>
											
											<select id="birthD" name="birthD">
												<c:forEach var="i" begin="1" end="31">
												<option <c:if test="${customer.birthD eq 'i'}">selected</c:if>>${i}</option>
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
											<input type="radio" id="custSx" name="custSx" value="f" <c:if test="${custSx eq 'f'}">selected</c:if>/>
											<label for="woman">여성</label>
											<input type="radio" id="custSx" name="custSx" value="m" <c:if test="${custSx eq 'm'}">selected</c:if>/>
											<label for="man">남성</label>
										</span>
									</td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>
										<span class="in_number">
										<select  name="telNo1" id="telNo1">
											<option <c:if test="${customer.telNo1}">selected</c:if>>02</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>031</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>032</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>033</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>041</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>042</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>043</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>051</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>052</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>053</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>054</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>061</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>062</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>063</option>
											<option <c:if test="${customer.telNo1}">selected</c:if>>064</option>
										</select>
										-<input type="text" title="전화번호" name="telNo2" id="telNo2" maxlength="4"  value="${customer.telNo2}" />-
										<input type="text" title="전화번호" name="telNo3" id="telNo3" maxlength="4" value="${customer.telNo3}"/>
									</span>
									</td>
								
									<th>휴대전화</th>
										<td>
										<span class="in_number">
										<select  name="hpNo1" id="hpNo1">
											<option <c:if test="${customer.hpNo1}">selected</c:if>>010</option>
											<option <c:if test="${customer.hpNo1}">selected</c:if>>011</option>
											<option <c:if test="${customer.hpNo1}">selected</c:if>>017</option>
											<option <c:if test="${customer.hpNo1}">selected</c:if>>016</option>
											<option <c:if test="${customer.hpNo1}">selected</c:if>>019</option>
											<option <c:if test="${customer.hpNo1}">selected</c:if>>018</option>
										</select>
										-<input type="text"  name="hpNo2" id="hpNo2"title="휴대전화번호" maxlength="4" value="${customer.hpNo2}"/>-
										<input type="text"  name="hpNo3" id="hpNo3" title="휴대전화번호" maxlength="4" value="${customer.hpNo3}"/>
									</span>
									</td>
								</tr>
								<tr>
									<th>이메일</th>
									<td colspan="3">
										<span class="in_email">
											<input type="text" title="email text"  name="eMail1" id="eMail1"/>&nbsp;@&nbsp;
											<input type="text" title="email text"  name="eMail2" id="eMail2" />
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
										<input type="text" title="address text" style="width:120px;" id="custZip" name="custZip"/><button>우편번호 찾기</button><br/>
										<input type="text" title="address text" id="custAdd" name="custAdd"/>
									</td>
								</tr>
								<tr>
									<th class="bottomline">SMS수신여부</th>
									<td class="bottomline">
										<span class="in_radio">
											<input type="radio" id="custSmSRcv" name="custSmSRcv" value="y"/>
											<label for="agree">수신동의</label>
											<input type="radio" id="custSmSRcv" name="custSmSRcv" value="n"/>
											<label for="refusal">수신거부</label>
										</span>
									</td>
									<th class="bottomline">e-mail수신여부</th>
									<td class="bottomline">
										<span class="in_radio">
											<input type="radio" id="custMailRcv" name="custMailRcv" value="y"/>
											<label for="e_agree">수신동의</label>
											<input type="radio" id="custMailRcv" name="custMailRcv" value="n"/>
											<label for="e_refusal">수신거부</label>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
						<p class="tbl_text">※ SMS 및 이메일을 통해 서비스 안내 및 다양한 이벤트 소식이 제공됩니다. 원하시지 않을 경우에는 수신거부를 선택해주시기 바랍니다.</span>
						<h5 class="basic_infotit">선택정보</h5>
						<table class="myinfo_tbl" summary="정보 입력표">
							<caption>나의정보입력표</caption>
							<colgroup>
								<col width="15%"/>
								<col width="35%"/>
								<col width="15%"/>
								<col width="35%"/>
							</colgroup>
							<tbody>
								<tr>
									<th class="topline">결혼여부</th>
									<td class="topline">
										<span class="in_radio">
											<input type="radio" id="single" name="marry" />
											<label for="single">미혼</label>
											<input type="radio" id="married" name="marry" />
											<label for="married">기혼</label>
										</span>
									</td>
									<th class="topline">결혼기념일</th>
									<td class="topline">
										<span class="in_text">
											<select id="custMerryY" name="custMerryY">
												<c:forEach var="i" begin="1930" end="<%=y%>">
												<option <c:if test="${i == 1990}">selected</c:if>>${i}</option>
												</c:forEach>
											</select>
											<label for="married_year">년</label>
											<select id="custMerryM" name="custMerryM">
												<c:forEach var="i" begin="1" end="12">
												<option <c:if test="${i == 1}">selected</c:if>>${i}</option>
												</c:forEach>
											</select>
											<label for="married_month">월</label>
											<select id="custMerryD" name="custMerryD">
												<c:forEach var="i" begin="1" end="31">
												<option <c:if test="${i == 1}">selected</c:if>>${i}</option>
												</c:forEach>
											</select>
											<label for="married_day">일</label>
										</span>
								</tr>
								<tr>
									<th class="bottomline">자녀유무</th>
									<td class="bottomline" colspan="3">
										<span class="in_radio">
											<input type="radio" id="custChild" name="custChild" value="n"/>
											<label for="without">무</label>
											<input type="radio" id="custChild" name="custChild" value="y" />
											<label for="with">유</label>
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<span class="btn_submit">
						<input type="submit" value="개인정보 변경" title="개인정보변경버튼"/>
					</span>
				</div>
			</form>
		</div>
	</div>
	<!--  container 끝   -->	

	<c:import url="../inc/footer.jsp" />
</div>
</body>
</html>