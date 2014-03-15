<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



		<form id="sfrm" name="sfrm" method="get" action="./admin.member.php">
		<input type="hidden" id="slot" name="slot" value="member">
		<input type="hidden" id="type" name="type" value="member_list">
		<table>
			<tr>
				<td class="left">
					<div style="margin-top:5px;">
						<b>가입날짜검색</b> <input type="checkbox" id="schChkDate" name="schChkDate" value="Y" onClick="dateDisable();" <?=$_checked?>> &nbsp;&nbsp;
							<input type="text" name="schReqSDate" id="schReqSDate" readonly value="<?=$schReqSDate?>" class="Text Kor" style="width:65px;" <?=$_disabled?>/>일 부터
							<input type="text" name="schReqEDate" id="schReqEDate" readonly value="<?=$schReqEDate?>" class="Text Kor" style="width:65px;" <?=$_disabled?>/>일 까지 &nbsp;&nbsp;
					</div>
		
					<div style="margin-top:5px;">
						<select id="search_status" name="search_status" onChange="sfrm.submit();">
							<option value="">:: 회원 전체 ::</option>
							<option value="active"   <?=$_status["active"]?>>:: 활동회원 ::</option>
							<option value="dormancy" <?=$_status["dormancy"]?>>:: 휴면회원 ::</option>
							<option value="stoppage" <?=$_status["stoppage"]?>>:: 탈퇴회원 ::</option>
						</select>
						
						<select id="keyfield" name="keyfield">
							<option value="t1.user_name"  <?if($_search["keyfield"] == "t1.user_name")	echo "selected";?>>회원명</option>
							<option value="t1.user_id"    <?if($_search["keyfield"] == "t1.user_id")		echo "selected";?>>회원ID</option>
							<option value="t2.user_email" <?if($_search["keyfield"] == "t2.user_email")	echo "selected";?>>이메일</option>
							<option value="t2.user_hp"    <?if($_search["keyfield"] == "t2.user_hp")		echo "selected";?>>핸드폰번호</option>
						</select>
						<input type="text" id="keyword" name="keyword" class="Text" value="<?=$_search["keyword"]?>">
						<input type="submit" value="검색"   class="Small_Button Gray">&nbsp;&nbsp;
						<input type="button" value="초기화" class="Small_Button Gray" onClick="location.href='./admin.member.php?slot=member&type=member_list'">&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
		</form>