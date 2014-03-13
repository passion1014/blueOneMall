<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



 
<div id="LNB">
	<h1>환경설정</h1>
	<ul>
		<h2>&nbsp;</h2>
		<li class="on"></li>
	</ul>
</div>


<c:choose>
   <c:when test="${param.member == 'member'}"> 
		<div id="LNB">
			<h1>회원관리1</h1>
			<ul>
				<h2>&nbsp;</h2>
				<span style="line-height:10px;">&nbsp;</span>
			</ul>
		</div>
  </c:when>		


<c:otherwise>
<div id="LNB">
	<h1>환경설정2</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:10px;">&nbsp;</span>
	</ul>
</div>

</c:otherwise>
</c:choose>
<div id="LNB">
	<h1>환경설정3</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:10px;">&nbsp;</span>
	</ul>
</div>


<div id="LNB">
	<h1>환경설정4</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:10px;">&nbsp;</span>
	</ul>
</div>
