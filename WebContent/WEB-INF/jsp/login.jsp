<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<h1>ログイン</h1>
<form action="/toDoAppli/LoginServlet" method="post">
ユーザーID:<input type="text" name="user_id">
パスワード:<input type="password" name="password">
<input type="submit" value="ログイン">
<h2>${errorMsg }</h2>
</form>
</body>
</html>