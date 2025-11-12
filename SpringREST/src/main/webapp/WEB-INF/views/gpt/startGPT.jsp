<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/gpt/startGPT.jsp</h1>
	<fieldset>
		<legend>ITWILL GPT 서비스</legend>
		<form action="/sendAsk" method="post">
			<textarea rows="5" cols="20" placeholder="궁금한 사항을 질문하세요!" name="prompt"></textarea><br>
			<input type="submit" value="질문하기">
		</form>
	</fieldset>
	
</body>
</html>