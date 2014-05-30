<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="lnb">
	<h3>마이페이지</h3>
	<ul class="lnb_list">
		<li><a href="/user/orderListView.do">주문내역관리</a></li>
		<!--
		<div class="onclick_list">
			<a href="/user/orderCancel.do">주문취소신청</a>
			<a href="/user/userPoint.do">주문반품신청</a>
		</div>
		-->
		<li><a href="/user/userPoint.do">포인트사용현황</a></li>
		<!--
		<div class="onclick_list">
			<a href="/user/userPointSaving.do">적립금현황</a>
			<a href="/user/userPoint.do">사용내역조회</a>
		</div>
		-->
		<li><a href="javascript:void(0)">내정보관리</a></li>
		<div class="onclick_list">
			<a href="/user/userEdit.do">나의정보</a>
		</div>
	</ul>
</div>