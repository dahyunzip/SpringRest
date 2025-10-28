<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//정보 출력 버튼 클릭시, REST 컨트롤러에서 JSON 정보를 가져오기
		$('#btnRead').click(function(){
			$.ajax({
				url : "${contextPath}/rest/read",
				type : "GET",
				success : function(result){
					alert("정보 출력 성공");
					//alert(result); [object]
					console.log(result);
					
					// 기존의 입력창에 가져온 정보를 출력
					$('#inputNumber').val(result.bno);
					$('#inputName').val(result.name);
					$('#inputTitle').val(result.title);
				}
			})
		});
		// 정보입력 버튼이 클릭시, 비동기 방식으로 REST컨트롤러 호출
		$("#btnAdd").click(function(){
			var sample = {
				//"bno" : "999",
				"bno" : $("#inputNumber").val(),
				"name" : $("#inputName").val(),
				"title" : $("#inputTitle").val()
			};
			// 비동기방식 호출(ajax)
			$.ajax({
				url : "/rest/create",
				type : "POST",
				//data : sample,
				// => ?bno=33&name=qwe&title=qwe
				//contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				// => 일반 폼태그 정보를 전달하기위해 사용하는 타입
				// => 서버(컨트롤러)에서는 @RequestParam/@ModelAttribute 사용
				
				// REST 컨트롤러를 사용할때는 이렇게 써야 한다.
				// data를 Json타입으로 보내준다.
				data : JSON.stringify(sample),
				// 객체정보를 JSON 문자열 형태로 전달
				// => { "bno" : 11, "name" : "asdsd", "title" : "zxczxc"}
				
				contentType : "application/json",
				success : function(result){
								   // result는 받아오는 이름에 불과하다. 아무거나 써도 된다.
								   // 보통은 data라고 많이 쓴다.
					//alert(" REST 컨트롤러 다녀옴");
					//alert(result); // rest에서 만든 정보를 가져옴. 
					
					$("body").append("<h2> SampleVO 정보 입력 성공! </h2>");
				}
			}); //ajax
		}); //click
		
	});
</script>
</head>
<body>
	<h1>/views/test2.jsp</h1>
	<h2>REST 컨트롤러를 호출(비동기방식)</h2>
	<h2>정보입력(Create) 동작 구현</h2>
	
	bno 입력 : <input type="number" name="num" id="inputNumber"><br>
	name 입력 : <input type="text" name="name" id="inputName"><br>
	title 입력 : <input type="text" name="title" id="inputTitle">
	
	<!-- test2.jsp <=> RESTController -->
	<input type="button" value="정보입력(Create)" id="btnAdd">

	<hr>
	
	<input type="button" value="정보출력(Read)" id="btnRead">
</body>
</html>