<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../inc/top.jsp" />

<body>

<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="member"/>
	</c:import>
	<c:import url="membercontainer.jsp">
	    <c:param name="container" value="memberAgreement"/>
	</c:import>	
	

</div>
</body>

<c:import url="../inc/footer.jsp" />