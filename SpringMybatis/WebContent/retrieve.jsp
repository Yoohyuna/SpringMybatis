<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="yoo.*" %>
<%
     //Board data=(Board)request.getAttribute("data");//${data}
     BoardCommand data=(BoardCommand)request.getAttribute("data");
     //웹상에서 출력할때에는 getter method를 이용
     int num=data.getNum();
     String title=data.getTitle();
     String author=data.getAuthor();
     String content=data.getContent();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내용보기 및 수정</title>
</head>
<body>
  <form action="update.do"><!-- readonly="readonly"(읽기전용 표시) -->
  번 호 : <input type="text" name="num" value="<%= num %>" readonly="readonly" /><br>
  제 목 : <input type="text" name="title" value="<%= title %>"><br>
  작성자:<input type="text" name="author" value="<%= author %>"><br>
  내 용 : <textarea name="content" rows="5" cols="30"><%= content %></textarea><p/>
  <input type="submit" value="수정완료" />&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="delete.do?num=<%= num %>">삭제</a>
  &nbsp;&nbsp;<a href="list.do">목록보기</a>
</form>
</body>
</html>