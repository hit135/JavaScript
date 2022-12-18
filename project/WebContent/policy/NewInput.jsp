<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ajax통신</title>
</head>
<body>
	<input type="button" value="데이터 가져오기" onclick="f_getData()">


	<script>
		// ajax 통신(Asynchronous JavaScript And XML)
		// 비동기
		// XML 에서 JSON 형태로 데이터를 주고 받는게 더 보편화 됨
		// 서버에서 데이터를 받아서 현재 페이지에서 바꾸고 싶은 부분만 바꿀 수 있다..!

		// 환율 정보 데이터 가져오기
		// 해외 환율 정보를 API로 제공하는 사이트 https://exchangerate.host/#/

		// let totalData = {};
		// totalData.push(...c_json2["empsInfo"]["emp"]);
		// pageIndex
		function f_getData() {

			var requestURL = 'https://www.youthcenter.go.kr/opi/empList.do?pageIndex=1&display=100&openApiVlak=5063d4888bafb47dceace58f';

			// 비동기 통신 객체
			var request = new XMLHttpRequest();

			// 통신 준비(통신 방식)
			request.open('GET', requestURL);

			// 요청 타입
			request.responseType = 'json';

			// 통신 시작
			request.send();

			// 통신 시작후 통신이 끝난 시점
			request.onload = function() {
				var response = request.response;
				console.log(response); // json 객체 형태로 데이터가 넘어옴

			}

			// console.log("끝!!");가 var response = request.response;console.log(response); 보다 먼저 실행된다 >> 이것이 비동기
			console.log("끝!!");
		}
	</script>


</body>
</html>