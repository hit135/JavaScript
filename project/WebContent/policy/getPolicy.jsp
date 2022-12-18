<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/x2js/1.2.0/xml2json.min.js" integrity="sha512-HX+/SvM7094YZEKOCtG9EyjRYvK8dKlFhdYAnVCGNxMkA59BZNSZTZrqdDlLXp0O6/NjDb1uKnmutUeuzHb3iQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
    <div>
        <input type="button" value="데이터 가져오기" onclick="f_getTotalData()">
    </div>
    <div>
		<button onclick='fn_submit()'>데이터 넘기기</button>
	</div>
    <form action="${pageContext.request.contextPath}/policy/inputPolicy.do " name="dataForm" id="dataForm" method="post">
		<input type="hidden" name="arrayPolicy" value="">
	</form>

    <script>
        // 하나의 json 객체에 모두 담기
        let totalData = [];

        // 반복문으로 데이터 다 가져오기
        function f_getTotalData(){
            for(let i = 1; i <= 2; i++){
                f_getData(i)
            }
            console.log("totalData : " , totalData);

        }

        // 데이터 전송
        function fn_submit() {
        	console.log("JSON.stringify(totalData) : " , JSON.stringify(totalData));
			$("input[name='arrayPolicy']").val(JSON.stringify(totalData));
			$("#dataForm").submit();
		}


        // ajax 통신(Asynchronous JavaScript And XML)
        // 비동기
        // XML 에서 JSON 형태로 데이터를 주고 받는게 더 보편화 됨
        // 서버에서 데이터를 받아서 현재 페이지에서 바꾸고 싶은 부분만 바꿀 수 있다..!

        // 환율 정보 데이터 가져오기
        // 해외 환율 정보를 API로 제공하는 사이트 https://exchangerate.host/#/

        // let totalData = {};
		// totalData.push(...c_json2["empsInfo"]["emp"]);
        // pageIndex
        function f_getData(indexI){
            $.ajax({
                url: 'https://www.youthcenter.go.kr/opi/empList.do?pageIndex='+indexI+'&display=100&openApiVlak=5063d4888bafb47dceace58f&query=청년취업',
                type : "GET",
                dataType : "Text",
                success : function(data){
                    var x2js = new X2JS();
                    var jsonObj = x2js.xml_str2json( data )["empsInfo"]["emp"];
                    totalData.push(...jsonObj);
                },
                error : function(e){
                    console.log(e);
                },
                async : false
            });
        }
		
	
	</script>


</body>
</html>