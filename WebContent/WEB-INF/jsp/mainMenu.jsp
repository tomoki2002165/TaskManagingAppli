<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO リスト</title>
</head>
<body>
<h1>こんにちは、${ account.userName }さん</h1>
<h2><a href="/toDoAppli/LogoutServlet">ログアウト</a></h2><br>
<h3><a href="/toDoAppli/CreateServlet">作成</a></h3>
<div class="main_wrapper">
<div id="main_todo">
<h3>やることリスト</h3>
<h4>${resultMsg}</h4>
<ul>
<c:forEach var="task" items="${tasks}">
<c:choose>
	<c:when test="${task.cleared != true }">
	<li><button onclick="location.href='/toDoAppli/TaskClearServlet?task_id=${ task.id }&task_name=${task.text}'">□</button><c:out value="${task.text}" /><br></li>
	</c:when>
	<c:otherwise>
	<li><button>☑</button><c:out value="${task.text}" /><br></li>
	</c:otherwise>
</c:choose>
</c:forEach>
</ul>
</div>
<div id="main_cleared">
<h2>${clearMsg}</h2>
<ul>
<c:forEach var="task" items="${cleared_tasks}">
<li>${task.text}が達成されました！</li>
</c:forEach>
</ul>
</div>
</div>
</body>
</html>