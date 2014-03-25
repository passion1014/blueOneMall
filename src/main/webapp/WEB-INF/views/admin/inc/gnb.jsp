<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="Header">
	<a href="/_admin/"><img src="/img/top_logo.gif" alt=""></a>
	<div style="padding-right:10px">
		<b>${adminSession.id}</b> 께서 로그인하셨습니다. <input type="button" value="로그아웃" onClick="location.href='adminLoginOut.do';" style="cursor:pointer;"/>
	</div>
</div>
	
<div id="GNB">
	<ul>
		<li><a href="adminMain.do">환경설정</a></li>
		<li><a href="memberList.do">회원관리</a></li>
		<li><a href="goods.do">상품관리</a></li>
		<li><a href="order.do">주문관리</a></li>
		<li><a href="community.do">커뮤니티</a></li>
	</ul>
</div>