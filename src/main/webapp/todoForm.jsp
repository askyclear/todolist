<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/todoadd.css"/>
<title>Todo ADD</title>
</head>
<body>
	<div id="wraper">
        <div id="header">
            <h1>할일 등록</h1>
        </div>
        <div id="container">
            <form action="./todoadd" method="post">
                <label class="title">어떤일인가요?<br/>
                    <input type="text" maxlength="24" name="title" placeholder="swift 공부하기(24자까지)"
                    value="${title}"
                    required/>
                </label><br><br>
                <label class="name">누가 할일인가요?<br>
                <input type="text" name="name" placeholder="홍길동" value="${name}" required/></label><br><br>
                <label>우선순위를 선택하세요.</label>
                <br>
                <label class="sequence"><input type="radio" required name="sequence1"value="1">1순위</label>
                <label  class="sequence"><input type="radio" required name="sequence1"value="2">2순위</label>
                <label  class="sequence"><input type="radio" required name="sequence1"value="3">3순위</label>
        
                <br><br><br>
                <input class="back" type="button" onclick="location.href='./main'" value="< 이전">
                <input class="hidden_but" type="submit" value=""/>
                <input class="but" type="submit" value="제출"/>
                <input class="but" type="reset" value="내용지우기">
            </form>
        </div>
    </div>
</body>
</html>