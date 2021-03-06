<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="img"/>
	</c:import>
		
	<div id="Contents">
	<h1>메인관리 &gt; 배너 관리 &gt; <strong>배너관리</strong></h1>
	
	<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="adminBannerProc.do">
	<input type="hidden" id="mdUrl1" name="mdUrl1" value="${adImgInfo.mdUrl1}">
	<input type="hidden" id="mdText1" name="mdText1" value="${adImgInfo.mdText1}">	
	<input type="hidden" id="mdUrl2" name="mdUrl2" value="${adImgInfo.mdUrl2}">
	<input type="hidden" id="mdText2" name="mdText2" value="${adImgInfo.mdText2}">	
	<input type="hidden" id="mdUrl3" name="mdUrl3" value="${adImgInfo.mdUrl3}">
	<input type="hidden" id="mdText3" name="mdText3" value="${adImgInfo.mdText3}">	
	<input type="hidden" id="mdUrl4" name="mdUrl4" value="${adImgInfo.mdUrl4}">
	<input type="hidden" id="mdText4" name="mdText4" value="${adImgInfo.mdText4}">	
	<input type="hidden" id="mdUrl5" name="mdUrl5" value="${adImgInfo.mdUrl5}">
	<input type="hidden" id="mdText5" name="mdText5" value="${adImgInfo.mdText5}">	
	<input type="hidden" id="mdUrl6" name="mdUrl6" value="${adImgInfo.mdUrl6}">
	<input type="hidden" id="mdText6" name="mdText6" value="${adImgInfo.mdText6}">
		
	<table>
		<colgroup>
			<col width="15%">
			<col width="*">
		</colgroup>
				
		<tr>
			<th>배너이미지1</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg1 eq NULL || adImgInfo.bnImg1 == ''}">
						<input type="file" id="ban1Up" name="ban1Up" style="width:60%;"> [153px X 153px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg1}" width="153">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg1&fileName=${adImgInfo.bnImg1}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>배너이미지1-URL</th>
			<td class="left">
				<input type="text" id="bnUrl1" name="bnUrl1" value="${adImgInfo.bnUrl1}" style="width:80%;">
			</td>
		</tr>
		
		
		
		<tr>
			<th>배너이미지2</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg2 eq NULL || adImgInfo.bnImg2 == ''}">
						<input type="file" id="ban2Up" name="ban2Up" style="width:60%;"> [153px X 301px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg2}" width="153">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg2&fileName=${adImgInfo.bnImg2}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>배너이미지2-URL</th>
			<td class="left">
				<input type="text" id="bnUrl2" name="bnUrl2" value="${adImgInfo.bnUrl2}" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>배너이미지3</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg3 eq NULL || adImgInfo.bnImg3 == ''}">
						<input type="file" id="ban3Up" name="ban3Up" style="width:60%;"> [153px X 153px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg3}" width="153">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg3&fileName=${adImgInfo.bnImg3}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>배너이미지3-URL</th>
			<td class="left">
				<input type="text" id="bnUrl3" name="bnUrl3" value="${adImgInfo.bnUrl3}" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>배너이미지4</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg4 eq NULL || adImgInfo.bnImg4 == ''}">
						<input type="file" id="ban4Up" name="ban4Up" style="width:60%;"> [153px X 301px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg4}" width="153">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg4&fileName=${adImgInfo.bnImg4}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>배너이미지4-URL</th>
			<td class="left">
				<input type="text" id="bnUrl4" name="bnUrl4" value="${adImgInfo.bnUrl4}" style="width:80%;"> 
			</td>
		</tr>
		
		
		<tr>
			<th>하단 배너 1</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg5 eq NULL || adImgInfo.bnImg5 == ''}">
						<input type="file" id="ban5Up" name="ban5Up" style="width:60%;"> [494px X 100px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg5}" width="494">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg5&fileName=${adImgInfo.bnImg5}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>하단 배너 1-URL</th>
			<td class="left">
				<input type="text" id="bnUrl5" name="bnUrl5" value="${adImgInfo.bnUrl5}" style="width:80%;"> 
			</td>
		</tr>
		
		
		
		<tr>
			<th>하단 배너 2</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg6 eq NULL || adImgInfo.bnImg6 == ''}">
						<input type="file" id="ban6Up" name="ban6Up" style="width:60%;"> [494px X 100px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg6}" width="494">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg6&fileName=${adImgInfo.bnImg6}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>하단 배너 2-URL</th>
			<td class="left">
				<input type="text" id="bnUrl6" name="bnUrl6" value="${adImgInfo.bnUrl6}" style="width:80%;"> 
			</td>
		</tr>
		
		
		
		<tr>
			<th>Top 배너</th>
			<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.bnImg7 eq NULL || adImgInfo.bnImg7 == ''}">
						<input type="file" id="ban7Up" name="ban7Up" style="width:60%;"> [336px X 52px]
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.bnImg7}" width="336">
						<br />
						<input type="button" value="이미지 삭제"  onClick="confirm_process('','이미지를 삭제하시겠습니까? \n\n삭제 후에는 복구가 불가능합니다.','deleteBannerImgProc.do?fieldName=bnImg7&fileName=${adImgInfo.bnImg7}');"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<th>Top 배너-URL</th>
			<td class="left">
				<input type="text" id="bnUrl7" name="bnUrl7" value="${adImgInfo.bnUrl7}" style="width:80%;"> 
			</td>
		</tr>
	</table>

	<div class="Btn_area">
		<input type="submit" value="등록" 	  class="Button Gray" /> &nbsp; 
		<input type="button" value="취소"     class="Button Gray" onClick="history.back();" />
	</div>

	</form>

	</div>
</div>
</body>

<c:import url="../inc/footer.jsp" />