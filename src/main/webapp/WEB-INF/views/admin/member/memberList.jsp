<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../inc/top.jsp" />

<body>

<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
	   <c:param name="slot" value="member"/>
	</c:import>
	 <div id="Contents">
	<h1>회원관리 &gt; 회원정보 &gt; <strong>회원목록</strong></h1>
	
	<form id="sfrm" name="sfrm" method="get" action="./admin.member.php">
		<input type="hidden" id="slot" name="slot" value="member">
		<input type="hidden" id="type" name="type" value="member_list">
		<table>
			<tr>
				<td class="left">
					<div style="margin-top:5px;">
						<b>가입날짜검색</b> <input type="checkbox" id="schChkDate" name="schChkDate" value="Y" onClick="dateDisable();" /> &nbsp;&nbsp;
							<input type="text" name="schReqSDate" id="schReqSDate" readonly value="" class="Text Kor" style="width:65px;" /> 일 부터
							<input type="text" name="schReqEDate" id="schReqEDate" readonly value="" class="Text Kor" style="width:65px;" />일 까지 &nbsp;&nbsp;
					</div>
		
					<div style="margin-top:5px;">
						<select id="search_status" name="search_status" onChange="sfrm.submit();">
							<option value="">:: 회원 전체 ::</option>
							<option value="active"   >:: 활동회원 ::</option>
							<option value="dormancy" >:: 휴면회원 ::</option>
							<option value="stoppage" >:: 탈퇴회원 ::</option>
						</select>
						
						<select id="keyfield" name="keyfield">
							<option value="t1.user_name"  >회원명</option>
							<option value="t1.user_id"    >회원ID</option>
							<option value="t2.user_email" >이메일</option>
							<option value="t2.user_hp"    >핸드폰번호</option>
						</select>
						<input type="text" id="keyword" name="keyword" class="Text" value="">
						<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
						<input type="button" value="초기화" class="Small_Button Gray" onClick="location.href='./admin.member.php?slot=member&type=member_list'">&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
		</form>    
	    
	<div class="left" style="margin-top:15px;"><b>회원 리스트</b> ( 검색 인원 : <?=number_format($totalnum)?> 명 )</div>
	
	<table>
		<colgroup>
			<col width="5%" />
			<col width="8%" />
			<col width="8%" />
			<col width="*" />
			<col width="12%" />
		</colgroup>
		<tr>
			<th>No</th>
			<th>상태</th>
			<th>등급</th>
			<th>회원정보</th>
			<th>관리</th>
		</tr>
		<tr>
			<td><?=$_row["idx"]?></td>
			<td><?=$_row["userStatusName"]?></td>
			<td colspan="2" class="left" style="line-height:180%;"></td>
			<td>
				<input type="button" value="관리" class="Small_Button Gray" onClick="location.href='<?=$_information_ref?>'">
				<input type="button" value="삭제" class="Small_Button Gray" onClick="confirm_process('actionForm','회원 정보를 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다.','<?=$_del_ref?>');">
			</td>
		</tr>
					<?
				}else{
					?>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td class="left" style="line-height:180%;">
							
							<br /> 
							<br /> 
							<br />
						</td>
						<td>
							<input type="button" value="관리" class="Small_Button Gray" onClick="location.href='<?=$_information_ref?>'">
							<input type="button" value="삭제" class="Small_Button Gray" onClick="confirm_process('actionForm','회원 정보를 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다.','<?=$_del_ref?>');">
						</td>
					</tr>
					<?
				}
			}
		}else{
		
		}
		?>
	</table>

	<div align="center" style="padding-top:10px;">
	
	</div>
	
	
</div>
	

</div>
</body>

<c:import url="../inc/footer.jsp" />