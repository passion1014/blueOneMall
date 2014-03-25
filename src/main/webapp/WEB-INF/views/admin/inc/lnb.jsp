<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${param.slot == 'conf'}"> 
	<div id="LNB">
		<h1>환경설정</h1>
		<ul>
			<h2>운영 관리</h2>
			<li>운영 설정</li>			
		</ul>
		
		<ul>
			<h2>운영자 관리</h2>
			<li><a href="adminList.do">운영자 목록</a></li>
			<li><a href="adminRegistForm.do">운영자 등록</a></li>			
		</ul>
		
		<ul>
			<h2>계좌 관리</h2>
			<li>계좌 목록</span>			
		</ul>
	</div>
	</c:when>
	
	<c:when test="${param.slot == 'member'}"> 
	<div id="LNB">
		<h1>회원관리</h1>
		<ul>
			<h2>약관정보</h2>
			<li><a href="memberAgreement.do">이용약관</a></li>
			<li><a href="memberInfo.do">개인보호취급방침</a></li>
		</ul>
		<ul>
			<h2>회원정보</h2>
			<li><a href="memberList.do">회원목록</a></li>
			<li><a href="memberRegist.do">회원등록</a></li>			
		</ul>
	</div>
	</c:when>
	
	<c:when test="${param.slot == 'goods'}"> 
	<div id="LNB">
		<h1>상품관리</h1>
		<ul>
			<h2>상품분류</h2>
			<li><a href="largeTypeList.do">대분류 목록</a></li>
			<li><a href="middleTypeList.do">중분류 목록</a></li>
			<li><a href="smallTypeList.do">소분류 목록</a></li>
		</ul>
		<ul>
			<h2>상품관리</h2>
			<li><a href="goodsList.do">상품목록</a></li>
			<li><a href="goodsRegister.do">상품등록</a></li>
		</ul>
	</div>
	</c:when>
	
	<c:when test="${param.slot == 'order'}"> 
	<div id="LNB">
		<h1>주문관리</h1>
		<ul>
			<h2>&nbsp;</h2>
			<li>&nbsp;</li>
		</ul>
	</div>
	</c:when>
	
	<c:when test="${param.slot == 'community'}"> 
	<div id="LNB">
		<h1>커뮤니티</h1>
		<ul>
			<h2>&nbsp;</h2>
			<li>&nbsp;</li>
		</ul>
	</div>
	</c:when>		
	
	<c:otherwise>
	<div id="LNB">
		<h1>&nbsp;</h1>
		<ul>
			<h2>&nbsp;</h2>
			<li>&nbsp;</li>
		</ul>
	</div>
	</c:otherwise>
</c:choose>