<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Calendar"%>
<%
	Calendar calendar = Calendar.getInstance();
	String year = Integer.toString(calendar.get(Calendar.YEAR)); //년도를 구한다
	int y = Integer.parseInt(year);
%>

<c:import url="../inc/top.jsp" />

<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="member"/>
	</c:import>
	<div id="Contents">
		<h1>회원관리 &gt; 회원정보 &gt; <strong>회원수정</strong></h1>
		
		<form name="frm" method="post" action="memberEditProc.do" >
		<input type="hidden" id="custId" name="custId" value="${customer.custId}">
		<table>
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="*" />
			</colgroup>
			<tr>
				<th>ID</th>
				<td class="left">${customer.custId}</td>
				<th class="topline">이름</th>
				<td class="topline"><input type="text" id="custNm" name="custNm" value="${customer.custNm}"/></td>
			</tr>
			<!--
			<tr>
				<th class="topline">생년월일</th>
				<td class="topline">
					<span class="in_text">
					<select id="birthY" name="birthY">
						<c:forEach var="i" begin="1930" end="<%=y%>">
							<option <c:if test="${customer.birthY == i}">selected</c:if>>${i}</option>
						</c:forEach>
					</select>
					<label for="year">년</label>

					<select id="birthM" name="birthM">
						<c:forEach var="i" begin="1" end="12">
							<option <c:if test="${customer.birthM == i}">selected</c:if>>${i}</option>
						</c:forEach>
					</select>
					<label for="month">월</label>
					
					<select id="birthD" name="birthD">
						<c:forEach var="i" begin="1" end="31">
							<option <c:if test="${customer.birthD == i}">selected</c:if>>${i}</option>
						</c:forEach>
					</select>
					<label for="day">일</label>
					</span>
				</td>
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
			-->

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
					</select> -
					<input type="text" title="전화번호" name="telNo2" id="telNo2" size="5" maxlength="4"  value="${customer.telNo2}" /> -
					<input type="text" title="전화번호" name="telNo3" id="telNo3" size="5" maxlength="4" value="${customer.telNo3}" />
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
					</select> - 
					<input type="text"  name="hpNo2" id="hpNo2"title="휴대전화번호" maxlength="4" value="${customer.hpNo2}"/> - 
					<input type="text"  name="hpNo3" id="hpNo3" title="휴대전화번호" maxlength="4" value="${customer.hpNo3}"/>
					</span>
				</td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td colspan="3">
					<span class="in_email">
					<input type="text" title="email text"  name="eMail1" id="eMail1" value="${customer.eMail1}"/>&nbsp;@&nbsp;
					<input type="text" title="email text"  name="eMail2" id="eMail2" value="${customer.eMail2}"/>
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
					<div>
						<input type="text" title="address text" style="width:120px;" id="custZip" name="custZip" value="${customer.custZip}"/>
						<input type="button" value="우편번호 찾기" onClick="openWin('./searchZipCode.do?custId=${customer.custId}','searchZipForm',600,450,'scrollbars=no');">
					</div>
					<div>
						<input type="text" title="address text" style="width:200px;"id="custAdd" name="custAdd" value="${customer.custAdd}"/>
					</div>
				</td>
			</tr>
			<tr>
				<th class="bottomline">SMS수신여부</th>
				<td class="bottomline">
					<span class="in_radio">
					<input type="radio" id="custSmSRcv" name="custSmSRcv" value="y" <c:if test="${customer.custSmSRcv eq 'y'}">checked</c:if>/>
					<label for="agree">수신동의</label> &nbsp;&nbsp;
					<input type="radio" id="custSmSRcv" name="custSmSRcv" value="n" <c:if test="${customer.custSmSRcv eq 'n'}">checked</c:if>/>
					<label for="refusal">수신거부</label>
					</span>
				</td>
				<th class="bottomline">e-mail수신여부</th>
				<td class="bottomline">
					<span class="in_radio">
						<input type="radio" id="custMailRcv" name="custMailRcv" value="y" <c:if test="${customer.custMailRcv eq 'y'}">checked</c:if>/>
						<label for="e_agree">수신동의</label> &nbsp;&nbsp;
						<input type="radio" id="custMailRcv" name="custMailRcv" value="n" <c:if test="${customer.custMailRcv eq 'n'}">checked</c:if>/>
						<label for="e_refusal">수신거부</label>
					</span>
				</td>
			</tr>
		</table>
		
		<div style="margin-top:20px;" class="center">
			<input type="submit" value="수정하기" class="button_green button_medium"> &nbsp; 
			<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
		</div>
	</form>
	</div>

</div>
</body>

<c:import url="../inc/footer.jsp" /> 