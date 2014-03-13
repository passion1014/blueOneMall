<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.slot == 'conf'}">
<div id="LNB">
	<h1>환경설정</h1>
	<ul>
		<h2>&nbsp;</h2>
		<li class="on"></li>
	</ul>
</div>
</c:if>

<c:if test="${param.slot == 'member'}">
<div id="LNB">
	<h1>회원관리1</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:50px;">&nbsp;</span>
	</ul>
</div>
</c:if>

<c:if test="${var.type == 'member'}">
<div id="LNB">
	<h1>환경설정2</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:50px;">&nbsp;</span>
	</ul>
</div>
</c:if>

<c:if test="${param.slot == 'order'}">
<div id="LNB">
	<h1>환경설정</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:50px;">&nbsp;</span>
	</ul>
</div>
</c:if>

<c:if test="${param.slot == 'community'}">
<div id="LNB">
	<h1>환경설정</h1>
	<ul>
		<h2>&nbsp;</h2>
		<span style="line-height:50px;">&nbsp;</span>
	</ul>
</div>
</c:if>
