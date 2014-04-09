<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%> 
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>###### 현대프로모션몰 ######</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/common.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/component.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/default.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/sub.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui.custom.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"> </script>

<script type="text/javascript">
	<!-- tab module -->
	$(function() {
		$( ".mcont" ).tabs();
	});

	function change_area(n){
		for(var i=1;i<=6;i++){
			area_id = "area_"+i ;
			if(i == n){
				document.getElementById(area_id).style.display = "block";
			}else{
				document.getElementById(area_id).style.display = "none";
			}
		}
	}

		function change_area1(n){
		for(var i=1;i<=3;i++){
			area_id = "area_"+i ;
			if(i == n){
				document.getElementById(area_id).style.display = "block";
			}else{
				document.getElementById(area_id).style.display = "none";
			}
		}
	}
	<!--// tab module -->
</script>
</head>