<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODOアプリメイン画面</title>
<link rel="stylesheet" href="/toDoAppli/css/mainStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<header>
    <h1 class="logo header-content"><a href="#">ToDo</a></h1>
    <nav class="header-contents">
        <ul>
            <li class="header-content"><a href="/toDoAppli/RegisterServlet">新規登録</a></li>
            <li class="header-content"><a href="/toDoAppli/LoginServlet">ログイン</a></li>            
        </ul>
    </nav>
</header>
<main>
<img src="img/study_bg.jpg" id="bg">
<div class="main">
    <div>
        <h1 class="main-title">MANAGE YOUR TASKS,<br>THROUGH OUR EYES.</h1>
        <h3 class="main-title_disc">自分のTODOをサイト上に登録し、管理できるアプリです。<br>TODOが完了するとサイト上の掲示板に投稿されます。</h3>
        <a href="/toDoAppli/RegisterServlet" class="main-btn main-register_btn">新規登録</a>
        <a href="/toDoAppli/LoginServlet" class="main-btn main-login_btn">またはログイン</a>
    </div>
</div>
</main>
<script src="/toDoAppli/js/script.js"></script>
</body>
</html>