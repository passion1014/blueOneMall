<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- admin.top.html start -->

<!doctype html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=1280; user-scalable=yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>:: ADMIN ::</title>
<link rel="stylesheet" href="../css/basic.css" />
<link rel="stylesheet" href="../css/admin.css" />
<script language="javascript" type="text/javascript" src="../js/js_ajax.js"> </script>
<script language="javascript" type="text/javascript" src="../js/js_common.js"> </script>
<script language="javascript" type="text/javascript" src="../js/js_admin.js"> </script>
<!--[if lt IE 9]>
<script src="js/jquery_html5shiv.js"></script>
<![endif]-->

<!-- admin.top.html end -->








<!-- admin.header.html start -->
</head>
<iframe name="actionForm" width="700" height="200" frameborder="0" style="display:none;"> </iframe>

<body>
<div id="Wrap">

	<div id="Header">
		<a href="/_admin/"><img src="/img/top_logo.gif" alt=""></a>
		<div style="padding-right:10px">
			<b>��ü������</b> ���� �α����ϼ̽��ϴ�. <input type="button" value="�α׾ƿ�" onClick="location.href='./admin.login.html';" style="cursor:pointer;"/>
		</div>
	</div>
<!-- admin.header.html end -->









<!-- admin.gnb.html start -->
	<div id="GNB">
		<ul>
			<li><a href="./admin.conf1.html?slot=conf&type=admin_list">ȯ�漳��</a></li>
			<li><a href="#">ȸ������</a></li>
			<li><a href="#">��ǰ����</a></li>
			<li><a href="#">�ֹ�����</a></li>
			<li><a href="#">Ŀ�´�Ƽ</a></li>
		</ul>
	</div>
<!-- admin.gnb.html end -->













<!-- admin.lnb_none.html start -->
<div id="LNB">

	<div style="display:block;">
		<h1>ȯ�漳��</h1>
		<ul>
			<h2>SITE ȯ�漳��</h2>
			<li><a href="#">��������</a></li>
		</ul>
		<ul>
			<h2>�������</h2>
			<li><a href="#">������</a></li>
		</ul>
		<ul>
			<h2>��� ����</h2>
			<li><a href="./admin.conf1.html?slot=conf&type=admin_list">��� ���</a></li>
			<li><a href="./admin.conf2.html?slot=conf&type=admin_register">��� ���</a></li>
		</ul>
	</div>

</div>


<!-- admin.lnb_none.html end -->










