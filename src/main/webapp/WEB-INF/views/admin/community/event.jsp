<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import  url="../inc/top.jsp" />

<body>
<div id="Wrap">

	<c:import url="../inc/gnb.jsp"/>
	<c:import url="../inc/lnb.jsp">
		<c:param name="slot" value="community"/>
	</c:import>
	
	<div id="Contents">
	<h1>커뮤니티 &gt; Event &gt; <strong>Event</strong></h1>
	<table class="inquire_tbl" summary="제품문의목록표">
		<colgroup>
			<col width="6%" />
			<col />
			<col width="10%"/>
			<col width="10%"/>
		</colgroup>
		<thead>
			<th>No</th>
			<th>Event Title</th>
			<th>Date</th>
			<th>수정 / 삭제</th>
			
		</thead>
		<tbody>
			<c:forEach items="${eventList}" var="event">
				<tr>
					<td style="text-align:center;">${event.brdSeq}</td>
					<td class="texalign"><a href="javascript:location.href='eventEdit.do?brdSeq=${event.brdSeq}';">${event.title}</a></td>
					<td style="text-align:center;">${event.insDt.substring(0,10)}</td>
					<td style="text-align:center;">
						<input type="button" value="수정" onclick="location.href='eventEdit.do?brdSeq=${event.brdSeq}'" class="Small_Button Gray" />
						<input type="button" value="삭제"  onClick="confirm_process('','해당 이벤트를 삭제하시겠습니까?','eventDelete.do?brdSeq=${event.brdSeq}');" class="Small_Button Gray" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!--<div id="Paser"> 1 | 2 | 3</div>-->
	<div id="Paser">
		${pageHtml}
	</div>
	
</div>

</div>
</body>

<c:import url="../inc/footer.jsp" />

