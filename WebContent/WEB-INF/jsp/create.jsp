<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODOを作成</title>
</head>
<body>
<h1>作成</h1>
<form action="/toDoAppli/MenuServlet" method="post">
内容:<input type="text" name="text"><br>
<input type="submit" value="作成">
</form>
</body>
</html>