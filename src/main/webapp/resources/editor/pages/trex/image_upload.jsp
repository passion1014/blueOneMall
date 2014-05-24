<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String imageurl = request.getParameter("imageurl");
	String filename = request.getParameter("filename");
	String filesize = request.getParameter("filesize");
	String originalurl = request.getParameter("originalurl");
	String thumburl = request.getParameter("thumburl");
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<script src="../../js/popup.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="../../css/popup.css" type="text/css"  charset="utf-8"/>

<script type="text/javascript">
function initUploader() {

	var _opener = PopupUtil.getOpener();
	if (!_opener) {
		alert('잘못된 경로로 접근하셨습니다.');
		return;
	}

	var _attacher = getAttacher('image', _opener);
	registerAction(_attacher);

	if (typeof(execAttach) == 'undefined') { //Virtual Function
		return;
	}

	var _mockdata = {
		<%-- 'imageurl': '../upload/<%=imageurl%>', --%>
		'imageurl': '/resources/upload/<%=imageurl%>',
		'filename': '<%=filename%>',
		'filesize': '<%=filesize%>',
		'imagealign': 'C',
		'originalurl': '/file1/<%=originalurl%>',
		'thumburl': '/file2/<%=thumburl%>',
	};

	parent.execAttach(_mockdata);
	closeWindow();
}
</script>
</head>
<body onload="initUploader();">
</body>
</html>