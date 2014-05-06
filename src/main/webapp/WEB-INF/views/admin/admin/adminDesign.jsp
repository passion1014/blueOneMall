<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${reloadVar=='yes'}"><script>opener.location.reload();</script></c:if>
<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">
	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="img"/>
	</c:import>
		
	<div id="Contents">
		<h1>메인관리 &gt; 메인디자인 관리 &gt; <strong>디자인관리</strong></h1>
		
		<form name="tx_editor_form" method="post" enctype="multipart/form-data" action="adminDesignProc.do">
		<input type="text" id="bnUrl1" name="bnUrl1" value="${adImgInfo.bnUrl1}">
		<input type="text" id="bnUrl2" name="bnUrl2" value="${adImgInfo.bnUrl2}">
		<input type="text" id="bnUrl3" name="bnUrl3" value="${adImgInfo.bnUrl3}">
		<input type="text" id="bnUrl4" name="bnUrl4" value="${adImgInfo.bnUrl4}">
		<input type="text" id="bnUrl5" name="bnUrl5" value="${adImgInfo.bnUrl5}">
		<input type="text" id="bnUrl6" name="bnUrl6" value="${adImgInfo.bnUrl6}">
		<input type="text" id="bnUrl7" name="bnUrl7" value="${adImgInfo.bnUrl7}">
		
		<table>
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			
			<tr>
				<th>메인이미지1</th>
				<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.mdImg1 eq NULL || adImgInfo.mdImg1 == ''}">
						<input type="file" id="main1Up" name="main1Up" style="width:80%;">
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.mdImg1}" width="666">
						<br />
						<input type="button" value="이미지 삭제"  onClick="location.href='deleteDesignImgProc.do?fieldName=mdImg1&fileName=${adImgInfo.mdImg1}';"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>	
				<th>메인제목1</th>
				<td class="left">
					<input type="text" id="mdText1" name="mdText1" value="${adImgInfo.mdText1}" style="width:80%;"> 
				</td>
			</tr>
			<tr>	
				<th>링크주소1</th>
				<td class="left">
					<input type="text" id="mdUrl1" name="mdUrl1" value="${adImgInfo.mdUrl1}" style="width:80%;"> 
				</td>
			</tr>
			
			<tr>
				<th>메인이미지2</th>
				<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.mdImg2 eq NULL || adImgInfo.mdImg2 == ''}">
						<input type="file" id="main2Up" name="main2Up" style="width:80%;">
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.mdImg2}" width="666">
						<br />
						<input type="button" value="이미지 삭제"  onClick="location.href='deleteDesignImgProc.do?fieldName=mdImg2&fileName=${adImgInfo.mdImg2}';"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>	
				<th>메인제목2</th>
				<td class="left">
					<input type="text" id="mdText2" name="mdText2" value="${adImgInfo.mdText2}" style="width:80%;"> 
				</td>
			</tr>
			<tr>	
				<th>링크주소2</th>
				<td class="left">
					<input type="text" id="mdUrl2" name="mdUrl2" value="${adImgInfo.mdUrl2}" style="width:80%;"> 
				</td>
			</tr>
			
			
			<tr>
				<th>메인이미지3</th>
				<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.mdImg3 eq NULL || adImgInfo.mdImg3 == ''}">
						<input type="file" id="main3Up" name="main3Up" style="width:80%;">
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.mdImg3}" width="666">
						<br />
						<input type="button" value="이미지 삭제"  onClick="location.href='deleteDesignImgProc.do?fieldName=mdImg3&fileName=${adImgInfo.mdImg3}';"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>	
				<th>메인제목3</th>
				<td class="left">
					<input type="text" id="mdText3" name="mdText3" value="${adImgInfo.mdText3}" style="width:80%;"> 
				</td>
			</tr>
			<tr>	
				<th>링크주소3</th>
				<td class="left">
					<input type="text" id="mdUrl3" name="mdUrl3" value="${adImgInfo.mdUrl3}" style="width:80%;"> 
				</td>
			</tr>
			
			
			<tr>
				<th>메인이미지4</th>
				<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.mdImg4 eq NULL || adImgInfo.mdImg4 == ''}">
						<input type="file" id="main4Up" name="main4Up" style="width:80%;">
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.mdImg4}" width="666">
						<br />
						<input type="button" value="이미지 삭제"  onClick="location.href='deleteDesignImgProc.do?fieldName=mdImg4&fileName=${adImgInfo.mdImg4}';"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>	
				<th>메인제목4</th>
				<td class="left">
					<input type="text" id="mdText4" name="mdText4" value="${adImgInfo.mdText4}" style="width:80%;"> 
				</td>
			</tr>
			<tr>	
				<th>링크주소4</th>
				<td class="left">
					<input type="text" id="mdUrl4" name="mdUrl4" value="${adImgInfo.mdUrl4}" style="width:80%;"> 
				</td>
			</tr>
			
			
			<tr>
				<th>메인이미지5</th>
				<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.mdImg5 eq NULL || adImgInfo.mdImg5 == ''}">
						<input type="file" id="main5Up" name="main5Up" style="width:80%;">
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.mdImg5}" width="666">
						<br />
						<input type="button" value="이미지 삭제"  onClick="location.href='deleteDesignImgProc.do?fieldName=mdImg5&fileName=${adImgInfo.mdImg5}';"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>	
				<th>메인제목5</th>
				<td class="left">
					<input type="text" id="mdText5" name="mdText5" value="${adImgInfo.mdText5}" style="width:80%;"> 
				</td>
			</tr>
			<tr>	
				<th>링크주소5</th>
				<td class="left">
					<input type="text" id="mdUrl5" name="mdUrl5" value="${adImgInfo.mdUrl5}" style="width:80%;"> 
				</td>
			</tr>
			
			
			<tr>
				<th>메인이미지6</th>
				<td class="left">
				<c:choose>
					<c:when test="${adImgInfo.mdImg6 eq NULL || adImgInfo.mdImg6 == ''}">
						<input type="file" id="main6Up" name="main6Up" style="width:80%;">
					</c:when>
					<c:otherwise>
						<img src="${adImgInfo.mdImg6}" width="666">
						<br />
						<input type="button" value="이미지 삭제"  onClick="location.href='deleteDesignImgProc.do?fieldName=mdImg6&fileName=${adImgInfo.mdImg6}';"   class="Small_Button Gray" />
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>	
				<th>메인제목6</th>
				<td class="left">
					<input type="text" id="mdText6" name="mdText6" value="${adImgInfo.mdText6}" style="width:80%;"> 
				</td>
			</tr>
			<tr>	
				<th>링크주소6</th>
				<td class="left">
					<input type="text" id="mdUrl6" name="mdUrl6" value="${adImgInfo.mdUrl6}" style="width:80%;"> 
				</td>
			</tr>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		</table>
	
		<div class="Btn_area">
			<input type="submit" value="정보변경" 	class="Button Gray"  /> &nbsp; 
			<input type="button" value="취소"     class="Button Gray" onClick="history.back();">
		</div>
	
		</form>

	</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />
</body>
</html>