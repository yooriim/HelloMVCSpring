<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMvc 프로젝트</title>

<link rel="stylesheet" type="text/css" href="${path }/resources/css/style.css">
<script src="${path }/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<div id="container">
		<header>
			<h1>HelloMVC Spring</h1>
			<div class="login-container">
			
			<c:if test="${empty loginMember }">
			
			<%-- <%if(loginMember==null){ %> --%> <!-- if문 조건은 session유무 -->
			<!-- 로그인 후 f12-application 가면 session생겨있음. 
			근데 session 삭제했다가 새로고침하면 새로생기는데 그건 그냥 내장객체라서 
			자동으로 생기는 것임. 페이지 설정에 session false 주면 안생김-->
				<form id="loginFrm" action="${path }/member/login.do" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디" 
								value="${cookie.saveId.value}"
								<%-- value="<%=saveId!=null?saveId:""%>" --%>
								 	>
								
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="비밀번호">
							</td>						
							<td>
								<input type="submit" value="로그인">
							</td>						
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId"
								${not empty cookie.saveId?"checked":""} >	<!-- 아이디 저장 누르고 로그인 성공하면 담부터 계속 아이디 저장에 표시되도록 -->
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입"
								onclick="location.replace('${path}/member/enrollMember.do')">
							</td>						
						</tr>
					</table>					
				</form>
			<%-- <%}else{%> --%>
			</c:if>
			<c:if test="${not empty loginMember }">
				<table id="logged-in">
					<tr>
						<td colspan="2">
							<%-- <%=loginMember.getUserName() %> --%>${loginMember.userName } 님, 어서오고?
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="내 정보보기"
							onclick="location.replace('${path }/member/memberView.do?userId=${loginMember.userId }');">
						</td>
						<td>
							<input type="button" value="로그아웃" 
							onclick="location.replace('${path }/member/logout.do');">
							<!-- replace하면 뒤로가기 X 히스토리 남기지않음 -->
						</td>
					</tr>
				</table>
			</c:if>
			<%-- <%} %> --%>
			</div>
			<c:if test="${empty loginMember }">
			<%-- <%if(loginMember==null){ %> --%>
				<nav>
					<ul class="main-nav">
						<li class="home"><a href="">Home</a></li>						
						<li id="notice" onclick="dologin();"><a href="">공지사항</a></li>						
						<li id="board"><a href="${path }/board/viewboard.do">게시판</a></li>
						<li id="gallery"><a href="">갤러리</a></li>
					</ul>
				</nav>
			</c:if>
			<c:if test="${loginMember.userId=='admin' }">
			<%-- <%} else if(loginMember.getUserId().equals("admin")){ %> --%>
				<nav>
					<ul class="main-nav">
						<li class="home"><a href="">Home</a></li>
						<li id="notice"><a href="${path }/notice/noticeList.do">공지사항</a></li>
						<li id="board"><a href="${path }/board/viewboard.do">게시판</a></li>
						<li id="gallery"><a href="">갤러리</a></li>
						<li id="memberManage"><a href="${path }/admin/memberList.do">회원관리</a></li>						
					</ul>
				</nav>
			</c:if>
 			<c:if test="${loginMember.userId!='admin' }&&${not empty loginMember }">
				<nav>
					<ul class="main-nav">
						<li class="home"><a href="">Home</a></li>
						<li id="notice"><a href="${path }/notice/noticeList.do">공지사항</a></li>
						<li id="board"><a href="${path }/board/viewboard.do">게시판</a></li>
						<li id="gallery"><a href="">갤러리</a></li>
					</ul>
				</nav>
			</c:if> 
		</header>
		<script>
			const dologin=()=>{
				alert("로그인하세요.");
				location.replace('${path }')
			}
		</script>