<!-- ./conf/conf.admin_list.html start -->
<div id="Contents">
	<h1>ȯ�漳�� &gt; ��ڰ��� &gt; <strong>��ڸ��</strong></h1>
	<table>
		<colgroup>
			<col width="4%" />
			<col width="7%" />
			<col width="10%" />
			<col width="*" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="7%" />
			<col width="6%" />
			<col width="10%" />
		</colgroup>
		<tr>
			<th rowspan="2">No</th>
			<th rowspan="2">����</th>
			<th rowspan="2">ID</th>
			<th rowspan="2">�̸�</th>
			<th colspan="5">��������</th>
			<th rowspan="2">�α�<br />Ƚ��</th>
			<th rowspan="2">�����</th>
		</tr>
		<tr>
			<th>ȯ�漳��</th>
			<th>ȸ������</th>
			<th>��ǰ����</th>
			<th>�ֹ�����</th>
			<th>Ŀ�´�Ƽ</th>
		</tr>
      <c:forEach items="${getAdminInfoList}" var="infoList">
		<tr onClick="line_detail('1')" style="cursor:pointer;">
			<td><b>${infoList.idx}</b></td>
			<td>${infoList.status}</td>
			<td>${infoList.admin_id}</td>
			<td>${infoList.admin_name}</td>
			<td>��</td>
			<td>��</td>
			<td>��</td>
			<td>��</td>
			<td>��</td>
			<td>212</td>
			<td>2014.02.15</td>
		</tr>
		<tr id="line_1" style="display:none;">
			<td colspan="13" style="padding:5px 5px 30px 50px;" class="right">
						
					<table>
						<colgroup>
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="35%" />
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>����</th>
							<td colspan="3" class="left"><b><u><?=$_row["ast"]?></u></b> &nbsp;&nbsp; [ �� : 222 ȸ,  �ֱ� �α��� �ð� : 2015.15.21  ]</td>
						</tr>
						<tr>
							<th>������ ����</th>
							<td class="left"><?=$_row["admin_name"]?> ( ID : )</td>
							<th>PASS</th>
							<td class="left"><?=$_row["admin_pass"]?></td>
						</tr>
						<tr>
							<th>����ó</th>
							<td class="left"><?=stripslashes($_row["admin_phone"])?></td>
							<th>�ڵ���</th>
							<td class="left"><?=stripslashes($_row["admin_mobile"])?></td>
						</tr>
						<tr>
							<th>Email</th>
							<td colspan="3" class="left"><?=stripslashes($_row["admin_email"])?></td>
						</tr>
						<tr>
							<th>�������</th>
							<td colspan="3" style="padding:5px;">
								<table width="100%">
									<colgroup>
										<col width="35%" />
										<col width="15%" />
										<col width="35%" />
										<col width="*"   />
									</colgroup>
									<tr>
										<th>ȯ�����</th>
										<td>��</td>
										<th>ȸ������</th>
										<td>��</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3" style="padding:5px 10px 5px 10px;" class="left"><?=nl2br(stripslashes($_row["admin_cmt"]))?>&nbsp;</td>
						</tr>
					</table>
					<div style="margin-top:10px;" class="right">
						<input type="button" value="��������" class="button button_green button_medium" onClick="location.href='./admin.conf.php?slot=conf&type=admin_update&idx='"> &nbsp;
						<input type="button" value="����" class="button button_red button_medium" onClick="confirm_process('actionForm','������ ������ �����Ͻðڽ��ϱ�? \n\n�����Ŀ��� ������ �Ұ����մϴ�. \n\n���¸� ���� ��Ű�� ������ ��Ż�� �� �ֽ��ϴ�. \n\n�׷��� �����Ͻðڽ��ϱ�?','');">
					</div>
						

			</td>
		</tr>


		<tr onClick="line_detail('2')" style="cursor:pointer;">
			<td><b>2</b></td>
			<td>��������</td>
			<td>admin222</td>
			<td>��ü������222</td>
			<td>��</td>
			<td>��</td>
			<td>��</td>
			<td>��</td>
			<td>��</td>
			<td>212</td>
			<td>2014.02.15</td>
		</tr>
		<tr id="line_2" style="display:none;">
			<td colspan="13" style="padding:5px 5px 30px 50px;" class="right">
						
					<table>
						<colgroup>
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="35%" />
							<col width="15%" align="center" bgcolor="#F7F7F7" />
							<col width="*" />
						</colgroup>
						<tr>
							<th>����</th>
							<td colspan="3" class="left"><b><u><?=$_row["ast"]?></u></b> &nbsp;&nbsp; [ �� : 222 ȸ,  �ֱ� �α��� �ð� : 2015.15.21  ]</td>
						</tr>
						<tr>
							<th>������ ����</th>
							<td class="left"><?=$_row["admin_name"]?> ( ID : )</td>
							<th>PASS</th>
							<td class="left"><?=$_row["admin_pass"]?></td>
						</tr>
						<tr>
							<th>����ó</th>
							<td class="left"><?=stripslashes($_row["admin_phone"])?></td>
							<th>�ڵ���</th>
							<td class="left"><?=stripslashes($_row["admin_mobile"])?></td>
						</tr>
						<tr>
							<th>Email</th>
							<td colspan="3" class="left"><?=stripslashes($_row["admin_email"])?></td>
						</tr>
						<tr>
							<th>�������</th>
							<td colspan="3" style="padding:5px;">
								<table width="100%">
									<colgroup>
										<col width="35%" />
										<col width="15%" />
										<col width="35%" />
										<col width="*"   />
									</colgroup>
									<tr>
										<th>ȯ�����</th>
										<td>��</td>
										<th>ȸ������</th>
										<td>��</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3" style="padding:5px 10px 5px 10px;" class="left"><?=nl2br(stripslashes($_row["admin_cmt"]))?>&nbsp;</td>
						</tr>
					</table>
					<div style="margin-top:10px;" class="right">
						<input type="button" value="��������" class="button button_green button_medium" onClick="location.href='./admin.conf.php?slot=conf&type=admin_update&idx='"> &nbsp;
						<input type="button" value="����" class="button button_red button_medium" onClick="confirm_process('actionForm','������ ������ �����Ͻðڽ��ϱ�? \n\n�����Ŀ��� ������ �Ұ����մϴ�. \n\n���¸� ���� ��Ű�� ������ ��Ż�� �� �ֽ��ϴ�. \n\n�׷��� �����Ͻðڽ��ϱ�?','');">
					</div>
						

			</td>
		</tr>
	</table>

	<div id="Paser"> 1 | 2 | 3</div>

</div>
<!-- ./conf/conf.admin_list.html end -->















<!-- admin.bottom.html start-->
</div>
</body>
</html>
<!-- admin.bottom.html end-->