<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../inc/top.jsp" />

<body>

<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="conf"/>
	</c:import>
	 <div id="Contents">
	    <h1>환경설정 &gt; 운영 관리 &gt; <strong>운영 설정</strong></h1>

			<form name="frm" method="post" action="configEditProc.do" onSubmit="return chk_admin_form(this);" style="display:inline;" >
			<table>
				<colgroup>
					<col width="15%" />
					<col width="*" />
				</colgroup>
				<tr>
					<th>사이트명</th>
					<td class="left">
					<input type="text" id="siteName" name="siteName" style="width:80%;" value="${config.siteName}"/>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td class="left">
					<input type="text" id="siteDomain" name="siteDomain" style="width:80%;" value="${config.siteDomain}"/>
					</td>
				</tr>
				<tr>
					<th>담당자 명</th>
					<td class="left">
					<input type="text" id="siteMaster" name="siteMaster" style="width:80%;" value="${config.siteMaster}"/>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td class="left">
					<input type="text" id="sitePh" name="sitePh"  style="width:80%;" value="${config.sitePh}"/>
					</td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td class="left">
					<input type="text" id="siteHp" name="siteHp" style="width:80%;" value="${config.siteHp}"/>
					</td>
				</tr>
				<tr>
					<th>포인트설정</th>
					<td class="left"><input type="text" id="pointConf" name="pointConf" value="${config.pointConf}"/>
					</td>
				</tr>
				<tr>
					<th>배송비설정</th>
					<td class="left">
						<input type="text" id="buyPrice" name="buyPrice" value="${config.buyPrice}"/>원 이하 &nbsp;&nbsp; 배송비&nbsp;<input type="text" id="trasferPrice" name="trasferPrice" value="${config.trasferPrice}"/>원 추가
					</td>
				</tr>
				<tr>
					<th>배송업체</th>
					<td class="left">
					<select id="transferOffice" name="transferOffice">
						<option <c:if test="${config.transferOffice eq '한진택배'}">selected</c:if>>한진택배</option>
						<option <c:if test="${config.transferOffice eq 'CJ택배'}">selected</c:if>>CJ택배</option>
						<option <c:if test="${config.transferOffice eq '우체국택배'}">selected</c:if>>우체국택배</option>
					</select>
					</td>
				</tr>
			</table>
		
			<div style="margin-top:20px;" class="center">
				<input type="submit" value="정보변경" class="button_green button_medium"> &nbsp; 
				<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
			</div>
			</form>
		
		</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" /> 