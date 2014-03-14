/******************************************************************************
 *
 * 파일명        : js_admin.js
 * 최초작성일    : 2009-04-14
 * 비고          : 각 함수 설명참조
 *                 필요에 의해 추가되는 function이 있을 경우 
 *                 아래 function list에 주석을 추가.
 *
 * = function list =
 *
 *
 *****************************************************************************/
/******************************************************************************
 * 함수명    : chk_double_admin_id()
 * 함수내용  : 서브 관리자 중복 아이디 체크
 *****************************************************************************/
function chk_double_admin_id() {
	var ID = document.getElementById("admin_id").value;

	if(!isAlphaNum(ID)){
		alert('아이디(ID)에는 한글을 입력이 불가능합니다. \n\n아이디를 다시 확인하세요.');
		document.getElementById("admin_id").value = "";
		document.getElementById("admin_id").focus();
		return false;
	}

	if(!ID || ID.length < 4 || ID.length > 16) {

		alert('아이디(ID)를 확인하세요. \n\n아이디는 4~16 입니다.');
		document.getElementById("admin_id").value = "";
		document.getElementById("admin_id").focus();
		return false;

	}else{
		actionForm.location.href="./_action/conf.do.php?Mode=id_find&admin_id="+ID;
	}
}


/******************************************************************************
 * 함수명    : chk_admin_form(f)
 * 함수내용  : 폼내용 확인.
 *****************************************************************************/
function chk_admin_form(f){
	if(chkForm(f)){
		if(f.password.value != f.re_pass.value){
			alert("비밀번호가 일치하지 않습니다.");
			f.re_pass.value = "" ;
			f.re_pass.focus();
			return false;
		}
	}else{
		return false;
	}
}


 /******************************************************************************
 * 함수명    : delete_sub_admin(f)
 * 함수내용  : 서브 관리자 삭제.
 *****************************************************************************/
function delete_sub_admin(idx,aName){
	if(confirm(aName+" 님의 정보를 정말 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다. \n\n상태를 중지 시키면 권한을 박탈할 수 있습니다. \n\n그래도 삭제하시겠습니까?")){
		actionForm.location.href="./config/config.do.php?Mode=admin_delete&idx="+idx;
	}
}


 /******************************************************************************
 * 함수명    : chk_point_term(chk)
 * 함수내용  : 
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
function chk_point_term(chk)
{
	if(chk == "fix"){
		document.getElementById("term_s_yy").disabled = false ;
		document.getElementById("term_s_mm").disabled = false ;
		document.getElementById("term_s_dd").disabled = false ;

		document.getElementById("term_e_yy").disabled = false ;
		document.getElementById("term_e_mm").disabled = false ;
		document.getElementById("term_e_dd").disabled = false ;
	}else{
		document.getElementById("term_s_yy").disabled = true ;
		document.getElementById("term_s_mm").disabled = true ;
		document.getElementById("term_s_dd").disabled = true ;

		document.getElementById("term_e_yy").disabled = true ;
		document.getElementById("term_e_mm").disabled = true ;
		document.getElementById("term_e_dd").disabled = true ;
	}
}



 /******************************************************************************
 * 함수명    : multi_alert_delete(f)
 * 함수내용  : 
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
function multi_alert_delete(f) {	
	var chk_num = f.elements.length;
	
	for(var i = 0; i < chk_num; i++){
		var checkbox_obj = eval("f.elements["+i+"]");
		if(checkbox_obj.checked == true)	break;
	}

	if(i == chk_num) {
		alert("먼저 삭제하고자 하는 정보를 선택하여 주십시오");
		return false;
	} else {
		return confirm("삭제된 후에는 복구가 불가능합니다. \n\n정말 삭제하시겠습니까?")
	}	
}


 /******************************************************************************
 * 함수명    : dateDisable()
 * 함수내용  : 
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
/*
function dateDisable()
{
	if(document.getElementById("dateSearch").checked == true){
		document.getElementById("s_year").disabled  = false ;
		document.getElementById("s_month").disabled = false ;
		document.getElementById("s_day").disabled   = false ;

		document.getElementById("e_year").disabled  = false ;
		document.getElementById("e_month").disabled = false ;
		document.getElementById("e_day").disabled   = false ;
	}else{
		document.getElementById("s_year").disabled  = true ;
		document.getElementById("s_month").disabled = true ;
		document.getElementById("s_day").disabled   = true ;

		document.getElementById("e_year").disabled  = true ;
		document.getElementById("e_month").disabled = true ;
		document.getElementById("e_day").disabled   = true ;
	}
}
*/

