<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<style>
html,
body,
.entire{
	width: 100%;
	height: 100%;
}

.part{
		height: 100%;
}

#eva_map{
	position:relative;
	height: 100%;
}

.home_reg_btn{
	position:absolute;
	z-index: 90;
}

.eva_search{
	position: absolute;
	z-index: 90;
	top: 100px;
}
.marker_list{
	background-color: yellow;
	height: 100%;
}

</style>
<!-- 페이지 첫 시작시 모든 데이터 가져오기 -->
<c:forEach items="${markerList }" var="marker">
	<input type="hidden" class="home_title" value="${marker.markerLocation }">
	<input type="hidden" class="home_lat" value="${marker.markerLat }">
	<input type="hidden" class="home_longt" value="${marker.markerLongt }">
</c:forEach>

<div class="entire">
	<div class="part row">
		<!-- 지도 부분 -->
		<div id="eva_map" class="col-10">
			<div>
				<!-- 내 집 등록 버튼 -->
				<button class="home_reg_btn btn btn-dark" onclick="fn_getUserAdd()">내 집 등록</button>
			</div>
			<!-- 주소 검색창 -->
			<div class="eva_search">
				<input type="text">
			</div>
		</div>
		<!-- 마커 목록 부분 -->
		<div class="col-2 marker_list">
			<div id="home_review" class="row"></div>
			<div></div>
		</div>
	</div>
