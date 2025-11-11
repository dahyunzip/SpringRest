<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>

<input type="button" value="API호출" id="btnAPI">
<input type="button" value="RESTAPI호출" id="btnREST"><br>
<div id="resultDIV">
</div>
<hr>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("jQuery 실행~");
		//버튼 클릭시 ajax 사용해서 API 서버에 방문하기
		$('#btnAPI').click(function(){
			$.ajax({
				type : "GET",
				url : "https://dog.ceo/api/breeds/image/random",
				success : function(data){
					alert("DOG API 서버 방문!");
					//alert(data);
					console.log(data);
					// 정보를 가져온다음 resultDIV에 정보 출력
					$('#resultDIV').html("<img src="+data.message+">");
				}
			});
		});
		
		// btnREST 클릭시 해당 REST 컨트롤러 호출
		$('#btnREST').click(function(){
			$.ajax({
				type : "GET",
				url : "/api/call",
				success: function(data){
					alert("RESTAPI 다녀옴");
					alert(data);
					console.log(data);
					// 정보 가져온 다음에 resultDIV에 정보(이미지) 출력
					$('#resultDIV').html("<img src="+data.message+">");
				}
			});
		}); // btnREST
	});
</script>
</body>
</html>
