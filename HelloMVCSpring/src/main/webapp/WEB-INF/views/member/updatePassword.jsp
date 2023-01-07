<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.spring.member.model.vo.Member" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<c:set var="path" value="${ pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
    <style>
    div#updatePassword-container{
        background:red;
    }
    div#updatePassword-container table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatePassword-container table tr:last-of-type td {
        text-align:center;
    }
    </style>
    
<div id="updatePassword-container">
		<form name="updatePwdFrm" action="${path }/member/updatePasswordEnd" method="post" 
		onsubmit="return passwordCheck();">
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="password_new" id="password_new" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="password_chk" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="변경" />&nbsp;
						<input type="button" value="닫기" />						
					</td>
				</tr>
			</table>
			<input type="hidden" name="userId" value="${param.userId}"> 
			<!-- 기존 비번/ 새비번 / 아이디 까지 넘어가야하는데 비번 값들만 넘어가서 id 넘겨줄수 있는 input 태그 하나 만들어서 같이 넘겨주기 
			사용자가 이거까지 볼 피룡는 없으니가 hidden처리  -->
			<!-- session값 가져와도 O -->
			
		</form>
</div>

<script>
	const passwordCheck=()=>{
		const password=document.querySelector("[name=password_new]").value;
		const passwordck=document.querySelector("#password_chk").value;
		console.log(password,passwordck);
		if(password.trim().length<8){
			alert("비밀번호는 8글자 이상 작성해야 합니다.");
			return false;
		}
		
		if(password!=passwordck){
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		
	}
</script>

</body>
</html>