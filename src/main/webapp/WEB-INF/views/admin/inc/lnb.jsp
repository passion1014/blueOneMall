<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${param.slot == 'img'}"> 
		<div id="LNB">
			<h1>메인 관리</h1>
			<ul>
				<h2>메인디자인 관리</h2>
				<li><a href="adminDesign.do">디자인 관리</a></li>
			</ul>
			<ul>
				<h2>배너 관리</h2>
				<li><a href="adminBanner.do">배너 관리</a></li>
			</ul>
		</div>
	</c:when>
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
			<li><a href="accountList.do">계좌 목록</a></span>			
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
			<li><a href="largeTypeList.do?page=1">대분류 목록</a></li>
			<li><a href="middleTypeList.do?page=1">중분류 목록</a></li>
			<li><a href="smallTypeList.do?page=1">소분류 목록</a></li>
		</ul>
		<ul>
			<h2>상품관리</h2>
			<li><a href="productList.do?page=1">상품목록</a></li>
			<li><a href="productRegister.do">상품등록</a></li>
		</ul>
		<ul>
			<h2>배송정보관리</h2>
			<li><a href="transferList.do">배송정보목록</a></li>
			<li><a href="transferRegister.do">배송정보등록</a></li>
		</ul>
	</div>
	</c:when>
	
	<c:when test="${param.slot == 'order'}"> 
	<div id="LNB">
		<h1>주문관리</h1>
		<ul>
			<h2>주문서리스트</h2>
			<li><a href="orderList.do">전체리스트</a></li>
			<li>신청중</li>
			<li>배송준비중</li>
			<li>배송중</li>
			<li>주문완료</li>
			<li>최소신청</li>
			<li>취소완료</li>
			<li>반품신청</li>
			<li>반품완료</li>
		</ul>
		<ul>
			<h2>거래내역조회</h2>
			<li>월별거래내역</li>
			<li>상품별거래내역</li>
			
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