function dateDisable()
{
	if(document.getElementById("schChkDate").checked == true){
		document.getElementById("schReqSDate").disabled  = false ;
		document.getElementById("schReqEDate").disabled  = false ;
	}else{
		document.getElementById("schReqSDate").disabled  = true ;
		document.getElementById("schReqEDate").disabled  = true ;
	}
}


/******************************************************************************
 * 함수명    : chk_pass_form(f)
 * 함수내용  : 회원가입시 필드 체크
 * 반환값    : 조건에 맞지 않으면 false, 조건에 맞으면 true
 *****************************************************************************/
function chk_pass_form(f){
	if(chkForm(f)){

		var user_pass = document.getElementById("user_pass").value ;

		if (getByteLength(user_pass) < 4 || getByteLength(user_pass) > 13)
		{
			alert("암호의 길이는 4이상 16 이하의 문자열이어야 합니다.");
			document.getElementById("user_pass").value = "";
			document.getElementById("user_pass").focus();
			return false;
		}

	}else{
		return false;
	}
}



 /******************************************************************************
 * 함수명    : del_mail_background_img(mCode,fileName)
 * 함수내용  : 
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
function del_mail_background_img(mCode,fileName)
{
	if(confirm("배경 이미지를 정말 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다. ")){
		inForm.location.href = "./transfer/transfer.do.php?Mode=del_background_img&mCode="+ mCode +"&fileName="+ fileName ;
	}

}


 /******************************************************************************
 * 함수명    : view_Email()
 * 함수내용  : 
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
function view_Email()
{
	var w_size = document.getElementById("w_size").value ;
	var h_size = document.getElementById("h_size").value ;
	var opt    = "top=100,left=100,width="+ parseInt(w_size,10) +",height="+ (parseInt(h_size,10) + 150) +",scrollbars=no" ;

	document.getElementById("width_size2").value   = "" ;
	document.getElementById("height_size2").value  = "" ;
	document.getElementById("back_img2").value     = "" ;
	document.getElementById("email_cmt2").value    = "" ;
	document.getElementById("email_footer2").value = "" ;

	document.getElementById("width_size2").value   = document.getElementById("w_size").value ;
	document.getElementById("height_size2").value  = document.getElementById("h_size").value ;
	document.getElementById("back_img2").value     = document.getElementById("back_img").value ;
	document.getElementById("email_cmt2").value    = document.getElementById("edm_contents").value ;
	document.getElementById("email_footer2").value = document.getElementById("edm_footer").value ;

	var eWin = window.open("","view_window",opt) ;

	var obj    = eval(document.frm2) ;
	obj.method = "post";
	obj.action = "./webzine/webzine.view.php";
	obj.target = "view_window" ;
	obj.submit();
	
	eWin.focus();

}


 /******************************************************************************
 * 함수명    : updateBankAccount(idx)
 * 함수내용  : 무통장 내용 변경
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
function updateBankAccount(idx)
{

	var code   = "bank_code_" + idx ;
	var owner  = "account_owner_" + idx ;
	var number = "account_number_" + idx ;

	var codeValue   = document.getElementById(code).value ;
	var ownerValue  = document.getElementById(owner).value ;
	var numberValue = document.getElementById(number).value ;

	if(confirm("무통장 정보를 수정하시겠습니까?")){
		actionForm.location.href = "./order/order.do.php?Mode=bank_update&idx="+ idx + "&code="+ codeValue +"&owner="+ ownerValue +"&number="+ numberValue ;
	}

}


 /******************************************************************************
 * 함수명    : deleteBankAccount(idx)
 * 함수내용  : 무통장 내용 삭제
 * 반환값    :
 * 사용법    : 
 *****************************************************************************/
function deleteBankAccount(idx)
{
	if(confirm("무통장 정보를 삭제하시겠습니까? \n\n삭제후에는 복구가 불가능합니다. ")){
		actionForm.location.href = "./order/order.do.php?Mode=bank_delete&idx="+ idx ;
	}

}