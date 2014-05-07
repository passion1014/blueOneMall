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
	    <h1>회원관리 &gt; 회원정보 &gt; <strong>회원등록</strong></h1>

			<form name="frm" method="post" action="registAdminInf.do" onSubmit="return chk_admin_form(this);" style="display:inline;" >
			<input type="hidden" id="Mode" name="Mode" value="admin_add">
			<table>
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tr>
								<th>이름</th>
								<td><input type="text" id="custNm" name="custNm" title="이름입력창"/></td>
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
			</table>
		
			<div style="margin-top:20px;" class="center">
				<input type="submit" value="등록하기" class="button_green button_medium"> &nbsp; 
				<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
			</div>
			</form>
		
		</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" /> 