</div>
<!-- 카카오 맵 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cbe4f64d0e9315cf34ab23484183cd12&libraries=services"></script>
<!-- daum 주소 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	// 화면 로딩 완료 후
	$(document).ready(function() {
		// 이 함수를 ajax보다 늦게 실행되게 해야되는데..
		fn_getAreaData(swLatlng, neLatlng);
	});


	
	// 클래스를 배열로 바꾸는 함수
	function returnClassToArr(arr, attr) {
		let temp_arr = []
		if(attr == "innerHTML"){
			for(let i = 0; i < arr.length; i++){
				temp_arr.push(arr[i].innerHTML);
			}
		}else if(attr == "val"){
			arr.each(function(index,item){
				temp_arr.push($(this).val())
			});
		}
		return temp_arr
	}
	
	// 서버 >> html >> script 클래스 배열로 형변환
	let home_title = $(".home_title");
	let home_title_list = returnClassToArr(home_title, "val");
	let home_lat = $(".home_lat");
	let home_lat_list = returnClassToArr(home_lat, "val");
	let home_longt = $(".home_longt");
	let home_longt_list = returnClassToArr(home_longt, "val");
	
	console.log("markerList : ", home_title_list);
	console.log("markerLat : ", home_lat_list);
	console.log("markerLongt : ", home_longt_list);
	
	// 카카오 맵 생성
	var mapContainer = document.getElementById('eva_map'),
	mapOptions = {
	     center: new kakao.maps.LatLng(36.326725, 127.407470),
	     level: 3
	};
	// 맵 생성하고 div에 뿌리기
	var map = new kakao.maps.Map(mapContainer, mapOptions);
	
	// 전역변수 선언 >> 지도 영역 정보
	let bounds = map.getBounds();
	// 남서쪽의 정보
	let swLatlng = bounds.getSouthWest();
	// 북동쪽의 정보
	let neLatlng = bounds.getNorthEast();
	
	
	// DB에서 불러온 정보로 마커 찍기
	// 마커를 표시할 위치와 title 객체 배열입니다 
	var positions = []
	for(let i = 0; i < home_title_list.length; i++){
		let tempJSON = {}
		tempJSON.title = home_title_list[i];
		tempJSON.latlng = new kakao.maps.LatLng(home_lat_list[i], home_longt_list[i]);
		console.log("tempJSON : ", tempJSON);
		positions.push(tempJSON);
	};
	console.log("positions : ", positions);

	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	    
	for (var i = 0; i < positions.length; i ++) {
	    
	    // 마커 이미지의 이미지 크기 입니다
	    var imageSize = new kakao.maps.Size(24, 35); 
	    
	    // 마커 이미지를 생성합니다    
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
	    
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커를 표시할 위치
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
	        image : markerImage // 마커 이미지 
	    });
	}

	
	// 일단 여기는 나중에.. db에서 값 가져와서 마커 찍는거부터 하자
	// TODO 지도 안에 보이는 마커들만 목록에 출력
	// TODO 마커클러스터러 적용
	// TODO 내 집 평가 등록 할 때 다양한 정보들 (이미지, 월세, 전세, 주소 등 입력 할 수 있도록)
	// 현재 지도 영역의 남서쪽 값과 북동쪽 값 가져오기
	// 지도가 이동, 확대, 축소로 인해 지도영역이 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'bounds_changed', function() {             
	    
	    // 지도 영역정보를 얻어옵니다 
	    bounds = map.getBounds();
	    
	    // 영역정보의 남서쪽 정보를 얻어옵니다 
	    swLatlng = bounds.getSouthWest();
	    
	    // 영역정보의 북동쪽 정보를 얻어옵니다 
	    neLatlng = bounds.getNorthEast();
	    
	    console.log("swLatlng : ", swLatlng);
	    console.log("neLatlng : ", neLatlng);
	    
	    // 여기서 ajax로 위도, 경도에 맞는 마커 정보와 상세 정보 가져오기
	    fn_getAreaData(swLatlng, neLatlng);
	    
	});
	
	// 주소-좌표 변환 객체를 생성합니다(지도에 마커 찍기 위해.. 선언 필요)
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 파라미터로 주소를 입력받고
	// 그 주소에 마커 찍기
	function fn_signMarker(addr, title) {
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(addr, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        console.log("coords.La(경도) : ", coords.La);
		        console.log("coords.Ma(위도) : ", coords.Ma);
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
				
		     	// 마커 값을 가지고 데이터를 가지고 가서 db에 저장해야함
		     	// (위도, 경도, 건물명(타이틀), 주소)
		     	fn_registeMarkerAjax(coords.Ma, coords.La, title, addr);
		     	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">' + title + '</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});
	};
	    
	// 다음 주소 API
	function fn_getUserAdd() {
		// 마커에 간단한 정보를 등록할 창 띄워줌(마커 이미지는 관리자만 건들 수 있게 할까?)
		// 보통 건물명이 아니라 월세, 전세 가격으로 나와있는데 이를 어떻게 할까..
		let title = prompt("건물명을 입력해주세요!");
		
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
					// document.getElementById("sample6_extraAddress").value = extraAddr;
					console.log("extraAddr : ", extraAddr);
				} else {
					// document.getElementById("sample6_extraAddress").value = '';
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				// document.getElementById('sample6_postcode').value = data.zonecode;
				// document.getElementById("sample6_address").value = addr;
				console.log("data.zonecode : ", data.zonecode);
				console.log("addr : ", addr);
				
				// TODO 여기서 회원가입시 주소값과 같은지 판별하고 >> 서버 갔다와야겠지?
				
				// 마커를 지도에 표시 함수, db에 저장
				fn_signMarker(addr, title);
			}
		}).open();
	};
	
	// ajax로 마커 등록하기
	function fn_registeMarkerAjax(La, Ma, title, addr) {
		$.ajax({
			url : "<c:url value='/evaluation/registeMarker'/>"
			,type : "post"
			,data : {"markerLat" : La, "markerLongt" : Ma, 
					  "markerLocation" : title, "markerAdd" : addr,
					   "markerRegId" : 'hgp'}	// MARKER_REG_ID는 나중에서 세션에서 가져올것
			,success : function(data) {
				// 서버에서 오류 발생
				if(data == false){
					alert("서버에서 정상적으로 등록하지 못했습니다. 전산실에 문의해주세요 010-4403-9382")
				}
				alert("마커 등록 완료");
			}
			,error : function(request, status, error) {
		        console.log("code: " + request.status);
		        console.log("message: " + request.responseText);
		        console.log("error: " + error);
		        alert("마커를 정상적으로 등록하지 못했습니다. 전산실에 문의해주세요 010-4403-9382");
			}
		});
	};
	
	// ajax로 영역에 맞는 데이터 가져오기
	function fn_getAreaData(swLatlng, neLatlng) {
		$.ajax({
			url : "<c:url value='/evaluation/areaData'/>"
			,type : "post"
			,data : {"minLat" : swLatlng.Ma, "maxLat" : neLatlng.Ma,
					  "minLongt" : swLatlng.La, "maxLongt" : neLatlng.La}
			,success : function(data) {
				console.log("data : ", data);
				// 데이터 화면에 뿌려주기
				$("#home_review").empty();
				let home_review_text = "";
				for(let i = 0; i < data.length; i++){
					console.log("data[i] : ", data[i]["markerLocation"]);
					home_review_text += "<h3>" + data[i]["markerLocation"] + "</h3>"; 
					home_review_text += "<h3>" + data[i]["markerAdd"] + "</h3>"; 
					// home_review_text += "<h3>" + data[i]["markerRegId"] + "</h3>"; 
				}
				console.log("home_review_text : " , home_review_text);
				$("#home_review").append(home_review_text);
			}
			,error : function(request, status, error) {
		        console.log("code: " + request.status);
		        console.log("message: " + request.responseText);
		        console.log("error: " + error);
		        alert("서버와의 통신 오류가 발생했습니다. 전산실에 문의해주세요 010-4403-9382");
			}
		});
	}
	
	
</script>