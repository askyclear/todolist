<%@page import="java.util.Iterator"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="netscape.javascript.JSObject"%>
<%@page import="com.hudini.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%! ObjectMapper mapper = new ObjectMapper(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/main.css"/>
<title>TodoList</title>
</head>
<body>
<div id="wraper">
    <div id="header">
        <div id="header_rocate">
            <h1>나의 해야할 일들</h1></div>
        <input type="button" id="todoadd" value="새로운 TODO 등록"
        		onclick="location.href='./todo'"/>
    </div>
    <div id="container">
        <div id="todo" class="list_div">
           <div class="list_header">
                   TODO
           </div>
           <c:forEach var="list" items="${todolist}">
           		<c:if test="${list.type eq 'TODO'}">   		
           			<div class="list" id ='${list.type }${list.id}'>           			
           				<h2>${list.title}</h2>
           				<div>등록날짜:${list.regdate }, ${list.name }, 우선순위${list.sequence }
           				<input name="${list.type }_${list.id}" class="type_change" type="button" value=">"/></div>
           			</div>
           		</c:if>
           </c:forEach>
        </div>
        <div id="doing" class="list_div">
               <div class="list_header">
                   DOING
               </div>
               <c:forEach var="list" items="${todolist}">
           		<c:if test="${list.type eq 'DOING'}">
           		
           			<div class="list" id ='${list.type }${list.id}'>
           				<h2>${list.title}</h2>
           				<div>등록날짜:${list.regdate }, ${list.name }, 우선순위${list.sequence }
           				<input name="${list.type }_${list.id}" class="type_change" type="button" value=">"/></div>
           			</div>
           		</c:if>
           </c:forEach>
        </div>
        <div id="done" class="list_div">
            <div class="list_header" id ='${list.type }${list.id}'>
                   DONE
            </div>
            <c:forEach var="list" items="${todolist}">
           		<c:if test="${list.type eq'DONE'}">
           			<div class="list">
           				<h2>${list.title}</h2>
           				<div>등록날짜:${list.regdate }, ${list.name }, 우선순위${list.sequence }
           				</div>
           			</div>
           		</c:if>
           </c:forEach>  
        </div>
    </div>
    <script src="./js/main_change.js"></script>
    </div>
</body>
</html>