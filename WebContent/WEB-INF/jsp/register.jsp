<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" href="/toDoAppli/css/register.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<h1>登録を行う</h1>
<form action="/toDoAppli/RegisterServlet" method="post">
ユーザーID:<input type="text" name="user_id" id="user_id"><br>
ユーザー名:<input type="text" name="user_name" id="user_name"><br>
パスワード:<input type="password" name="password" id="password"><br>
<h2>${errorMsg}</h2>
<input class="submit-btn" type="submit" value="登録">
</form>
<script src="/toDoAppli/js/script.js"></script>
</body>
</html>