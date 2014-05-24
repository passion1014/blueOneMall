<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../inc/top.jsp" />

<body>

<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="member"/>
	</c:import>
	<div id="Contents">
	<h1>회원관리 &gt; 약관정보 &gt; <strong>개인보호취급방침</strong></h1>

	<form name="frm" method="post" action="memberInfoProc.do" >
	<input type="hidden" id="agrType" name="agrType" value="2">
	
	<table>
		<colgroup>
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tr>
			<th>개인보호취급방침</th>
			<td class="left">
				<textarea name="agrContents" id="agrContents" class="input" style="width:95%;height:400px;padding:3pt;" required hname="약관을 입력하여 주십시오!">${agreementInfo.agrContents}</textarea>
			</td>
		</tr>
	</table>

	<div class="center" style="padding-top:10px;">
		<input type="submit" value=" 수정 " class="Button Gray"> &nbsp;
		<input type="button" value=" 취소 " class="Button Gray" onClick="reset();">
	</div>
	</form>

</div>	
	

</div>
</body>

<c:import url="../inc/footer.jsp" />