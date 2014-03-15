<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${param.container == 'memberAgreement'}"> 
	    <div id="Contents">
	       <h1>회원관리 &gt; 약관정보 &gt; <strong><?=$_row["title"]?></strong></h1>

	             <form name="frm" method="post" action="./_action/member.do.php" onSubmit="return chkForm(this);" style="display:inline;" target="actionForm">
	               <input type="hidden" name="Mode" value="agreement_update">
	               <input type="hidden" name="idx" value="<?=$_idx?>">
	           <table>
				  <colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="*" />
				  </colgroup>
				  <tr>
					<th>항목</th>
					<td class="left"><input type="text" id="title" name="title" value="<?=$_row["title"]?>" class="Text Kor" style="width:95%;"></td>
					<th>최종변경일</th>
					<td class="left"><?=$_row["reg_date"]?></td>
				  </tr>
				  <tr>
					<th>약관내용</th>
					<td colspan="3" class="left">
						<textarea name="contents" class="input" style="width:95%;height:400px;padding:3pt;" required hname="약관을 입력하여 주십시오!"><?=$contents?></textarea>
					</td>
				  </tr>
	           </table>

		  <div style="text-align:center;">
			<input type="submit" value=" 수정 " class="Button Gray"> &nbsp;
			<input type="button" value=" 취소 " class="Button Gray" onClick="reset();">
		  </div>
		    </form>

      </div>
	</c:when>
	
	<c:when test="${param.container == 'personInfo'}"> 
	   <div id="Contents">
	<h1>회원관리 &gt; 약관정보 &gt; <strong><?=$_row["title"]?></strong></h1>

	<form name="frm" method="post" action="./_action/member.do.php" onSubmit="return chkForm(this);" style="display:inline;" target="actionForm">
	<input type="hidden" name="Mode" value="agreement_update">
	<input type="hidden" name="idx" value="<?=$_idx?>">
	<table>
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tr>
			<th>항목</th>
			<td class="left"><input type="text" id="title" name="title" value="<?=$_row["title"]?>" class="Text Kor" style="width:95%;"></td>
			<th>최종변경일</th>
			<td class="left"><?=$_row["reg_date"]?></td>
		</tr>
		<tr>
			<th>약관내용</th>
			<td colspan="3" class="left">
				<textarea name="contents" class="input" style="width:95%;height:400px;padding:3pt;" required hname="약관을 입력하여 주십시오!"><?=$contents?></textarea>
			</td>
		</tr>
	</table>

	<div style="text-align:center;">
		<input type="submit" value=" 수정 " class="Button Gray"> &nbsp;
		<input type="button" value=" 취소 " class="Button Gray" onClick="reset();">
	</div>
	</form>

</div>
	</c:when>
	
	<c:when test="${param.container == 'memberList'}"> 
      <div id="Contents">
	<h1>회원관리 &gt; 회원정보 &gt; <strong>회원목록</strong></h1>
	
	    <c:import url="memberSerach.jsp" />
	    
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
		
		
			
			
			
			
			<font color=\"#3300FF\">활동회원</font>
			<font color=\"#009900\">휴면회원</font>
			<font color=\"#FF6600\">탈퇴회원</font>

			

			
			
			
					
					<tr>
						<td><?=$_row["idx"]?></td>
						<td><?=$_row["userStatusName"]?></td>
						<td colspan="2" class="left" style="line-height:180%;">
							<b>[ 활동중지시간 : <?=$_row["stop_date"]?> ] <?=$_row["user_name"]?>  (<?=$_row["user_id"]?>)</b>
							
						</td>
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
							
							<br /> Tel. <?=$_row["user_tel"]?> &nbsp;&nbsp; H.P <?=$_row["user_hp"]?>
							<br /> <?=$_row["user_email"]?>
							<br /> 가입일 : <?=substr($_row["reg_date"],0,10)?> // 마지막 로그인 ( 횟수 ) : <?=$_row["last_log"]?> ( <?=number_format($_row["login_count"])?> )
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
	<?
	$_url   = "&slot=member&type=member_list" ;
	$paging = new paging("./admin.member.php",$_url,$offset,$page_block,$totalnum,$page,$_opt);
	$paging->pagingArea("","") ;
	?>
	</div>
	
	<?$db->db_close();?>

</div>
	</c:when>
	
	<c:when test="${param.container == 'memberRegist'}"> 
      <div id="Contents">
	    <h1>환경설정 &gt; 운영자관리 &gt; <strong>운영자등록</strong></h1>

			<form name="frm" method="post" action="registAdminInf.do" onSubmit="return chk_admin_form(this);" style="display:inline;" >
			<input type="hidden" id="Mode" name="Mode" value="admin_add">
			<input type="hidden" id="chk_id" name="chk_id" value="" required hname="아이디 중복찾기를 하여 주십시오!">
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
						<input type="text" id="admin_id" name="id" value="" class="Text Eng" style="width:120px;" required hname="아이디를 등록하여 주십시오" option="regId"> <input type="button" value="중복찾기" class="button_green button_small" onClick="chk_double_admin_id();">
					</td>
					<th>이름</th>
					<td class="left">
						<input type="text" id="admin_name" name="name" value="" class="Text Kor" style="width:200px;" required hname="이름를 입력하여 주십시오">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td class="left">
						<input type="password" id="admin_pass" name="password" value="" class="Text Eng" style="width:100px;" maxlength="15"  required hname="비밀번호를 입력하여 주십시오"  option="regPass"> ( 4 ~ 16자 )
					</td>
					<th>비밀번호확인</th>
					<td class="left">
						<input type="password" id="re_pass" name="re_pass" value="" class="Text Eng" style="width:100px;" maxlength="15"  required hname="비밀번호를 입력하여 주십시오">
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td class="left">
						<input type="text" id="Phone1" name="Phone" value="" class="Text Eng" style="width:50px;"> -
						<input type="text" id="Phone2" name="Phone" value="" class="Text Eng" style="width:50px;"> -
						<input type="text" id="Phone3" name="Phone" value="" class="Text Eng" style="width:50px;">
					</td>
					<th>핸드폰</th>
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
							<option value="">직접입력</option>
							<option value="naver.com">naver.com</option>
							<option value="yahoo.co.kr">yahoo.co.kr</option>
							<option value="gmail.com">gmail.com</option>
						</select>
						<input id="emailDomain" name="email" type="text" class="Text Eng" style="width:250px;" /> 
					</td>
				</tr>
				<tr>
					<th>관리등급</th>
					<td colspan="3" style="padding:5px 5px 5px 10px;" class="left">
						<input type="checkbox" id="g1" name="g1" value="y" /> 환경설정
						<input type="checkbox" id="g2" name="g2" value="y" /> 회원관리
						<input type="checkbox" id="g3" name="g3" value="y" /> 상품관리
						<input type="checkbox" id="g4" name="g4" value="Y" /> 주문관리
						<input type="checkbox" id="g5" name="g5" value="Y" /> 커뮤니티
					</td>
				</tr>
				<tr>
					<th>추가관리정보</th>
					<td colspan="3" class="left">
						<textarea id="admin_cmt" name="comment" class="Text Kor" style="width:97%;height:80pt;padding:3px;"></textarea>
					</td>
				</tr>
			</table>
		
			<div style="margin-top:20px;" class="center">
				<input type="submit" value="등록하기" class="button_green button_medium"> &nbsp; 
				<input type="button" value="취소" class="button_red button_medium" onClick="reset();">
			</div>
			</form>
		
		</div>
	</c:when>
</c:choose>