<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/basic.css'/>" />
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>" />

<script type="text/javascript" src="<c:url value='/resources/js/js_ajax.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_common.js'/>"> </script>
<script type="text/javascript" src="<c:url value='/resources/js/js_admin.js'/>"> </script>
<title>Insert title here</title>
</head>

<body>
<div id="Wrap">
<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="conf"/>
	</c:import>

	

<iframe name="actionForm" width="700" height="200" frameborder="0" style="display:none;"> </iframe>
<div id="Contents">
	<h1>ȯ�漳�� &gt; ��ڰ��� &gt; <strong>��ڵ��</strong></h1>

	<form name="frm" method="post" action="registAdminInf.do" >
	<input type="hidden" id="Mode" name="Mode" value="admin_add">
	<input type="hidden" id="chk_id" name="chk_id" value="" required hname="���̵� �ߺ�ã�⸦ �Ͽ� �ֽʽÿ�!">
	<table>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tr>
			<th>ID</th>
			<td class="left">
				<input type="text" id="admin_id" name="id" value="" class="Text Eng" style="width:120px;" required hname="���̵� ����Ͽ� �ֽʽÿ�" option="regId"> <input type="button" value="�ߺ�ã��" class="button_green button_small" onClick="chk_double_admin_id();">
			</td>
			<th>�̸�</th>
			<td class="left">
				<input type="text" id="admin_name" name="name" value="" class="Text Kor" style="width:200px;" required hname="�̸��� �Է��Ͽ� �ֽʽÿ�">
			</td>
		</tr>
		<tr>
			<th>��й�ȣ</th>
			<td class="left">
				<input type="password" id="admin_pass" name="password" value="" class="Text Eng" style="width:100px;" maxlength="15"  required hname="��й�ȣ�� �Է��Ͽ� �ֽʽÿ�"  option="regPass"> ( 4 ~ 16�� )
			</td>
			<th>��й�ȣȮ��</th>
			<td class="left">
				<input type="password" id="re_pass" name="re_pass" value="" class="Text Eng" style="width:100px;" maxlength="15"  required hname="��й�ȣ�� �Է��Ͽ� �ֽʽÿ�">
			</td>
		</tr>
		<tr>
			<th>����ó</th>
			<td class="left">
				<input type="text" id="Phone1" name="Phone" value="" class="Text Eng" style="width:50px;"> -
				<input type="text" id="Phone2" name="Phone" value="" class="Text Eng" style="width:50px;"> -
				<input type="text" id="Phone3" name="Phone" value="" class="Text Eng" style="width:50px;">
			</td>
			<th>�ڵ���</th>
			<td class="left">
				<input type="text" id="Mobile1" name="Mobile" value="" class="Text Eng" style="width:50px;"> -
				<input type="text" id="Mobile2" name="Mobile" value="" class="Text Eng" style="width:50px;"> -
				<input type="text" id="Mobile3" name="Mobile" value="" class="Text Eng" style="width:50px;">
			</td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td colspan="3" class="left">
				<input id="emailID" name="email" type="text" class="Text Eng" style="width:250px;" /> @
				<select id="selectDomin" name="selectDomin" onChange="document.getElementById('emailDomain').value = this.value; ">
					<option value="">�����Է�</option>
					<option value="naver.com">naver.com</option>
					<option value="yahoo.co.kr">yahoo.co.kr</option>
					<option value="gmail.com">gmail.com</option>
				</select>
				<input id="emailDomain" name="email" type="text" class="Text Eng" style="width:250px;" /> 
			</td>
		</tr>
		<tr>
			<th>�������</th>
			<td colspan="3" style="padding:5px 5px 5px 10px;" class="left">
				<input type="checkbox" id="g1" name="grade" value="y" /> ȯ�漳��
				<input type="checkbox" id="g2" name="grade" value="y" /> ȸ������
				<input type="checkbox" id="g3" name="grade" value="y" /> ��ǰ����
				<input type="checkbox" id="g4" name="grade" value="Y" /> �ֹ�����
				<input type="checkbox" id="g5" name="grade" value="Y" /> Ŀ�´�Ƽ
			</td>
		</tr>
		<tr>
			<th>�߰���������</th>
			<td colspan="3" class="left">
				<textarea id="admin_cmt" name="comment" class="Text Kor" style="width:97%;height:80pt;padding:3px;"></textarea>
			</td>
		</tr>
	</table>

	<div style="margin-top:20px;" class="center">
		<input type="submit" value="����ϱ�" class="button_green button_medium"> &nbsp; 
		<input type="button" value="���" class="button_red button_medium" onClick="reset();">
	</div>
	</form>

</div>

<!-- admin.bottom.html start-->
</div>
</body>
</html>